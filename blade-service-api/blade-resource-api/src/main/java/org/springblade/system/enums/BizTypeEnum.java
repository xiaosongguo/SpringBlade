package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BizTypeEnum implements IEnum<Integer> {
  marketing(11, "营销"),
  identifying(14, "验证码"),
  notification(16, "行业通知");

  @JsonValue
  @EnumValue
  private final int code;

  private final String descp;

  BizTypeEnum(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  public Integer getValue() {
    return Integer.valueOf(this.code);
  }
}
