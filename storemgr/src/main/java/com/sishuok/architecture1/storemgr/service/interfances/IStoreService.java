package com.sishuok.architecture1.storemgr.service.interfances;

import com.sishuok.architecture1.common.service.interfances.IBaseService;
import com.sishuok.architecture1.storemgr.bean.StoreModel;


public interface IStoreService extends IBaseService<StoreModel>{
	public StoreModel getByGoodsUuid(int goodsUuid);
}

