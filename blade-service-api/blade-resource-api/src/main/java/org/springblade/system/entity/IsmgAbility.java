package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("CM_ISMG_ABILITY")
@Data
public class IsmgAbility implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableField("ISMG_ID")
  private Integer ismgId;

  @TableField("USER_LEVEL")
  private Integer userLevel;

  @TableField("BIZ_TYPES")
  private String bizTypes;
}
