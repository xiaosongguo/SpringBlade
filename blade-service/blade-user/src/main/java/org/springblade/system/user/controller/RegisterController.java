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


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.service.IUserService;
import org.springblade.system.user.util.RandomValidateCodeUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

	private IUserService userService;

	@ApiOperation(value = "判断用户存在", notes = "传入id", position = 1)
	@GetMapping("/isExist")
	public R isExist(String account, @RequestParam(defaultValue = "000000", required = false) String tenantCode) {
		int count = userService.count(Wrappers.<User>query().lambda().eq(User::getTenantCode, tenantCode).eq(User::getAccount, account));
		if (count > 0) {
			return R.success("用户已存在");
		}
		return R.success("");
	}

	@PostMapping("")
	@ApiOperation(value = "注册", notes = "传入User", position = 2)
	@Transactional
	public R register(@RequestBody UserDTO user, HttpServletRequest request) {
		if (!checkVerify(user.getImgCode(), request.getSession())) {
			return R.fail("验证码不正确");
		}
		boolean submit = userService.submit(user);
		if (!submit) {
			return R.fail("新建用户失败");
		}
		String roleIds = "1493";
		boolean grant = userService.grant(Func.toStr(user.getId()), roleIds);
		if (!grant) {
			return R.fail("分配权限失败");
		}
		return R.success("注册成功");
	}

	@ApiOperation(value = "生成验证码", notes = "传入User", position = 3)
	@GetMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
		randomValidateCode.getRandcode(request, response);//输出验证码图片方法
	}

	public boolean checkVerify(String imgCode, HttpSession session) {
		String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		return Objects.equals(random, imgCode);
	}
}
