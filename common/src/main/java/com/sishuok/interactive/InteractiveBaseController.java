package com.sishuok.interactive;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sishuok.interactive.bean.InteractiveModel;
import com.sishuok.util.interactive.InteractiveUtil;

/**
 * 进行模块间交互调用的基础控制器
 */
public abstract class InteractiveBaseController {
	

	
//	@responsebody表示该方法的返回结果直接写入HTTP response body中。
//	一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
//	加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
//	比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	
	@RequestMapping(value="call",method=RequestMethod.GET)
	@ResponseBody
	public Object call(@RequestParam("jsonParam") String jsonParam){
		if(jsonParam!=null && jsonParam.contains("*")){
			jsonParam = jsonParam.replace("*", "#");
		}
		InteractiveModel im = InteractiveUtil.paramJson2Model(jsonParam);
		//去做真正的业务
		Object ret = doCall(im.getOpeType(),im.getMap());
		return ret;
	}
	
	protected abstract Object doCall(String opeType,Map<String,Object> map);
}
