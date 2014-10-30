package com.adu.stocks.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adu.stocks.model.Company;
import com.adu.stocks.model.Stock;
import com.adu.stocks.service.StockService;
import com.adu.stocks.utils.CompanyParserUtil;
import com.adu.stocks.utils.StockPriceUtil;

public class PullTask {
	@Autowired
	private StockService stockService;
	private Log logger = LogFactory.getLog(this.getClass());

	public void updateCompanies() {
		try {
			long t1 = System.currentTimeMillis();
			List<Company> companies = CompanyParserUtil.getCompanyList();
			int addCount = stockService.updateCompanies(companies);
			long t2 = System.currentTimeMillis();

			logger.debug("[update-companies]addCount=" + addCount + ",time="
					+ (t2 - t1) + "ms");
		} catch (Exception e) {
			logger.error("[ERROR-updateCompanies]", e);
		}
	}

	public void updateStocksPrice() {
		try {
			long t1 = System.currentTimeMillis();

			List<String> codes = stockService.getAllCodes();// 从DB获取所有上市公司代码
			Map<String, Stock> stocksPriceMap = StockPriceUtil
					.getStocksPrice(codes);// 在线爬取相应的股价

			List<Stock> stocks = new ArrayList<Stock>();
			List<String> notExistsCodes = new ArrayList<String>();
			for (String code : codes) {
				Stock stock = stocksPriceMap.get(code);
				if (stock == null) {
					notExistsCodes.add(code);
				} else {
					stocks.add(stock);
				}
			}
			if (notExistsCodes.size() != 0) {
				logger.warn("[WARN-NULL-stock]notExistsCodes=" + notExistsCodes);
			}

			int addCount = stockService.addStocks(stocks);// 今天的股价信息加入到DB

			long t2 = System.currentTimeMillis();
			logger.debug("[update-stocks]addCount=" + addCount + ",time="
					+ (t2 - t1) + "ms");
		} catch (Exception e) {
			logger.error("[ERROR-updateCompanies]", e);
		}
	}

}
