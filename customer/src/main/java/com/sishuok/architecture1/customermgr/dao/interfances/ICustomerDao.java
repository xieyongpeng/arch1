package com.sishuok.architecture1.customermgr.dao.interfances;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;

@Repository
public interface ICustomerDao extends IBaseDao<CustomerModel>{
	public CustomerModel getByCustomerId(String customerId);
}
