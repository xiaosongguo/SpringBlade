package org.springblade.system.strategy.route;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.entity.*;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.http.CommandRmiClient;
import org.springblade.system.mapper.DicLocationMapper;
import org.springblade.system.service.IRouteRuleService;
import org.springblade.system.service.IRouteService;
import org.springblade.system.vo.ChannelResourceVO;

import java.util.List;
import java.util.Objects;

@Slf4j
public class GenericRouteIsmgStrategy implements RouteIsmgStrategy {
    private IRouteService routeService = (IRouteService) SpringUtil.getBean(IRouteService.class);
    private IRouteRuleService routeRuleService = (IRouteRuleService)SpringUtil.getBean(IRouteRuleService.class);
    private IDictClient dictClient = (IDictClient)SpringUtil.getBean(IDictClient.class);
    private DicLocationMapper dicLocationMapper = (DicLocationMapper)SpringUtil.getBean(DicLocationMapper.class);
    private CommandRmiClient commandRmiClient = (CommandRmiClient)SpringUtil.getBean(CommandRmiClient.class);
    protected List<String> routeNames = Lists.newArrayList();

    public GenericRouteIsmgStrategy() {
    }

    public List<String> getRouteNames() {
        return this.routeNames;
    }

    public List<String> setRouteNames(String routeName) {
        this.routeNames.add(routeName);
        return this.routeNames;
    }

    public void addRouteIsmgs(RouteIsmg routeIsmg, List<RouteIsmg> routeIsmgs, ChannelResourceVO channelResource) {
        String routeUserId = (String)this.dictClient.getValue("routeUserId", 0).getData();
        this.getRouteNames().forEach((routeName) -> {
            routeName = routeName.replaceAll("(-)+", "-");
            Route route = new Route();
            route.setRouteName(routeName);
            Route routeDB = routeService.getOne(Wrappers.<Route>lambdaQuery(route), false);
            if (Objects.isNull(routeDB) && routeService.save(route)) {
                this.commandRmiClient.connecTion(new String[]{"CM_ROUTE"});
                List<RouteRule> routeRules = Lists.newArrayList();
                RouteRule routeRule = new RouteRule();
                routeRule.setUserId(Func.toInt(routeUserId));
                if ("全国".equals(channelResource.getProvinceName())) {
                    routeRule.setPriority(950);
                } else {
                    routeRule.setPriority(1000);
                }

                routeRule.setOperator(routeIsmg.getOperator());
                routeRule.setRouteId(route.getRouteId());
                routeRule.setRouteType(RouteTypeEnum.price);
                String provinceName = channelResource.getProvinceName();
                if ("全国".equals(provinceName)) {
                    routeRule.setLocationId((Integer)null);
                    routeRules.add(BeanUtil.clone(routeRule));
                } else {
                    DicLocation dicLocation = new DicLocation();
                    dicLocation.setProvince(channelResource.getProvinceName());
                    dicLocation.setOperator("[中国|虚拟]" + channelResource.getSupplierType().getOperatorDesc());
                    List<Integer> locationIds = this.dicLocationMapper.getlocationIds(dicLocation);
                    log.info("locationIds:{}", locationIds);
                    locationIds.forEach((locationId) -> {
                        routeRule.setLocationId(locationId);
                        log.info("bizTypeEnum:{}", JsonUtil.toJson(channelResource.getResourceType().getBizType()));
                        routeRules.add(BeanUtil.clone(routeRule));
                    });
                }

                this.routeRuleService.saveBatch(routeRules);
                this.commandRmiClient.connecTion(new String[]{"CM_ROUTE_RULE"});
                log.info("routeRules:{}", JsonUtil.toJson(routeRules));
                routeIsmg.setRouteId(route.getRouteId());
            } else {
                routeIsmg.setRouteId(routeDB.getRouteId());
            }

            routeIsmgs.add(BeanUtil.clone(routeIsmg));
            log.info("routeIsmgs:{}", JsonUtil.toJson(routeIsmgs));
        });
    }
}
