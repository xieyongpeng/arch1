package com.sishuok.util.memcached;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.whalin.MemCached.MemCachedClient;

public class MemcachedTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MemCachedClient cachedClient = (MemCachedClient) applicationContext.getBean("memCachedClient");
		cachedClient.set("r2", "33");
	}
}
