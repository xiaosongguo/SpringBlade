//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.UserCertificate;
import org.springblade.system.vo.UserCertificateVO;

import java.util.List;

public interface IUserCertificateService extends IService<UserCertificate> {
    IPage<UserCertificateVO> selectUserCertificatePage(IPage<UserCertificateVO> page, UserCertificateVO userCertificate);

    List<UserCertificateVO> selectUserCertificate(UserCertificate userCertificate);
}
