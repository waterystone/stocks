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

/**
 * 股票相关的DB服务
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:48:36
 */
public class StockService {
	@Autowired
	private StockDao stockDao;
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 从DB取出统计结果
	 * 
	 * @param startDate
	 *            开始时间，形如"2014-10-22"
	 * @param endDate
	 *            结束时间，形如"2014-10-28"
	 * @param riseRange
	 *            浮动基数
	 * @return
	 */
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

	/**
	 * 增加股票价格信息
	 * 
	 * @param stocks
	 * @return
	 */
	public int addStocks(final List<Stock> stocks) {
		int ret = 0;
		try {
			ret = stockDao.addStocks(stocks);
		} catch (Exception e) {
			logger.error("[ERROR-addStocks]", e);
		}
		return ret;
	}

	/**
	 * 获取所有上市公司信息
	 * 
	 * @return
	 */
	public Map<String, Company> getAllCompanies() {
		Map<String, Company> ret = null;
		try {
			ret = stockDao.getAllCompanies();
		} catch (Exception e) {
			logger.error("[ERROR-getAllCompanies]", e);
		}
		return ret;
	}

	/**
	 * 获取所有上市公司代码
	 * 
	 * @return
	 */
	public List<String> getAllCodes() {
		List<String> ret = null;
		try {
			ret = stockDao.getAllCodes();
		} catch (Exception e) {
			logger.error("[ERROR-getAllCodes]", e);
		}
		return ret;
	}

	/**
	 * 增加公司信息(存在则忽略)
	 * 
	 * @param companies
	 * @return
	 */
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
