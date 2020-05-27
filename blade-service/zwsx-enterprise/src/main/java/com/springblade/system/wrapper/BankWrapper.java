package com.springblade.system.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Bank;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.BankVO;

@AllArgsConstructor
public class BankWrapper extends BaseEntityWrapper<Bank, BankVO> {
    private IDictClient dictClient;

    public BankVO entityVO(Bank bank) {
        BankVO bankVO = (BankVO) BeanUtil.copy(bank, BankVO.class);
        return bankVO;
    }

}
