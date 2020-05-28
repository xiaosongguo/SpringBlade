//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.UserCertificate;
import org.springblade.system.vo.UserCertificateVO;

import java.util.List;

public interface UserCertificateMapper extends BaseMapper<UserCertificate> {
    List<UserCertificateVO> selectUserCertificatePage(IPage page, UserCertificateVO userCertificate);

    List<UserCertificateVO> selectUserCertificate(@Param("userCertificate") UserCertificate userCertificate);
}
