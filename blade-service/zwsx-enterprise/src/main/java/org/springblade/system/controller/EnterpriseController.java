//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.controller;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import org.springblade.system.service.IBankService;
import org.springblade.system.service.IContactService;
import org.springblade.system.service.IEnterpriseService;
import org.springblade.system.service.IUserCertificateService;
import org.springblade.system.wrapper.EnterpriseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Enterprise;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.user.feign.IUserClient;
import org.springblade.system.vo.EnterpriseVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping({"/enterprise"})
@Api(
    value = "",
    tags = {"企业信息"}
)
@AllArgsConstructor
public class EnterpriseController extends BladeController {
    private IEnterpriseService enterpriseService;
    private IContactService contactService;
    private IUserCertificateService userCertificateService;
    private IBankService bankService;
    private IDictClient dictClient;
    private IUserClient userClient;
    private BladeLogger logger;

    @GetMapping({"/detail"})
    @ApiOperation(
        value = "详情",
        notes = "用户Id",
        position = 1
    )
    public R<EnterpriseVO> detail(@RequestParam(required = false) Integer userId) {
        userId = (Integer)Optional.ofNullable(userId).orElse(SecureUtil.getUserId());
        Enterprise enterprise = (Enterprise)((LambdaQueryChainWrapper)this.enterpriseService.lambdaQuery().eq(Enterprise::getUserId, userId)).one();
        EnterpriseWrapper enterpriseWrapper = new EnterpriseWrapper(this.dictClient, this.contactService, this.userCertificateService, this.bankService);
        return R.data(enterpriseWrapper.entityVO(enterprise));
    }

    @PostMapping({"/save"})
    @ApiOperation(
        value = "新增",
        notes = "传入enterprise",
        position = 2
    )
    public R save(@Valid @RequestBody EnterpriseVO enterprise) {
        Integer userId = SecureUtil.getUserId();
        enterprise.setUserId(userId);
        return R.status(this.enterpriseService.compositeSave(enterprise));
    }

    @PostMapping({"/update"})
    @ApiOperation(
        value = "修改",
        notes = "传入enterprise",
        position = 3
    )
    public R update(@Valid @RequestBody EnterpriseVO enterprise) {
        return R.status(this.enterpriseService.compositeUpdateById(enterprise));
    }

    @PostMapping({"/review"})
    @ApiOperation(
        value = "审核",
        notes = "传入enterprise",
        position = 3
    )
    public R review(@Valid @RequestBody Enterprise enterprise) {
        return this.enterpriseService.review(enterprise);
    }

}
