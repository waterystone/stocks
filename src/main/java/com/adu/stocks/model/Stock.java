package com.adu.stocks.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 某天的股票价格信息
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:45:06
 */
public class Stock {
	private long id;
	private String code;// 代码
	private Date date;// 时间
	private float lastClose;// 昨天收盘价格
	private float open;// 开盘价格
	private float high;// 今日最高价
	private float low;// 今日最低价
	private float close;// 今日由盘价
	private float rise;// 今日最高涨幅(相对于开盘价)
	private float fall;// 今日最高跌幅(相对于开盘价)
	private float lastRise;// 今日最高涨幅(相对于昨天收盘价)
	private float lastFall;// 今日最高跌幅(相对于昨天收盘价)
	private Timestamp insertTime;

	public Stock(long id, String code, Date date, float lastClose, float open,
			float high, float low, float close, float rise, float fall,
			float lastRise, float lastFall, Timestamp insertTime) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.lastClose = lastClose;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.rise = rise;
		this.fall = fall;
		this.lastRise = lastRise;
		this.lastFall = lastFall;
		this.insertTime = insertTime;
	}

	public Stock(String code, Date date, float lastClose, float open,
			float high, float low, float close) {
		super();
		this.code = code;
		this.date = date;
		this.lastClose = lastClose;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;

		if (open != 0) {
			this.rise = (high - open) * 100 / open;
			this.fall = (low - open) * 100 / open;
		}
		if (lastClose != 0) {
			this.lastRise = (high - lastClose) * 100 / open;
			this.lastFall = (low - lastClose) * 100 / open;
		}
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Date getDate() {
		return date;
	}

	public float getLastClose() {
		return lastClose;
	}

	public float getOpen() {
		return open;
	}

	public float getHigh() {
		return high;
	}

	public float getLow() {
		return low;
	}

	public float getClose() {
		return close;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public float getRise() {
		return rise;
	}

	public float getFall() {
		return fall;
	}

	public float getLastRise() {
		return lastRise;
	}

	public float getLastFall() {
		return lastFall;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", date=" + date
				+ ", lastClose=" + lastClose + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", rise=" + rise
				+ ", fall=" + fall + ", lastRise=" + lastRise + ", lastFall="
				+ lastFall + ", insertTime=" + insertTime + "]";
	}

}
