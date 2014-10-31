package com.adu.stocks.model;

import java.sql.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class StockTest {
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void test() {
		String code = "sh600495";
		Date date = new Date(System.currentTimeMillis());
		float lastClose = 20.50f;
		float open = 20.22f;
		float high = 20.65f;
		float low = 19.88f;
		float close = 20.18f;

		Stock stock = new Stock(code, date, lastClose, open, high, low, close);
		logger.debug("stock=" + stock);
	}
}
