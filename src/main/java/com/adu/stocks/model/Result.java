package com.adu.stocks.model;

public class Result {
	private String code;
	private String name;
	private int count;
	private float avgRise;
	private float avgFall;
	private float avgLastRise;
	private Float avgLastFall;

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
