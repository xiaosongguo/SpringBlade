package org.springblade.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

public enum ResourceTypeEnum implements IEnum<Integer> {
  placeholder(0, "占位"),
  app(1, "行业"),
  marketing(2, "营销");

  @JsonValue
  @EnumValue
  private final int code;

  private final String descp;

  ResourceTypeEnum(int code, String descp) {
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

  public List<BizTypeEnum> getBizType() {
    if (this == app)
      return Arrays.asList(new BizTypeEnum[] { BizTypeEnum.identifying, BizTypeEnum.notification });
    return Arrays.asList(new BizTypeEnum[] { BizTypeEnum.marketing });
  }

  public static void main(String[] args) {
    System.out.println(marketing.getBizType());
  }
}
