//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.Certificate;
import org.springblade.system.vo.CertificateVO;

import java.util.List;

public interface CertificateMapper extends BaseMapper<Certificate> {
    List<CertificateVO> selectCertificatePage(IPage page, CertificateVO certificate);
}
