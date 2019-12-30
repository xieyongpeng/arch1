package com.sishuok.architecture1.customermgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;
import com.sishuok.architecture1.customermgr.dao.interfances.ICustomerDao;
import com.sishuok.architecture1.customermgr.service.interfances.ICustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel> implements ICustomerService{
	
	private ICustomerDao dao = null;
	@Autowired
	private void setDao(ICustomerDao dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public CustomerModel getByCustomerId(String customerId) {
		return dao.getByCustomerId(customerId);
	}
}
