package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("CM_USER")
@ApiModel(value = "CmUser, description = ")
@Data
public class CmUser implements Serializable {
	private static final long serialVersionUID = 1L;


	@TableId("USER_ID")
	private Long userId;


	@TableField("USER_NAME")
	private String userName;


	@TableField("LOGIN_NAME")
	private String loginName;


	@TableField("PASSWORD")
	private String password;

	@TableField("ACCOUNT_STATUS")
	private Integer accountStatus;

	@TableField("ACCOUNT_TYPE")
	private Integer accountType;


	@TableField("PROVINCE")
	private String province;


	@TableField("CITY")
	private String city;


	@TableField("COMPANY")
	private String company;


	@TableField("DEPARTMENT")
	private String department;


	@TableField("LAST_LOGIN_TIME")
	private LocalDateTime lastLoginTime;


	@TableField("LAST_LOGIN_IP")
	private String lastLoginIp;


	@TableField("CREATER_ID")
	private Long createrId;


	@TableField("CREATE_TIME")
	private LocalDateTime createTime;


	@TableField("CREATER_NAME")
	private String createrName;


	@TableField("MOBILE_PHONE")
	private String mobilePhone;


	@TableField("EMAIL")
	private String email;


	@TableField("PRIVILEGE")
	private String privilege;


	@TableField("ISMG_ID")
	private Double ismgId;


	@TableField("BLACK_LEVEL_MAX")
	private Double blackLevelMax;


	@TableField("SUBMIT_INTERVAL")
	private Double submitInterval;


	@TableField("SUBMIT_SAME_DAY")
	private Double submitSameDay;


	@TableField("BILLING_TYPE")
	private Double billingType;


	@TableField("MAX_ORDER_CNT")
	private Double maxOrderCnt;


	@TableField("FORCE_IP")
	private String forceIp;


	@TableField("SPEED")
	private Double speed;

	@TableField("SGIP_DELIVER")
	private String sgipDeliver;


	@TableField("DOMAIN")
	private String domain;


	@TableField("LOGIN_NAME2")
	private String loginName2;


	@TableField("SIGNTEST")
	private Double signtest;

	@TableField("HTTP_DELIVER")
	private String httpDeliver;


	@TableField("SUBMIT_LIMIT")
	private Double submitLimit;


	@TableField("TEMPLATE_TYPE_BITWISE")
	private Double templateTypeBitwise;


	@TableField("BAD_WORD_LEVEL")
	private Double badWordLevel;


	@TableField("USER_TYPE")
	private Double userType;


	@TableField("ORDER_RULE_USER_ID")
	private Double orderRuleUserId;


	@TableField("SIGN_FIXED")
	private String signFixed;


	@TableField("BLACK_LEVEL_MAX2")
	private Double blackLevelMax2;


	@TableField("SPEED2")
	private Double speed2;


	@TableField("BAD_WORD_LEVEL2")
	private Double badWordLevel2;

	@TableField("CMPP_LT_DX")
	private Double cmppLtDx;


	@TableField("ORDER_HOUR")
	private String orderHour;

	@TableField("HTTP_DELIVER_DATA_TYPE")
	private Double httpDeliverDataType;

	@TableField("DISABLE_PUBLIC_SMSTEMPLATE")
	private Double disablePublicSmstemplate;

	@TableField("DISABLE_CHECK_SEND")
	private Double disableCheckSend;


	@TableField("SUBMIT_LIMIT2")
	private Double submitLimit2;


	@TableField("BILLING_ACCOUNT")
	private Double billingAccount;

	@TableField("PARENT_SUBMIT_RULE")
	private Double parentSubmitRule;


	@TableField("TAIL_FIXED")
	private String tailFixed;

	@TableField("VERSION_SN")
	private Double versionSn;


	@TableField("SPEED_EXCEED_TO_READY")
	private Double speedExceedToReady;


	@TableField("KEEP_ORIGINAL_SRC_ID")
	private Double keepOriginalSrcId;


	@TableField("SIGN_AUTO_TEMPLATE")
	private Double signAutoTemplate;


	@TableField("SRCID_EX_ISMG_ID")
	private Double srcidExIsmgId;


	@TableField("SRCID_EX_LTRIM")
	private Double srcidExLtrim;


	@TableField("ORDER_ON_TIME")
	private Double orderOnTime;

	@TableField("Z2A")
	private Integer z2a;

	@TableField("PM_NODE")
	private String pmNode;

}
