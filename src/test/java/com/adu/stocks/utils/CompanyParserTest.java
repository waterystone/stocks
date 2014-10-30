package com.adu.stocks.utils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.adu.stocks.model.Company;
import com.adu.stocks.utils.CompanyParserUtil;

public class CompanyParserTest {
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void getCompanyList() {
		List<Company> res = CompanyParserUtil.getCompanyList();
		for (int i = 0; i < res.size(); i++) {
			logger.debug("[" + (i + 1) + "]" + res.get(i));
		}
	}
}
