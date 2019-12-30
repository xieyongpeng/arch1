package com.sishuok.architecture1.cartmgr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.cartmgr.dao.interfances.ICartDAO;
import com.sishuok.architecture1.cartmgr.service.interfances.ICartService;
import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;


@Service
@Transactional
public class CartServiceImpl extends BaseServiceImpl<CartModel> implements ICartService{
	
	private ICartDAO dao = null;
	@Autowired
	private void setDao(ICartDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}