package org.springblade.system.user.service;

public interface IMailService {
	boolean sendSimpleMail(String to, String subject, String content);
}
