package com.sishuok.architecture1.ordermgr.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sishuok.architecture1.ordermgr.bean.OrderModel;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderService;
import com.sishuok.architecture1.ordermgr.web.webModel.OrderWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Autowired
	private IOrderService iservice = null;

	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "order/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(OrderModel m){
		iservice.create(m);
		return "order/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(OrderModel m){
		iservice.update(m);
		return "order/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "order/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(OrderWebModel orderWebModel,Model model){
		OrderModel orderModel = null;
		//包括了查询结果和分页信息
		Page<OrderModel> pageList = null;
		if("".equals(orderWebModel.getQueryJsonStr()) || orderWebModel.getQueryJsonStr().length() < 1){
			orderModel = new OrderModel();
		}else{
			orderModel = (OrderModel)JsonHelper.str2Object(orderWebModel.getQueryJsonStr(), OrderModel.class);
		}
		int nowPage = orderWebModel.getNowPage();
		int pageShow = orderWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		List<OrderModel> orderModes= iservice.getByCondition(orderModel);
		
		PageInfo<OrderModel> pageInfo = new PageInfo(orderModes);
		
		model.addAttribute("wm", orderWebModel);
		model.addAttribute("page", orderModes);
		
		return "order/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "order/query";
	}	
}
