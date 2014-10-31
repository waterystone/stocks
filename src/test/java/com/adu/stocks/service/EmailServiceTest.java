package com.adu.stocks.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/adu/stocks/service/service.xml")
public class EmailServiceTest {
	@Autowired
	private EmailService emailService;
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void sendMail() {
		String to = "350608693@qq.com";
		String subject = "email test";
		String htmlText = "hello,world!";
		emailService.sendMail(to, subject, htmlText);
		logger.debug("end~");
	}
}
