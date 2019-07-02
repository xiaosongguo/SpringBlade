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
import org.springblade.system.entity.Bill;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IBillService;
import org.springblade.system.vo.BillVO;
import org.springblade.system.wrapper.BillWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 通道账单表 控制器
 *
 * @author Blade
 * @since 2019-07-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/bill")
@Api(value = "通道账单表", tags = "通道账单表接口")
public class BillController extends BladeController {

	private IBillService billService;

	private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入bill", position = 1)
	public R<BillVO> detail(Bill bill) {
		Bill detail = billService.getOne(Condition.getQueryWrapper(bill));
		BillWrapper billWrapper = new BillWrapper(dictClient);
		return R.data(billWrapper.entityVO(detail));
	}

	/**
	* 分页 通道账单表
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入bill", position = 2)
	public R<IPage<BillVO>> list(Bill bill, Query query) {
		IPage<Bill> pages = billService.page(Condition.getPage(query), Condition.getQueryWrapper(bill));
		BillWrapper billWrapper = new BillWrapper(dictClient);
		return R.data(billWrapper.pageVO(pages));
	}

	/**
	* 自定义分页 通道账单表
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入bill", position = 3)
	public R<IPage<BillVO>> page(BillVO bill, Query query) {
		IPage<BillVO> pages = billService.selectBillPage(Condition.getPage(query), bill);
		return R.data(pages);
	}

	/**
	* 新增 通道账单表
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入bill", position = 4)
	public R save(@Valid @RequestBody Bill bill) {
		return R.status(billService.save(bill));
	}

	/**
	* 修改 通道账单表
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入bill", position = 5)
	public R update(@Valid @RequestBody Bill bill) {
		return R.status(billService.updateById(bill));
	}

	/**
	* 新增或修改 通道账单表
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入bill", position = 6)
	public R submit(@Valid @RequestBody Bill bill) {
		return R.status(billService.saveOrUpdate(bill));
	}

	
	/**
	* 删除 通道账单表
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(billService.removeByIds(Func.toIntList(ids)));
	}

	
}
