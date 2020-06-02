package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "ISMGSend对象", description = "网关发送")
public class ISMGSend implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日期")
	@TableField("VEST_DATE")
	private String vestDate;

	@ApiModelProperty(value = "网关")
	@TableField("ISMG_NAME")
	private String ismgName;

	@ApiModelProperty(value = "总发送量")
	@TableField("SENT_ALL")
	private Integer sentAll;

	@ApiModelProperty(value = "回执成功率")
	@TableField("SUCC_RATE")
	private Integer succRate;

	@ApiModelProperty(value = "回执失败率")
	@TableField("ERR_RATE")
	private Integer errRate;

	@ApiModelProperty(value = "移动回执量")
	@TableField("CMCC_SENT")
	private Integer cmccSent;

	@ApiModelProperty(value = "联通回执量")
	@TableField("CU_SENT")
	private Integer cuSent;

	@ApiModelProperty(value = "电信回执量")
	@TableField("CT_SENT")
	private Integer ctSent;

	@ApiModelProperty(value = "移动回执成功量")
	@TableField("CMCC_DELV")
	private Integer cmccDelv;

	@ApiModelProperty(value = "移动回执失败量")
	@TableField("CMCC_UNDELV")
	private Integer cmccUndelv;

	@ApiModelProperty(value = "联通回执成功量")
	@TableField("CU_DELV")
	private Integer cuDelv;

	@ApiModelProperty(value = "联通回执失败量")
	@TableField("CU_UNDELV")
	private Integer cuUndelv;

	@ApiModelProperty(value = "电信回执成功量")
	@TableField("CT_DELV")
	private Integer ctDelv;

	@ApiModelProperty(value = "电信回执失败量")
	@TableField("CT_UNDELV")
	private Integer ctUndelv;

	@ApiModelProperty(value = "本地占比")
	@TableField("LOCAL_PCT")
	private Integer localPct;




}
