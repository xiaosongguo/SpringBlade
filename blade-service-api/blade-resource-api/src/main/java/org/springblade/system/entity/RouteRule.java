package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springblade.system.enums.BizTypeEnum;
import org.springblade.system.enums.MsisdnTypeEnum;

import java.io.Serializable;

@TableName("CM_ROUTE_RULE")
@ApiModel(value = "RouteRule, description = ")
@KeySequence(value = "SEQ_OBJ_SN", clazz = Integer.class)
@Data
public class RouteRule implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ROUTE_RULE_ID", type = IdType.INPUT)
  private Integer routeRuleId;


  @TableField("PRIORITY")
  private Integer priority;


  @TableField("USER_ID")
  private Integer userId;


  @TableField("SIGN_NAME")
  private String signName;


  @TableField("MSISDN_TYPE")
  private MsisdnTypeEnum msisdnType;


  @TableField("OPERATOR")
  private Integer operator;


  @TableField("BIZ_TYPE")
  private BizTypeEnum bizType;


  @TableField("MSG_CONTENT")
  private String msgContent;


  @TableField("EXPRESSION")
  private String expression;


  @TableField("ROUTE_ID")
  private Integer routeId;


  @TableField("ISMG_ID")
  private Integer ismgId;


  @TableField("AS_USER_ID")
  private Integer asUserId;

  @TableField("LOCATION_ID")
  private Integer locationId;


  @TableField("EXTRA_RULE")
  private Integer extraRule;


  @TableField("ROUTE_TYPE")
  private RouteTypeEnum routeType;
}
