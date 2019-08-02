package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 码号类型枚举
 */
@Getter
public enum ProtocolTypeEnum implements IEnum<Integer> {
	placeholder(0,"占位"),
	CMPP(1, "CMPP"),
	SGIP(2, "SGIP"),
	SMGP(3, "SMGP"),
	;
	private ProtocolTypeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

	@JsonValue
	@EnumValue
	private final int code;
	private final String descp;

	@Override
	public Integer getValue() {
		return code;
	}
}
