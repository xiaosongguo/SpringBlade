package org.springblade.system.strategy;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springblade.system.style.MultiLineToStringStyle;

@Setter
@Getter
public class IsmgConfig {

	//Sp接入号
	private String sgipSpno ;
	//Sp接入接点编码
	private Long sgipNodeid ;
	//Sp接入企业代码
	private String sgipSpid ;
	//回执的端口号
	private Integer receiptPort;

	private String serviceType;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, MultiLineToStringStyle.MULTI_LINE_STYLE);
	}

}
