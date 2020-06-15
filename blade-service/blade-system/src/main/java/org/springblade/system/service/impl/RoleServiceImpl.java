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
package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.system.entity.Role;
import org.springblade.system.entity.RoleMenu;
import org.springblade.system.mapper.RoleMapper;
import org.springblade.system.service.IRoleMenuService;
import org.springblade.system.service.IRoleService;
import org.springblade.system.vo.RoleVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@Validated
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	IRoleMenuService roleMenuService;

	AuthFun authFun;

	@Override
	public IPage<RoleVO> selectRolePage(IPage<RoleVO> page, RoleVO role) {
		return page.setRecords(baseMapper.selectRolePage(page, role));
	}

	@Override
	public List<RoleVO> tree(String tenantCode) {
		String userRole = SecureUtil.getUserRole();
		//以逗号分隔，在mapper语句中解析
		String includeRole = null;
		//不是管理员只能创建同样角色的账号
		if (!authFun.denyAll()) {
			includeRole = userRole;
		}
		//代理渠道专员有创建供应商、游客的权限
		if(authFun.hasRole(RoleConstant.AGENTEXECUTIVE)){
			includeRole= RoleConstant.SUPPLIER+","+RoleConstant.TOURIST;
		}
		//供应商总监拥有创建代理渠道专员、供应商、游客的权限
		if(authFun.hasRole(RoleConstant.SUPPLIERMANAGEMENT)){
			includeRole= RoleConstant.SUPPLIER+","+RoleConstant.TOURIST+","+RoleConstant.AGENTEXECUTIVE;
		}
		//供应商、游客不拥有创建角色的权限
		if(authFun.hasRole(RoleConstant.SUPPLIER)||authFun.hasRole(RoleConstant.TOURIST)){
			includeRole="";
		}
		//设置租户
		if(authFun.isSupplier() || authFun.permitAll()){
			tenantCode = BladeConstant.ADMIN_TENANT_CODE;
		}

		//设置为树的结构
		return ForestNodeMerger.merge(baseMapper.tree(tenantCode, includeRole));
	}

	@Override
	public boolean grant(@NotEmpty List<Integer> roleIds, @NotEmpty List<Integer> menuIds) {
		// 删除角色配置的菜单集合
		roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
		// 组装配置
		List<RoleMenu> roleMenus = new ArrayList<>();
		roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenus.add(roleMenu);
		}));
		// 新增配置
		return roleMenuService.saveBatch(roleMenus);
	}

}
