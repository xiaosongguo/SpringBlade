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
package org.springblade.system.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Quote;
import org.springblade.system.entity.QuoteDetail;
import org.springblade.system.feign.IDictClient;
import org.springblade.system.service.IQuoteDetailService;
import org.springblade.system.vo.QuoteVO;

import java.util.List;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Blade
 * @since 2019-06-10
 */
@AllArgsConstructor
public class QuoteWrapper extends BaseEntityWrapper<Quote, QuoteVO>  {

	private IDictClient dictClient;
	private IQuoteDetailService quoteDetailService;

	@Override
	public QuoteVO entityVO(Quote quote) {
		QuoteVO quoteVO = BeanUtil.copy(quote, QuoteVO.class);
		quoteVO.setSupplierId(SecureUtil.getUserId());
		/*R<String> dict = dictClient.getValue("quote" , quoteVO.getCategory());
		if (dict.isSuccess()) {
			String categoryName = dict.getData();
			quoteVO.setCategoryName(categoryName);
		}*/
		QuoteDetail quoteDetail = new QuoteDetail();
		quoteDetail.setQuiteId(quoteVO.getId());
		List<QuoteDetail> quoteDetails = quoteDetailService.list(Condition.getQueryWrapper(quoteDetail));
		quoteVO.setQuoteDetails(quoteDetails);
		return quoteVO;
	}

}
