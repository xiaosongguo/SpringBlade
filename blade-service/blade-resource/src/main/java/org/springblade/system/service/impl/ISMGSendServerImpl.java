package org.springblade.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.ISMGSend;
import org.springblade.system.mapper.ISMGSendMapper;
import org.springblade.system.service.IISMGSendServer;
import org.springblade.system.vo.ISMGSendVO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

@Service
@DS(SMSMAN)
@AllArgsConstructor
public class ISMGSendServerImpl extends ServiceImpl<ISMGSendMapper, ISMGSend> implements IISMGSendServer {
	@Override
	public IPage<ISMGSendVO> selectISMGSendPage(IPage<ISMGSendVO> page, ISMGSendVO ismgSend) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(Func.isBlank(ismgSend.getBeginDate())){
			ismgSend.setBeginDate(sdf.format(new Date()));
		}
		if(Func.isBlank(ismgSend.getEndDate())){
			ismgSend.setEndDate(sdf.format(new Date()));
		}
		return page.setRecords(baseMapper.selectISMGSendPage(page,ismgSend));
	}

}
