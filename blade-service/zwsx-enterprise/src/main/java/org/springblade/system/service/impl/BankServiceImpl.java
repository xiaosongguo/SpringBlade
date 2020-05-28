//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.mapper.BankMapper;
import org.springblade.system.service.IBankService;
import org.springblade.system.service.IFileManagerService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.Bank;
import org.springblade.system.entity.FileManager;
import org.springblade.system.vo.BankVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements IBankService {

    private IFileManagerService fileManagerService;

    public List<BankVO> selectBank(Bank bank) {
        return ((BankMapper)this.baseMapper).selectBank(bank);
    }

    public boolean compositeRemoveByIds(List<Integer> toIntList) {
        boolean remove = this.fileManagerService.remove(Wrappers.<FileManager>lambdaQuery().in(FileManager::getOwnerId, toIntList));
        return remove && this.removeByIds(toIntList);
    }
}
