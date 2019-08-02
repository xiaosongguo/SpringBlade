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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由详表实体类
 *
 * @author Blade
 * @since 2019-07-24
 */
@Data
@TableName("CM_ROUTE_ISMG")
@ApiModel(value = "RouteIsmg对象", description = "路由详表")
public class RouteIsmg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 路由ID
	 */
	@ApiModelProperty(value = "路由ID")
	@TableId(value = "ROUTE_ID",type = IdType.INPUT)
	private Integer routeId;
	/**
	 * 网关ID
	 */
	@ApiModelProperty(value = "网关ID")
	@TableField("ISMG_ID")
	private Integer ismgId;
	/**
	 * 运营商
	 */
	@ApiModelProperty(value = "运营商")
	@TableField("OPERATOR")
	private Integer operator;
	/**
	 * 匹配优先级
	 */
	@ApiModelProperty(value = "匹配优先级")
	@TableField("PRIORITY")
	private Integer priority;
	/**
	 * 发送失败自动切换 0：不重发1：全部重发2：发送失败重发3：回执失败重发
	 */
	@ApiModelProperty(value = "发送失败自动切换 0：不重发1：全部重发2：发送失败重发3：回执失败重发")
	@TableField("AUTO_CHANGE")
	private Integer autoChange;
	/**
	 * 分配百分比
	 */
	@ApiModelProperty(value = "分配百分比")
	@TableField("RATIO")
	private Double ratio;
	/**
	 * 按回执重发1是0否关联cm_receipt_resend
	 */
	@ApiModelProperty(value = "按回执重发1是0否关联cm_receipt_resend")
	@TableField("RECEIPT_RESEND")
	private Integer receiptResend;


}
