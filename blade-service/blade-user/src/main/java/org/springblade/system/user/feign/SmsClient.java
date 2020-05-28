package org.springblade.system.user.feign;

import lombok.AllArgsConstructor;
import org.springblade.system.user.service.ISmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SmsClient implements ISmsClient {

    private ISmsService smsService;

    @PostMapping({"/sms/sendSms"})
    public String sendSms(String mobile, String msgContent) {
        return smsService.sendSms(mobile, msgContent);
    }

}
