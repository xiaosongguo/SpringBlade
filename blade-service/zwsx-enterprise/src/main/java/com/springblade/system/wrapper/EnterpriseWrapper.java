package com.springblade.system.wrapper;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.springblade.system.service.IBankService;
import com.springblade.system.service.IContactService;
import com.springblade.system.service.IUserCertificateService;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Bank;
import org.springblade.system.entity.Contact;
import org.springblade.system.entity.Enterprise;
import org.springblade.system.entity.UserCertificate;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.BankVO;
import org.springblade.system.vo.EnterpriseVO;
import org.springblade.system.vo.UserCertificateVO;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class EnterpriseWrapper extends BaseEntityWrapper<Enterprise, EnterpriseVO> {
    private IDictClient dictClient;
    private IContactService contactService;
    private IUserCertificateService userCertificateService;
    private IBankService bankService;

    public EnterpriseVO entityVO(Enterprise enterprise) {
        if (!Optional.ofNullable(enterprise).isPresent()) {
            return null;
        } else {
            Integer userId = enterprise.getUserId();
            EnterpriseVO enterpriseVO = (EnterpriseVO)BeanUtil.copy(enterprise, EnterpriseVO.class);
            List<Contact> contacts = ((LambdaQueryChainWrapper)this.contactService.lambdaQuery().eq(Contact::getUserId, userId)).list();
            enterpriseVO.setContacts(contacts);
            UserCertificate userCertificate = new UserCertificate();
            userCertificate.setUserId(userId);
            List<UserCertificateVO> userCertificates = this.userCertificateService.selectUserCertificate(userCertificate);
            enterpriseVO.setUserCertificates(userCertificates);
            Bank bank = new Bank();
            bank.setUserId(userId);
            List<BankVO> banks = this.bankService.selectBank(bank);
            enterpriseVO.setBanks(banks);
            return enterpriseVO;
        }
    }

}
