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
import java.time.LocalDateTime;

/**
 * 通道账单表实体类
 *
 * @author Blade
 * @since 2019-07-02
 */
@Data
@TableName("EXT_BILL")
@ApiModel(value = "Bill对象", description = "通道账单表")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    @TableId("ID")
  private Double id;
    /**
     * 网关ID
     */
    @ApiModelProperty(value = "网关ID")
    @TableField("ISMG_ID")
  private Double ismgId;
    /**
     * 账期
     */
    @ApiModelProperty(value = "账期")
    @TableField("BILL_MONTH")
  private LocalDateTime billMonth;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
  private LocalDateTime createTime;
    /**
     * 核实时间
     */
    @ApiModelProperty(value = "核实时间")
    @TableField("AUDIT_TIME")
  private LocalDateTime auditTime;
    /**
     * 核实用户
     */
    @ApiModelProperty(value = "核实用户")
    @TableField("AUDIT_USER")
  private Double auditUser;
    /**
     * 供应商对账资源量
     */
    @ApiModelProperty(value = "供应商对账资源量")
    @TableField("SUPPLIER_AMOUNT")
  private Double supplierAmount;
    /**
     * 供应商对账金额(元)
     */
    @ApiModelProperty(value = "供应商对账金额(元)")
    @TableField("SUPPLIER_MONEY")
  private Double supplierMoney;
    /**
     * 结算资源量
     */
    @ApiModelProperty(value = "结算资源量")
    @TableField("SYS_AMOUNT")
  private Double sysAmount;
    /**
     * 结算金额(元)
     */
    @ApiModelProperty(value = "结算金额(元)")
    @TableField("SYS_MONEY")
  private Double sysMoney;
    /**
     * 账单状态：0待提交；1待核实；2已核实
     */
    @ApiModelProperty(value = "账单状态：0待提交；1待核实；2已核实")
    @TableField("BILL_STATUS")
  private Double billStatus;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
  private String remark;


}
