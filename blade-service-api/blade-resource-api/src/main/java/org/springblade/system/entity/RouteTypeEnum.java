package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springblade.system.enums.IEnum;

public enum RouteTypeEnum implements IEnum<Integer> {
  priority(0, "优先级最高"),
  price(1, "价格最低");

  @JsonValue
  @EnumValue
  private final int code;

  private final String descp;

  RouteTypeEnum(int code, String descp) {
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
