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
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.dto.BillDTO;
import org.springblade.system.entity.Bill;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IBillService;
import org.springblade.system.vo.BillVO;
import org.springblade.system.wrapper.BillWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

	private AuthFun authFun;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入bill", position = 1)
	public R<IPage<BillVO>> detail(BillVO bill, Query query) {
		return R.data(billService.detail(Condition.getPage(query), bill));	}

	/**
	* 结算查询
	*/
	@GetMapping("/settle")
	@ApiOperation(value = "结算", notes = "传入bill", position = 8)
	public R<IPage<BillVO>> settle(BillDTO bill, Query query) {
		if (authFun.hasAnyRole(RoleConstant.SUPPLIER)){
			bill.setUserId(SecureUtil.getUserId());
		}
		return R.data(billService.settle(Condition.getPage(query), bill));
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
	public R submit(@Valid @RequestBody List<Bill> bill) {
		List<Bill> collect = bill.stream().map(i -> {
			Bill b = new Bill();
			Long id = i.getId();
			b.setId(i.getId());
			if(Func.isEmpty(id)){
				b.setBillStatus(0);
			}
			if (authFun.hasAnyRole(RoleConstant.SUPPLIER)) {
				b.setSupplierAmount(i.getSupplierAmount());
				b.setSupplierMoney(i.getSupplierMoney());
				b.setBillStatus(1);
			} else {
				b.setSysAmount(i.getSysAmount());
				b.setSysMoney(i.getSysMoney());
				b.setBillStatus(2);
			}
			return b;
		}).collect(Collectors.toList());
		return R.status(billService.saveOrUpdateBatch(collect));
	}


	/**
	* 删除 通道账单表
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(billService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 批量导入对账
	 */
	@PostMapping("/import")
	@ApiOperation(value = "导入", notes = "传入bills", position = 5)
	public R update(@Valid @RequestBody List<Bill> bills) {
		return R.status(billService.saveBatch(bills));
	}

	@PostMapping({"/createBills"})
	@ApiImplicitParams({@ApiImplicitParam(
		name = "vestDate",
		value = "账期",
		paramType = "query",
		dataType = "string"
	)})
	@ApiOperation(
		value = "创建账单",
		notes = "传入vestDate",
		position = 5
	)
	public R createBills(String vestDate) {
		return R.status(this.billService.createBills(LocalDate.parse(vestDate)));
	}

	@PostMapping({"/updateAmount"})
	@ApiImplicitParams({@ApiImplicitParam(
		name = "vestDate",
		value = "账期",
		paramType = "query",
		dataType = "string"
	)})
	@ApiOperation(
		value = "更新账单",
		notes = "传入vestDate",
		position = 5
	)
	public R updateAmount(String vestDate) {
		return R.status(this.billService.updateAmount(vestDate));
	}
}
