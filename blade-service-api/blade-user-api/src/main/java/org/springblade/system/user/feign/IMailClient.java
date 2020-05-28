package org.springblade.system.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("blade-user")
public interface IMailClient {
  public static final String API_PREFIX = "/mail";

  @PostMapping({"/mail/simple"})
  boolean sendSimpleMail(@RequestParam("to") String paramString1, @RequestParam("subject") String paramString2, @RequestParam("content") String paramString3);
}
