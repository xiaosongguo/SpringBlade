//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springblade.system.service.IContactService;
import com.springblade.system.wrapper.ContactWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Contact;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.ContactVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/contact"})
@Api(
    value = "",
    tags = {"接口"}
)
public class ContactController extends BladeController {
    private IContactService contactService;
    private IDictClient dictClient;

    @GetMapping({"/detail"})
    @ApiOperation(
        value = "详情",
        notes = "传入contact",
        position = 1
    )
    public R<ContactVO> detail(Contact contact) {
        Contact detail = (Contact)this.contactService.getOne(Condition.getQueryWrapper(contact));
        ContactWrapper contactWrapper = new ContactWrapper(this.dictClient);
        return R.data(contactWrapper.entityVO(detail));
    }

    @GetMapping({"/list"})
    @ApiOperation(
        value = "分页",
        notes = "传入contact",
        position = 2
    )
    public R<IPage<ContactVO>> list(Contact contact, Query query) {
        IPage<Contact> pages = this.contactService.page(Condition.getPage(query), Condition.getQueryWrapper(contact));
        ContactWrapper contactWrapper = new ContactWrapper(this.dictClient);
        return R.data(contactWrapper.pageVO(pages));
    }

    @GetMapping({"/page"})
    @ApiOperation(
        value = "分页",
        notes = "传入contact",
        position = 3
    )
    public R<IPage<ContactVO>> page(ContactVO contact, Query query) {
        IPage<ContactVO> pages = this.contactService.selectContactPage(Condition.getPage(query), contact);
        return R.data(pages);
    }

    @PostMapping({"/save"})
    @ApiOperation(
        value = "新增",
        notes = "传入contact",
        position = 4
    )
    public R save(@Valid @RequestBody Contact contact) {
        return R.status(this.contactService.save(contact));
    }

    @PostMapping({"/update"})
    @ApiOperation(
        value = "修改",
        notes = "传入contact",
        position = 5
    )
    public R update(@Valid @RequestBody Contact contact) {
        return R.status(this.contactService.updateById(contact));
    }

    @PostMapping({"/submit"})
    @ApiOperation(
        value = "新增或修改",
        notes = "传入contact",
        position = 6
    )
    public R submit(@Valid @RequestBody Contact contact) {
        return R.status(this.contactService.saveOrUpdate(contact));
    }

    @PostMapping({"/remove"})
    @ApiOperation(
        value = "删除",
        notes = "传入ids",
        position = 7
    )
    public R remove(@ApiParam(value = "主键集合",required = true) @RequestParam String ids) {
        return R.status(this.contactService.compositeRemoveByIds(Func.toIntList(ids)));
    }

    public ContactController(final IContactService contactService, final IDictClient dictClient) {
        this.contactService = contactService;
        this.dictClient = dictClient;
    }
}
