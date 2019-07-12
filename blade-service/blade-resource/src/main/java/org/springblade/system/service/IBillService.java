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
package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.system.dto.BillDTO;
import org.springblade.system.entity.Bill;
import org.springblade.system.vo.BillVO;

/**
 * 通道账单表 服务类
 *
 * @author Blade
 * @since 2019-07-02
 */
public interface IBillService extends IService<Bill> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bill
	 * @return
	 */
	IPage<BillVO> selectBillPage(IPage<BillVO> page, BillVO bill);

	/**
	 * 月度结算
	 *
	 * @param page
	 * @param bill
	 * @return
	 */
	IPage<BillVO> settle(IPage<BillVO> page, BillDTO bill);

	/**
	 * 每月网关发送查询
	 * @param page
	 * @param bill
	 * @return
	 */
	IPage<BillVO> detail(IPage<BillVO> page, BillVO bill);

	/**
	 * 每月生成账单
	 * @return
	 */
	boolean createBills();
}
