package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.IsmgAbility;
import org.springblade.system.mapper.IsmgAbilityMapper;
import org.springblade.system.service.IIsmgAbilityService;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@Service
@DS(SMSMAN)
public class IsmgAbilityServiceImpl extends ServiceImpl<IsmgAbilityMapper, IsmgAbility> implements IIsmgAbilityService {
    public IsmgAbilityServiceImpl() {
    }
}
