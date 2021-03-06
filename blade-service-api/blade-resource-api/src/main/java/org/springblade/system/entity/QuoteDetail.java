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
package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Blade
 * @since 2019-06-10
 */
@Data
@TableName("EXT_QUOTE_DETAIL")
@ApiModel(value = "QuoteDetail对象", description = "QuoteDetail对象")
@KeySequence(value = "SEQ_USER", clazz = Long.class)
public class QuoteDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@TableId(value = "ID", type = IdType.INPUT)
	private Long id;
	/**
	 * 报价单Id
	 */
	@ApiModelProperty(value = "报价单Id")
	@TableField("QUITE_ID")
	private Long quiteId;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	@TableField("TAX_RATE")
	private Double taxRate;
	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价")
	@TableField("UNIT_PRICE")
	private Double unitPrice;
	/**
	 * 附件路径
	 */
	@ApiModelProperty(value = "附件路径")
	@TableField("FILE_PATH")
	private String filePath;
	@ApiModelProperty(value = "物品名称")

	@TableField("CHANNEL_ID")
	private String channelId;
	@ApiModelProperty(value = "物品名称")
	@TableField("ITEM_NAME")
	private String itemName;


}
