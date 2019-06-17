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
import org.springblade.system.entity.Quote;
import org.springblade.system.entity.QuoteDetail;
import org.springblade.system.mapper.QuoteMapper;
import org.springblade.system.service.IQuoteDetailService;
import org.springblade.system.service.IQuoteService;
import org.springblade.system.vo.QuoteVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2019-06-10
 */
@Service
@AllArgsConstructor
public class QuoteServiceImpl extends ServiceImpl<QuoteMapper, Quote> implements IQuoteService {

	private IQuoteDetailService quoteDetailService;

	@Override
	public IPage<QuoteVO> selectQuotePage(IPage<QuoteVO> page, QuoteVO quote) {
		return page.setRecords(baseMapper.selectQuotePage(page, quote));
	}

	@Override
	public boolean saveQuote(QuoteVO quoteVO) {
		boolean isSucc = super.save(quoteVO);
		List<QuoteDetail> quoteDetails = quoteVO.getQuoteDetails();
		if (quoteDetails.isEmpty()){
			return isSucc;
		}
		quoteDetails.stream().forEach(quoteDetail->quoteDetail.setQuiteId(quoteVO.getId()));
		isSucc = quoteDetailService.saveBatch(quoteVO.getQuoteDetails());
		return isSucc;
	}


}
