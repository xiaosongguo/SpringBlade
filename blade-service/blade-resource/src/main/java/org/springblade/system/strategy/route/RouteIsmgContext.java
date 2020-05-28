package org.springblade.system.strategy.route;

import org.springblade.system.entity.RouteIsmg;
import org.springblade.system.strategy.route.name.AreaRouteIsmgStrategy;
import org.springblade.system.strategy.route.name.BusiTypeRouteNameStrategy;
import org.springblade.system.strategy.route.name.OperatorTypeRouteNameStrategy;
import org.springblade.system.strategy.route.name.RouteNameStrategy;
import org.springblade.system.vo.ChannelResourceVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteIsmgContext {
    public RouteIsmgContext() {
    }

    public List<RouteIsmg> getRouteIsmgs(List<ChannelResourceVO> channelResources) {
        List<RouteIsmg> routeIsmgs = new ArrayList();
        channelResources.stream().forEach((channelResource) -> {
            RouteIsmg routeIsmg = new RouteIsmg();
            routeIsmg.setIsmgId(channelResource.getIsmgId());
            routeIsmg.setPriority(300);
            routeIsmg.setAutoChange(0);
            routeIsmg.setReceiptResend(0);
            String routeName = this.getRouteNameStrategy(channelResource);
            channelResource.getSupplierType().getOperatorType().forEach((operatorTypeEnum) -> {
                routeIsmg.setOperator(operatorTypeEnum.getOperator());
                this.addRouteIsmgs(routeIsmgs, channelResource, routeIsmg, routeName.replaceAll("三网", operatorTypeEnum.getDescp()));
            });
        });
        return routeIsmgs;
    }

    private void addRouteIsmgs(List<RouteIsmg> routeIsmgs, ChannelResourceVO channelResource, RouteIsmg routeIsmg, String routeName) {
        GenericRouteIsmgStrategy routeIsmgStrategy = new GenericRouteIsmgStrategy();
        routeIsmgStrategy.setRouteNames(routeName);
        routeIsmgStrategy.addRouteIsmgs(routeIsmg, routeIsmgs, channelResource);
    }

    private String getRouteNameStrategy(ChannelResourceVO channelResource) {
        RouteNameStrategy routeNameStrategy = new BusiTypeRouteNameStrategy((RouteNameStrategy)null);
         routeNameStrategy = new AreaRouteIsmgStrategy(routeNameStrategy);
         routeNameStrategy = new OperatorTypeRouteNameStrategy(routeNameStrategy);
        return routeNameStrategy.getRouteName(channelResource);
    }
}
