package com.sishuok.interactive;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.sishuok.interactive.bean.CsfModel;

public class InteractiveCallHelper {
	/**
	 * 实现模块间的远程调用
	 * @param moduleId 模块id
	 * @param opeType 要调用的具体的业务操作类型
	 * @param mapParams 调用所需要传递的参数
	 * @return 服务端返回的Json数据
	 */
	public String call(String moduleId,String opeType,Map<String,Object> mapParams){
		//1：根据moduleId去获取到该模块部署的信息
		//这里没有模块管理，所以只是简单的做个测试数据
		CsfModel mm = new CsfModel();
//		mm.setDeployIP("localhost");
//		mm.setDeployPort("9080");
//		mm.setInteractiveUrl("/goods/goodsFI/call");
		
		mm.setDeployIP(mapParams.get("ip").toString());
		mm.setDeployPort(mapParams.get("port").toString());
		mm.setInteractiveUrl(mapParams.get("url").toString());
		
		mapParams.remove("ip");
		mapParams.remove("port");
		mapParams.remove("url");
		
		
		//2：准备要传递的数据，把Map转换成为Json
		String paramStr = JSON.toJSONString(mapParams);
		paramStr = paramStr.replace("#","*");
		
		//3：拼接一个远程调用的URL
		String urlStr = "http://"+mm.getDeployIP()+":"+mm.getDeployPort()+mm.getInteractiveUrl()
				+"?jsonParam={opeType:"+opeType+",map:"+paramStr+"}";
		
		//4：使用URL进行远程调用，流式操作
		InputStream in = null;
		String retJson = "";
		try{
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			in = conn.getInputStream();
			//5：得到返回，关闭
			byte[] bs = new byte[in.available()];
			in.read(bs);
			
			retJson = new String(bs);
			
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return retJson;
	}
	/**
	 * 实现模块间的远程调用
	 * @param moduleId
	 * @param opeType
	 * @param mapParams
	 * @param cls 要转换成的业务对象的class
	 * @return 业务对象模型
	 */
	public <T> T call(String moduleId,String opeType,Map<String,Object> mapParams,Class<T> cls){
		String json = this.call(moduleId, opeType, mapParams);
		T ret = InteractiveUtil.retJson2Model(json, cls);
		return ret;
	}
}
