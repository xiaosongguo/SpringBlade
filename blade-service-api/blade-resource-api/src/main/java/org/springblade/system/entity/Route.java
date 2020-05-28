package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("CM_ROUTE")
@ApiModel(value = "Route, description = ")
@KeySequence(value = "SEQ_OBJ_SN", clazz = Integer.class)
@Data
@NoArgsConstructor
public class Route implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId(value = "ROUTE_ID", type = IdType.INPUT)
  private Integer routeId;

  @TableField("ROUTE_NAME")
  private String routeName;
}

