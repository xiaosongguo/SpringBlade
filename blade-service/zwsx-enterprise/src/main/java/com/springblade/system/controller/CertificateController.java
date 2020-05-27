//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springblade.system.service.ICertificateService;
import com.springblade.system.wrapper.CertificateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Certificate;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.CertificateVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/certificate"})
@Api(
    value = "",
    tags = {"接口"}
)
public class CertificateController extends BladeController {
    private ICertificateService certificateService;
    private IDictClient dictClient;

    @GetMapping({"/detail"})
    @ApiOperation(
        value = "详情",
        notes = "传入certificate",
        position = 1
    )
    public R<CertificateVO> detail(Certificate certificate) {
        Certificate detail = (Certificate)this.certificateService.getOne(Condition.getQueryWrapper(certificate));
        CertificateWrapper certificateWrapper = new CertificateWrapper(this.dictClient);
        return R.data(certificateWrapper.entityVO(detail));
    }

    @GetMapping({"/list"})
    @ApiOperation(
        value = "分页",
        notes = "传入certificate",
        position = 2
    )
    public R<IPage<CertificateVO>> list(Certificate certificate, Query query) {
        IPage<Certificate> pages = this.certificateService.page(Condition.getPage(query), Condition.getQueryWrapper(certificate));
        CertificateWrapper certificateWrapper = new CertificateWrapper(this.dictClient);
        return R.data(certificateWrapper.pageVO(pages));
    }

    @GetMapping({"/page"})
    @ApiOperation(
        value = "分页",
        notes = "传入certificate",
        position = 3
    )
    public R<IPage<CertificateVO>> page(CertificateVO certificate, Query query) {
        IPage<CertificateVO> pages = this.certificateService.selectCertificatePage(Condition.getPage(query), certificate);
        return R.data(pages);
    }

    @PostMapping({"/save"})
    @ApiOperation(
        value = "新增",
        notes = "传入certificate",
        position = 4
    )
    public R save(@Valid @RequestBody Certificate certificate) {
        return R.status(this.certificateService.save(certificate));
    }

    @PostMapping({"/update"})
    @ApiOperation(
        value = "修改",
        notes = "传入certificate",
        position = 5
    )
    public R update(@Valid @RequestBody Certificate certificate) {
        return R.status(this.certificateService.updateById(certificate));
    }

    @PostMapping({"/submit"})
    @ApiOperation(
        value = "新增或修改",
        notes = "传入certificate",
        position = 6
    )
    public R submit(@Valid @RequestBody Certificate certificate) {
        return R.status(this.certificateService.saveOrUpdate(certificate));
    }

    @PostMapping({"/remove"})
    @ApiOperation(
        value = "删除",
        notes = "传入ids",
        position = 7
    )
    public R remove(@ApiParam(value = "主键集合",required = true) @RequestParam String ids) {
        return R.status(this.certificateService.removeByIds(Func.toIntList(ids)));
    }

    public CertificateController(final ICertificateService certificateService, final IDictClient dictClient) {
        this.certificateService = certificateService;
        this.dictClient = dictClient;
    }
}
