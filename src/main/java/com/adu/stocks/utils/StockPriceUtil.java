package com.adu.stocks.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.adu.stocks.model.Stock;

/**
 * 网上爬取股价工具
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:55:48
 */
public class StockPriceUtil {
	private static final int TIMEOUT = 3000;// 超时时间

	private static final String STOCK_URL_FORMAT = "http://hq.sinajs.cn/list=%s";// 获取股价的链接，支持批量查询

	private static final String REGEX = "var hq_str_(\\w{8})=\".+?,(.*?),(.*?),(.*?),(.*?),(.*?),.+?,(\\d{4}-\\d{2}-\\d{2}),.+?\"";
	private static final Pattern PATTERN = Pattern.compile(REGEX);

	private static final SimpleDateFormat formater = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final Log logger = LogFactory.getLog(StockPriceUtil.class);

	private static String getHttpResult(String url) {
		String res = null;
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(TIMEOUT);

			// 创建GET方法的实例
			GetMethod getMethod = new GetMethod(url);
			getMethod.getParams().setContentCharset("GB2312");

			// 执行getMethod
			httpClient.executeMethod(getMethod);

			InputStream inputStream = getMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = null;
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			res = stringBuffer.toString();
		} catch (ConnectTimeoutException e) {
			logger.error("[ERROR-CONNECTION-TIMEOUT]url=" + url);
		} catch (Exception e) {
			logger.error("[ERROR-getHttpResult]url=" + url, e);
		}
		return res;
	}

	/**
	 * 实时获取公司的股价
	 * 
	 * @param codes
	 * @return
	 */
	public static Map<String, Stock> getStocksPrice(List<String> codes) {
		Map<String, Stock> res = new HashMap<String, Stock>();
		if (codes == null || codes.size() == 0) {
			return res;
		}

		try {
			int index = 0;
			final int MAX_LIMIT = 100;

			while (index < codes.size()) {
				StringBuffer buffer = new StringBuffer();
				for (int i = index; i < index + MAX_LIMIT && i < codes.size(); i++) {
					buffer.append(codes.get(i) + ",");
				}

				// 去掉末尾的逗号
				buffer.setLength(buffer.length() - 1);

				String url = String.format(STOCK_URL_FORMAT, buffer.toString());
				String httpResult = getHttpResult(url);
				while (httpResult == null) {
					httpResult = getHttpResult(url);
					Thread.sleep(10);
				}
				Matcher matcher = PATTERN.matcher(httpResult);

				while (matcher.find()) {
					try {
						String code = matcher.group(1);
						float open = Float.valueOf(matcher.group(2));
						float lastClose = Float.valueOf(matcher.group(3));
						float close = Float.valueOf(matcher.group(4));
						float high = Float.valueOf(matcher.group(5));
						float low = Float.valueOf(matcher.group(6));
						String date = matcher.group(7);

						Stock stock = new Stock(code, new Date(formater.parse(
								date).getTime()), lastClose, open, high, low,
								close);
						res.put(code, stock);
					} catch (Exception e) {
						logger.error("[ERROR-match]httpResult=" + httpResult, e);
					}
				}
				index += MAX_LIMIT;
			}

		} catch (Exception e) {
			logger.error("[ERROR-getHttpResult]", e);
		}
		return res;
	}
}
