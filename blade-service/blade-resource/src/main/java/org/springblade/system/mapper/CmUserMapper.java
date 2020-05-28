package org.springblade.system.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.system.entity.CmUser;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@DS(SMSMAN)
public interface CmUserMapper extends BaseMapper<CmUser> {
}
