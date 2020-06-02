package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.entity.UserSign;
import org.springblade.system.vo.UserSignVO;

public interface IUserSignService extends IService<UserSign> {

	//得到通道下签名的发送条数
	IPage<UserSignVO> selectUserSignPage(IPage<UserSignVO> page, UserSignVO userSignVO);


}
