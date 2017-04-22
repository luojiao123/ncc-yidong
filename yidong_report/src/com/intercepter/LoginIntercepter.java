package com.intercepter;   
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
 

public class LoginIntercepter implements Interceptor{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
	        
		   // 如果没有登陆,返回重新登陆  	  
	          
	            return invocation.invoke();  
	         
	}

}
  