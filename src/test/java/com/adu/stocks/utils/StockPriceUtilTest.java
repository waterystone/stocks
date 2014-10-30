package com.adu.stocks.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.adu.stocks.model.Stock;

public class StockPriceUtilTest {
	private Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void getStocksPrice() {
		List<String> codes = Arrays.asList("sz000673", "sh600495");
		// List<String> codes = Arrays.asList("sh600495", "sz000673");
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
