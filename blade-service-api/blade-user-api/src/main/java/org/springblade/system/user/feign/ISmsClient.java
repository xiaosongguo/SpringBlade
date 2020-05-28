package org.springblade.system.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("blade-user")
public interface ISmsClient {
  public static final String API_PREFIX = "/sms";

  @PostMapping({"/sms/sendSms"})
  String sendSms(@RequestParam("mobile") String paramString1, @RequestParam("msgContent") String paramString2);
}
