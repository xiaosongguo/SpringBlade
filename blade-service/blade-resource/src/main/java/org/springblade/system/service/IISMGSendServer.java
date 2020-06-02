package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.ISMGSend;
import org.springblade.system.vo.ISMGSendVO;

public interface IISMGSendServer extends IService<ISMGSend> {
	IPage<ISMGSendVO> selectISMGSendPage(IPage<ISMGSendVO> page, ISMGSendVO ismgSend);
}
