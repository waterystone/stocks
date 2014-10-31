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

public class EmailTask {
	@Autowired
	private StockService stockService;
	@Autowired
	private EmailService emailService;

	private Log logger = LogFactory.getLog(this.getClass());

	public void sendMail() {
		try {
			long t1 = System.currentTimeMillis();

			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = new Date();
			Date startDate = new Date(endDate.getTime() - 7l * 24 * 60 * 60
					* 1000);
			String startDateStr = formater.format(startDate);
			String endDateStr = formater.format(endDate);

			float riseRange = 2.0f;
			String subject = String.format("最近7天(%s~%s)股市报表", startDateStr,
					endDateStr);
			String htmlResult = getHtmlResult(startDateStr, endDateStr,
					riseRange);
			String to = "350608693@qq.com";

			emailService.sendMail(to, subject, htmlResult);

			long t2 = System.currentTimeMillis();
			logger.debug("[send-email]startDate=" + startDateStr + ",endDate="
					+ endDateStr + ",subject=" + subject + ",time=" + (t2 - t1)
					+ "ms");
		} catch (Exception e) {
			logger.debug("[ERROR-sendMail]", e);
		}
	}

	private String getHtmlResult(String startDate, String endDate,
			float riseRange) {
		String ret = null;
		try {
			List<Result> results = stockService.getResult(startDate, endDate,
					riseRange);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<table border=\"1\" align=\"center\">");

			// 表头
			buffer.append("<tr> <th>序号</th> <th>代码</th> <th>公司</th> <th>浮动超过天数</th> <th>平均开盘最高涨幅(%)</th>  <th>平均开盘最高跌幅(%)</th> <th>平均昨天收盘最高涨幅(%)</th> <th>平均昨天收盘最高跌幅(%)</th> </tr>");

			String trFormat = "<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%d</td> <td>%.2f</td>  <td>%.2f</td> <td>%.2f</td> <td>%.2f</td> </tr>";
			int count = 1;
			for (Result result : results) {
				String tr = String.format(trFormat, count++, result.getCode(),
						result.getName(), result.getCount(),
						result.getAvgRise(), result.getAvgFall(),
						result.getAvgLastRise(), result.getAvgLastFall());
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
