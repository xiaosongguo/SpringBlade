package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MsisdnTypeEnum implements IEnum<Integer> {
  VIP(1, "VIP"),
  WHITE(2, "白名单"),
  BLACK(3, "黑名单"),
  NORMAL(4, "普通");

  @JsonValue
  @EnumValue
  private final int code;

  private final String descp;

  MsisdnTypeEnum(int code, String descp) {
    this.code = code;
    this.descp = descp;
  }

  public int getCode() {
    return this.code;
  }

  public String getDescp() {
    return this.descp;
  }

  public Integer getValue() {
    return Integer.valueOf(this.code);
  }
}
