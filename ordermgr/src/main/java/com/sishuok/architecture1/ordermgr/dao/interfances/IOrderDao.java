package com.sishuok.architecture1.ordermgr.dao.interfances;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.ordermgr.bean.OrderModel;


@Repository
public interface IOrderDao extends IBaseDao<OrderModel>{
	
}
