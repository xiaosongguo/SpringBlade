package org.springblade.system.strategy.route.name;

import org.springblade.system.vo.ChannelResourceVO;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class DecoratorRouteNameStrategy implements RouteNameStrategy {
    protected RouteNameStrategy routeNameStrategy;
    protected final StringJoiner joiner = new StringJoiner("-");

    public DecoratorRouteNameStrategy(RouteNameStrategy routeNameStrategy) {
        this.routeNameStrategy = routeNameStrategy;
    }

    public String getRouteName(ChannelResourceVO resource) {
        if (Objects.nonNull(this.routeNameStrategy)) {
            this.joiner.add(this.routeNameStrategy.getRouteName(resource));
        }

        this.joiner.add(this.append(resource));
        return this.joiner.toString();
    }

    protected abstract String append(ChannelResourceVO resource);
}
