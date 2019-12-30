package com.sishuok.architecture1.storemgr.web.action;

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
import com.sishuok.architecture1.storemgr.bean.StoreModel;
import com.sishuok.architecture1.storemgr.service.interfances.IStoreService;
import com.sishuok.architecture1.storemgr.web.webModel.StoreWebModel;
import com.sishuok.util.format.DateFormatHelper;
import com.sishuok.util.json.JsonHelper;

@Controller
@RequestMapping(value="/storemgr")
public class StoreController {
	@Autowired
	private IStoreService iservice = null;
	
	private final int PAGE_NUM = 5;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "store/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(StoreModel m){
		iservice.create(m);
		return "store/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") StoreModel m){
		iservice.update(m);
		return "store/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = iservice.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		iservice.delete(uuid);
		return "store/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(StoreWebModel storeWebModel,Model model){
		StoreModel storeModel = null;
		//包括了查询结果和分页信息
		Page<StoreModel> pageList = null;
		if("".equals(storeWebModel.getQueryJsonStr()) || storeWebModel.getQueryJsonStr().length() < 1){
			storeModel = new StoreModel();
		}else{
			storeModel = (StoreModel)JsonHelper.str2Object(storeWebModel.getQueryJsonStr(), StoreModel.class);
		}
		int nowPage = storeWebModel.getNowPage();
		int pageShow = storeWebModel.getPageShow();
		if(pageShow > 0){
			pageList = PageHelper.startPage(nowPage, pageShow);
		}else{
			pageList = PageHelper.startPage(nowPage, PAGE_NUM);
		}
		iservice.getByCondition(storeModel);
		
		model.addAttribute("wm", storeWebModel);
		model.addAttribute("page", pageList);
		
		return "store/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "store/query";
	}	
}
