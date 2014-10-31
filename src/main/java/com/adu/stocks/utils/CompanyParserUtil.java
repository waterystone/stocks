package com.adu.stocks.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.adu.stocks.model.Company;

/**
 * 获取最新上市公司列表
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-30 下午1:46:35
 */
public class CompanyParserUtil {
	private static final String COMPANY_LIST_URL = "http://quote.eastmoney.com/stocklist.html";
	private static Log logger = LogFactory.getLog(CompanyParserUtil.class);

	/**
	 * 获取所有上市公司的信息
	 * 
	 * @return
	 */
	public static List<Company> getCompanyList() {
		List<Company> res = new ArrayList<Company>();
		try {
			Parser parser = new Parser(COMPANY_LIST_URL);
			parser.setEncoding("GB2312");

			// <li><a target="_blank"
			// href="http://quote.eastmoney.com/sh201001.html">R007(201001)</a></li>
			NodeFilter filter = new AndFilter(new TagNameFilter("li"),
					new HasChildFilter(new LinkRegexFilter(
							"http://quote.eastmoney.com/(sh|sz)")));
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			for (int i = 0; i < nodeList.size(); i++) {
				try {
					Tag li = (Tag) nodeList.elementAt(i);
					Node a = li.getFirstChild();

					String href = ((Tag) a).getAttribute("href");// http://quote.eastmoney.com/sh201001.html
					String text = a.getFirstChild().getText();// R007(201001)

					String code = href.substring(27, 35);// 股票代号
					if (!code.matches("(sh600|sh601|sz000|sz300|sz002|sz112)\\d{3}")) {
						continue;
					}
					String name = text.substring(0, text.indexOf("("));// 公司名称
					Company company = new Company(code, name);
					res.add(company);
				} catch (Exception e) {
					logger.error("[ERROR-parser]node=" + nodeList.elementAt(i),
							e);
				}
			}
		} catch (Exception e) {
			logger.error("[ERROR-getCompanyList]", e);
		}
		return res;

	}
}
