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
	private Timestamp insertTime;

	public Stock(long id, String code, Date date, float lastClose, float open,
			float high, float low, float close, Timestamp insertTime) {
		super();
		this.id = id;
		this.code = code;
		this.date = date;
		this.lastClose = lastClose;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
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

	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", date=" + date
				+ ", lastClose=" + lastClose + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", insertTime="
				+ insertTime + "]";
	}

}
