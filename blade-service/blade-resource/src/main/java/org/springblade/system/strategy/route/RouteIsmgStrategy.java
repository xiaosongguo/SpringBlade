package org.springblade.system.strategy.route;

import org.springblade.system.entity.RouteIsmg;
import org.springblade.system.vo.ChannelResourceVO;

import java.util.List;

public interface RouteIsmgStrategy {
    void addRouteIsmgs(RouteIsmg routeIsmg, List<RouteIsmg> routeIsmgs, ChannelResourceVO channelResource);
}
