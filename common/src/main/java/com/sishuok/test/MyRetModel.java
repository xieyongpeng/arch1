package com.sishuok.test;

public class MyRetModel {
	
	private int uuid;
	private String name;
	private String desc;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "MyRetModel [uuid=" + uuid + ", name=" + name + ", desc=" + desc
				+ "]";
	}
}
