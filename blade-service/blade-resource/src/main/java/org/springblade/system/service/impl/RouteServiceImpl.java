package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.Route;
import org.springblade.system.mapper.RouteMapper;
import org.springblade.system.service.IRouteService;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@Service
@DS(SMSMAN)
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {
    public RouteServiceImpl() {
    }

    public boolean save(Route entity) {
        return super.save(entity);
    }
}
