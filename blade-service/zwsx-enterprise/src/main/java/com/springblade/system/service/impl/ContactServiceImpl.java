//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springblade.system.mapper.ContactMapper;
import com.springblade.system.service.IContactService;
import com.springblade.system.service.IFileManagerService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.Contact;
import org.springblade.system.entity.FileManager;
import org.springblade.system.vo.ContactVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements IContactService {
    private IFileManagerService fileManagerService;

    public IPage<ContactVO> selectContactPage(IPage<ContactVO> page, ContactVO contact) {
        return page.setRecords(((ContactMapper)this.baseMapper).selectContactPage(page, contact));
    }

    public boolean compositeRemoveByIds(List<Integer> toIntList) {
        boolean remove = this.fileManagerService.remove(Wrappers.<FileManager>lambdaQuery().in(FileManager::getOwnerId, toIntList));
        return remove && this.removeByIds(toIntList);
    }

}
