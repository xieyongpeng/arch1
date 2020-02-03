package com.sishuok.architecture1.goodsmgr.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sishuok.architecture1.goodsmgr.bean.GoodsModel;
import com.sishuok.architecture1.goodsmgr.service.interfances.IGoodsService;
import com.sishuok.architecture1.goodsmgr.web.webModel.GoodsWebModel;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/goodsmgrweb")
public class GoodsController {
	@Autowired
	private IGoodsService iservice = null;
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "goods/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(GoodsModel m){
		iservice.create(m);
		return "goods/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(GoodsModel m){
		iservice.update(m);
		return "goods/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "goods/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(GoodsWebModel goodsWebModel,Model model){
		GoodsModel goodsModel = null;
		//包括了查询结果和分页信息
		Page<GoodsModel> pageList = null;
		if("".equals(goodsWebModel.getQueryJsonStr()) || goodsWebModel.getQueryJsonStr().length() < 1){
			goodsModel = new GoodsModel();
		}else{
			goodsModel = (GoodsModel)JsonHelper.str2Object(goodsWebModel.getQueryJsonStr(), GoodsModel.class);
		}
		int nowPage = goodsWebModel.getNowPage();
		int pageShow = goodsWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		List<GoodsModel> goodsList = iservice.getByCondition(goodsModel);
		PageInfo page = new PageInfo(goodsList) ;
		
		model.addAttribute("wm", goodsWebModel);
		model.addAttribute("page", page);
		
		return "goods/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "goods/query";
	}	
}
