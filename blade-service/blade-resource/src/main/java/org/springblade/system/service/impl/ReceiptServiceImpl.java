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

import org.springblade.system.entity.Receipt;
import org.springblade.system.vo.ReceiptVO;
import org.springblade.system.mapper.ReceiptMapper;
import org.springblade.system.service.IReceiptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-06-18
 */
@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements IReceiptService {

	@Override
	public IPage<ReceiptVO> selectReceiptPage(IPage<ReceiptVO> page, ReceiptVO receipt) {
		return page.setRecords(baseMapper.selectReceiptPage(page, receipt));
	}

}
