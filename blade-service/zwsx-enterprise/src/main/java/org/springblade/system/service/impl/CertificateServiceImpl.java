//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.mapper.CertificateMapper;
import org.springblade.system.service.ICertificateService;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.Certificate;
import org.springblade.system.vo.CertificateVO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    public IPage<CertificateVO> selectCertificatePage(IPage<CertificateVO> page, CertificateVO certificate) {
        return page.setRecords(((CertificateMapper)this.baseMapper).selectCertificatePage(page, certificate));
    }
}
