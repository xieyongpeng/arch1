//package com.sishuok.architecture1.customermgr.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.sishuok.architecture1.customermgr.bean.CustomerModel;
//
//public class PersonDaoImpl implements PersonDao{
//	public Page<CustomerModel> paginationQuery(Integer pageNum,Integer pageIsize) { 
//		 
////	    SpringDataPageable pageable = new SpringDataPageable(); 
////	    Query query = new Query(); 
////	    List<Order> orders = new ArrayList<Order>();  //排序 
////	    orders.add(new Order(Direction.DESC, "age")); 
////	    Sort sort = new Sort(orders); 
////	 
////	    // 开始页 
////	    pageable.setPagenumber(pageNum); 
////	    // 每页条数 
////	    pageable.setPagesize(pageIsize); 
////	    // 排序 
////	    pageable.setSort(sort); 
////	    // 查询出一共的条数 
////	    Long count = mongoTemplate.count(query, CustomerModel.class); 
////	    // 查询 
////	    List<CustomerModel> list = mongoTemplate.find(query.with(pageable), CustomerModel.class); 
////	    // 将集合与分页结果封装 
////	    Page<CustomerModel> pagelist = new PageImpl<CustomerModel>(list, pageable, count); 
////	 
////	    return pagelist; 
//	}
//}
