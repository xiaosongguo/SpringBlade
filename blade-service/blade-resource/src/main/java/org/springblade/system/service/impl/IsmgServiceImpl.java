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
import org.springblade.system.entity.Ismg;
import org.springblade.system.mapper.IsmgMapper;
import org.springblade.system.service.IIsmgService;
import org.springblade.system.vo.IsmgVO;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springblade.common.constant.CommonConstant.SMSMAN;

/**
 * 网关表 服务实现类
 *
 * @author Blade
 * @since 2019-06-28
 */
@Service
@DS(SMSMAN)
public class IsmgServiceImpl extends ServiceImpl<IsmgMapper, Ismg> implements IIsmgService {

	@Override
	public IPage<IsmgVO> selectIsmgPage(IPage<IsmgVO> page, IsmgVO ismg) {
		return page.setRecords(baseMapper.selectIsmgPage(page, ismg));
	}

	@Override
	public List<Ismg> dic() {
		return baseMapper.dic();
	}

}
