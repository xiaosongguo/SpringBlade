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
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 签名与端口实体类
 *
 * @author Blade
 * @since 2019-06-28
 */
@Data
@Accessors(chain = true)
@TableName("CM_SIGN_ISMG")
@ApiModel(value = "SignIsmg对象", description = "签名与端口")
@KeySequence(value = "SEQ_OBJ_SN", clazz = Long.class)
public class SignIsmg implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "SIGN_ID",type = IdType.INPUT)
	private Long signId;
	/**
	 * 签名
	 */
	@ApiModelProperty(value = "签名")
	@TableField("SIGN_NAME")
	private String signName;
	/**
	 * 所属网关
	 */
	@ApiModelProperty(value = "所属网关")
	@TableField("ISMG_ID")
	private Long ismgId;
	/**
	 * 发送端口
	 */
	@ApiModelProperty(value = "发送端口")
	@TableField("SRC_ID")
	private String srcId;
	/**
	 * 0停用1可用2测试用3同用户验证主端口截取子端口4同签名固定主端口截取子端口
	 */
	@ApiModelProperty(value = "0停用1可用2测试用3同用户验证主端口截取子端口4同签名固定主端口截取子端口")
	@TableField("IS_ENABLED")
	private Integer isEnabled;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField("CREATE_TIME")
	private LocalDateTime createTime;
	@TableField("FEE_NUMBER")
	private String feeNumber;


}
