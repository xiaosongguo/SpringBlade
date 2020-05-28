package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName("EXT_CERTIFICATE")
@KeySequence(value = "SEQ_USER", clazz = Long.class)
@Data
public class Certificate implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId(value = "ID", type = IdType.INPUT)
  private Long id;

  @TableField("NAME")
  private String name;

  @TableField("TYPE")
  private Integer type;
}
