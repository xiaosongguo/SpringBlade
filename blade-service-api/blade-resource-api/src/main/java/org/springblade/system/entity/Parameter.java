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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统参数表实体类
 *
 * @author Blade
 * @since 2019-07-24
 */
@Data
@TableName("CM_PARAMETER")
@ApiModel(value = "Parameter对象", description = "系统参数表")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    @TableId("PAR_NAME")
  private String parName;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    @TableField("PAR_VALUE")
  private Double parValue;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @TableField("IS_ENABLED")
  private Integer isEnabled;
    /**
     * 注释
     */
    @ApiModelProperty(value = "注释")
    @TableField("COMMENTS")
  private String comments;


}
