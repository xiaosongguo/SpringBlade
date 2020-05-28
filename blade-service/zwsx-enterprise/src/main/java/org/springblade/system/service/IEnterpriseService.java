//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Enterprise;
import org.springblade.system.vo.EnterpriseVO;

public interface IEnterpriseService extends IService<Enterprise> {
    IPage<EnterpriseVO> selectEnterprisePage(IPage<EnterpriseVO> page, EnterpriseVO enterprise);

    boolean compositeSave(EnterpriseVO enterprise);

    boolean compositeUpdateById(EnterpriseVO enterprise);

    R review(Enterprise enterprise);
}
