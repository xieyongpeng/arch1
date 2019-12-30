package com.sishuok.test;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.util.JSON;

public class MongoTest {
	public static void main(String[] args) throws Exception{
		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(10)
				.maxWaitTime(3000).build();
		MongoClient mongoClient = new MongoClient("192.168.174.3:27017",clientOptions);
		DB db = mongoClient.getDB("db1");
		DBCollection personCollection = db.getCollection("person");
		
		BasicDBObject condition = new BasicDBObject("age",new BasicDBObject("$lt",50));
		DBCursor cursor = personCollection.find(condition).sort(new BasicDBObject("name",-1)).skip(2).limit(10);
		while(cursor.hasNext()){
			System.out.println(cursor.next().toString());
		}
		
		
//		BasicDBObject condition = new BasicDBObject("age",new BasicDBObject("$lt",50));
//		DBCursor cursor = personCollection.find(condition).sort(new BasicDBObject("name",-1));
//		while(cursor.hasNext()){
//			System.out.println(cursor.next().toString());
//		}	
		
//		BasicDBObject person = new BasicDBObject();
//		person.put("age", 5);
//		DBCursor cursor = personCollection.find(person).limit(3);
//		while(cursor.hasNext()){
//			System.out.println(cursor.next().toString());
//		}	
		
//		BasicDBObject condition = new BasicDBObject();
//		condition.put("name", "zhangwq");
//		personCollection.update(condition, new BasicDBObject("$set",new BasicDBObject("age",22)));
		
//		BasicDBObject condition = new BasicDBObject();
//		condition.put("name", "zhangwq");
//		personCollection.update(condition, new BasicDBObject("name","zhangwq").append("userId", "201912200001")
//				.append("age", 30).append("weight", 148));
		
		
//		BasicDBObject person = new BasicDBObject();
//		person.put("name", "zhangwq");
//		DBCursor cursor = personCollection.find(person);
//		while(cursor.hasNext()){
//			System.out.println(cursor.next().toString());
//		}	
		
//		String person = "{'name':'gongh','userId':'201912200005','age':24,'weight':130}";
//		BasicDBObject personObject = (BasicDBObject)JSON.parse(person);
//		personCollection.insert(personObject);
		
		
//		Map<String,Object> person = new HashMap<String, Object>();
//		person.put("name", "gongrf");
//		person.put("userId", "201912200004");
//		person.put("age", 24);
//		person.put("weight", 135);
//		personCollection.insert(new BasicDBObject(person));
		
//		BasicDBObjectBuilder person1 = BasicDBObjectBuilder.start().add("name", "tongDD")
//				.add("userId", "201912200003").add("age", 24).add("weight", 138);
//		personCollection.insert(person1.get());

//		BasicDBObject person = new BasicDBObject();
//		person.put("name", "zhangwq");
//		person.put("userId", "201912200001");
//		person.put("age", 24);
//		person.append("weight", 148);
//		personCollection.insert(person);
//		
//		BasicDBObject person1 = new BasicDBObject();
//		person1.put("name", "jinwq");
//		person1.put("userId", "201912200002");
//		person1.put("age", 24);
//		person1.append("weight", 150);
//		personCollection.save(person1);
		
		
		
//		DBObject dbObject = personCollection.findOne();
//		System.out.println(dbObject.toString());
	}
}
