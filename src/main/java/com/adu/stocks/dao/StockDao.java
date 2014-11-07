package com.adu.stocks.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.adu.stocks.model.Company;
import com.adu.stocks.model.Result;
import com.adu.stocks.model.Stock;

/**
 * DB相关操作
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:38:03
 */
public class StockDao {
	private JdbcTemplate jdbcTemplate;
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
		List<Result> ret = new ArrayList<Result>();
		// SQL语句模板
		final String format = "SELECT statistic.code, companies.name, statistic.count, statistic.avg_rise, statistic.avg_fall, statistic.avg_last_rise, statistic.avg_last_fall "
				+ " FROM "
				+ " (SELECT code, COUNT(*) AS count, AVG(rise) AS avg_rise, AVG(fall) AS avg_fall, AVG(last_rise) AS avg_last_rise, AVG(last_fall) AS avg_last_fall FROM stocks WHERE date BETWEEN '%s' AND '%s' AND last_rise > '%f' GROUP BY code) "
				+ " statistic, companies "
				+ " WHERE statistic.code = companies.code"
				+ " ORDER BY count DESC, avg_last_rise DESC LIMIT 100";

		String querySQL = String.format(format, startDate, endDate, riseRange);

		List<Map<String, Object>> rows = this.jdbcTemplate
				.queryForList(querySQL);

		// 对查询到的每条记录进行解析
		for (Map<String, Object> row : rows) {
			String code = row.get("code").toString();// 代码
			String name = row.get("name").toString();// 公司名称
			int count = ((Long) row.get("count")).intValue();// 开盘-最高价浮动超过基数的天数
			float avgRise = ((Double) row.get("avg_rise")).floatValue();// 平均开盘最高涨幅
			float avgFall = ((Double) row.get("avg_fall")).floatValue();// 平均开盘最高跌幅
			float avgLastRise = ((Double) row.get("avg_last_rise"))
					.floatValue();// 平均昨天收盘最高涨幅
			float avgLastFall = ((Double) row.get("avg_last_fall"))
					.floatValue();// 平均昨天收盘最高跌幅
			Result result = new Result(code, name, count, avgRise, avgFall,
					avgLastRise, avgLastFall);
			ret.add(result);

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
		if (stocks == null || stocks.size() == 0) {
			return ret;
		}

		try {
			// SQL语句模板
			String format = "INSERT IGNORE INTO stocks (code, date, last_close, open, high, low, close, rise, fall, last_rise, last_fall) VALUES %s";

			int index = 0;
			final int MAX_LIMIT = 1000;

			// 分批次写
			while (index < stocks.size()) {
				StringBuffer values = new StringBuffer();
				for (int i = index; i < index + MAX_LIMIT && i < stocks.size(); i++) {
					Stock stock = stocks.get(i);
					// 转换成字符串批量插入
					values.append(String
							.format("('%s','%s','%f','%f','%f','%f','%f','%f','%f','%f','%f'),",
									stock.getCode(), stock.getDate(),
									stock.getLastClose(), stock.getOpen(),
									stock.getHigh(), stock.getLow(),
									stock.getClose(), stock.getRise(),
									stock.getFall(), stock.getLastRise(),
									stock.getLastFall()));
				}

				// 去掉末尾的逗号
				values.setLength(values.length() - 1);

				String updateSql = String.format(format, values);

				ret += this.jdbcTemplate.update(updateSql);
				index += MAX_LIMIT;
			}
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
		Map<String, Company> res = new HashMap<String, Company>();

		// SQL语句模板
		final String querySQL = "SELECT id, code, name, insert_time from companies ";

		List<Map<String, Object>> rows = this.jdbcTemplate
				.queryForList(querySQL);

		// 对查询到的每条记录进行解析
		for (Map<String, Object> row : rows) {
			int id = (Integer) row.get("id");
			String code = row.get("code").toString();
			String name = row.get("name").toString();
			Timestamp insertTime = (Timestamp) row.get("insert_time");

			Company company = new Company(id, code, name, insertTime);
			res.put(code, company);
		}
		return res;
	}

	/**
	 * 获取所有上市公司代码
	 * 
	 * @return
	 */
	public List<String> getAllCodes() {
		List<String> res = new ArrayList<String>();

		// SQL语句模板
		final String querySQL = "SELECT code from companies ";

		List<Map<String, Object>> rows = this.jdbcTemplate
				.queryForList(querySQL);

		// 对查询到的每条记录进行解析
		for (Map<String, Object> row : rows) {
			String code = row.get("code").toString();
			res.add(code);
		}
		return res;
	}

	/**
	 * 增加公司信息(存在则忽略)
	 * 
	 * @param companies
	 * @return
	 */
	public int updateCompanies(final List<Company> companies) {
		int ret = 0;
		if (companies == null || companies.size() == 0) {
			return ret;
		}

		try {
			// SQL语句模板
			String format = "INSERT IGNORE INTO companies (code, name) VALUES %s";

			int index = 0;
			final int MAX_LIMIT = 1000;

			// 分批次写
			while (index < companies.size()) {
				StringBuffer values = new StringBuffer();
				for (int i = index; i < index + MAX_LIMIT
						&& i < companies.size(); i++) {
					Company company = companies.get(i);
					// 转换成字符串批量插入
					values.append(String.format("('%s','%s'),",
							company.getCode(), company.getName()));
				}

				// 去掉末尾的逗号
				values.setLength(values.length() - 1);

				String updateSql = String.format(format, values);

				ret += this.jdbcTemplate.update(updateSql);
				index += MAX_LIMIT;
			}
		} catch (Exception e) {
			logger.error("[ERROR-updateCompanies]", e);
		}
		return ret;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
