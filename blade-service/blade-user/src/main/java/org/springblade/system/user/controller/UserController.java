/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.system.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.service.IUserService;
import org.springblade.system.user.vo.UserVO;
import org.springblade.system.user.wrapper.UserWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

	private IUserService userService;

	private IDictClient dictClient;

	private RedisTemplate redisTemplate;

	private AuthFun authFun;

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id", position = 1)
	@GetMapping("/detail")
	public R<UserVO> detail(User user) {
		User detail = userService.getOne(Condition.getQueryWrapper(user));
		UserWrapper userWrapper = new UserWrapper(userService, dictClient);
		return R.data(userWrapper.entityVO(detail));
	}

	/**
	 * 用户列表
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "账号名", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "realName", value = "姓名", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "列表", notes = "传入account和realName", position = 2)
	public R<IPage<UserVO>> list(@ApiIgnore @RequestParam Map<String, Object> user, Query query, BladeUser bladeUser) {
		QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(user, User.class);
		//管理员拥有查询全部用户的权限,其他用户只拥有查询子用户的权限
		Integer userId = bladeUser.getUserId();
		queryWrapper.last(bladeUser.getRoleId()!= RoleConstant.ADMIN,"start with id = " + userId + " connect by  prior id = create_user");
		IPage<User> pages = this.userService.page(Condition.getPage(query), queryWrapper);
		UserWrapper userWrapper = new UserWrapper(this.userService, this.dictClient);
		return R.data(userWrapper.pageVO(pages));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入User", position = 3)
	public R submit(@Valid @RequestBody User user) {
		return R.status(userService.submit(user));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入User", position = 3)
	public R update(@Valid @RequestBody User user) {
		boolean update = this.userService.updateById(user);
		if (update)
			redisTemplate.opsForHash().put(user.getTenantCode(), user.getAccount(), Boolean.FALSE);
		return R.status(update);
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入地基和", position = 4)
	public R remove(@RequestParam String ids) {
		return R.status(userService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 设置菜单权限
	 *
	 * @param userIds
	 * @param roleIds
	 * @return
	 */
	@PostMapping("/grant")
	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合", position = 5)
	public R grant(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds,
				   @ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds) {
		boolean temp = userService.grant(userIds, roleIds);
		return R.status(temp);
	}

	@PostMapping("/reset-password")
	@ApiOperation(value = "初始化密码", notes = "传入userId集合", position = 5)
	public R resetPassword(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds) {
		boolean temp = userService.resetPassword(userIds);
		return R.status(temp);
	}

}
