package com.sishuok.architecture1.goodsmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.dao.interfances.IGoodsMapperDao;
import com.whalin.MemCached.MemCachedClient;

@Service
@Primary
public class GoodsMemcachedImpl implements IGoodsMapperDao{
	
	private final String MEM_PRE = "Goods";
	@Autowired
	private IGoodsMapperDao goodsMapper;
	@Autowired
	private MemCachedClient memCachedClient;

	@Override
	public void create(GoodsModel arg0) {
		goodsMapper.create(arg0);
	}

	@Override
	public void delete(int arg0) {
		goodsMapper.delete(arg0);
		Object obj = memCachedClient.get(MEM_PRE + arg0);
		if(obj != null){
			memCachedClient.delete(MEM_PRE + arg0);
		}
	}

	@Override
	public List<GoodsModel> getAll() {
		List<GoodsModel> GoodsModelList = new ArrayList<GoodsModel>();
		List<Integer> idsAll = goodsMapper.getAllIds();
		if (idsAll != null && idsAll.size() > 0) {
			for (int i = 0; idsAll.size() > i; i++) {
				int id = idsAll.get(i);
				Object obj = memCachedClient.get(MEM_PRE + id);
				if (obj == null) {
					GoodsModel goodModel = goodsMapper.getByUuid(id);
					memCachedClient.add(MEM_PRE + id, goodModel);
					GoodsModelList.add(goodModel);
				} else {
					GoodsModelList.add((GoodsModel) obj);
				}
			}
		} else {
			return null;
		}
		return GoodsModelList;
	}

	@Override
	public List<GoodsModel> getByCondition(GoodsModel arg0) {
		List<Integer> ids = goodsMapper.getIdsByCondition(arg0);
		List<GoodsModel> goodsList = new ArrayList<GoodsModel>();
		for(Integer id : ids){
			Object obj = memCachedClient.get(MEM_PRE + id);
			if(obj != null){
				goodsList.add((GoodsModel)obj);
			}else{
				GoodsModel goodModel = goodsMapper.getByUuid(id);
				memCachedClient.add(MEM_PRE + id, goodModel);
				goodsList.add(goodModel);
			}
		}
		return goodsList;
	}

	@Override
	public GoodsModel getByUuid(int arg0) {
		GoodsModel goodsModel = null;
		Object obj = memCachedClient.get(MEM_PRE + arg0);
		if(obj == null){
			goodsModel = goodsMapper.getByUuid(arg0);
			memCachedClient.add(MEM_PRE + arg0, goodsModel);
		}else{
			return (GoodsModel)obj;
		}
		return goodsModel;
	}

	@Override
	public void update(GoodsModel arg0) {
		Object obj = memCachedClient.get(MEM_PRE + arg0.getUuid());
		if(obj != null){
			memCachedClient.replace(MEM_PRE + arg0.getUuid(), arg0);
		}
		goodsMapper.update(arg0);
	}

	@Override
	public List<Integer> getAllIds() {
		return goodsMapper.getAllIds();
	}

	@Override
	public List<GoodsModel> getByIds(List ids) {
		return goodsMapper.getByIds(ids);
	}

	@Override
	public List<Integer> getIdsByCondition(GoodsModel goodsModel) {
		return goodsMapper.getIdsByCondition(goodsModel);
	}

}
