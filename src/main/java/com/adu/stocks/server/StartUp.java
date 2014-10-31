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
 * @date 2014-10-31 下午5:47:08
 */
public class StartUp {
	private static Log logger = LogFactory.getLog("startup");

	public static void main(String[] args) {
		ApplicationContext context;
		logger.info("main start~");
		try {
			context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			// 摘取今日股票信息并入库
			PullTask pullTask = (PullTask) context.getBean("pullTask");
			pullTask.updateStocksPrice();

			// 统计结果并发送邮件
			EmailTask emailTask = (EmailTask) context.getBean("emailTask");
			emailTask.sendMail();
			logger.info("main end~");
		} catch (Exception e) {
			logger.error("[ERROR-main]", e);
		}
	}

}
