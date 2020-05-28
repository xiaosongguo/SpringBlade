//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.Enterprise;
import org.springblade.system.vo.EnterpriseVO;

import java.util.List;

public interface EnterpriseMapper extends BaseMapper<Enterprise> {
    List<EnterpriseVO> selectEnterprisePage(IPage page, EnterpriseVO enterprise);
}
