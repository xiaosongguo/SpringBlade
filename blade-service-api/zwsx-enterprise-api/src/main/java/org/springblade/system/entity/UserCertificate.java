package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("EXT_USER_CERTIFICATE")
@Data
@KeySequence(value = "SEQ_USER", clazz = Integer.class)
public class UserCertificate implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ID", type = IdType.INPUT)
  private Integer id;


  @TableField("CERTIFICATE_ID")
  @Min(0L)
  private Integer certificateId;


  @TableField("USER_ID")
  private Integer userId;

  @TableField("CODE")
  private String code;

  @TableField("BEGIN_TIME")
  private LocalDateTime beginTime;

  @TableField("END_TIME")
  private LocalDateTime endTime;

  @TableField("LONG_TERM")
  private Integer longTerm;
}
