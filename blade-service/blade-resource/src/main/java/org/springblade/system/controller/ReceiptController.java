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
import org.springblade.system.entity.Receipt;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IReceiptService;
import org.springblade.system.vo.ReceiptVO;
import org.springblade.system.wrapper.ReceiptWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 *  控制器
 *
 * @author Blade
 * @since 2019-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/receipt")
@Api(value = "", tags = "接口")
public class ReceiptController extends BladeController {

	private IReceiptService receiptService;

	private IDictClient dictClient;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入receipt", position = 1)
	public R<ReceiptVO> detail(Receipt receipt) {
		Receipt detail = receiptService.getOne(Condition.getQueryWrapper(receipt));
		ReceiptWrapper receiptWrapper = new ReceiptWrapper(dictClient);
		return R.data(receiptWrapper.entityVO(detail));
	}

	/**
	* 分页 
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入receipt", position = 2)
	public R<IPage<ReceiptVO>> list(Receipt receipt, Query query) {
		IPage<Receipt> pages = receiptService.page(Condition.getPage(query), Condition.getQueryWrapper(receipt));
		ReceiptWrapper receiptWrapper = new ReceiptWrapper(dictClient);
		return R.data(receiptWrapper.pageVO(pages));
	}

	/**
	* 自定义分页 
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入receipt", position = 3)
	public R<IPage<ReceiptVO>> page(ReceiptVO receipt, Query query) {
		IPage<ReceiptVO> pages = receiptService.selectReceiptPage(Condition.getPage(query), receipt);
		return R.data(pages);
	}

	/**
	* 新增 
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入receipt", position = 4)
	public R save(@Valid @RequestBody Receipt receipt) {
		return R.status(receiptService.save(receipt));
	}

	/**
	* 修改 
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入receipt", position = 5)
	public R update(@Valid @RequestBody Receipt receipt) {
		return R.status(receiptService.updateById(receipt));
	}

	/**
	* 新增或修改 
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入receipt", position = 6)
	public R submit(@Valid @RequestBody Receipt receipt) {
		return R.status(receiptService.saveOrUpdate(receipt));
	}

	
	/**
	* 删除 
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(receiptService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 结算
	 */
	@GetMapping("/settle")
	@ApiOperation(value = "结算", notes = "传入receipt", position = 8)
	public R<IPage<ReceiptVO>> settle(ReceiptVO receipt, Query query,@RequestParam(value = "vestDate[]") Date[] vestDate) {
		receipt.setBeginDate(vestDate[0]);
		receipt.setEndDate(vestDate[1]);
		IPage<ReceiptVO> pages = receiptService.settle(Condition.getPage(query), receipt);
		return R.data(pages);
	}
}
