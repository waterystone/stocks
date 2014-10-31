package com.adu.stocks.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adu.stocks.dao.StockDao;
import com.adu.stocks.model.Company;
import com.adu.stocks.model.Result;
import com.adu.stocks.model.Stock;

public class StockService {
	@Autowired
	private StockDao stockDao;
	private Log logger = LogFactory.getLog(this.getClass());

	public List<Result> getResult(String startDate, String endDate,
			float riseRange) {
		List<Result> ret = null;
		try {
			ret = stockDao.getResult(startDate, endDate, riseRange);
		} catch (Exception e) {
			logger.error("[ERROR-getResult]startDate=" + startDate
					+ ",endDate=" + endDate + ",riseRange=" + riseRange, e);
		}
		return ret;
	}

	public int addStocks(final List<Stock> stocks) {
		int ret = 0;
		try {
			ret = stockDao.addStocks(stocks);
		} catch (Exception e) {
			logger.error("[ERROR-addStocks]", e);
		}
		return ret;
	}

	public Map<String, Company> getAllCompanies() {
		Map<String, Company> ret = null;
		try {
			ret = stockDao.getAllCompanies();
		} catch (Exception e) {
			logger.error("[ERROR-getAllCompanies]", e);
		}
		return ret;
	}

	public List<String> getAllCodes() {
		List<String> ret = null;
		try {
			ret = stockDao.getAllCodes();
		} catch (Exception e) {
			logger.error("[ERROR-getAllCodes]", e);
		}
		return ret;
	}

	public int updateCompanies(final List<Company> companies) {
		int ret = 0;
		try {
			ret = stockDao.updateCompanies(companies);
		} catch (Exception e) {
			logger.error("[ERROR-getAllCompanies]", e);
		}
		return ret;
	}

}
