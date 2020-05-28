//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.service.IBankService;
import org.springblade.system.wrapper.BankWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Bank;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.BankVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/bank"})
@Api(
    value = "",
    tags = {"接口"}
)
@AllArgsConstructor
public class BankController extends BladeController {
    private IBankService bankService;
    private IDictClient dictClient;

    @GetMapping({"/detail"})
    @ApiOperation(
        value = "详情",
        notes = "传入bank",
        position = 1
    )
    public R<BankVO> detail(Bank bank) {
        Bank detail = (Bank)this.bankService.getOne(Condition.getQueryWrapper(bank));
        BankWrapper bankWrapper = new BankWrapper(this.dictClient);
        return R.data(bankWrapper.entityVO(detail));
    }

    @GetMapping({"/list"})
    @ApiOperation(
        value = "分页",
        notes = "传入bank",
        position = 2
    )
    public R<IPage<BankVO>> list(Bank bank, Query query) {
        IPage<Bank> pages = this.bankService.page(Condition.getPage(query), Condition.getQueryWrapper(bank));
        BankWrapper bankWrapper = new BankWrapper(this.dictClient);
        return R.data(bankWrapper.pageVO(pages));
    }

    @PostMapping({"/save"})
    @ApiOperation(
        value = "新增",
        notes = "传入bank",
        position = 4
    )
    public R save(@Valid @RequestBody Bank bank) {
        return R.status(this.bankService.save(bank));
    }

    @PostMapping({"/update"})
    @ApiOperation(
        value = "修改",
        notes = "传入bank",
        position = 5
    )
    public R update(@Valid @RequestBody Bank bank) {
        return R.status(this.bankService.updateById(bank));
    }

    @PostMapping({"/submit"})
    @ApiOperation(
        value = "新增或修改",
        notes = "传入bank",
        position = 6
    )
    public R submit(@Valid @RequestBody Bank bank) {
        return R.status(this.bankService.saveOrUpdate(bank));
    }

    @PostMapping({"/remove"})
    @ApiOperation(
        value = "删除",
        notes = "传入ids",
        position = 7
    )
    public R remove(@ApiParam(value = "主键集合",required = true) @RequestParam String ids) {
        return R.status(this.bankService.compositeRemoveByIds(Func.toIntList(ids)));
    }

}
