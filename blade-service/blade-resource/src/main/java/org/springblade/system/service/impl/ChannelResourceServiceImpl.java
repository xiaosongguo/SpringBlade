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

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.ChannelRegular;
import org.springblade.system.entity.ChannelResource;
import org.springblade.system.entity.Ismg;
import org.springblade.system.entity.RouteIsmg;
import org.springblade.system.entity.ServiceUnit;
import org.springblade.system.entity.SignIsmg;
import org.springblade.system.enums.OperatorTypeEnum;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.http.CommandRmiClient;
import org.springblade.system.mapper.ChannelResourceMapper;
import org.springblade.system.service.IChannelRegularService;
import org.springblade.system.service.IChannelResourceService;
import org.springblade.system.service.IIsmgService;
import org.springblade.system.service.IRouteIsmgService;
import org.springblade.system.service.IServiceUnitService;
import org.springblade.system.service.ISignIsmgService;
import org.springblade.system.strategy.IsmgConfig;
import org.springblade.system.strategy.IsmgNameContext;
import org.springblade.system.vo.ChannelResourceVO;
import org.springblade.system.vo.IsmgVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通道资源表 服务实现类
 *
 * @author Blade
 * @since 2019-05-23
 */
@Service
@Slf4j
@AllArgsConstructor
public class ChannelResourceServiceImpl extends ServiceImpl<ChannelResourceMapper, ChannelResource> implements IChannelResourceService {

	private IsmgNameContext ismgNameContext;

	private IIsmgService ismgService;

	private ISignIsmgService signIsmgService;

	private IChannelRegularService channelRegularService;

	private CommandRmiClient commandRmiClient;

	private RedisTemplate redisTemplate;

	private IRouteIsmgService routeIsmgService;

	private IDictClient dictClient;

	private IServiceUnitService serviceUnitService;


	@Override
	public IPage<ChannelResourceVO> selectChannelResourcePage(IPage<ChannelResourceVO> page, ChannelResourceVO channelResource) {
		return page.setRecords(baseMapper.selectChannelResourcePage(page, channelResource));
	}

	@Override
	public List<ChannelResource> selectAvailableChannels() {
		return list(Wrappers.<ChannelResource>lambdaQuery().eq(ChannelResource::getStatus,1));
	}

	@Override
	public boolean saveOrUpdateCustom(ChannelResource channelResource) {
		if (channelResource.getIsmgId() == null){
			//新增网关
			Ismg ismg = getIsmg(channelResource);
			ismgService.save(ismg);
			channelResource.setIsmgId(ismg.getIsmgId());
			//新增网签名
			List<SignIsmg> signIsmgs = getSignIsmgs(ismg, channelResource);
			signIsmgService.saveBatch(signIsmgs);
//			//新增通道管理
			ChannelRegular channelRegular = getChannelRegular(ismg, channelResource);
			if (channelRegularService.save(channelRegular)){
				//入redis
				signIsmgs.forEach(signIsmg -> redisTemplate.opsForHash().put(signIsmg.getSignName(),signIsmg.getIsmgId(),signIsmg.getSrcId()));
				//通知
				commandRmiClient.connecTion("CM_ISMG","CM_SIGN_ISMG");
			}
		}
		channelResource.setStatus(0);
		return super.saveOrUpdate(channelResource);
	}

	@Override
	public boolean removeCascadeByIds(List<Integer> toIntList) {
		boolean removeCascadeByIds = false;
		Collection<ChannelResource> channelResources = super.listByIds(toIntList);
		List<Integer> ismgIds = channelResources.stream().map(channelResource -> channelResource.getIsmgId()).collect(Collectors.toList());
		List<Integer> routeIds = dictClient.getList("triple_route").getData().stream().map(dict -> Func.toInt(dict.getDictValue())).collect(Collectors.toList());

		//下架网关
		String serviceUnits = serviceUnitService.lambdaQuery().eq(ServiceUnit::getSuId, 5)
			.select(ServiceUnit::getIsmgIds).one().getIsmgIds();
		Collection subtract = CollectionUtils.subtract(Func.toIntList(serviceUnits), ismgIds);
		log.info("serviceUnit:{},ismgIds:{},routeIds:{},subtract:{}", serviceUnits,ismgIds,routeIds,subtract);
		serviceUnitService.update(Wrappers.<ServiceUnit>lambdaUpdate().set(ServiceUnit::getIsmgIds, StringUtils.join(subtract,",")).eq(ServiceUnit::getSuId, 5));
		//删除路由详情
		routeIsmgService.remove(Wrappers.<RouteIsmg>lambdaQuery().in(RouteIsmg::getRouteId,routeIds).in(RouteIsmg::getIsmgId,ismgIds));
		//删除通道路由
		channelRegularService.remove(Wrappers.<ChannelRegular>lambdaQuery().in(ChannelRegular::getIsmgId,ismgIds));
		//删除网关签名
		signIsmgService.removeByIds(ismgIds);
		//删除网关
		ismgService.removeByIds(ismgIds);
		removeCascadeByIds = super.removeByIds(toIntList);
		if (removeCascadeByIds){
			//删除通道资源
			commandRmiClient.connecTion("CM_ISMG","CM_SIGN_ISMG","CM_ROUTE_ISMG","CM_CHANNEL_REGULAR");
		}
		return removeCascadeByIds;
	}

