package com.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.entity.Operator; 
import com.service.OperatorService;

@Component("operatoraction")
@Scope("prototype")
public class OperatorAction extends BaseAction {

	@Autowired
	private OperatorService operatorservice;
	private Operator operator; 
	private String date;
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	  
	
	public String md5(String plainText)
	{
		StringBuffer buf = new StringBuffer("");
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 

			int i; 

			buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
			buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
			} 

			System.out.println("result: " + buf.toString());//32位的加密 

			System.out.println("result: " + buf.toString().substring(8,24));//16位的加密 

			return buf.toString();
			} catch (NoSuchAlgorithmException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			} 
		    return buf.toString();
	}
	
	
	/**
	 * 客户登陆
	 * @return
	 */
	public String login()
	{
		System.out.println(operator.getLoginName()+"============="+operator.getPassword());
		if(operator.getLoginName()==null || "".equals(operator.getLoginName()))
		{ 
			this.addFieldError("error", "用户名不能为空!"); 
		}
		if(operator.getPassword()==null || "".equals(operator.getPassword()))
		{
			this.addFieldError("error", "密码不能为空!"); 
		}
	    
	    
	    operator.setPassword(this.md5(operator.getPassword()));
	    System.out.println("--------"+operator.getPassword());
		operator=operatorservice.login(operator); 
		
	    //if("yidong".equals(operator.getLoginName()) && "123456".equals(operator.getPassword()))
	   
		if(operator!=null)
	    { 
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");//可以方便地修改日期格式
            date = dateFormat.format(new Date()); 
            //operator =new Operator();
            //operator.setLoginName("yidong");
            //operator.setPassword("123456");
            //operator.setPhone("yidong");
            super.getSession().setAttribute("operator", operator);
	        System.out.println("-------loginaction----"+operator.getPassword());
	    	return "success";
	    }else{
	    	this.addFieldError("error", "用户名或密码错误!");
	    	return "input";
	    }
	}
	 
	
	
/*	//用户退出登陆
	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().removeAttribute("operator");
		return "input";
	}*/
	
}
