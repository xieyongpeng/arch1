package com.sishuok.architecture1.common.service.impl;

import java.util.List;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.common.service.interfances.IBaseService;


public class BaseServiceImpl<M> implements IBaseService<M> {
	private IBaseDao<M> dao = null;
	
	public void setDAO(IBaseDao<M> dao){
		this.dao = dao;
	}
	
	public void create(M customerModel) {
		dao.create(customerModel);
	}

	public void update(M customerModel) {
		dao.update(customerModel);
	}

	public void delete(int uuid) {
		dao.delete(uuid);
	}

	public M getByUuid(int uuid) {
		return dao.getByUuid(uuid);
	}

	public List<M> getByCondition(M customerModel) {
		return dao.getByCondition(customerModel);
	}

	public List<M> getAll() {
		return dao.getAll();
	}
	
}
