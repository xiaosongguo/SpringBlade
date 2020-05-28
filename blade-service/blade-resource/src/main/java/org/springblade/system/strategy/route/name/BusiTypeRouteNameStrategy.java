package org.springblade.system.strategy.route.name;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.entity.CmUser;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.mapper.CmUserMapper;
import org.springblade.system.vo.ChannelResourceVO;

public class BusiTypeRouteNameStrategy extends DecoratorRouteNameStrategy {
    private CmUserMapper cmUserMapper = (CmUserMapper) SpringUtil.getBean(CmUserMapper.class);

    public BusiTypeRouteNameStrategy(RouteNameStrategy routeNameStrategy) {
        super(routeNameStrategy);
    }

    protected String append(ChannelResourceVO resource) {
        String routeUserId = (String)((IDictClient)SpringUtil.getBean(IDictClient.class)).getValue("routeUserId", 0).getData();
        CmUser cmUser = (CmUser)this.cmUserMapper.selectOne((Wrappers.<CmUser>lambdaQuery().select(CmUser::getLoginName).eq(CmUser::getUserId, Func.toInt(routeUserId))));
        return cmUser.getLoginName() + resource.getBusinessType().getName() + "-三方通道";
    }
}
