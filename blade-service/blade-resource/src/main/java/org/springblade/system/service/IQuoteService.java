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
import org.springblade.system.dto.QuoteDTO;
import org.springblade.system.entity.Quote;
import org.springblade.system.vo.QuoteVO;

/**
 *  服务类
 *
 * @author Blade
 * @since 2019-06-10
 */
public interface IQuoteService extends IService<Quote> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param quote
	 * @return
	 */
	IPage<QuoteVO> selectQuotePage(IPage<QuoteVO> page, QuoteVO quote);

	/**
	 * 新增报价
	 * @param quoteDTO
	 * @return
	 */
	boolean saveQuote(QuoteDTO quoteDTO);

	/**
	 * 报价审核
	 * @param quoteDTO
	 * @return
	 */
	boolean review(QuoteDTO quoteDTO);

}
