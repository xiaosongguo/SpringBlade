package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.ISMGSend;
import org.springblade.system.vo.ISMGSendVO;

import java.util.List;

public interface ISMGSendMapper extends BaseMapper<ISMGSend> {

	List<ISMGSendVO> selectISMGSendPage(IPage page, ISMGSendVO ismgSend);
}
