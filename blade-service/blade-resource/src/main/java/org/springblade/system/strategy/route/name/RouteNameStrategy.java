package org.springblade.system.strategy.route.name;

import org.springblade.system.vo.ChannelResourceVO;

public interface RouteNameStrategy {
    String getRouteName(ChannelResourceVO resource);
}
