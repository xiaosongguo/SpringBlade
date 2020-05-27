//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.Certificate;
import org.springblade.system.vo.CertificateVO;

public interface ICertificateService extends IService<Certificate> {
    IPage<CertificateVO> selectCertificatePage(IPage<CertificateVO> page, CertificateVO certificate);
}
