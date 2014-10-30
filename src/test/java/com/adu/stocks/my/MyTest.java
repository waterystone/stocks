package com.adu.stocks.my;

import java.util.Arrays;
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
import com.adu.stocks.service.StockService;
import com.adu.stocks.utils.CompanyParserUtil;
import com.adu.stocks.utils.StockPriceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
	@Autowired
	private StockService stockService;
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void getStocksPrice() {
		List<String> codes = stockService.getAllCodes();
		Map<String, Stock> res = StockPriceUtil.getStocksPrice(codes);
		for (int i = 0; i < codes.size(); i++) {
			String code = codes.get(i);
			Stock stock = res.get(code);
			if (stock == null) {
				logger.error("[NULL-stock]code=" + code);
			} else {
				logger.debug(stock);
			}
		}
	}

}
