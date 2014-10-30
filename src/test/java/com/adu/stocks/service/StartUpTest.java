package com.adu.stocks.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StartUpTest {
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void start() throws Exception {
		Thread.sleep(10000000);
		logger.debug("end~");
	}
}
