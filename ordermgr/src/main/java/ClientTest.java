

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



@Service
@Transactional
public class ClientTest {
//	@Autowired
//	private ICustomerService customerService;
//	
//	public ICustomerService getCustomerService(){
//		return this.customerService;
//	}
	
	public static void main(String[] args) throws Exception {
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ClientTest client = (ClientTest) context.getBean("clientTest"); 
//		Page<CustomerModel> page = PageHelper.startPage(2, 2);
//		System.out.println("++++++++++++"+page);
//		client.getCustomerService().getAll();
//		System.out.println(page.getTotal());
		
		
		
//		CustomerModel cm = new CustomerModel();
//		cm.setCustomerId("c2");
//		cm.setPwd("c2");
//		cm.setRegisterTime("111");
//		cm.setShowName("c2");
//		cm.setTrueName("王6");
//		
//		client.getICustomerDao().create(cm);
		
//		long a = new Date().getTime();
//		Thread.sleep(1000);
//		long b = new Date().getTime();
//		System.out.println(b-a);

	}
}
