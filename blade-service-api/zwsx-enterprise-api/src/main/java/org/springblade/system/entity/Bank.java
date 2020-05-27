package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName("EXT_BANK")
@KeySequence(value = "SEQ_USER", clazz = Integer.class)
@Data
public class Bank implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ID", type = IdType.INPUT)
  private Integer id;


  @TableField("USER_ID")
  private Integer userId;


  @TableField("LOCATION")
  private Integer location;


  @TableField("RECEIVER")
  private String receiver;


  @TableField("PROVINCE")
  private Integer province;


  @TableField("CITY")
  private Integer city;


  @TableField("ADDRESS")
  private String address;


  @TableField("BILL_TYPE")
  private Integer billType;


  @TableField("BRANCH_NAME")
  private String branchName;


  @TableField("NAME")
  private String name;


  @TableField("SWIFT_CODE")
  private String swiftCode;


  @TableField("ACCOUNT")
  private String account;


  @TableField("FILE_ID")
  private Long fileId;
}
