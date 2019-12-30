package com.sishuok.architecture1.cartmgr.dao.interfances;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.common.dao.IBaseDao;


@Repository
public interface ICartDAO extends IBaseDao<CartModel>{
	
}
