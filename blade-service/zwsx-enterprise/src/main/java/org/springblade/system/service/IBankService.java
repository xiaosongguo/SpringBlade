//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.Bank;
import org.springblade.system.vo.BankVO;

import java.util.List;

public interface IBankService extends IService<Bank> {
    List<BankVO> selectBank(Bank bank);

    boolean compositeRemoveByIds(List<Integer> toIntList);
}
