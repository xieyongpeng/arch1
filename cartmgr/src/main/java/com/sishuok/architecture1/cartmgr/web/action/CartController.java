package com.sishuok.architecture1.cartmgr.web.action;

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
import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.cartmgr.service.interfances.ICartService;
import com.sishuok.architecture1.cartmgr.web.webModel.CartWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/cartmgr")
public class CartController {
	@Autowired
	private ICartService iservice = null;
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "cart/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(CartModel m){
		iservice.create(m);
		return "cart/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		CartModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(CartModel m){
		iservice.update(m);
		return "cart/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		CartModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "cart/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "cart/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(CartWebModel wm,Model model){
		//包括了查询结果和分页信息
		Page<CartWebModel> pageList = null;
		CartModel qm = null;
		if(wm.getQueryJsonStr()==null || wm.getQueryJsonStr().trim().length()==0){
			qm =  new CartModel();
		}else{
			String s = wm.getQueryJsonStr();
			s = s.replace("-", "%");
			qm = (CartModel)JsonHelper.str2Object(s, CartModel.class);
		}
		
		int nowPage = wm.getNowPage();
		int pageShow = wm.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		
		iservice.getByCondition(qm);
		
		model.addAttribute("wm", wm);
		model.addAttribute("page", pageList);
				
		return "cart/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "cart/query";
	}	
}
