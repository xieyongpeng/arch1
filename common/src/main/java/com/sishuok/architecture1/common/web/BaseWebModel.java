package com.sishuok.architecture1.common.web;

public class BaseWebModel {
	//保存查询条件
	private String queryJsonStr = "";
	//默认从第一页开始
	private int nowPage = 1;
	//默认一页显示0条
	private int pageShow = 0;
	public String getQueryJsonStr() {
		return queryJsonStr;
	}
	public void setQueryJsonStr(String queryJsonStr) {
		this.queryJsonStr = queryJsonStr;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageShow() {
		return pageShow;
	}
	public void setPageShow(int pageShow) {
		this.pageShow = pageShow;
	}
	@Override
	public String toString() {
		return "BaseWebModel [queryJsonStr=" + queryJsonStr + ", nowPage="
				+ nowPage + ", pageShow=" + pageShow + "]";
	}
}
