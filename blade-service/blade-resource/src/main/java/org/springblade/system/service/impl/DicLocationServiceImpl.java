package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.DicLocation;
import org.springblade.system.mapper.DicLocationMapper;
import org.springblade.system.service.IDicLocationService;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@Service
@DS(SMSMAN)
public class DicLocationServiceImpl extends ServiceImpl<DicLocationMapper, DicLocation> implements IDicLocationService {
    public DicLocationServiceImpl() {
    }
}
