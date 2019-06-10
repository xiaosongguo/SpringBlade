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
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author Blade
 * @since 2019-06-10
 */
@Data
@TableName("EXT_QUOTE")
@ApiModel(value = "Quote对象", description = "Quote对象")
public class Quote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableField("ID")
  private Long id;
    /**
     * 供应商Id
     */
    @ApiModelProperty(value = "供应商Id")
    @TableField("SUPPLIER_ID")
  private Long supplierId;
    /**
     * 报价创建时间
     */
    @ApiModelProperty(value = "报价创建时间")
    @TableField("CREATE_TIME")
  private LocalDateTime createTime;
    /**
     * 报价起始时间
     */
    @ApiModelProperty(value = "报价起始时间")
    @TableField("BEGIN_TIME")
  private LocalDateTime beginTime;
    /**
     * 报价截至时间
     */
    @ApiModelProperty(value = "报价截至时间")
    @TableField("END_TIME")
  private LocalDateTime endTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
  private Integer status;
    /**
     * 报价名称
     */
    @ApiModelProperty(value = "报价名称")
    @TableField("TITLE")
  private String title;
    /**
     * 附件路径
     */
    @ApiModelProperty(value = "附件路径")
    @TableField("FILE_PATH")
  private String filePath;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
  private String remark;


}
