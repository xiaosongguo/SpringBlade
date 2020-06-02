package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.system.entity.UserSign;
import org.springblade.system.mapper.UserSignMapper;
import org.springblade.system.service.IUserSignService;
import org.springblade.system.vo.UserSignVO;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.MYCAT;


@Service
@DS(MYCAT)
@AllArgsConstructor
public class UserSignServiceImpl extends ServiceImpl<UserSignMapper, UserSign> implements IUserSignService {

	//得到通道下签名的发送条数
	@Override
	public IPage<UserSignVO> selectUserSignPage(IPage<UserSignVO> page, UserSignVO userSignVO) {
		return page.setRecords(baseMapper.selectUserSignPage(page,userSignVO));
	}
}
