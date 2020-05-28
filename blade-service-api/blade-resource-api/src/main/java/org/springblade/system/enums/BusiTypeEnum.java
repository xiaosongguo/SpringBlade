package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 业务类型枚举
 */
@Getter
public enum BusiTypeEnum implements IEnum<Integer> {
	placeholder(0,"占位","placeholder"),
	SMS(1, "短信","dx"),
	MMS(2, "彩信", "cx"),
	VOICE(3, "语音", "yy"),
	;
	private BusiTypeEnum(int code, String descp, String name) {
        this.code = code;
        this.descp = descp;
		this.name = name;
    }

	@JsonValue
	@EnumValue
	private final int code;
	private final String descp;
	private final String name;

	@Override
	public Integer getValue() {
		return code;
	}
}
