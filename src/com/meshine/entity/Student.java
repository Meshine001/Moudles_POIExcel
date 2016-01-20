package com.meshine.entity;



public class Student {
	
	private int id;
	private String name;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Student(String name) {
		super();
		this.name = name;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}
