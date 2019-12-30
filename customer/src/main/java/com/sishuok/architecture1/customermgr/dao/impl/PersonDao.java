package com.sishuok.architecture1.customermgr.dao.impl;

import org.springframework.data.domain.Page;

import com.sishuok.architecture1.customermgr.bean.CustomerModel;

public interface PersonDao {
	/** 
	 * 分页查询 
	 * @param pageNum  开始页 
	 * @return 
	 */ 
	public Page<CustomerModel> paginationQuery(Integer pageNum);
}
