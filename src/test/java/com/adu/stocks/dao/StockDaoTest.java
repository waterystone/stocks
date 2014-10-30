package com.adu.stocks.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adu.stocks.model.Company;
import com.adu.stocks.model.Stock;
import com.adu.stocks.utils.CompanyParserUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/adu/stocks/dao/dao.xml")
public class StockDaoTest {
	@Autowired
	private StockDao stockDao;
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void addStocks() {
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(new Stock("sh600495", new Date(24, 10, 30), 20.50f, 20.02f,
				20.65f, 19.76f, 20.08f));
		stocks.add(new Stock("sz000673", new Date(24, 10, 30), 18.23f, 18.23f,
				18.60f, 18.09f, 18.45f));
		int ret = stockDao.addStocks(stocks);
		logger.debug("ret=" + ret);

	}

	@Test
	public void getAllCompanies() {
		Map<String, Company> res = stockDao.getAllCompanies();
		for (Company company : res.values()) {
			logger.debug(company);
		}
	}

	@Test
	public void updateCompanies() {
		List<Company> companies = new ArrayList<Company>();
		Company company1 = new Company("sh600495", "晋西车轴");
		Company company2 = new Company("sz000673", "当代东方");
		companies.add(company1);
		companies.add(company2);
		int ret = stockDao.updateCompanies(companies);
		logger.debug("ret=" + ret);

	}

	@Test
	public void updateOnlineCompanies() {
		List<Company> companies = CompanyParserUtil.getCompanyList();

		companies = CompanyParserUtil.getCompanyList();
		int ret = stockDao.updateCompanies(companies);
		logger.debug("ret=" + ret);

	}

}
