package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.RouteRule;
import org.springblade.system.mapper.RouteRuleMapper;
import org.springblade.system.service.IRouteRuleService;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@Service
@DS(SMSMAN)
public class RouteRuleServiceImpl extends ServiceImpl<RouteRuleMapper, RouteRule> implements IRouteRuleService {
}
