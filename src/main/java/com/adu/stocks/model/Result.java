package com.adu.stocks.model;

/**
 * 公司的股票报表信息
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:43:52
 */
public class Result {
	private String code;// 代码
	private String name;// 公司名称
	private int count;// 开盘-最高价浮动超过基数的天数
	private float avgRise;// 平均开盘最高涨幅
	private float avgFall;// 平均开盘最高跌幅
	private float avgLastRise;// 平均昨天收盘最高涨幅
	private Float avgLastFall;// 平均昨天收盘最高跌幅

	public Result(String code, String name, int count, float avgRise,
			float avgFall, float avgLastRise, Float avgLastFall) {
		super();
		this.code = code;
		this.name = name;
		this.count = count;
		this.avgRise = avgRise;
		this.avgFall = avgFall;
		this.avgLastRise = avgLastRise;
		this.avgLastFall = avgLastFall;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public float getAvgRise() {
		return avgRise;
	}

	public float getAvgFall() {
		return avgFall;
	}

	public float getAvgLastRise() {
		return avgLastRise;
	}

	public Float getAvgLastFall() {
		return avgLastFall;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", name=" + name + ", count=" + count
				+ ", avgRise=" + avgRise + ", avgFall=" + avgFall
				+ ", avgLastRise=" + avgLastRise + ", avgLastFall="
				+ avgLastFall + "]";
	}

}
