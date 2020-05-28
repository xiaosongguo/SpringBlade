//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.*;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.mapper.EnterpriseMapper;
import org.springblade.system.service.*;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springblade.system.vo.BankVO;
import org.springblade.system.vo.EnterpriseVO;
import org.springblade.system.vo.UserCertificateVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {
    private static final Logger log = LoggerFactory.getLogger(EnterpriseServiceImpl.class);
    private IBankService bankService;
    private IContactService contactService;
    private IUserCertificateService userCertificateService;
    private IFileManagerService fileManagerService;
    private ISysClient sysClient;
    private IUserClient userClient;

    public IPage<EnterpriseVO> selectEnterprisePage(IPage<EnterpriseVO> page, EnterpriseVO enterprise) {
        return page.setRecords(((EnterpriseMapper)this.baseMapper).selectEnterprisePage(page, enterprise));
    }

    @Transactional
    public boolean compositeSave(EnterpriseVO enterprise) {
        Integer userId = enterprise.getUserId();
        List<UserCertificateVO> userCertificateVOS = enterprise.getUserCertificates();
        List<UserCertificate> userCertificates = (List)userCertificateVOS.stream().filter((userCertificateVO) -> {
            return !CollectionUtil.isEmpty(userCertificateVO.getFileManagers());
        }).map((userCertificateVO) -> {
            userCertificateVO.setUserId(userId);
            return userCertificateVO;
        }).collect(Collectors.toList());
        this.userCertificateService.saveBatch(userCertificates);
        List<Contact> contacts = (List)enterprise.getContacts().stream().map((contact) -> {
            contact.setUserId(userId);
            return contact;
        }).collect(Collectors.toList());
        this.contactService.saveBatch(contacts);
        List<BankVO> bankVOS = enterprise.getBanks();
        List<Bank> banks = (List)bankVOS.stream().map((bankVO) -> {
            bankVO.setUserId(userId);
            return bankVO;
        }).collect(Collectors.toList());
        this.bankService.saveBatch(banks);
        List<FileManager> certificatesFiles = (List)userCertificates.stream().map((userCertificate) -> {
            return (UserCertificateVO)userCertificate;
        }).flatMap((userCertificateVO) -> {
            return userCertificateVO.getFileManagers().stream().map((fileManager) -> {
                fileManager.setOwnerId(userCertificateVO.getId());
                fileManager.setStatus(1);
                return fileManager;
            });
        }).collect(Collectors.toList());
        List<FileManager> bankFiles = (List)banks.stream().map((bank) -> {
            return (BankVO)bank;
        }).flatMap((bankVO) -> {
            return bankVO.getFileManagers().stream().map((fileManager) -> {
                fileManager.setOwnerId(bankVO.getId());
                fileManager.setStatus(1);
                return fileManager;
            });
        }).collect(Collectors.toList());
        List<FileManager> fileManagers = (List)Stream.of(certificatesFiles, bankFiles).flatMap(Collection::stream).collect(Collectors.toList());
        this.fileManagerService.updateBatchById(fileManagers);
        return this.save(enterprise);
    }

    @Transactional
    public boolean compositeUpdateById(EnterpriseVO enterprise) {
        Integer userId = enterprise.getUserId();
        List<UserCertificateVO> userCertificateVOS = enterprise.getUserCertificates();
        List<UserCertificate> userCertificates = (List)userCertificateVOS.stream().filter((userCertificateVO) -> {
            return !CollectionUtil.isEmpty(userCertificateVO.getFileManagers());
        }).map((userCertificateVO) -> {
            return userCertificateVO;
        }).collect(Collectors.toList());
        this.userCertificateService.saveOrUpdateBatch(userCertificates);
        List<Contact> contacts = (List)enterprise.getContacts().stream().map((contact) -> {
            if (contact.getId() == null) {
                contact.setUserId(userId);
            }

            return contact;
        }).collect(Collectors.toList());
        List<Integer> contactDelIds = this.contactService.listObjs(Wrappers.<Contact>lambdaQuery().notIn(Contact::getId, (Collection)contacts.stream().filter((contact) -> {
            return !Func.isEmpty(contact.getId());
        }).map((contact) -> {
            return contact.getId();
        }).collect(Collectors.toList())).eq(Contact::getUserId, userId), (obj) -> {
            return Func.toInt(obj);
        });
        if (CollectionUtil.isNotEmpty(contactDelIds)) {
            this.contactService.compositeRemoveByIds(contactDelIds);
        }

        this.contactService.saveOrUpdateBatch(contacts);
        List<BankVO> bankVOS = enterprise.getBanks();
        List<Bank> banks = (List)bankVOS.stream().map((bankVO) -> {
            if (bankVO.getId() == null) {
                bankVO.setUserId(userId);
            }

            return bankVO;
        }).collect(Collectors.toList());
        List<Integer> delBankIds = this.bankService.listObjs(Wrappers.<Bank>lambdaQuery().notIn(Bank::getId, (Collection)banks.stream().filter((bank) -> {
            return !Func.isEmpty(bank.getId());
        }).map((bank) -> {
            return bank.getId();
        }).collect(Collectors.toList())).eq(Bank::getUserId, userId), (obj) -> {
            return Func.toInt(obj);
        });
        if (CollectionUtil.isNotEmpty(delBankIds)) {
            this.bankService.compositeRemoveByIds(delBankIds);
        }

        this.bankService.saveOrUpdateBatch(banks);
        List<FileManager> certificatesFiles = (List)userCertificates.stream().map((userCertificate) -> {
            return (UserCertificateVO)userCertificate;
        }).flatMap((userCertificateVO) -> {
            return userCertificateVO.getFileManagers().stream().map((fileManager) -> {
                fileManager.setOwnerId(userCertificateVO.getId());
                fileManager.setStatus(1);
                return fileManager;
            });
        }).collect(Collectors.toList());
        List<FileManager> bankFiles = (List)banks.stream().map((bank) -> {
            return (BankVO)bank;
        }).flatMap((bankVO) -> {
            return bankVO.getFileManagers().stream().map((fileManager) -> {
                fileManager.setOwnerId(bankVO.getId());
                fileManager.setStatus(1);
                return fileManager;
            });
        }).collect(Collectors.toList());
        List<FileManager> fileManagers = (List)Stream.of(certificatesFiles, bankFiles).flatMap(Collection::stream).collect(Collectors.toList());
        List<Integer> delFileIds = this.fileManagerService.listObjs(Wrappers.<FileManager>lambdaQuery().notIn(FileManager::getId, (Collection)fileManagers.stream().map((fileManager) -> {
            return fileManager.getId();
        }).collect(Collectors.toList())).eq(FileManager::getUserId, userId), (obj) -> {
            return Func.toInt(obj);
        });
        if (delFileIds.size() > 0) {
            this.fileManagerService.removeByIds(delFileIds);
        }

        this.fileManagerService.saveOrUpdateBatch(fileManagers);
        return this.updateById(enterprise);
    }

    public R review(Enterprise enterprise) {
        if (enterprise.getStatus() == 1) {
            Integer userId = enterprise.getUserId();
            String enterpriseName = enterprise.getName();
            Dept dept = new Dept();
            dept.setTenantCode("000000");
            dept.setParentId(2);
            dept.setDeptName(enterpriseName);
            dept.setFullName(enterpriseName);
            dept.setSort(2);
            dept.setIsDeleted(0);
            Dept supplierDept = this.sysClient.saveDept(dept);
            if (supplierDept == null || supplierDept.getId() == null) {
                return R.fail("新建部门失败");
            }

            Role role = new Role();
            role.setTenantCode("000000");
            role.setRoleAlias("supplier");
            String roleIds = this.sysClient.getRoleIds(role);
            User user = new User();
            user.setId(userId);
            user.setRoleId(roleIds);
            user.setName(enterpriseName);
            user.setRealName(enterpriseName);
            user.setDeptId(Func.toStr(supplierDept.getId()));
            User updateUser = this.userClient.updateUser(user);
            if (updateUser == null) {
                return R.fail("更新用户角色失败");
            }
        }
        return R.status(lambdaUpdate().set(Enterprise::getStatus, enterprise.getStatus()).eq(Enterprise::getId, enterprise.getId()).update());
    }

}
