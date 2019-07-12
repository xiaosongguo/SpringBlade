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

/**
 * 通道资源表实体类
 *
 * @author Blade
 * @since 2019-05-23
 */
@Data
@TableName("EXT_CHANNEL_RESOURCE")
@ApiModel(value = "ChannelResource对象", description = "通道资源表")
@KeySequence(value = "SEQ_USER", clazz = Long.class)
public class ChannelResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "ID", type = IdType.INPUT)
	private Long id;
	/**
	 * 供应商类型
	 */
	@ApiModelProperty(value = "供应商类型")
	@TableField("SUPPLIER_TYPE")
	private Integer supplierType;
	/**
	 * 供应商名称
	 */
	@ApiModelProperty(value = "供应商ID")
	@TableField("SUPPLIER_ID")
	private Integer supplierId;
	/**
	 * 企业号
	 */
	@ApiModelProperty(value = "企业号")
	@TableField("ENTERPRISE_NUMBER")
	private Long enterpriseNumber;
	/**
	 * 接入号
	 */
	@ApiModelProperty(value = "接入号")
	@TableField("ACCESS_NUMBER")
	private Long accessNumber;
	/**
	 * 连接IP
	 */
	@ApiModelProperty(value = "连接IP")
	@TableField("CONNECT_IP")
	private String connectIp;
	/**
	 * 连接端口
	 */
	@ApiModelProperty(value = "连接端口")
	@TableField("PORT")
	private Integer port;
	/**
	 * 协议类型
	 */
	@ApiModelProperty(value = "协议类型")
	@TableField("PROTOCOL_TYPE")
	private Integer protocolType;
	/**
	 * 协议版本
	 */
	@ApiModelProperty(value = "协议版本")
	@TableField("PROTOCOL_VERSION")
	private String protocolVersion;
	/**
	 * 行业类型
	 */
	@ApiModelProperty(value = "行业类型")
	@TableField("INDUSTRY_TYPE")
	private Integer industryType;
	/**
	 * 是否分省
	 */
	@ApiModelProperty(value = "是否分省")
	@TableField("IS_PROVINCIAL")
	private Integer isProvincial;
	/**
	 * 分省名称
	 */
	@ApiModelProperty(value = "分省名称")
	@TableField("PROVINCE_NAME")
	private String provinceName;
	/**
	 * 最小连接数
	 */
	@ApiModelProperty(value = "最小连接数")
	@TableField("MIN_CONNECT")
	private Long minConnect;
	/**
	 * 第一条短信长度
	 */
	@ApiModelProperty(value = "第一条短信长度")
	@TableField("FIRST_MESSAGE_LEN")
	private Integer firstMessageLen;
	/**
	 * 多条短信长度
	 */
	@ApiModelProperty(value = "多条短信长度")
	@TableField("MULTIPLE_MESSAGE_LEN")
	private Integer multipleMessageLen;
	/**
	 * 签名类型
	 */
	@ApiModelProperty(value = "签名类型")
	@TableField("SIGN_TYPE")
	private Integer signType;
	/**
	 * 资源类型
	 */
	@ApiModelProperty(value = "资源类型")
	@TableField("RESOURCE_TYPE")
	private Integer resourceType;
	/**
	 * 业务类型
	 */
	@ApiModelProperty(value = "业务类型")
	@TableField("BUSINESS_TYPE")
	private Integer businessType;
	/**
	 * 码号属性
	 */
	@ApiModelProperty(value = "码号属性")
	@TableField("CODE_TYPE")
	private Integer codeType;
	/**
	 * 码号位长
	 */
	@ApiModelProperty(value = "码号位长")
	@TableField("CODE_LEN")
	private Integer codeLen;
	/**
	 * 投诉率
	 */
	@ApiModelProperty(value = "投诉率")
	@TableField("COMPLAINT_RATE")
	private Integer complaintRate;
	/**
	 * 服务类型
	 */
	@ApiModelProperty(value = "服务类型")
	@TableField("SERVICE_REMARK")
	private String serviceRemark;
	/**
	 * 通道账号
	 */
	@ApiModelProperty(value = "通道账号")
	@TableField("ACCOUNT")
	private String account;
	/**
	 * 通道密码
	 */
	@ApiModelProperty(value = "通道密码")
	@TableField("PASSWORD")
	private String password;
	/**
	 * 通道总TPS
	 */
	@ApiModelProperty(value = "通道总TPS")
	@TableField("CHANNEL_TPS")
	private Integer channelTps;
	/**
	 * 签名
	 */
	@ApiModelProperty(value = "签名")
	@TableField("SIGNATURE")
	private String signature;
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@TableField("STATUS")
	private Integer status;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	@TableField("REMARK")
	private String remark;
	/**
	 * 网关ID
	 */
	@ApiModelProperty(value = "网关ID")
	@TableField("ISMG_ID")
	private Long ismgId;


}
