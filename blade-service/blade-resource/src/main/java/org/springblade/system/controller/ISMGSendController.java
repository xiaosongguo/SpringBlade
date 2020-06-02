package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.system.service.IISMGSendServer;
import org.springblade.system.vo.ISMGSendVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ismgSend")
@Api(value = "网关发送统计", tags = "网关发送统计接口")
public class ISMGSendController extends BladeController {

	IISMGSendServer ismgSendServer;

	/**
	 * 自定义分页 用户发送统计
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "ismgSend")
	public R<IPage<ISMGSendVO>> page(ISMGSendVO ismgSend, Query query) {

		IPage<ISMGSendVO> iPages=ismgSendServer.selectISMGSendPage(Condition.getPage(query),ismgSend);
		return R.data(iPages);
	}

}
