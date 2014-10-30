package com.adu.stocks.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class CompanyTest {
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void test() {
		Company company = new Company("sh600495", "晋西车轴");
		logger.debug("company=" + company);
	}
}
