package com.sishuok.architecture1.customermgr.dao.interfances;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;

public interface ICustomerMongoDao extends IBaseDao<CustomerModel>{
	public List<CustomerModel> getByIds(String ids);
	
	public Page<CustomerModel> getConditionPage(CustomerModel model,Integer pageNum,Integer pageSize);
}
