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
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.OkHttpUtil;
import org.springblade.system.entity.Role;
import org.springblade.system.entity.Tenant;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.service.IUserService;
import org.springblade.system.user.util.RandomValidateCodeUtil;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping("/register")
@AllArgsConstructor
@Slf4j
public class RegisterController extends BladeController {

	private static  final  String JSESSIONID = "JSESSIONID";

	private IUserService userService;

	private ISysClient sysClient;

	private RedisTemplate redisTemplate;


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
		if (!checkSmsCode(user.getSmsCode(), request.getSession())) {
			return R.fail("验证码不正确");
		}
		Tenant tenant = new Tenant();
		tenant.setTenantName(user.getTenantCode());
		tenant = sysClient.saveSupplierTenant(tenant);
		if (tenant == null){
			return R.fail("新建租户失败");
		}
		Role role = new Role();
		role.setTenantCode(BladeConstant.ADMIN_TENANT_CODE);
		role.setRoleAlias(RoleConstant.SUPPLIER);
		String roleIds = sysClient.getRoleIds(role);
		user.setRoleId(roleIds);
		user.setTenantCode(tenant.getTenantCode());

		boolean submit = userService.submit(user);
		if (!submit) {
			return R.fail("新建用户失败");
		}
		return R.data(tenant.getTenantCode(),"注册成功");
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

	private boolean checkSmsCode(String smsCode, HttpSession session) {
		Object sessionId = getRequest().getSession().getAttribute(JSESSIONID);
		if (sessionId == null){
			return false;
		}
		Object random = redisTemplate.opsForValue().get(sessionId);
		log.debug("random:{},smsCode:{}",random,smsCode);
		return Objects.equals(Func.toStr(random), smsCode);
	}

	@ApiOperation(value = "生成手机短信码", notes = "手机号码", position = 4)
	@GetMapping(value = "/getSmsCode")
	public void getSmsCode(@RequestParam("mobile") String phone) {
		log.debug("phone:",phone);
		Object sessionId = getRequest().getSession().getAttribute(JSESSIONID);
		if(sessionId!= null && redisTemplate.opsForValue().get(sessionId)!=null){
			log.debug("不要重复点击");
			return;
		}
		int smsCode = ThreadLocalRandom.current().nextInt(100000, 999999);
		sessionId = UUID.randomUUID().toString().replace("-", "");

		getRequest().getSession().setAttribute(JSESSIONID,sessionId);
		redisTemplate.opsForValue().set(sessionId,smsCode,10, TimeUnit.MINUTES);
		Map<String, String> params = new HashMap<>();
		params.put("apName","v3gaol");
		params.put("apPassword", "v3gaol");
		params.put("calledNumber", phone);
		params.put("content", "【中网信】您正在登录V3版网关支撑，验证码是:"+smsCode+"请妥善保管并尽快登录。");
		String res = OkHttpUtil.post("http://192.168.3.231:18081/wgws/BatchSubmit", params);
		log.debug(res);
		log.debug("生成的验证码是：{}",smsCode);
	}


}
