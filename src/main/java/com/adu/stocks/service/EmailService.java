package com.adu.stocks.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件服务
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:48:25
 */
public class EmailService {
	private JavaMailSender javaMailSender;
	private String systemEmail;
	private Log logger = LogFactory.getLog(this.getClass());

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	/**
	 * 发邮件
	 * 
	 * @param to
	 *            收件人
	 * @param subject
	 *            主题
	 * @param htmlText
	 *            html格式内容
	 */
	public void sendMail(String to, String subject, String htmlText) {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, "utf-8");

			msgHelper.setFrom(systemEmail);
			// 收件人
			msgHelper.setTo(to);
			// 主题
			msgHelper.setSubject(subject);
			// 内容,true表示html格式
			msgHelper.setText(htmlText, true);
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			logger.error("[ERROR-sendMail]", e);
		}

	}
}
