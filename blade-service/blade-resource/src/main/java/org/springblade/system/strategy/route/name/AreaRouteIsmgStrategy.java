package org.springblade.system.strategy.route.name;

import org.springblade.system.vo.ChannelResourceVO;

public class AreaRouteIsmgStrategy extends DecoratorRouteNameStrategy {
    public AreaRouteIsmgStrategy(RouteNameStrategy routeNameStrategy) {
        super(routeNameStrategy);
    }

    protected String append(ChannelResourceVO resource) {
        String provinceName = resource.getProvinceName();
        return "全国".equals(provinceName) ? "" : resource.getProvinceName();
    }
}
