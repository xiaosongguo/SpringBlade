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
package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.dto.BillDTO;
import org.springblade.system.entity.Bill;
import org.springblade.system.vo.BillVO;

import java.util.List;

/**
 * 通道账单表 Mapper 接口
 *
 * @author Blade
 * @since 2019-07-02
 */
public interface BillMapper extends BaseMapper<Bill> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param bill
	 * @return
	 */
	List<BillVO> selectBillPage(IPage page, BillVO bill);

	List<BillVO> settle(IPage<BillVO> page, BillDTO bill);

	List<BillVO> detail(IPage<BillVO> page, BillVO bill);

	boolean createBills(List<Bill> bills);

	boolean updateAmount(@Param("vestDate") String vestDate);

}
