package com.sishuok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.cartmgr.service.interfances.ICartService;

@Service
@Transactional
public class ClientTest {
	@Autowired
	private ICartService iCartService;
	
	public ICartService getCartService(){
		return this.iCartService;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClientTest client = (ClientTest) context.getBean("clientTest");
		
//		Page<CartModel> page = PageHelper.startPage(2, 2);
//		System.out.println("++++++++++++"+page);
		System.out.println(client.getCartService().getAll());
		
		
		
//		CustomerModel cm = new CustomerModel();
//		cm.setCustomerId("c2");
//		cm.setPwd("c2");
//		cm.setRegisterTime("111");
//		cm.setShowName("c2");
//		cm.setTrueName("王6");
//		
//		client.getICustomerDao().create(cm);

	}
}
