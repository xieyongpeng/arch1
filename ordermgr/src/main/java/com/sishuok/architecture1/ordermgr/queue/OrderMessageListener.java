package com.sishuok.architecture1.ordermgr.queue;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.cartmgr.service.interfances.ICartService;
import com.sishuok.architecture1.ordermgr.bean.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.bean.OrderModel;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderService;
import com.sishuok.architecture1.storemgr.bean.StoreModel;
import com.sishuok.architecture1.storemgr.service.interfances.IStoreService;
import com.sishuok.util.format.DateFormatHelper;

public class OrderMessageListener implements MessageListener{
	@Autowired
	private ICartService cartService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private IStoreService storeService;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		try {
			int customerId = Integer.valueOf(textMessage.getText());
			CartModel cqm = new CartModel();
			cqm.setCustomerUuid(customerId);
			List<CartModel> resultList = cartService.getByCondition(cqm);
			
			float totalMoney = 0.0f;
			for(CartModel result : resultList){
				totalMoney += 10;
			}
			
			OrderModel order = new OrderModel();
			order.setCustomerUuid(customerId);
			order.setOrderTime(DateFormatHelper.long2str(System
					.currentTimeMillis()));
			order.setSaveMoney(0.0F);
			order.setTotalMoney(totalMoney);
			order.setState(1);
			orderService.create(order);

			OrderModel oqm = new OrderModel();
			oqm.setOrderTime(order.getOrderTime());
			List<OrderModel> orderList = orderService.getByCondition(oqm);
			order = orderList.get(0);
			
			
			// 3:
			for (CartModel cm : resultList) {
				OrderDetailModel odm = new OrderDetailModel();
				odm.setGoodsUuid(cm.getGoodsUuid());
				odm.setOrderNum(cm.getBuyNum());
				odm.setPrice(10.0f);
				odm.setMoney(odm.getPrice() * odm.getOrderNum());
				odm.setSaveMoney(0.0f);
				odm.setOrderUuid(order.getUuid());

				orderDetailService.create(odm);
				// 4:
				synchronized (storeService) {
					StoreModel storeModel = storeService.getByGoodsUuid(cm
							.getGoodsUuid());
					storeModel.setStoreNum(storeModel.getStoreNum()
							- odm.getOrderNum());
					storeService.update(storeModel);
				}
				// 5:
				cartService.delete(cm.getUuid());
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
