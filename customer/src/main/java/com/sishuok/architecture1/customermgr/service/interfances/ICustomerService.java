package com.sishuok.architecture1.customermgr.service.interfances;

import com.sishuok.architecture1.common.service.interfances.IBaseService;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;

public interface ICustomerService extends IBaseService<CustomerModel>{
	public CustomerModel getByCustomerId(String customerId);
}
