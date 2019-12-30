package com.sishuok.architecture1.ordermgr.service.interfances;

import com.sishuok.architecture1.common.service.interfances.IBaseService;
import com.sishuok.architecture1.ordermgr.bean.OrderModel;




public interface IOrderService extends IBaseService<OrderModel>{
	public void order(int customerUuid);
}

