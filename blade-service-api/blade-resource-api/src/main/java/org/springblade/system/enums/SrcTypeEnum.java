package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 码号类型枚举
 */
@Getter
public enum SrcTypeEnum implements IEnum<Integer> {
	placeholder(0,"占位"),
	SINGLE(1, "单网码号"),
	TRIPLE(2, "三网合一"),
	;
	private SrcTypeEnum(int code, String descp) {
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
