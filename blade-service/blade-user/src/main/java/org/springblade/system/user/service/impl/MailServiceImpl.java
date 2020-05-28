package org.springblade.system.user.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.system.user.service.IMailService;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailServiceImpl implements IMailService {

    private JavaMailSender mailSender;
    Environment environment;

    public boolean sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.environment.getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            this.mailSender.send(message);
            log.info("简单邮件已经发送。");
            return true;
        } catch (Exception var6) {
            log.error("发送简单邮件时发生异常！", var6);
            return false;
        }
    }

}
