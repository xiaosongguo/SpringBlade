package org.springblade.system.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("EXT_ENTERPRISE")
@KeySequence(value = "SEQ_USER", clazz = Integer.class)
@Data
public class Enterprise implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ID", type = IdType.INPUT)
  private Integer id;


  @TableField("USER_ID")
  private Integer userId;


  @TableField("LICENSE_NUM")
  @NotBlank
  private String licenseNum;


  @TableField("LAST_ANNUAL_TIME")
  private LocalDateTime lastAnnualTime;


  @TableField("CREATE_TIME")
  private LocalDateTime createTime;


  @TableField("REGEDIT_AMOUNT")
  @Min(100L)
  private Double regeditAmount;


  @TableField("REGEDIT_AMOUNT_UNIT")
  @Min(0L)
  private Integer regeditAmountUnit;


  @TableField("LAST_SALE_AMOUNT")
  @Min(100L)
  private Double lastSaleAmount;


  @TableField("LAST_SALE_AMOUNT_UNIT")
  @Min(0L)
  private Integer lastSaleAmountUnit;


  @TableField("OFFICE_PROVINCE")
  @Min(0L)
  private Integer officeProvince;


  @TableField("OFFICE_CITY")
  @Min(0L)
  private Integer officeCity;


  @TableField("OFFICE_ADDRESS")
  @NotBlank
  private String officeAddress;


  @TableField("STAFF_COUNT")
  @Min(0L)
  private Integer staffCount;


  @TableField("ENTERPRISE_TYPE")
  @Min(0L)
  private Integer enterpriseType;


  @TableField("BUSINESS_SCOPE")
  @NotBlank
  private String businessScope;


  @TableField("LEGAL_PERSON")
  @NotBlank
  private String legalPerson;


  @TableField("STATUS")
  @Min(0L)
  private Integer status;


  @TableField("NAME")
  @NotBlank
  private String name;
}
