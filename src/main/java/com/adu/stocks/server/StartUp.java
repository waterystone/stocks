package com.adu.stocks.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adu.stocks.task.EmailTask;
import com.adu.stocks.task.PullTask;

/**
 * 启动主程序
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午4:48:22
 */
public class StartUp {
	private static Log logger = LogFactory.getLog("startup");

	public static void main(String[] args) {
		ApplicationContext context;
		logger.info("main start~");
		try {
			context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			// 启动consumer，分析视频广告的日志数据
			PullTask pullTask = (PullTask) context.getBean("pullTask");
			pullTask.updateStocksPrice();

			EmailTask emailTask = (EmailTask) context.getBean("emailTask");
			emailTask.sendMail();
			logger.info("main end~");
		} catch (Exception e) {
			logger.error("[ERROR-main]", e);
		}
	}

}
