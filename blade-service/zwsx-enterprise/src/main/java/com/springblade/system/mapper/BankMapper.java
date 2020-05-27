//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.Bank;
import org.springblade.system.vo.BankVO;

import java.util.List;

public interface BankMapper extends BaseMapper<Bank> {
    List<BankVO> selectBank(@Param("bank") Bank bank);
}
