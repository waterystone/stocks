package com.adu.stocks.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adu.stocks.model.Result;
import com.adu.stocks.service.EmailService;
import com.adu.stocks.service.StockService;

/**
 * 发邮件任务
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:50:02
 */
public class EmailTask {
	@Autowired
	private StockService stockService;
	@Autowired
	private EmailService emailService;

	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 统计结果，并发邮件
	 */
	public void sendMail() {
		try {
			long t1 = System.currentTimeMillis();

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");// 格式化成DB的Date格式
			Date endDate = new Date();// 结束时间
			Date startDate = new Date(endDate.getTime() - 7l * 24 * 60 * 60
					* 1000);// 开始时间
			String startDateStr = formater.format(startDate);
			String endDateStr = formater.format(endDate);

			float riseRange = 2.0f;// 浮动基数
			String subject = String.format("最近7天(%s~%s)股市报表", startDateStr,
					endDateStr);// 主题
			String htmlResult = getHtmlResult(startDateStr, endDateStr,
					riseRange);// html格式的邮件内容
			String to = "350608693@qq.com";// 收件人

			// 发送
			emailService.sendMail(to, subject, htmlResult);

			long t2 = System.currentTimeMillis();
			logger.debug("[send-email]startDate=" + startDateStr + ",endDate="
					+ endDateStr + ",subject=" + subject + ",time=" + (t2 - t1)
					+ "ms");
		} catch (Exception e) {
			logger.debug("[ERROR-sendMail]", e);
		}
	}

	/**
	 * 获取html格式的统计结果
	 * 
	 * @param startDate
	 * @param endDate
	 * @param riseRange
	 * @return
	 */
	private String getHtmlResult(String startDate, String endDate,
			float riseRange) {
		String ret = null;
		try {
			// 先从DB取出统计结果
			List<Result> results = stockService.getResult(startDate, endDate,
					riseRange);

			// 转化为html的表格样式
			StringBuffer buffer = new StringBuffer();
			buffer.append("<table border=\"1\" align=\"center\">");

			// 表头
			buffer.append("<tr> <th>序号</th> <th>代码</th> <th>公司</th> <th>浮动超过天数</th> <th>平均昨天收盘最高涨幅(%)</th> <th>平均昨天收盘最高跌幅(%)</th> <th>平均开盘最高涨幅(%)</th>  <th>平均开盘最高跌幅(%)</th> </tr>");

			// 每个公司的股票对应一行
			String trFormat = "<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%d</td> <td>%.2f</td>  <td>%.2f</td> <td>%.2f</td> <td>%.2f</td> </tr>";
			int count = 1;
			for (Result result : results) {
				String codeTd = String
						.format("<a href=\"http://finance.sina.com.cn/realstock/company/%s/nc.shtml\">%s</a>",
								result.getCode(), result.getCode());
				String tr = String.format(trFormat, count++, codeTd,
						result.getName(), result.getCount(),
						result.getAvgLastRise(), result.getAvgLastFall(),
						result.getAvgRise(), result.getAvgFall());
				buffer.append(tr);
			}
			buffer.append("</table>");
			ret = buffer.toString();

		} catch (Exception e) {
			logger.error("[ERRROR-getHtmlResult]startDate=" + startDate
					+ ",endDate=" + endDate + ",riseRange+" + riseRange, e);
		}

		return ret;
	}
}
