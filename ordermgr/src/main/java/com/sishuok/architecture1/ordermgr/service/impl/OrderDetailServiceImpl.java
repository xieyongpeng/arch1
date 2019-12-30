package com.sishuok.architecture1.ordermgr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.ordermgr.bean.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.dao.interfances.IOrderDetailDao;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderDetailService;


@Service
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailModel> implements IOrderDetailService{
	private IOrderDetailDao dao = null;
	@Autowired
	private void setDao(IOrderDetailDao dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}