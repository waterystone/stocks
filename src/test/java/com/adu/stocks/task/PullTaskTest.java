package com.adu.stocks.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/adu/stocks/task/task.xml")
public class PullTaskTest {
	@Autowired
	private PullTask pullTask;
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void updateStocksPrice() {
		pullTask.updateStocksPrice();
	}
}
