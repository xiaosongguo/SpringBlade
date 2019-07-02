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
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Ismg;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IIsmgService;
import org.springblade.system.vo.IsmgVO;
import org.springblade.system.wrapper.IsmgWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 网关表 控制器
 *
 * @author Blade
 * @since 2019-06-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ismg")
@Api(value = "网关表", tags = "网关表接口")
public class IsmgController extends BladeController {

	private IIsmgService ismgService;

	private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入ismg", position = 1)
	public R<IsmgVO> detail(Ismg ismg) {
		Ismg detail = ismgService.getOne(Condition.getQueryWrapper(ismg));
		IsmgWrapper ismgWrapper = new IsmgWrapper(dictClient);
		return R.data(ismgWrapper.entityVO(detail));
	}

	/**
	* 分页 网关表
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入ismg", position = 2)
	public R<IPage<IsmgVO>> list(Ismg ismg, Query query) {
		IPage<Ismg> pages = ismgService.page(Condition.getPage(query), Condition.getQueryWrapper(ismg));
		IsmgWrapper ismgWrapper = new IsmgWrapper(dictClient);
		return R.data(ismgWrapper.pageVO(pages));
	}

	/**
	* 自定义分页 网关表
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入ismg", position = 3)
	public R<IPage<IsmgVO>> page(IsmgVO ismg, Query query) {
		IPage<IsmgVO> pages = ismgService.selectIsmgPage(Condition.getPage(query), ismg);
		return R.data(pages);
	}

	/**
	* 新增 网关表
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入ismg", position = 4)
	public R save(@Valid @RequestBody Ismg ismg) {
		return R.status(ismgService.save(ismg));
	}

	/**
	* 修改 网关表
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入ismg", position = 5)
	public R update(@Valid @RequestBody Ismg ismg) {
		return R.status(ismgService.updateById(ismg));
	}

	/**
	* 新增或修改 网关表
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入ismg", position = 6)
	public R submit(@Valid @RequestBody Ismg ismg) {
		return R.status(ismgService.saveOrUpdate(ismg));
	}

	
	/**
	* 删除 网关表
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ismgService.removeByIds(Func.toIntList(ids)));
	}


	/**
	 * 不分页 网关id与名称
	 */
	@GetMapping("/dic")
	@ApiOperation(value = "网关id与名称", notes = "", position = 2)
	public R dic() {
		return R.data(ismgService.dic());
	}

	
}
