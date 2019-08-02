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
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.dto.QuoteDTO;
import org.springblade.system.entity.ChannelRegular;
import org.springblade.system.entity.Quote;
import org.springblade.system.entity.QuoteDetail;
import org.springblade.system.entity.RouteIsmg;
import org.springblade.system.enums.OperatorTypeEnum;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.http.CommandRmiClient;
import org.springblade.system.mapper.ChannelResourceMapper;
import org.springblade.system.mapper.QuoteMapper;
import org.springblade.system.service.IChannelRegularService;
import org.springblade.system.service.IQuoteDetailService;
import org.springblade.system.service.IQuoteService;
import org.springblade.system.service.IRouteIsmgService;
import org.springblade.system.vo.ChannelResourceVO;
import org.springblade.system.vo.QuoteVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-06-10
 */
@Service
@AllArgsConstructor
@Slf4j
public class QuoteServiceImpl extends ServiceImpl<QuoteMapper, Quote> implements IQuoteService {

	private IQuoteDetailService quoteDetailService;

	private IRouteIsmgService routeIsmgService;

	private ChannelResourceMapper channelResourceMapper;

	private IDictClient dictClient;

	private  CommandRmiClient commandRmiClient;

	private IChannelRegularService channelRegularService;


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
		quoteDetails.forEach(quoteDetail->quoteDetail.setQuiteId(quoteVO.getId()));
		isSucc = quoteDetailService.saveBatch(quoteVO.getQuoteDetails());
		return isSucc;
	}

	@Override
	public boolean review(QuoteDTO quoteDTO) {
		boolean update = update(Wrappers.<Quote>lambdaUpdate()
			.set(Quote::getStatus,quoteDTO.getStatus())
			.eq(Quote::getId, quoteDTO.getId()));
		if (quoteDTO.getStatus() == 1 ){
			//选出报价的通道
			List<ChannelResourceVO> channelResources = channelResourceMapper.selectChannels(quoteDTO.getId());

			//更新通道管理报价
			List<ChannelRegular> channelRegulars = new ArrayList<>(channelResources.size());
			channelResources.forEach(channelResource ->{
				ChannelRegular channelRegular = new ChannelRegular();
				channelRegular.setIsmgId(channelResource.getIsmgId());
				Double unitPrice = channelResource.getUnitPrice();
				OperatorTypeEnum operatorType = channelResource.getSupplierType();
				if (operatorType == OperatorTypeEnum.YDLTDX){
					channelRegular.setBillPrice(unitPrice);
				}else if (operatorType == OperatorTypeEnum.YD){
					channelRegular.setBillCmPrice(unitPrice);
				}else if(operatorType == OperatorTypeEnum.LT){
					channelRegular.setBillCuPrice(unitPrice);
				}else if(operatorType == OperatorTypeEnum.DX){
					channelRegular.setBillCtPrice(unitPrice);
				}
				channelRegulars.add(channelRegular);
			});
			channelRegularService.updateBatchByIsmgId(channelRegulars);
			//插路由详情
			List<RouteIsmg> routeIsmgs = getSignIsmgs(channelResources);
			if (routeIsmgService.saveBatch(routeIsmgs)){
				commandRmiClient.connecTion(new String[]{"CM_ROUTE_ISMG"});
			}

		}
		return update ;

	}


	private List<RouteIsmg> getSignIsmgs(List<ChannelResourceVO> channelResources) {

		List<RouteIsmg> routeIsmgs = new ArrayList<>();
		channelResources.forEach(channelResource -> {
			RouteIsmg routeIsmg = new RouteIsmg();
			routeIsmg.setIsmgId(channelResource.getIsmgId());
			routeIsmg.setPriority(300);
			routeIsmg.setAutoChange(0);
//			routeIsmg.setRatio();
			routeIsmg.setReceiptResend(0);

			if (channelResource.getSupplierType() == OperatorTypeEnum.YDLTDX){
				dictClient.getList("triple_route").getData().forEach(dic->{
						RouteIsmg copy = BeanUtil.copy(routeIsmg, RouteIsmg.class);
						copy.setOperator(dic.getDictKey());
						copy.setRouteId(Func.toInt(dic.getDictValue()));
						routeIsmgs.add(copy);
					}
				);
			}else{
				int operator = channelResource.getSupplierType().getOperator();
				int routeId = Func.toInt(dictClient.getValue("triple_route", operator).getData());
				routeIsmg.setRouteId(routeId);
				routeIsmg.setOperator(operator);
				routeIsmgs.add(routeIsmg);
			}


		});
		return routeIsmgs;
	}


	@Override
	@Transactional
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return quoteDetailService.remove(Wrappers.<QuoteDetail>lambdaQuery().in(QuoteDetail::getQuiteId,idList)) && super.removeByIds(idList);
	}
}
