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
import org.springblade.system.entity.SignIsmg;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.ISignIsmgService;
import org.springblade.system.vo.SignIsmgVO;
import org.springblade.system.wrapper.SignIsmgWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 签名与端口 控制器
 *
 * @author Blade
 * @since 2019-06-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/signismg")
@Api(value = "签名与端口", tags = "签名与端口接口")
public class SignIsmgController extends BladeController {

	private ISignIsmgService signIsmgService;

	private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入signIsmg", position = 1)
	public R<SignIsmgVO> detail(SignIsmg signIsmg) {
		SignIsmg detail = signIsmgService.getOne(Condition.getQueryWrapper(signIsmg));
		SignIsmgWrapper signIsmgWrapper = new SignIsmgWrapper(dictClient);
		return R.data(signIsmgWrapper.entityVO(detail));
	}

	/**
	* 分页 签名与端口
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入signIsmg", position = 2)
	public R<IPage<SignIsmgVO>> list(SignIsmg signIsmg, Query query) {
		IPage<SignIsmg> pages = signIsmgService.page(Condition.getPage(query), Condition.getQueryWrapper(signIsmg));
		SignIsmgWrapper signIsmgWrapper = new SignIsmgWrapper(dictClient);
		return R.data(signIsmgWrapper.pageVO(pages));
	}

	/**
	* 自定义分页 签名与端口
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入signIsmg", position = 3)
	public R<IPage<SignIsmgVO>> page(SignIsmgVO signIsmg, Query query) {
		IPage<SignIsmgVO> pages = signIsmgService.selectSignIsmgPage(Condition.getPage(query), signIsmg);
		return R.data(pages);
	}

	/**
	* 新增 签名与端口
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入signIsmg", position = 4)
	public R save(@Valid @RequestBody SignIsmg signIsmg) {
		return R.status(signIsmgService.save(signIsmg));
	}

	/**
	* 修改 签名与端口
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入signIsmg", position = 5)
	public R update(@Valid @RequestBody SignIsmg signIsmg) {
		return R.status(signIsmgService.updateById(signIsmg));
	}

	/**
	* 新增或修改 签名与端口
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入signIsmg", position = 6)
	public R submit(@Valid @RequestBody SignIsmg signIsmg) {
		return R.status(signIsmgService.saveOrUpdate(signIsmg));
	}

	
	/**
	* 删除 签名与端口
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(signIsmgService.removeByIds(Func.toIntList(ids)));
	}

	
}
