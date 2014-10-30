package com.adu.stocks.model;

public class Company {
	private int id;
	private String code;
	private String name;

	public Company(int id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
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

	@Override
	public String toString() {
		return "Company [id=" + id + ", code=" + code + ", name=" + name + "]";
	}

}
