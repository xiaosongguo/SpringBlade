package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户签名实体类
 *
 * @author Blade
 * @since 2020-05-26
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserSign对象", description = "用户签名")
public class UserSign implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 签名
	 */
	@ApiModelProperty(value = "签名")
	@TableField("SIGN_NAME")
	private String signName;

	/**
	 * 条数
	 */
	@ApiModelProperty(value = "条数")
	@TableField("TOTAL")
	private Integer total;

}
