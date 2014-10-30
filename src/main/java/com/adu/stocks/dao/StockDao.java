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
import com.adu.stocks.model.Stock;

public class StockDao {
	private JdbcTemplate jdbcTemplate;
	private Log logger = LogFactory.getLog(this.getClass());

	public int addStocks(final List<Stock> stocks) {
		int ret = 0;
		if (stocks == null || stocks.size() == 0) {
			return ret;
		}

		try {
			// SQL语句模板
			String format = "INSERT IGNORE INTO stocks (code, date, last_close, open, high, low, close) VALUES %s";

			int index = 0;
			final int MAX_LIMIT = 1000;

			// 分批次写
			while (index < stocks.size()) {
				StringBuffer values = new StringBuffer();
				for (int i = index; i < index + MAX_LIMIT && i < stocks.size(); i++) {
					Stock stock = stocks.get(i);
					// 转换成字符串批量插入
					values.append(String.format(
							"('%s','%s','%f','%f','%f','%f','%f'),",
							stock.getCode(), stock.getDate(),
							stock.getLastClose(), stock.getOpen(),
							stock.getHigh(), stock.getLow(), stock.getClose()));
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