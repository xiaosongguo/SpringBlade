/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.dto.QuoteDTO;
import org.springblade.system.entity.ChannelResource;
import org.springblade.system.entity.Ismg;
import org.springblade.system.entity.Quote;
import org.springblade.system.entity.QuoteDetail;
import org.springblade.system.entity.SignIsmg;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.mapper.ChannelResourceMapper;
import org.springblade.system.mapper.QuoteMapper;
import org.springblade.system.service.IChannelResourceService;
import org.springblade.system.service.IIsmgService;
import org.springblade.system.service.IQuoteDetailService;
import org.springblade.system.service.IQuoteService;
import org.springblade.system.service.ISignIsmgService;
import org.springblade.system.vo.ChannelResourceVO;
import org.springblade.system.vo.IsmgVO;
import org.springblade.system.vo.QuoteVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-06-10
 */
@Service
@AllArgsConstructor
public class QuoteServiceImpl extends ServiceImpl<QuoteMapper, Quote> implements IQuoteService {

	private IQuoteDetailService quoteDetailService;

	private IIsmgService ismgService;

	private ISignIsmgService signIsmgService;

	private ChannelResourceMapper channelResourceMapper;

	private IChannelResourceService channelResourceService;

	private IDictClient dictClient;

	@Override
	public IPage<QuoteVO> selectQuotePage(IPage<QuoteVO> page, QuoteVO quote) {
		return page.setRecords(baseMapper.selectQuotePage(page, quote));
	}

	@Override
	public boolean saveQuote(QuoteDTO quoteVO) {
		quoteVO.setSupplierId(SecureUtil.getUserId());
		boolean isSucc = super.save(quoteVO);
		List<QuoteDetail> quoteDetails = quoteVO.getQuoteDetails();
		if (quoteDetails.isEmpty()){
			return isSucc;
		}
		quoteDetails.stream().forEach(quoteDetail->quoteDetail.setQuiteId(quoteVO.getId()));
		isSucc = quoteDetailService.saveBatch(quoteVO.getQuoteDetails());
		return isSucc;
	}

	@Override
	@Transactional
	public boolean review(QuoteDTO quoteDTO) {
		boolean update = update(Wrappers.<Quote>lambdaUpdate()
			.set(Quote::getStatus,quoteDTO.getStatus())
			.eq(Quote::getId, quoteDTO.getId()));

		if (quoteDTO.getStatus() == 1 ){
			List<ChannelResourceVO> channelResources = channelResourceMapper.selectChannels(quoteDTO.getId());
			List<Ismg> ismgs = getIsmgs(channelResources);
			boolean insertIsmg = ismgService.saveBatch(ismgs);
			boolean updateChannel = updateChannelResources(ismgs, channelResources);
			List<SignIsmg> signIsmgs = getSignIsmgs(ismgs);
			boolean insertSignIsmg = signIsmgService.saveBatch(signIsmgs);
			return update && insertIsmg && updateChannel && insertSignIsmg ;
		}
		return update ;

	}

	private boolean updateChannelResources(List<Ismg> ismgs, List<ChannelResourceVO> channelResources) {
		List<ChannelResource> collect = new ArrayList<>();
		ismgs.stream().forEach(ismg -> {
			IsmgVO ismgVo = (IsmgVO) ismg;
			channelResources.stream().forEach(channel -> {
					if (channel.getId() == ismgVo.getChannelId()) {
						channel.setIsmgId(ismgVo.getIsmgId());
						collect.add(channel);
					}
				}
			);
		});
		return channelResourceService.updateBatchById(collect);
	}

	/**
	 * 根据通道资源信息生成网关表，并记录签名
	 * @param channelResources
	 * @return
	 */
	private List<Ismg> getIsmgs(List<ChannelResourceVO> channelResources) {
		return channelResources.stream().map(channelResource -> {
			IsmgVO ismg = new IsmgVO();
			ismg.setIsmgName("ext_" + channelResource.getId());
			ismg.setProtocol(dictClient.getValue("protocol_type", channelResource.getProtocolType()).getData());
			ismg.setIp(channelResource.getConnectIp());
			ismg.setPort(channelResource.getPort());
			ismg.setLogonId(channelResource.getAccount());
			ismg.setLogonPwd(channelResource.getPassword());
			ismg.setSpid(Func.toStr(channelResource.getEnterpriseNumber()));
			ismg.setPhone(Func.toStr(channelResource.getAccessNumber()));
			ismg.setSpeed(channelResource.getChannelTps());
			ismg.setEnable(1);
			ismg.setBlackLevel("0-5");
			ismg.setMinSendSize(0);
			ismg.setMaxSendSize(1000);
			ismg.setBlackLevel2("0-4");
			ismg.setSignName(channelResource.getSignature());
			ismg.setChannelId(channelResource.getId());
			return ismg;
		}).collect(Collectors.toList());
	}

	/**
	 * 生成网关签名表数据
	 * @param ismgs
	 * @return
	 */
	private List<SignIsmg> getSignIsmgs(List<Ismg> ismgs) {
		List<SignIsmg> signIsmgs = new ArrayList<>();
		ismgs.stream().forEach(ismg->{
			IsmgVO ismgVo = (IsmgVO) ismg;
			Arrays.stream(ismgVo.getSignName().split(",|，")).forEach(sign->{
					SignIsmg signIsmg = new SignIsmg();
					signIsmg.setSignName(sign.trim());
					signIsmg.setIsmgId(ismg.getIsmgId());
					signIsmg.setSrcId(ismg.getPhone());
					signIsmg.setIsEnabled(1);
					signIsmg.setCreateTime(LocalDateTime.now());
					signIsmgs.add(signIsmg);
				}
			);
		});
		return signIsmgs;
	}


	@Override
	@Transactional
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return quoteDetailService.remove(Wrappers.<QuoteDetail>lambdaQuery().in(QuoteDetail::getQuiteId,idList)) && super.removeByIds(idList);
	}
}
