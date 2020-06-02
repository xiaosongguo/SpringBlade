package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IChannelResourceService;
import org.springblade.system.service.IUserSignService;
import org.springblade.system.vo.UserSignVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/userSign")
@Api(value = "用户签名", tags = "用户签名表接口")
public class UserSignController extends BladeController {

	private IUserSignService userSignService;
	private IChannelResourceService channelResourceService;

	private IDictClient dictClient;

	/**
	 * 自定义分页 用户通道签名
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "userSign")
	public R<IPage<UserSignVO>> page(UserSignVO userSign, Query query) {
		Integer id=SecureUtil.getUserId();
		//查询用户的通道资源id
		List<Integer> ISMGIds= channelResourceService.getISMGIdsById(id);
		userSign.setISMGIds(ISMGIds);
		//根据资源id统计用户签名发送量
		IPage<UserSignVO> pages = userSignService.selectUserSignPage(Condition.getPage(query), userSign);
		return R.data(pages);
	}




}
