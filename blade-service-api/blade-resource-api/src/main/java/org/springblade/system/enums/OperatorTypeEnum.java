package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 运营商类型
 */
@Getter
public enum OperatorTypeEnum implements IEnum<Integer> {
	placeholder(0,"占位","占位",-1),
	YD(1, "移动","中国移动",2),
	LT(2, "联通","中国联通",4),
	DX(3, "电信","中国电信",8),
	YDLTDX(4, "三网","三网",-1),
	;

	private OperatorTypeEnum(int code, String descp ,String fullName,int operator) {
		this.code = code;
		this.descp = descp;
		this.fullName = fullName;
		this.operator = operator;
	}

	@JsonValue
	@EnumValue
	private final int code;
	private final String descp;
	private final String fullName;
	private final int operator;


	@Override
	public Integer getValue() {
		return code;
	}
}
