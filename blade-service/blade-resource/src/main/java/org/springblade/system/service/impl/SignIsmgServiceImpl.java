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

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.system.entity.SignIsmg;
import org.springblade.system.mapper.SignIsmgMapper;
import org.springblade.system.service.ISignIsmgService;
import org.springblade.system.vo.SignIsmgVO;
import org.springframework.stereotype.Service;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

/**
 * 签名与端口 服务实现类
 *
 * @author Blade
 * @since 2019-06-28
 */
@Service
@DS(SMSMAN)
public class SignIsmgServiceImpl extends ServiceImpl<SignIsmgMapper, SignIsmg> implements ISignIsmgService {

	@Override
	public IPage<SignIsmgVO> selectSignIsmgPage(IPage<SignIsmgVO> page, SignIsmgVO signIsmg) {
		return page.setRecords(baseMapper.selectSignIsmgPage(page, signIsmg));
	}

}
