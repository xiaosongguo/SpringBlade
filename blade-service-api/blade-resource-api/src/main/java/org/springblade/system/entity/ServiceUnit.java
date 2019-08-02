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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Blade
 * @since 2019-07-29
 */
@Data
@TableName("CM_SERVICE_UNIT")
@ApiModel(value = "ServiceUnit对象", description = "ServiceUnit对象")
public class ServiceUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("SU_ID")
	private Integer suId;
	@TableField("SYSTEM_NAME")
	private String systemName;
	@TableField("PM_NODE")
	private String pmNode;
	@TableField("SU_NAME")
	private String suName;
	/**
	 * Session(1), Todb(2), Ismg(3), Deliver(4), Httpws(5);
	 */
	@ApiModelProperty(value = "Session(1), Todb(2), Ismg(3), Deliver(4), Httpws(5);")
	@TableField("SU_TYPE")
	private Integer suType;
	@TableField("ENABLED")
	private Integer enabled;
	@TableField("SU_IP")
	private String suIp;
	@TableField("SU_RPC_PORT")
	private Integer suRpcPort;
	@TableField("SU_HTTP_PORT")
	private Integer suHttpPort;
	@TableField("ISMG_IDS")
	private String ismgIds;
	@TableField("ORDER_TO_DB")
	private Integer orderToDb;
	@TableField("AUTO_VERIFY")
	private Integer autoVerify;
	@TableField("CEP_SERVER")
	private Integer cepServer;
	@TableField("SU_CONFIG")
	private String suConfig;


}
