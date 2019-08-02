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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 网关表实体类
 *
 * @author Blade
 * @since 2019-06-28
 */
@Data
@TableName("CM_ISMG")
@ApiModel(value = "Ismg对象", description = "网关表")
@KeySequence(value = "SEQ_CM_ISMG", clazz = Integer.class)
public class Ismg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 网关ID
	 */
	@ApiModelProperty(value = "网关ID")
	@TableId(value = "ISMG_ID",type = IdType.INPUT)
	private Integer ismgId;
	/**
	 * 网关名称
	 */
	@ApiModelProperty(value = "网关名称")
	@TableField("ISMG_NAME")
	private String ismgName;
	/**
	 * 协议
	 */
	@ApiModelProperty(value = "协议")
	@TableField("PROTOCOL")
	private String protocol;
	/**
	 * 网关IP地址
	 */
	@ApiModelProperty(value = "网关IP地址")
	@TableField("IP")
	private String ip;
	/**
	 * 网关连接端口
	 */
	@ApiModelProperty(value = "网关连接端口")
	@TableField("PORT")
	private Integer port;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@TableField("LOGON_ID")
	private String logonId;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	@TableField("LOGON_PWD")
	private String logonPwd;
	/**
	 * 服务id
	 */
	@ApiModelProperty(value = "服务id")
	@TableField("SPID")
	private String spid;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	@TableField("PHONE")
	private String phone;
	/**
	 * 业务代码
	 */
	@ApiModelProperty(value = "业务代码")
	@TableField("BUSICODE")
	private String busicode;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	@TableField("DESCRIBE")
	private String describe;
	/**
	 * 每秒钟限速
	 */
	@ApiModelProperty(value = "每秒钟限速")
	@TableField("SPEED")
	private Integer speed;
	/**
	 * 其他配置信息
	 */
	@ApiModelProperty(value = "其他配置信息")
	@TableField("CONFIG")
	private String config;
	/**
	 * 短信日限量
	 */
	@ApiModelProperty(value = "短信日限量")
	@TableField("SMS_DAY_CAPACITY")
	private Integer smsDayCapacity;
	/**
	 * 短信月限量
	 */
	@ApiModelProperty(value = "短信月限量")
	@TableField("SMS_MONTH_CAPACITY")
	private Integer smsMonthCapacity;
	/**
	 * 短信总限量
	 */
	@ApiModelProperty(value = "短信总限量")
	@TableField("SMS_CAPACITY")
	private Integer smsCapacity;
	/**
	 * 彩信日限量
	 */
	@ApiModelProperty(value = "彩信日限量")
	@TableField("MMS_DAY_CAPACITY")
	private Integer mmsDayCapacity;
	/**
	 * 彩信月限量
	 */
	@ApiModelProperty(value = "彩信月限量")
	@TableField("MMS_MONTH_CAPACITY")
	private Integer mmsMonthCapacity;
	/**
	 * 彩信总限量
	 */
	@ApiModelProperty(value = "彩信总限量")
	@TableField("MMS_CAPACITY")
	private Integer mmsCapacity;
	/**
	 * 是否可用1可用
	 */
	@ApiModelProperty(value = "是否可用1可用")
	@TableField("ENABLE")
	private Integer enable;
	/**
	 * 套餐开始时间
	 */
	@ApiModelProperty(value = "套餐开始时间")
	@TableField("LUNCH_BEGIN_TIME")
	private LocalDateTime lunchBeginTime;
	/**
	 * 套餐结束时间
	 */
	@ApiModelProperty(value = "套餐结束时间")
	@TableField("LUNCH_END_TIME")
	private LocalDateTime lunchEndTime;
	/**
	 * 套餐发送限制
	 */
	@ApiModelProperty(value = "套餐发送限制")
	@TableField("LUNCH_SENT_LIMIT")
	private Integer lunchSentLimit;
	/**
	 * 套餐回执限制
	 */
	@ApiModelProperty(value = "套餐回执限制")
	@TableField("LUNCH_RECEIPT_LIMIT")
	private Integer lunchReceiptLimit;
	/**
	 * 1=禁止发送
	 */
	@ApiModelProperty(value = "1=禁止发送")
	@TableField("FORBID_SEND")
	private Integer forbidSend;
	/**
	 * 允许发送内容
	 */
	@ApiModelProperty(value = "允许发送内容")
	@TableField("ALLOWED_SCOPE")
	private String allowedScope;
	/**
	 * 禁止发送内容
	 */
	@ApiModelProperty(value = "禁止发送内容")
	@TableField("ELIMINATE_SCOPE")
	private String eliminateScope;
	/**
	 * 可发行业黑名单级别范围
	 */
	@ApiModelProperty(value = "可发行业黑名单级别范围")
	@TableField("BLACK_LEVEL")
	private String blackLevel;
	@TableField("COMPLAINT_RATE")
	private Double complaintRate;
	/**
	 * 审核过的内容记录到pm_send_expost
	 */
	@ApiModelProperty(value = "审核过的内容记录到pm_send_expost")
	@TableField("LOG_NO_VERIFY")
	private Integer logNoVerify;
	/**
	 * 启发条数最小
	 */
	@ApiModelProperty(value = "启发条数最小")
	@TableField("MIN_SEND_SIZE")
	private Integer minSendSize;
	/**
	 * 启发条数最大
	 */
	@ApiModelProperty(value = "启发条数最大")
	@TableField("MAX_SEND_SIZE")
	private Integer maxSendSize;
	/**
	 * 可发营销黑名单级别范围
	 */
	@ApiModelProperty(value = "可发营销黑名单级别范围")
	@TableField("BLACK_LEVEL2")
	private String blackLevel2;


}
