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
package org.springblade.system.user.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Enterprise;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.mapper.UserMapper;
import org.springblade.system.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	AuthFun authFun;

	@Override
	public boolean submit(User user) {
		//对用户密码进行加密保存
		if (Func.isNotEmpty(user.getPassword())) {
			user.setPassword(DigestUtil.encrypt(user.getPassword()));
		}
		//对用户进行判断
		String roleName=user.getRealName();
		//代理渠道专员有创建供应商、游客的权限
		if(authFun.hasRole(RoleConstant.AGENTEXECUTIVE)){
			if(roleName!=RoleConstant.SUPPLIER&&roleName!=RoleConstant.TOURIST)
				throw new ApiException("用户没有创建该角色的权限!");
		}
		//供应商总监拥有创建代理渠道专员、供应商、游客的权限
		if(authFun.hasRole(RoleConstant.SUPPLIERMANAGEMENT)){
			if(roleName!=RoleConstant.SUPPLIER&&roleName!=RoleConstant.TOURIST&&roleName!=RoleConstant.AGENTEXECUTIVE)
				throw new ApiException("用户没有创建该角色的权限!");
		}
		//供应商、游客不拥有创建角色的权限
		if(authFun.hasRole(RoleConstant.SUPPLIER)||authFun.hasRole(RoleConstant.TOURIST)){
			throw new ApiException("游客和供应商没有创建角色的权限!");
		}

		//设置用户默认的租户
		if(user.getTenantCode()==null){
			user.setTenantCode(SecureUtil.getTenantCode());
		}

		Integer cnt = baseMapper.selectCount(Wrappers.<User>query().lambda().eq(User::getTenantCode, user.getTenantCode()).eq(User::getAccount, user.getAccount()));
		if (cnt > 0) {
			throw new ApiException("当前用户已存在!");
		}
		return saveOrUpdate(user);
	}

	@Override
	public IPage<User> selectUserPage(IPage<User> page, User user) {
		return page.setRecords(baseMapper.selectUserPage(page, user));
	}

	@Override
	public UserInfo userInfo(String tenantCode, String account, String password) {
		UserInfo userInfo = new UserInfo();
		User user = baseMapper.getUser(tenantCode, account, password);
		userInfo.setUser(user);
		if (Func.isNotEmpty(user)) {
			List<String> roleAlias = baseMapper.getRoleAlias(Func.toStrArray(user.getRoleId()));
			userInfo.setRoles(roleAlias);
		}
		return userInfo;
	}

	@Override
	public boolean grant(String userIds, String roleIds) {
		User user = new User();
		user.setRoleId(roleIds);
		return this.update(user, Wrappers.<User>update().lambda().in(User::getId, Func.toIntList(userIds)));
	}

	@Override
	public boolean resetPassword(String userIds) {
		User user = new User();
		user.setPassword(DigestUtil.encrypt(CommonConstant.DEFAULT_PASSWORD));
		user.setUpdateTime(LocalDateTime.now());
		return this.update(user, Wrappers.<User>update().lambda().in(User::getId, Func.toIntList(userIds)));
	}

	@Override
	public List<String> getRoleName(String roleIds) {
		return baseMapper.getRoleName(Func.toStrArray(roleIds));
	}

	@Override
	public List<String> getDeptName(String deptIds) {
		return deptIds != null ? baseMapper.getDeptName(Func.toStrArray(deptIds)):null;
	}

	@Override
	public Enterprise getEnterprise(Integer userId) {
		return ((UserMapper)this.baseMapper).getEnterprise(userId);
	}

}
