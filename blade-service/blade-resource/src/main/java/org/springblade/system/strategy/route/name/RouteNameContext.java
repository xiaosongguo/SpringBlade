package org.springblade.system.strategy.route.name;

import org.springblade.system.enums.BusiTypeEnum;
import org.springblade.system.enums.OperatorTypeEnum;
import org.springblade.system.enums.ResourceTypeEnum;
import org.springblade.system.vo.ChannelResourceVO;

public class RouteNameContext {
    public RouteNameContext() {
    }

    public void main(String[] args) {
        ChannelResourceVO resourceVO = new ChannelResourceVO();
        resourceVO.setSupplierType(OperatorTypeEnum.YD);
        resourceVO.setProvinceName("福建");
        resourceVO.setResourceType(ResourceTypeEnum.app);
        resourceVO.setBusinessType(BusiTypeEnum.SMS);
        RouteNameStrategy routeNameStrategy = null;
        routeNameStrategy = new BusiTypeRouteNameStrategy(routeNameStrategy);
        routeNameStrategy = new AreaRouteIsmgStrategy(routeNameStrategy);
        routeNameStrategy = new OperatorTypeRouteNameStrategy(routeNameStrategy);
        System.out.println(routeNameStrategy.getRouteName(resourceVO));
    }
}
