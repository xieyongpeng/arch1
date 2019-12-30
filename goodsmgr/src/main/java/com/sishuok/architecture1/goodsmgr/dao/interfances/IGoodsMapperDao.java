package com.sishuok.architecture1.goodsmgr.dao.interfances;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.dao.interfances.IGoodsDao;



@Repository
public interface IGoodsMapperDao extends IGoodsDao{
	public List<GoodsModel> getByIds(List ids);
	public List<Integer> getAllIds();
	public List<Integer> getIdsByCondition(GoodsModel goodsModel);
}
