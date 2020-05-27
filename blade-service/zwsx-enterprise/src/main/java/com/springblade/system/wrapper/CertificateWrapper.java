package com.springblade.system.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Certificate;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.vo.CertificateVO;

@AllArgsConstructor
public class CertificateWrapper extends BaseEntityWrapper<Certificate, CertificateVO> {
    private IDictClient dictClient;

    public CertificateVO entityVO(Certificate certificate) {
        CertificateVO certificateVO = (CertificateVO) BeanUtil.copy(certificate, CertificateVO.class);
        return certificateVO;
    }

}