	/**
	 * 根据通道资源信息生成网关信息
	 * @param channelResource
	 * @return
	 */
	private Ismg getIsmg(ChannelResource channelResource) {
			IsmgVO ismg = new IsmgVO();
			String ismgName = ismgNameContext.getIsmgName(channelResource);
			log.error("ismgName:{}",ismgName);
			ismg.setIsmgName(ismgName);
			ismg.setProtocol(channelResource.getProtocolType().getDescp());
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
			if (channelResource.getSupplierType()== OperatorTypeEnum.LT){
				IsmgConfig otherConfig = new IsmgConfig();
				otherConfig.setSgipSpno(channelResource.getAccessNumber());
				otherConfig.setSgipNodeid(channelResource.getSgipNodeId());
				otherConfig.setSgipSpid(channelResource.getEnterpriseNumber());
				otherConfig.setReceiptPort(channelResource.getReceiptPort());
				otherConfig.setServiceType(channelResource.getServiceRemark());
				ismg.setConfig(otherConfig.toString());
			}
			return ismg;
	}

	/**
	 * 生成网关签名表数据
	 * @param channelResource
	 * 
	 * @return
	 */
	private List<SignIsmg> getSignIsmgs(Ismg ismg,ChannelResource channelResource) {
		List<SignIsmg> signIsmgs = new ArrayList<>();

		SignIsmg signIsmg = new SignIsmg();
		signIsmg.setIsmgId(ismg.getIsmgId());
		signIsmg.setSrcId(ismg.getPhone());
		signIsmg.setIsEnabled(1);
		signIsmg.setCreateTime(LocalDateTime.now());

		if (channelResource.getSignType()== 1){
			signIsmg.setSignName("*");
			signIsmgs.add(signIsmg);
		}else{
			Arrays.stream(channelResource.getSignature().split(",|，")).forEach(sign->{
					SignIsmg fixedSignIsmg = BeanUtil.copy(signIsmg, SignIsmg.class);
					fixedSignIsmg.setSignName(sign.trim());
					signIsmgs.add(fixedSignIsmg);
				}
			);
		}
		return signIsmgs;
	}

	private ChannelRegular getChannelRegular(Ismg ismg,ChannelResource channelResource) {
		ChannelRegular channelRegular = new ChannelRegular();

		channelRegular.setChannelName(ismg.getIsmgName());
		channelRegular.setChannelType(channelResource.getBusinessType().getDescp());
		channelRegular.setChannelLocalProvince(channelResource.getProvinceName());
		String channelLocalOperator = channelResource.getSupplierType().getFullName();
		channelRegular.setChannelLocalOperator(channelLocalOperator);
		channelRegular.setChannelSupportOperator(channelLocalOperator);
		channelRegular.setChannelSmsMainNumber(Func.toStr(channelResource.getAccessNumber()));
//		channelRegular.setChannelApplyCompany("缺省");
		channelRegular.setChannelCharacter("三方通道");
		channelRegular.setChannelUsers("中网信");
//		channelRegular.setChannelSpid();
		channelRegular.setChannelStatus("已接入");
//		channelRegular.setChannelDisReason("");
		channelRegular.setTimeToInit(LocalDateTime.now());
		channelRegular.setTimeToAccess(LocalDateTime.now());
//		channelRegular.setTimeToWork("");
//		channelRegular.setTimeToDisable();
		channelRegular.setContactOfAccess("周外平");
//		channelRegular.setContactOfOperator();
//		channelRegular.setContactMobile();
//		channelRegular.setContactEmail();
//		channelRegular.setContactQq();
//		channelRegular.setContactWechat();
		channelRegular.setInterProtocol(channelResource.getProtocolType().getDescp());
		channelRegular.setInterIp(channelResource.getConnectIp());
		channelRegular.setInterPort(Func.toStr(channelResource.getPort()));
		channelRegular.setInterUser(channelResource.getAccount());
		channelRegular.setInterPassword(channelResource.getPassword());
		channelRegular.setBillMethods("成功计费");
//		channelRegular.setBillPrice();
		channelRegular.setBillType("后付费");
		channelRegular.setBillDescription("无");
//		channelRegular.setBillBalance();
//		channelRegular.setBillTime();
//		channelRegular.setComplainNumberPerMi();
//		channelRegular.setComplainNumber();
		channelRegular.setLmsSupport("支持");
//		channelRegular.setLmsHeadSize();
		channelRegular.setLmsSignType("最后一条");
		channelRegular.setReceIsSupport("支持");
		channelRegular.setReceReportSupport("支持");
		channelRegular.setSignType("网关签名");
		channelRegular.setSignReplace("抹去");
		channelRegular.setSignSize("8");
		channelRegular.setSignWhether("报备签名");
//		channelRegular.setCodeSize();
		channelRegular.setCodeChildType("限制性");

		channelRegular.setLimitArriveProv("全国");
//		channelRegular.setLimitShieldLocation();
		channelRegular.setLimitEnableTime("启用");
//		channelRegular.setLimitSendTime();
		channelRegular.setLimitSendSpeed(channelResource.getChannelTps());
//		channelRegular.setLimitLinkNum();
//		channelRegular.setLimitDailyNum();
//		channelRegular.setLimitMonthlyNum();
//		channelRegular.setLimitSendFrequency();
//		channelRegular.setLimitContentLength();
		channelRegular.setLimitSendProfession("是");
		channelRegular.setLimitSendVerify("是");
		channelRegular.setLimitSendMarketing("否");
		channelRegular.setLimitPersonalAudit("否");
		channelRegular.setLimitKeyword("否");
//		channelRegular.setLimitMassNumber();
		channelRegular.setLimitBlack("否");
//		channelRegular.setRemark();
//		channelRegular.setBillCtPrice();
//		channelRegular.setBillCuPrice();
//		channelRegular.setBillCmPrice();
		channelRegular.setIsmgId(ismg.getIsmgId());
//		channelRegular.setInterFilename();
		channelRegular.setChannelOperatorName("福州中网信通信科技有限公司");
//		channelRegular.setOperatorBalance();
		return channelRegular;
	}
}
