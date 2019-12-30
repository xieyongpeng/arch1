package com.sishuok.architecture1.ordermgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.ordermgr.bean.OrderModel;
import com.sishuok.architecture1.ordermgr.dao.interfances.IOrderDao;
import com.sishuok.architecture1.ordermgr.queue.QueueSender;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderService;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<OrderModel> implements
		IOrderService {
	private IOrderDao dao = null;

	@Autowired
	private QueueSender queueSender;

	@Autowired
	private void setDao(IOrderDao dao) {
		this.dao = dao;
		super.setDAO(dao);
	}

	public void order(int customerUuid) {
		queueSender.sendTextMessage(customerUuid);
	}
}