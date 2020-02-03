package com.sishuok.architecture1;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sishuok.architecture1.cartmgr.bean.CartModel;
import com.sishuok.architecture1.cartmgr.service.interfances.ICartService;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;
import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.service.interfances.IGoodsService;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.interfances.IOrderService;
import com.sishuok.architecture1.storemgr.service.interfances.IStoreService;



@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IGoodsService igs = null;
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderService ios = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;
	@Autowired
	private SecurityManager sm = null;
	
	private final int PAGE_NUM = 100;
	
	@RequestMapping(value="/toIndex",method=RequestMethod.GET)
	public String toIndex(Model model){
		GoodsModel gqm = new GoodsModel();
		//包括了查询结果和分页信息
		Page<GoodsModel> pageList = PageHelper.startPage(0,PAGE_NUM);
		
		List<GoodsModel> goodsList = igs.getByCondition(gqm);
		
		PageInfo pageInfo = new PageInfo(goodsList);
		
		model.addAttribute("page",pageInfo);
		
		SecurityUtils.setSecurityManager(sm);
		Subject currentUser = SecurityUtils.getSubject();
		CustomerModel cm = (CustomerModel)currentUser.getSession().getAttribute("Login_Customer");
		System.out.println("now cm==="+cm);
		
		return "index";
	}
	
	@RequestMapping(value="/toGoodsDesc/{goodsUuid}",method=RequestMethod.GET)
	public String toGoodsDesc(Model model,@PathVariable("goodsUuid")int goodsUuid){
		GoodsModel gm = igs.getByUuid(goodsUuid);
		
		model.addAttribute("m",gm);
		return "goods/desc";
	}
	
	@RequestMapping(value="/addToCart/{goodsUuid}",method=RequestMethod.GET)
	public String addToCart(Model model,@PathVariable("goodsUuid")int goodsUuid,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split("#")[0]);
		
		CartModel cm = new CartModel();
		cm.setBuyNum(1);
		cm.setCustomerUuid(customerUuid);
		cm.setGoodsUuid(goodsUuid);
		
		ics.create(cm);
		///////////////////////////
		CartModel cqm = new CartModel();
		cqm.setCustomerUuid(customerUuid);
		
		Page<CartModel> pageList = PageHelper.startPage(0,PAGE_NUM);
		
		ics.getByCondition(cqm);
		
		model.addAttribute("page",pageList);
		
		return "cart/myCart";
	}
	@RequestMapping(value="/toCart",method=RequestMethod.GET)
	public String toCart(Model model,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split(",")[0]);
		
		CartModel cqm = new CartModel();
		
		cqm.setCustomerUuid(customerUuid);
		
		Page<CartModel> pageList = PageHelper.startPage(0,PAGE_NUM);
		
		ics.getByCondition(cqm);
		
		model.addAttribute("page",pageList);
		
		return "cart/myCart";
	}
	
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public String order(@CookieValue("MyLogin")String myLogin){
		//1:查出这个人购物车所有的信息		
		int customerUuid = Integer.parseInt( myLogin.split("#")[0]);
		
		ios.order(customerUuid);
		
		return "success";
	}
}
