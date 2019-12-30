package com.sishuok.architecture1.customermgr.web.action;

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
import com.sishuok.architecture1.customermgr.bean.CustomerModel;
import com.sishuok.architecture1.customermgr.service.interfances.ICustomerService;
import com.sishuok.architecture1.customermgr.web.webModel.CustomerWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService iCustomerService; 
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "/customer/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	//@ModelAttribute("CM") CustomerModel customerModel
	public String add(@ModelAttribute("cm") CustomerModel customerModel){
		customerModel.setRegisterTime(DateFormatHelper.long2str(System.currentTimeMillis()));
		iCustomerService.create(customerModel);
		return "customer/success";
	}
	
	@RequestMapping(value="toUpdate/{customerUuid}",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("customerUuid") int customerUuid,Model model){
		CustomerModel cm = iCustomerService.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(@ModelAttribute("cm") CustomerModel cm){
		iCustomerService.update(cm);
		return "customer/success";
	}
	
	@RequestMapping(value="toDelete/{customerUuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("customerUuid") int customerUuid){
		CustomerModel cm = iCustomerService.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int customerUuid){
		iCustomerService.delete(customerUuid);
		return "customer/success";
	}
	
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(CustomerWebModel customerWebModel,Model model){
		CustomerModel customerModel = null;
		//包括了查询结果和分页信息
		Page<CustomerModel> pageList = null;
		if("".equals(customerWebModel.getQueryJsonStr()) || customerWebModel.getQueryJsonStr().length() < 1){
			customerModel = new CustomerModel();
		}else{
			customerModel = (CustomerModel)JsonHelper.str2Object(customerWebModel.getQueryJsonStr(), CustomerModel.class);
		}
		int nowPage = customerWebModel.getNowPage();
		int pageShow = customerWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		iCustomerService.getByCondition(customerModel);
		
		model.addAttribute("wm", customerWebModel);
		model.addAttribute("page", pageList);
		
		return "customer/list";
	}
	
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "customer/query";
	}
}
