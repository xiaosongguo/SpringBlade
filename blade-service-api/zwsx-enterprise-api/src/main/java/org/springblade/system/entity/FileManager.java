package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName("EXT_FILE_MANAGER")
@KeySequence(value = "SEQ_USER", clazz = Integer.class)
@Data
public class FileManager implements Serializable {
  private static final long serialVersionUID = 1L;


  @TableId(value = "ID", type = IdType.INPUT)
  private Integer id;


  @TableField("PATH")
  private String path;


  @TableField("NAME")
  private String name;


  @TableField("STATUS")
  private Integer status;


  @TableField("FILE_TYPE")
  private Integer fileType;


  @TableField("USER_ID")
  private Integer userId;


  @TableField("OWNER_ID")
  private Integer ownerId;
}
