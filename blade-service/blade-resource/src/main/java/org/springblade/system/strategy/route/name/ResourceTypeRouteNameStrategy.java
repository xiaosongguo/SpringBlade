package org.springblade.system.strategy.route.name;

import org.springblade.system.vo.ChannelResourceVO;

public class ResourceTypeRouteNameStrategy extends DecoratorRouteNameStrategy {
    public ResourceTypeRouteNameStrategy(RouteNameStrategy routeNameStrategy) {
        super(routeNameStrategy);
    }

    protected String append(ChannelResourceVO resource) {
        return resource.getResourceType().getDescp();
    }
}
