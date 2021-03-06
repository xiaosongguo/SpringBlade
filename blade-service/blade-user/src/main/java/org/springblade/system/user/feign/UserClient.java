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
package org.springblade.system.user.feign;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * 用户服务Feign实现类
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
public class UserClient implements IUserClient {

	IUserService service;

	@Override
	@GetMapping(API_PREFIX + "/user-info")
	public R<UserInfo> userInfo(String tenantCode, String account, String password) {
		return R.data(service.userInfo(tenantCode, account, password));
	}


	@GetMapping(API_PREFIX + "/getUser")
	public User getUser(Wrapper<User> queryWrapper) {
		return (User)this.service.getOne(queryWrapper, false);
	}

	@PostMapping({"/user/updateUser"})
	public User updateUser(User user) {
		this.service.updateById(user);
		return (User)this.service.getById((Serializable)user);
	}

}
