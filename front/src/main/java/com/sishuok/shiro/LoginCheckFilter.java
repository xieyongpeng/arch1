package com.sishuok.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.sishuok.architecture1.customermgr.bean.CustomerModel;


public class LoginCheckFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		
		System.out.println("req.getRequestURI()=="+req.getRequestURI());
		
		if(req.getRequestURI().contains("toLogin") || req.getRequestURI().contains("login")){
			System.out.println("to back==========>");
			chain.doFilter(request, response);
		}else{
			ApplicationContext ctx = (ApplicationContext)req.getServletContext()
					.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			
			DefaultWebSecurityManager sm = (DefaultWebSecurityManager)ctx.getBean("securityManager");
			SecurityUtils.setSecurityManager(sm);
			Subject currentUser = SecurityUtils.getSubject();
			
			Object obj = currentUser.getSession().getAttribute("Login_Customer");
			
			if(obj==null){
				req.getRequestDispatcher("/toLogin").forward(request, rep);
			}else{
				CustomerModel cm = (CustomerModel)obj;
				if(cm==null || cm.getCustomerId()==null || cm.getCustomerId().trim().length()==0){
					req.getRequestDispatcher("/toLogin").forward(request, rep);	
				}else{
					chain.doFilter(request, response);
				}
			}	
		}
	}

	public void destroy() {
	}

}
