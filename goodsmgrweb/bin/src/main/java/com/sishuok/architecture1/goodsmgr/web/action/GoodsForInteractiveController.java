package com.sishuok.architecture1.goodsmgr.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sishuok.architecture1.goodsmgr.service.GoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.interactive.InteractiveBaseController;
import com.sishuok.interactive.bean.InteractiveModel;
import com.sishuok.util.interactive.InteractiveUtil;

@Controller
@RequestMapping(value="/goodsFI")
public class GoodsForInteractiveController {

	@Autowired
	private GoodsService gs;
	
	@RequestMapping(value="call",method=RequestMethod.GET)
	public Object call(@RequestParam("jsonParam") String jsonParam){
		if(jsonParam!=null && jsonParam.contains("*")){
			jsonParam = jsonParam.replace("*", "#");
		}
		InteractiveModel im = InteractiveUtil.paramJson2Model(jsonParam);
		//去做真正的业务
		Object ret = doCall(im.getOpeType(),im.getMap());
		return ret;
	}
	
	@Override
	protected Object doCall(String opeType, Map<String, Object> map) {
		//根据opeType来转调逻辑层的业务方法
		if("1".equals(opeType)){
			System.out.println("now 处理 opeType=="+opeType);
		}else if("2".equals(opeType)){
			System.out.println("now 处理 opeType2222222222=="+opeType);
		}
		//准备一个测试数据来返回
		GoodsModel gm = new GoodsModel();
		gm.setName("goods123");
		gm.setUuid(11);
		
		return gm;
	}
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "goods/add";
	}
	
}
