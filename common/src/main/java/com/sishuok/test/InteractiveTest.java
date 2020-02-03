package com.sishuok.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

public class InteractiveTest {

	public static void main(String[] args) {
		InteractiveCallHelper call = new InteractiveCallHelper();
		// 远程调用其它模块的功能
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("abc", "aa11");
		mapParam.put("paramName", "testName");
		mapParam.put("ip","127.0.0.1");
		mapParam.put("port","8080");
		mapParam.put("url","/goodsmgrweb/goodsFI/call");
		
		MyRetModel rm = call.call("GoodsModule", "2", mapParam,
				MyRetModel.class);

		System.out.println("rm===" + rm);
		// 继续处理自己的业务功能

	}
}
