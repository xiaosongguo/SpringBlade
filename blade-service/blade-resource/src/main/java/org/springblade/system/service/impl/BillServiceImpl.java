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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.system.dto.BillDTO;
import org.springblade.system.entity.Bill;
import org.springblade.system.mapper.BillMapper;
import org.springblade.system.service.IBillService;
import org.springblade.system.service.IChannelResourceService;
import org.springblade.system.vo.BillVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通道账单表 服务实现类
 *
 * @author Blade
 * @since 2019-07-02
 */
@Service
@AllArgsConstructor
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

	private IChannelResourceService channelResourceService;

	@Override
	public IPage<BillVO> selectBillPage(IPage<BillVO> page, BillVO bill) {
		return page.setRecords(baseMapper.selectBillPage(page, bill));
	}

	@Override
	public IPage<BillVO> settle(IPage<BillVO> page, BillDTO bill) {
		return page.setRecords(baseMapper.settle(page, bill));
	}

	@Override
	public IPage<BillVO> detail(IPage<BillVO> page, BillVO bill) {
		return page.setRecords(baseMapper.detail(page, bill));
	}

	@Override
	public boolean createBills() {
		return baseMapper.createBills(channelResourceService.selectAvailableChannels().stream().filter(Objects::nonNull).map(channel -> {
			Bill bill = new Bill();
			bill.setBillStatus(0);
			bill.setIsmgId(channel.getIsmgId());
			bill.setUserId(channel.getSupplierId());
			bill.setBillMonth(LocalDateTime.now());
			bill.setCreateTime(LocalDateTime.now());
			return bill;
		}).collect(Collectors.toList()));
	}

}
