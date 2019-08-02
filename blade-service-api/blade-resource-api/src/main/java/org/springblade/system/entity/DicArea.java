/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * snapshot table for snapshot SMSMAN.DIC_AREA实体类
 *
 * @author Blade
 * @since 2019-07-23
 */
@Data
@TableName("DIC_AREA")
@ApiModel(value = "DicArea对象", description = "snapshot table for snapshot SMSMAN.DIC_AREA")
public class DicArea implements Serializable {

    private static final long serialVersionUID = 1L;

  @TableField("PROVINCE")
  private String province;
  @TableField("CITY")
  private String city;
  @TableField("AC")
  private String ac;


}
