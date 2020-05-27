//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springblade.system.mapper.UserCertificateMapper;
import com.springblade.system.service.IUserCertificateService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.UserCertificate;
import org.springblade.system.vo.UserCertificateVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserCertificateServiceImpl extends ServiceImpl<UserCertificateMapper, UserCertificate> implements IUserCertificateService {

    public IPage<UserCertificateVO> selectUserCertificatePage(IPage<UserCertificateVO> page, UserCertificateVO userCertificate) {
        return page.setRecords(((UserCertificateMapper)this.baseMapper).selectUserCertificatePage(page, userCertificate));
    }

    public List<UserCertificateVO> selectUserCertificate(UserCertificate userCertificate) {
        return ((UserCertificateMapper)this.baseMapper).selectUserCertificate(userCertificate);
    }
}
