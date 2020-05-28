package org.springblade.system.user.service;

public interface ISmsService {
    String sendSms(String mobile, String msgContent);
}
