package com.sishuok.architecture1.goodsmgr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






import com.sishuok.architecture1.common.service.impl.BaseServiceImpl;
import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.dao.interfances.IGoodsMapperDao;
import com.sishuok.architecture1.goodsmgr.service.interfances.IGoodsService;


@Service
@Transactional
public class GoodsServiceImpl extends BaseServiceImpl<GoodsModel> implements IGoodsService{
	private IGoodsMapperDao dao = null;
	@Autowired
	private void setDao(IGoodsMapperDao dao){
		this.dao = dao;
		super.setDAO(dao);
	}
}