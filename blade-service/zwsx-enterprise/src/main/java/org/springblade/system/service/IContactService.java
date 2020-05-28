//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.Contact;
import org.springblade.system.vo.ContactVO;

import java.util.List;

public interface IContactService extends IService<Contact> {
    IPage<ContactVO> selectContactPage(IPage<ContactVO> page, ContactVO contact);

    boolean compositeRemoveByIds(List<Integer> toIntList);
}
