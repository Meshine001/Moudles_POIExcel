package com.meshine.entity;

import java.util.Date;

public class DutyTable {
	private Date date;
	private String guys;
	private String sign;
	private String note;
	public DutyTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DutyTable(Date date, String guys, String sign, String note) {
		super();
		this.date = date;
		this.guys = guys;
		this.sign = sign;
		this.note = note;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGuys() {
		return guys;
	}
	public void setGuys(String guys) {
		this.guys = guys;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
