package org.springblade.system.strategy.route.name;

import org.springblade.system.vo.ChannelResourceVO;

public class OperatorTypeRouteNameStrategy extends DecoratorRouteNameStrategy {
    public OperatorTypeRouteNameStrategy(RouteNameStrategy routeNameStrategy) {
        super(routeNameStrategy);
    }

    protected String append(ChannelResourceVO resource) {
        return resource.getSupplierType().getDescp();
    }
}
