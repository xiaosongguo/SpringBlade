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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.auth.AuthFun;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.dto.QuoteDTO;
import org.springblade.system.entity.Quote;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.feign.IFileManagerClient;
import org.springblade.system.service.IQuoteDetailService;
import org.springblade.system.service.IQuoteService;
import org.springblade.system.vo.QuoteVO;
import org.springblade.system.wrapper.QuoteWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 *  控制器
 *
 * @author Blade
 * @since 2019-06-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/quote")
@Api(value = "", tags = "接口")
public class QuoteController extends BladeController {

	private IQuoteService quoteService;

	private IDictClient dictClient;

	private IQuoteDetailService quoteDetailService;

	private IFileManagerClient fileManagerClient;

	private AuthFun authFun;

	//配置权限
	private void authFun(Quote quote) {
		if (authFun.hasAnyRole(RoleConstant.SUPPLIER)){
			quote.setSupplierId(SecureUtil.getUserId());
		}
	}

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入quote", position = 1)
	public R<QuoteVO> detail(Quote quote) {
		Quote detail = quoteService.getOne(Condition.getQueryWrapper(quote));
		QuoteWrapper quoteWrapper = new QuoteWrapper(dictClient,quoteDetailService,fileManagerClient);
		return R.data(quoteWrapper.entityVO(detail));
	}

	/**
	* 分页
	*/
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入quote", position = 2)
	public R<IPage<QuoteVO>> list(Quote quote, Query query) {
		//authFun(quote);
		//管理员拥有全部的权限
		boolean adminRole=false;
		if (SecureUtil.getUserRole() == RoleConstant.ADMIN) adminRole=true;
		//只能查询自己的子账号的报价信息,管理员可以查询全部
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.inSql(!adminRole,"SUPPLIER_ID","select id from BLADE_USER start with id="+SecureUtil.getUserId()+" connect by prior id=CREATE_USER");
		IPage<Quote> pages = quoteService.page(Condition.getPage(query), queryWrapper);
		//对查询到的Quote类加入对应的QuoteDetail、FileManager类
		QuoteWrapper quoteWrapper = new QuoteWrapper(dictClient,quoteDetailService,fileManagerClient);
		return R.data(quoteWrapper.pageVO(pages));
	}

	/**
	* 自定义分页
	*/
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入quote", position = 3)
	public R<IPage<QuoteVO>> page(QuoteVO quote, Query query) {
		if (authFun.hasAnyRole(RoleConstant.SUPPLIER)){
			quote.setTenantCode(SecureUtil.getTenantCode());
		}
		IPage<QuoteVO> pages = quoteService.selectQuotePage(Condition.getPage(query), quote);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入quote与quoteDetail", position = 4)
	public R save(@Valid @RequestBody QuoteDTO quote) {
		return R.status(quoteService.saveQuote(quote));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入quote", position = 5)
	@PreAuth(RoleConstant.HAS_ROLE_OPERATION)
	public R update(@Valid @RequestBody Quote quote) {
		boolean isSucc = false;
		if (quote.getStatus() > 0) {
			return R.fail("状态不对");
		}
		return R.status(quoteService.updateById(quote));
	}

	/**
	* 新增或修改
	*/
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入quote", position = 6)
	@PreAuth(RoleConstant.HAS_ROLE_OPERATION)
	public R submit(@Valid @RequestBody Quote quote) {
		return R.status(quoteService.saveOrUpdate(quote));
	}


	/**
	* 删除
	*/
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids", position = 7)
	@PreAuth(RoleConstant.HAS_ROLE_OPERATION)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(quoteService.removeByIds(Func.toIntList(ids)));
	}


	@PostMapping("/review")
	@ApiOperation(value = "审核", notes = "quoteDTO", position = 7)
	@PreAuth(RoleConstant.HAS_ROLE_OPERATION)
	public R review(@ApiParam(value = "quoteDTO", required = true) @RequestBody QuoteDTO quoteDTO) {
		if (Objects.equals(null,quoteDTO.getId())){
			return R.fail("id不能为空");
		}
		return R.status(quoteService.review(quoteDTO));
	}

}
