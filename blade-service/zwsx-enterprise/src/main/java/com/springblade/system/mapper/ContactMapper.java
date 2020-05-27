//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.Contact;
import org.springblade.system.vo.ContactVO;

import java.util.List;

public interface ContactMapper extends BaseMapper<Contact> {
    List<ContactVO> selectContactPage(IPage page, ContactVO contact);
}
