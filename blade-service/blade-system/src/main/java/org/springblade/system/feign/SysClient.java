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
package org.springblade.system.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Role;
import org.springblade.system.entity.Tenant;
import org.springblade.system.service.IDeptService;
import org.springblade.system.service.IRoleService;
import org.springblade.system.service.ITenantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统服务Feign实现类
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class SysClient implements ISysClient {

	IDeptService deptService;

	IRoleService roleService;

	ITenantService tenantService;

	@Override
	@GetMapping(API_PREFIX + "/getDeptName")
	public String getDeptName(Integer id) {
		return deptService.getById(id).getDeptName();
	}

	@Override
	@GetMapping(API_PREFIX + "/getDept")
	public Dept getDept(Integer id) {
		return deptService.getById(id);
	}

	@Override
	@GetMapping(API_PREFIX + "/getRoleName")
	public String getRoleName(Integer id) {
		return roleService.getById(id).getRoleName();
	}

	@Override
	@GetMapping(API_PREFIX + "/getRoleAlias")
	public String getRoleAlias(Integer id) {
		return roleService.getById(id).getRoleAlias();
	}

	@Override
	@GetMapping(API_PREFIX + "/getRole")
	public Role getRole(Integer id) {
		return roleService.getById(id);
	}

	@Override
	@GetMapping(API_PREFIX + "/getRoleIds")
	public String getRoleIds(Role role) {
		List<Integer> roleIds = roleService.lambdaQuery()
			.eq(Role::getTenantCode, role.getTenantCode())
			.in(Role::getRoleAlias, role.getRoleAlias())
			.select(Role::getId)
			.list().stream().map(r -> r.getId()).collect(Collectors.toList());
		return StringUtil.join(roleIds);

	}

	@Override
	@PostMapping(API_PREFIX + "/saveSupplierTenant")
	public Tenant saveSupplierTenant(Tenant tenant) {
		return tenantService.saveSupplierTenant(tenant);
	}
}
