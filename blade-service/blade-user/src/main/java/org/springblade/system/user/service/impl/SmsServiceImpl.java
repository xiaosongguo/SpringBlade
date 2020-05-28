package org.springblade.system.user.service.impl;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.OkHttpUtil;
import org.springblade.system.user.config.ZwsxSmsProperties;
import org.springblade.system.user.service.ISmsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

;

@Service
@AllArgsConstructor
public class SmsServiceImpl implements ISmsService {

    private ZwsxSmsProperties smsProperties;

    public String sendSms(String mobile, String msgContent) {
        Map<String, String> params = new HashMap();
        params.put("apName", this.smsProperties.getApName());
        params.put("apPassword", this.smsProperties.getApPassword());
        params.put("calledNumber", mobile);
        params.put("content", msgContent);
        return OkHttpUtil.post(this.smsProperties.getUrl(), params);
    }

}
