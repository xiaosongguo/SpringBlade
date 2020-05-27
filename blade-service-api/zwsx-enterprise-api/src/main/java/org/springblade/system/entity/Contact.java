package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName("EXT_CONTACT")
@KeySequence(value = "SEQ_USER", clazz = Integer.class)
@Data
public class Contact implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ID", type = IdType.INPUT)
  private Integer id;


  @TableField("USER_ID")
  private Integer userId;


  @TableField("NAME")
  private String name;


  @TableField("STATION")
  private String station;


  @TableField("FIXED_PHONE")
  private String fixedPhone;


  @TableField("MOBILE")
  private Long mobile;


  @TableField("EMAIL")
  private String email;


  @TableField("BLOC_TYPE")
  private Integer blocType;
}
