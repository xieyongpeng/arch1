package com.sishuok.architecture1.customermgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.sishuok.ClientTest;
import com.sishuok.architecture1.customermgr.bean.CustomerModel;
import com.sishuok.architecture1.customermgr.dao.interfances.ICustomerMongoDao;

@Service
public class CustomerMongoDaoImpl implements ICustomerMongoDao {

	@Autowired
	private MongoTemplate mongoTemplate = null;
	private final String COL_NAME = "tbl_customer";

	@Override
	public void create(CustomerModel customerModel) {
		mongoTemplate.insert(customerModel, COL_NAME);
	}

	@Override
	public void update(CustomerModel customerModel) {
		DBCollection collection = mongoTemplate.getCollection(COL_NAME);
		BasicDBObject condition = new BasicDBObject("uuid",
				customerModel.getUuid());
		collection
				.update(condition,
						new BasicDBObject(
								"$set",
								new BasicDBObject("uuid", customerModel
										.getUuid())
										.append("customerId",
												customerModel.getCustomerId())
										.append("pwd", customerModel.getPwd())
										.append("showName",
												customerModel.getShowName())
										.append("trueName",
												customerModel.getTrueName())
										.append("registerTime",
												customerModel.getRegisterTime())));
	}

	@Override
	public void delete(int uuid) {
		Criteria criteria = new Criteria("uuid").is(uuid);
		mongoTemplate.remove(new Query(criteria), COL_NAME);
	}

	@Override
	public CustomerModel getByUuid(int uuid) {
		Criteria criteria = new Criteria("uuid").is(uuid);
		List<CustomerModel> customerModels = mongoTemplate.find(new Query(
				criteria), CustomerModel.class, COL_NAME);
		if(customerModels != null && customerModels.size()>0){
			return customerModels.get(0);
		}
		return null;
	}

	@Override
	public List<CustomerModel> getByCondition(CustomerModel customerModel) {
		Criteria criteria = new Criteria();
		if(customerModel.getUuid()>0){
			criteria.andOperator(new Criteria("uuid").is(customerModel.getUuid()));	
		}
		if(customerModel.getCustomerId() != null && customerModel.getCustomerId().trim().length()>0){
			criteria.andOperator(new Criteria("customerId").is(customerModel.getCustomerId()));	
		}
		if(customerModel.getShowName() != null && customerModel.getShowName().trim().length()>0){
			criteria.andOperator(new Criteria("showName").is(customerModel.getShowName()));	
		}
		if(customerModel.getPwd() != null && customerModel.getPwd().trim().length()>0){
			criteria.andOperator(new Criteria("pwd").is(customerModel.getPwd()));	
		}
		if(customerModel.getTrueName() != null && customerModel.getTrueName().trim().length()>0){
			criteria.andOperator(new Criteria("trueName").is(customerModel.getTrueName()));	
		}
		List<CustomerModel> customerModels = mongoTemplate.find(new Query(criteria), CustomerModel.class , COL_NAME);
		return customerModels;
	}

	@Override
	public List<CustomerModel> getAll() {
		List<CustomerModel> customerModels = mongoTemplate.find(new Query(), CustomerModel.class , COL_NAME);
		return customerModels;
	}

	

	@Override
	public List<CustomerModel> getByIds(String ids) {
		List<Integer> uuids = new ArrayList<Integer>();
		String[] ss = ids.split(",");
		
		for(String s : ss){
			uuids.add(Integer.parseInt(s));
		}
		Criteria c = new Criteria("uuid").in(uuids);
		List<CustomerModel> list = mongoTemplate.find(new Query(c),CustomerModel.class,COL_NAME);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public Page<CustomerModel> getConditionPage(CustomerModel customerModel,
			Integer pageNum, Integer pageSize) {
		SpringDataPageable pageable = new SpringDataPageable(); 
	     
	    List<Order> orders = new ArrayList<Order>();  //排序 
	    orders.add(new Order(Direction.DESC, "_id")); 
	    Sort sort = new Sort(orders); 
	 
	    // 开始页 
	    pageable.setPagenumber(pageNum); 
	    // 每页条数 
	    pageable.setPagesize(pageSize); 
	    // 排序 
	    pageable.setSort(sort); 
	    
	    Criteria criteria = new Criteria();
		if(customerModel.getUuid()>0){
			criteria.andOperator(new Criteria("uuid").is(customerModel.getUuid()));	
		}
		if(customerModel.getCustomerId() != null && customerModel.getCustomerId().trim().length()>0){
			criteria.andOperator(new Criteria("customerId").is(customerModel.getCustomerId()));	
		}
		if(customerModel.getShowName() != null && customerModel.getShowName().trim().length()>0){
			criteria.andOperator(new Criteria("showName").is(customerModel.getShowName()));	
		}
		if(customerModel.getPwd() != null && customerModel.getPwd().trim().length()>0){
			criteria.andOperator(new Criteria("pwd").is(customerModel.getPwd()));	
		}
		if(customerModel.getTrueName() != null && customerModel.getTrueName().trim().length()>0){
			criteria.andOperator(new Criteria("trueName").is(customerModel.getTrueName()));	
		}
		Query query = new Query();
		query.addCriteria(criteria);
	    // 查询出一共的条数 
	    Long count = mongoTemplate.count(query, CustomerModel.class,COL_NAME); 
	    //总页数
	    pageable.setCount(count);
	    // 查询 
	    List<CustomerModel> list = mongoTemplate.find(query.with(pageable), CustomerModel.class,COL_NAME); 
	    // 将集合与分页结果封装 
	    Page<CustomerModel> pagelist = new PageImpl<CustomerModel>(list, pageable, count); 
	 
	    return pagelist; 
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerMongoDaoImpl client = (CustomerMongoDaoImpl) context.getBean("customerMongoDaoImpl");
		CustomerModel customerModel = new CustomerModel();
		customerModel.setUuid(111);
		Page<CustomerModel> pagelist = client.getConditionPage(customerModel, 1, 5);
		List<CustomerModel> customerModels = pagelist.getContent();
		for(CustomerModel m : customerModels){
			System.out.println(m.toString());
		}
	}
	
}
