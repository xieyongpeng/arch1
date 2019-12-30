package com.sishuok.architecture1.storemgr.dao.interfances;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.IBaseDao;
import com.sishuok.architecture1.storemgr.bean.StoreModel;


@Repository
public interface IStoredao extends IBaseDao<StoreModel>{
	public StoreModel getByGoodsUuid(int goodsUuid);
}
