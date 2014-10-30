package com.adu.stocks.model;

import java.sql.Timestamp;

public class Company {
	private int id;
	private String code;
	private String name;
	private Timestamp insertTime;

	public Company(int id, String code, String name, Timestamp insertTime) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.insertTime = insertTime;
	}

	public Company(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", code=" + code + ", name=" + name
				+ ", insertTime=" + insertTime + "]";
	}

}
