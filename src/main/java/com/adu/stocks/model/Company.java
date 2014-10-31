package com.adu.stocks.model;

import java.sql.Timestamp;

/**
 * 公司信息
 * 
 * @author yunjiedu
 * @email yunjiedu@sohu-inc.com
 * @date 2014-10-31 下午5:43:19
 */
public class Company {
	private int id;// 库里ID
	private String code;// 代码
	private String name;// 名称
	private Timestamp insertTime;// 入库时间

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
