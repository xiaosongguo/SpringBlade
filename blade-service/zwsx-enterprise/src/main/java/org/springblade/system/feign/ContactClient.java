//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.system.entity.Contact;
import org.springblade.system.service.IContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactClient implements IContactClient {
    private IContactService service;

    @GetMapping({"/contact/byID"})
    public List<Contact> list(Integer userId) {
        return this.service.list(Wrappers.<Contact>lambdaQuery().eq(Contact::getUserId, userId));
    }



	@GetMapping({"/contact/one"})
	public Contact one(Integer userId) {
		Contact c= (Contact)this.service.getOne(
			Wrappers.<Contact>lambdaQuery().eq(Contact::getUserId,userId),false);
		if(c==null){
			c=new Contact();
		}
		return c;
	}

    public ContactClient(final IContactService service) {
        this.service = service;
    }
}
