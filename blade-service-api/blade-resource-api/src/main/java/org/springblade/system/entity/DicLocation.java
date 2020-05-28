package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@TableName("DIC_LOCATION")
@ApiModel(value = "DicLocation, description = ")
@Data
public class DicLocation implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId("LOCATION_ID")
  private Integer locationId;

  @TableField("PROVINCE")
  private String province;

  @TableField("OPERATOR")
  private String operator;
}
