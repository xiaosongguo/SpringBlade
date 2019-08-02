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
package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.ChannelResource;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IChannelResourceService;
import org.springblade.system.vo.ChannelResourceVO;
import org.springblade.system.wrapper.ChannelResourceWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 通道资源表 控制器
 *
 * @author Blade
 * @since 2019-05-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/channelresource")
@Api(value = "通道资源表", tags = "通道资源表接口")
@Slf4j
public class ChannelResourceController extends BladeController {

	private IChannelResourceService channelResourceService;

	private IDictClient dictClient;

	private AuthFun authFun;

	private RedisTemplate redisTemplate;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入channelResource", position = 1)
	public R<ChannelResourceVO> detail(ChannelResource channelResource) {
		ChannelResource detail = channelResourceService.getOne(Condition.getQueryWrapper(channelResource));
		ChannelResourceWrapper channelResourceWrapper = new ChannelResourceWrapper(dictClient);
		return R.data(channelResourceWrapper.entityVO(detail));
	}

	/**
	* 分页 通道资源表
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入channelResource", position = 2)
	public R<IPage<ChannelResourceVO>> list(ChannelResourceVO channelResource, Query query) {
		supplierId(channelResource);
		IPage<ChannelResourceVO> pages = channelResourceService.selectChannelResourcePage(Condition.getPage(query), channelResource);
		return R.data(pages);
	}


	/**
	* 新增或修改 通道资源表
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入channelResource", position = 6)
	public R submit(@Valid @RequestBody ChannelResourceVO channelResource) {
		supplierId(channelResource);
		return R.status(channelResourceService.saveOrUpdateCustom(channelResource));
	}


	/**
	* 删除 通道资源表
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	@PreAuth(RoleConstant.HAS_ROLE_OPERATION)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(channelResourceService.removeCascadeByIds(Func.toIntList(ids)));
	}

	private void supplierId(ChannelResourceVO channelResource) {
		log.debug("当前角色：{}",SecureUtil.getUserRole());
		if (authFun.hasAnyRole(RoleConstant.SUPPLIER)){
			channelResource.setTenantCode(SecureUtil.getTenantCode());
		}
		channelResource.setSupplierId(SecureUtil.getUserId());
	}
}
