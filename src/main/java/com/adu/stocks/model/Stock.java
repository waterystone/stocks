package com.adu.stocks.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Stock {
	private long id;
	private String code;
	private Date date;
	private float lastClose;
	private float open;
	private float high;
	private float low;
	private float close;
	private float rise;
	private float fall;
	private float lastRise;
	private float lastFall;
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
