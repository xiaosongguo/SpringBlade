package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.UserSign;
import org.springblade.system.vo.UserSignVO;

import java.util.List;

public interface UserSignMapper extends BaseMapper<UserSign> {

	//通过通道ids获得用户签名统计
	List<UserSignVO> selectUserSignPage(IPage page, UserSignVO userSign);
}
