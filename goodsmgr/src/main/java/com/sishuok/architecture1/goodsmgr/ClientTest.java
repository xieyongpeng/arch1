package com.sishuok.architecture1.goodsmgr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.dao.impl.GoodsMemcachedImpl;
import com.sishuok.architecture1.goodsmgr.dao.interfances.IGoodsMapperDao;

public class ClientTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsMemcachedImpl memcachedImpl = (GoodsMemcachedImpl) applicationContext.getBean("goodsMemcachedImpl");
//		GoodsModel googsModel = memcachedImpl.getByUuid(1);
//		System.out.println(googsModel);
//		List ids= new ArrayList();
//		ids.add("1");
		GoodsModel goodsModel = new GoodsModel();
//		goodsModel.setUuid(1);
		System.out.println(memcachedImpl.getByCondition(goodsModel));
	}
}
