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
import com.sishuok.architecture1.ordermgr.bean.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.web.webModel.OrderDetailWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/orderDetail")
public class OrderDetailController {
	@Autowired
	private IOrderDetailService iservice = null;
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "orderDetail/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(OrderDetailModel m){
		iservice.create(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(OrderDetailModel m){
		iservice.update(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "orderDetail/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(OrderDetailWebModel orderDetailWebModel,Model model){
		OrderDetailModel orderDetailModel = null;
		//包括了查询结果和分页信息
		Page<OrderDetailModel> pageList = null;
		if("".equals(orderDetailWebModel.getQueryJsonStr()) || orderDetailWebModel.getQueryJsonStr().length() < 1){
			orderDetailModel = new OrderDetailModel();
		}else{
			orderDetailModel = (OrderDetailModel)JsonHelper.str2Object(orderDetailWebModel.getQueryJsonStr(), OrderDetailModel.class);
		}
		int nowPage = orderDetailWebModel.getNowPage();
		int pageShow = orderDetailWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		List<OrderDetailModel> orderDetailModelList = iservice.getByCondition(orderDetailModel);
		
		model.addAttribute("wm", orderDetailWebModel);
		model.addAttribute("page", orderDetailModelList);
		
		return "orderDetail/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "orderDetail/query";
	}	
}
