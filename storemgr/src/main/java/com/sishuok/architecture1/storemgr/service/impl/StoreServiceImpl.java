package com.sishuok.architecture1.storemgr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.storemgr.bean.StoreModel;
import com.sishuok.architecture1.storemgr.dao.interfances.IStoredao;
import com.sishuok.architecture1.storemgr.service.interfances.IStoreService;


@Service
@Transactional
public class StoreServiceImpl extends BaseServiceImpl<StoreModel> implements IStoreService{
	private IStoredao dao = null;
	@Autowired
	private void setDao(IStoredao dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public StoreModel getByGoodsUuid(int goodsUuid) {
		return dao.getByGoodsUuid(goodsUuid);
	}
	
}