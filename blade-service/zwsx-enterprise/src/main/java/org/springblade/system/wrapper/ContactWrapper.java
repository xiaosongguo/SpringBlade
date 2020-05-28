package org.springblade.system.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Contact;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.ContactVO;

@AllArgsConstructor
public class ContactWrapper extends BaseEntityWrapper<Contact, ContactVO> {
    private IDictClient dictClient;

    public ContactVO entityVO(Contact contact) {
        ContactVO contactVO = (ContactVO)BeanUtil.copy(contact, ContactVO.class);
        return contactVO;
    }

}
