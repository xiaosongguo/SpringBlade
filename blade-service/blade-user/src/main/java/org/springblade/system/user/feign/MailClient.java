package org.springblade.system.user.feign;

import lombok.AllArgsConstructor;
import org.springblade.system.user.service.IMailService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MailClient implements IMailClient {

  private IMailService mailService;

  public boolean sendSimpleMail(String to, String subject, String content) {
    return this.mailService.sendSimpleMail(to, subject, content);
  }
}
