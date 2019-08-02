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
 * 通道详表实体类
 *
 * @author Blade
 * @since 2019-07-18
 */
@Data
@TableName("CM_CHANNEL_REGULAR")
@ApiModel(value = "ChannelRegular对象", description = "通道详表")
@KeySequence(value = "SEQ_OBJ_SN", clazz = Integer.class)
public class ChannelRegular implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 通道标识,为长度2-5位不以0开头的数字串,系统生成
	 */
	@ApiModelProperty(value = "通道标识,为长度2-5位不以0开头的数字串,系统生成")
	@TableId(value = "CHANNEL_ID" ,type = IdType.INPUT)
	private Integer channelId;
	/**
	 * 通道名称至少2个字符，最多25个字符 直连通道：可发运营商（如：yd移动）+落地区号（福建0591）+序号（01）+码号后4位 三方通道：可发运营商+通道商名称缩写+行业或营销
	 */
	@ApiModelProperty(value = "通道名称至少2个字符，最多25个字符 直连通道：可发运营商（如：yd移动）+落地区号（福建0591）+序号（01）+码号后4位 三方通道：可发运营商+通道商名称缩写+行业或营销")
	@TableField("CHANNEL_NAME")
	private String channelName;
	/**
	 * 枚举：短信，彩信，语音
	 */
	@ApiModelProperty(value = "枚举：短信，彩信，语音")
	@TableField("CHANNEL_TYPE")
	private String channelType;
	/**
	 * 落地省份,字符
	 */
	@ApiModelProperty(value = "落地省份,字符")
	@TableField("CHANNEL_LOCAL_PROVINCE")
	private String channelLocalProvince;
	/**
	 * 落地运营商,枚举:中国移动，中国联通，中国电信
	 */
	@ApiModelProperty(value = "落地运营商,枚举:中国移动，中国联通，中国电信")
	@TableField("CHANNEL_LOCAL_OPERATOR")
	private String channelLocalOperator;
	/**
	 * 支持运营商，枚举：中国移动，中国联通，中国电信，三网合一
	 */
	@ApiModelProperty(value = "支持运营商，枚举：中国移动，中国联通，中国电信，三网合一")
	@TableField("CHANNEL_SUPPORT_OPERATOR")
	private String channelSupportOperator;
	/**
	 * 发送号码主端口，字符
	 */
	@ApiModelProperty(value = "发送号码主端口，字符")
	@TableField("CHANNEL_SMS_MAIN_NUMBER")
	private String channelSmsMainNumber;
	/**
	 * 申请公司名称，字符
	 */
	@ApiModelProperty(value = "申请公司名称，字符")
	@TableField("CHANNEL_APPLY_COMPANY")
	private String channelApplyCompany;
	/**
	 * 通道性质，枚举：直连通道，三方通道，转租通道，转租回签通道
	 */
	@ApiModelProperty(value = "通道性质，枚举：直连通道，三方通道，转租通道，转租回签通道")
	@TableField("CHANNEL_CHARACTER")
	private String channelCharacter;
	/**
	 * 通道使用者，字符
	 */
	@ApiModelProperty(value = "通道使用者，字符")
	@TableField("CHANNEL_USERS")
	private String channelUsers;
	/**
	 * SPID，数字
	 */
	@ApiModelProperty(value = "SPID，数字")
	@TableField("CHANNEL_SPID")
	private Integer channelSpid;
	/**
	 * 通道状态，枚举：已开通，已接入，运营中，禁用
	 */
	@ApiModelProperty(value = "通道状态，枚举：已开通，已接入，运营中，禁用")
	@TableField("CHANNEL_STATUS")
	private String channelStatus;
	/**
	 * 禁用原因，枚举：不需要，需要报备签名，需要报备模板，价格太高，欠费，停止合作，到达率不高，没有回执，限速，关停
	 */
	@ApiModelProperty(value = "禁用原因，枚举：不需要，需要报备签名，需要报备模板，价格太高，欠费，停止合作，到达率不高，没有回执，限速，关停")
	@TableField("CHANNEL_DIS_REASON")
	private String channelDisReason;
	/**
	 * 开通时间
	 */
	@ApiModelProperty(value = "开通时间")
	@TableField("TIME_TO_INIT")
	private LocalDateTime timeToInit;
	/**
	 * 接入时间
	 */
	@ApiModelProperty(value = "接入时间")
	@TableField("TIME_TO_ACCESS")
	private LocalDateTime timeToAccess;
	/**
	 * 运营时间
	 */
	@ApiModelProperty(value = "运营时间")
	@TableField("TIME_TO_WORK")
	private String timeToWork;
	/**
	 * 禁用时间
	 */
	@ApiModelProperty(value = "禁用时间")
	@TableField("TIME_TO_DISABLE")
	private LocalDateTime timeToDisable;
	/**
	 * 接入负责人,接入通道的本公司负责人员，默认是登录用户
	 */
	@ApiModelProperty(value = "接入负责人,接入通道的本公司负责人员，默认是登录用户")
	@TableField("CONTACT_OF_ACCESS")
	private String contactOfAccess;
	/**
	 * 通道联系人,移动联通的接口人
	 */
	@ApiModelProperty(value = "通道联系人,移动联通的接口人")
	@TableField("CONTACT_OF_OPERATOR")
	private String contactOfOperator;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话")
	@TableField("CONTACT_MOBILE")
	private Double contactMobile;
	/**
	 * 联系邮箱
	 */
	@ApiModelProperty(value = "联系邮箱")
	@TableField("CONTACT_EMAIL")
	private String contactEmail;
	/**
	 * QQ号码
	 */
	@ApiModelProperty(value = "QQ号码")
	@TableField("CONTACT_QQ")
	private Long contactQq;
	/**
	 * 微信号码
	 */
	@ApiModelProperty(value = "微信号码")
	@TableField("CONTACT_WECHAT")
	private String contactWechat;
	/**
	 * 协议类型，枚举：smgp34DC,sgip4DC,cmpp24DC,cmpp3,cmpp,SuperSkyHttp,apsdhttp,channel-xiaomi-msq-http
	 */
	@ApiModelProperty(value = "协议类型，枚举：smgp34DC,sgip4DC,cmpp24DC,cmpp3,cmpp,SuperSkyHttp,apsdhttp,channel-xiaomi-msq-http")
	@TableField("INTER_PROTOCOL")
	private String interProtocol;
	/**
	 * 网关ip地址
	 */
	@ApiModelProperty(value = "网关ip地址")
	@TableField("INTER_IP")
	private String interIp;
	/**
	 * 网关连接端口
	 */
	@ApiModelProperty(value = "网关连接端口")
	@TableField("INTER_PORT")
	private String interPort;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	@TableField("INTER_USER")
	private String interUser;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	@TableField("INTER_PASSWORD")
	private String interPassword;
	/**
	 * 计费方式，枚举：提交计费，成功计费
	 */
	@ApiModelProperty(value = "计费方式，枚举：提交计费，成功计费")
	@TableField("BILL_METHODS")
	private String billMethods;
	/**
	 * 三网通道价格，单位分，单价只能有一位小数
	 */
	@ApiModelProperty(value = "三网通道价格，单位分，单价只能有一位小数")
	@TableField("BILL_PRICE")
	private Double billPrice;
	/**
	 * 付费方式，枚举：预付费，后付费
	 */
	@ApiModelProperty(value = "付费方式，枚举：预付费，后付费")
	@TableField("BILL_TYPE")
	private String billType;
	/**
	 * 价格说明，如阶梯定价
	 */
	@ApiModelProperty(value = "价格说明，如阶梯定价")
	@TableField("BILL_DESCRIPTION")
	private String billDescription;
	/**
	 * 余额
	 */
	@ApiModelProperty(value = "余额")
	@TableField("BILL_BALANCE")
	private Double billBalance;
	/**
	 * 缴费时间
	 */
	@ApiModelProperty(value = "缴费时间")
	@TableField("BILL_TIME")
	private String billTime;
	/**
	 * 投诉百万比，单位%
	 */
	@ApiModelProperty(value = "投诉百万比，单位%")
	@TableField("COMPLAIN_NUMBER_PER_MI")
	private Double complainNumberPerMi;
	/**
	 * 投诉个数，截至最新时间的投诉个数
	 */
	@ApiModelProperty(value = "投诉个数，截至最新时间的投诉个数")
	@TableField("COMPLAIN_NUMBER")
	private Integer complainNumber;
	/**
	 * 是否支持长短信，枚举：支持，不支持
	 */
	@ApiModelProperty(value = "是否支持长短信，枚举：支持，不支持")
	@TableField("LMS_SUPPORT")
	private String lmsSupport;
	/**
	 * 长短信协议头长度，枚举：6，7，单位：字节
	 */
	@ApiModelProperty(value = "长短信协议头长度，枚举：6，7，单位：字节")
	@TableField("LMS_HEAD_SIZE")
	private Double lmsHeadSize;
	/**
	 * 长短信是否都签名，枚举：最后一条，全部
	 */
	@ApiModelProperty(value = "长短信是否都签名，枚举：最后一条，全部")
	@TableField("LMS_SIGN_TYPE")
	private String lmsSignType;
	/**
	 * 是否支持回复，枚举：支持，不支持
	 */
	@ApiModelProperty(value = "是否支持回复，枚举：支持，不支持")
	@TableField("RECE_IS_SUPPORT")
	private String receIsSupport;
	/**
	 * 是否支持状态报告，枚举：支持，不支持
	 */
	@ApiModelProperty(value = "是否支持状态报告，枚举：支持，不支持")
	@TableField("RECE_REPORT_SUPPORT")
	private String receReportSupport;
	/**
	 * 通道签名类型，枚举：平台签名，网关签名
	 */
	@ApiModelProperty(value = "通道签名类型，枚举：平台签名，网关签名")
	@TableField("SIGN_TYPE")
	private String signType;
	/**
	 * 是否抹去通道签名，枚举：抹去，保留
	 */
	@ApiModelProperty(value = "是否抹去通道签名，枚举：抹去，保留")
	@TableField("SIGN_REPLACE")
	private String signReplace;
	/**
	 * 通道签名长度只能为0-70之间的数
	 */
	@ApiModelProperty(value = "通道签名长度只能为0-70之间的数")
	@TableField("SIGN_SIZE")
	private String signSize;
	/**
	 * 是否报备，枚举：否，报备签名，报备模板
	 */
	@ApiModelProperty(value = "是否报备，枚举：否，报备签名，报备模板")
	@TableField("SIGN_WHETHER")
	private String signWhether;
	/**
	 * 子码号长度
	 */
	@ApiModelProperty(value = "子码号长度")
	@TableField("CODE_SIZE")
	private String codeSize;
	/**
	 * 子号码类型，枚举：完全支持，限制性，不支持
	 */
	@ApiModelProperty(value = "子号码类型，枚举：完全支持，限制性，不支持")
	@TableField("CODE_CHILD_TYPE")
	private String codeChildType;
	/**
	 * 可到达省份，枚举：全国，北京|上海|天津|重庆|新疆
	 */
	@ApiModelProperty(value = "可到达省份，枚举：全国，北京|上海|天津|重庆|新疆")
	@TableField("LIMIT_ARRIVE_PROV")
	private String limitArriveProv;
	/**
	 * 屏蔽区域
	 */
	@ApiModelProperty(value = "屏蔽区域")
	@TableField("LIMIT_SHIELD_LOCATION")
	private String limitShieldLocation;
	/**
	 * 是否启用禁发时间，枚举：启用，不启用
	 */
	@ApiModelProperty(value = "是否启用禁发时间，枚举：启用，不启用")
	@TableField("LIMIT_ENABLE_TIME")
	private String limitEnableTime;
	/**
	 * 通道发送时间，如：8:00-20:00
	 */
	@ApiModelProperty(value = "通道发送时间，如：8:00-20:00")
	@TableField("LIMIT_SEND_TIME")
	private String limitSendTime;
	/**
	 * 发送速度，单位条每秒，通道发送速度只能是数字，0表示无限制
	 */
	@ApiModelProperty(value = "发送速度，单位条每秒，通道发送速度只能是数字，0表示无限制")
	@TableField("LIMIT_SEND_SPEED")
	private Integer limitSendSpeed;
	/**
	 * 链路数，取值范围1-3
	 */
	@ApiModelProperty(value = "链路数，取值范围1-3")
	@TableField("LIMIT_LINK_NUM")
	private Integer limitLinkNum;
	/**
	 * 日告警阈值为正整数或0，为0表示不限制
	 */
	@ApiModelProperty(value = "日告警阈值为正整数或0，为0表示不限制")
	@TableField("LIMIT_DAILY_NUM")
	private Integer limitDailyNum;
	/**
	 * 月告警阈值
	 */
	@ApiModelProperty(value = "月告警阈值")
	@TableField("LIMIT_MONTHLY_NUM")
	private Integer limitMonthlyNum;
	/**
	 * 通道号码频控限制
	 */
	@ApiModelProperty(value = "通道号码频控限制")
	@TableField("LIMIT_SEND_FREQUENCY")
	private String limitSendFrequency;
	/**
	 * 内容最大长度为1-1000
	 */
	@ApiModelProperty(value = "内容最大长度为1-1000")
	@TableField("LIMIT_CONTENT_LENGTH")
	private Integer limitContentLength;
	/**
	 * 是否可发行业通知，枚举：是，否
	 */
	@ApiModelProperty(value = "是否可发行业通知，枚举：是，否")
	@TableField("LIMIT_SEND_PROFESSION")
	private String limitSendProfession;
	/**
	 * 是否可发验证码，枚举：是，否
	 */
	@ApiModelProperty(value = "是否可发验证码，枚举：是，否")
	@TableField("LIMIT_SEND_VERIFY")
	private String limitSendVerify;
	/**
	 * 是否可发营销内容，枚举：是，否
	 */
	@ApiModelProperty(value = "是否可发营销内容，枚举：是，否")
	@TableField("LIMIT_SEND_MARKETING")
	private String limitSendMarketing;
	/**
	 * 是否人工审核，枚举：是，否
	 */
	@ApiModelProperty(value = "是否人工审核，枚举：是，否")
	@TableField("LIMIT_PERSONAL_AUDIT")
	private String limitPersonalAudit;
	/**
	 * 关键字限制，如：助考
	 */
	@ApiModelProperty(value = "关键字限制，如：助考")
	@TableField("LIMIT_KEYWORD")
	private String limitKeyword;
	/**
	 * 群发最多号码个数1到100
	 */
	@ApiModelProperty(value = "群发最多号码个数1到100")
	@TableField("LIMIT_MASS_NUMBER")
	private Integer limitMassNumber;
	/**
	 * 黑名单限制
	 */
	@ApiModelProperty(value = "黑名单限制")
	@TableField("LIMIT_BLACK")
	private String limitBlack;
	/**
	 * 备注，字符
	 */
	@ApiModelProperty(value = "备注，字符")
	@TableField("REMARK")
	private String remark;
	/**
	 * 电信价格，单位分，单价只能有一位小数
	 */
	@ApiModelProperty(value = "电信价格，单位分，单价只能有一位小数")
	@TableField("BILL_CT_PRICE")
	private Double billCtPrice;
	/**
	 * 联通价格，单位分，单价只能有一位小数
	 */
	@ApiModelProperty(value = "联通价格，单位分，单价只能有一位小数")
	@TableField("BILL_CU_PRICE")
	private Double billCuPrice;
	/**
	 * 移动价格，单位分，单价只能有一位小数
	 */
	@ApiModelProperty(value = "移动价格，单位分，单价只能有一位小数")
	@TableField("BILL_CM_PRICE")
	private Double billCmPrice;
	/**
	 * 对应网关Id
	 */
	@ApiModelProperty(value = "对应网关Id")
	@TableField("ISMG_ID")
	private Integer ismgId;
	/**
	 * 接入附件保存路径
	 */
	@ApiModelProperty(value = "接入附件保存路径")
	@TableField("INTER_FILENAME")
	private String interFilename;
	/**
	 * 通道运营商名称，对接财务，如中国移动福州分公司，中国移动江苏公司
	 */
	@ApiModelProperty(value = "通道运营商名称，对接财务，如中国移动福州分公司，中国移动江苏公司")
	@TableField("CHANNEL_OPERATOR_NAME")
	private String channelOperatorName;
	@TableField("OPERATOR_BALANCE")
	private Double operatorBalance;


}
