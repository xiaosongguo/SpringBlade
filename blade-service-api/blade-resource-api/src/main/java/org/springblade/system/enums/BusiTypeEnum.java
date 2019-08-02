package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 业务类型枚举
 */
@Getter
public enum BusiTypeEnum implements IEnum<Integer> {
	placeholder(0,"占位"),
	SMS(1, "短信"),
	MMS(2, "彩信"),
	VOICE(3, "语音"),
	;
	private BusiTypeEnum(int code, String descp) {
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
