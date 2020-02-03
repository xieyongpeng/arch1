package com.sishuok.interactive.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装客户端调用传递过来的参数的 model
 */
public class InteractiveModel {
	private String opeType;
	private Map<String,Object> map = new HashMap<String,Object>();
	public String getOpeType() {
		return opeType;
	}
	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "InteractiveModel [opeType=" + opeType + ", map=" + map + "]";
	}
}
