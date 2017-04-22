package com.action;

import java.util.Date;
 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.CommonUtil;
import com.entity.Customer;
import com.service.CustomerService;
import com.shiro.CaptchaUsernamePasswordToken;
import com.shiro.ShiroUser;

public class SystemAction extends BaseAction{

	private static final long serialVersionUID = -5032746573352067642L;

	@Autowired
	private CustomerService service;	
	
	private String userName;
	private String password;
	private String captcha;
	private Date loginDate;
	
	private Customer customer;
	
	/**
	 * 登录
	 * 
	 * @return
	 */
	public String load() {
		boolean flag = true;
		Subject subject = SecurityUtils.getSubject();

		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		if(userName==null || "".equals(userName))
		{
			this.addFieldError("error", "用户名不能为空!");
			return "login";
		}
		if(password == null || "".equals(password))
		{
			this.addFieldError("error", "密码不能为空!");
			return "login";
		}
		token.setUsername(userName);// 用户名 
		token.setPassword(CommonUtil.md5(password).toCharArray());// 密码 
		//token.setCaptcha(captcha);// 验证码
		token.setRememberMe(true);// 是否记住用户

		
		try {
			subject= SecurityUtils.getSubject();
			
			subject.login(token);
			System.out.println("sessionTimeout===>"+ subject.getSession().getTimeout());
			loginDate =  new Date();
			customer = service.getCustomerByUsername(userName);
			flag = true;
			
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			this.addFieldError("error", "用户名不存在");
			flag = false;
		} catch (UnknownAccountException ex) {
			// 帐号错误
			this.addFieldError("error", "帐号错误");
			flag = false;
		} catch (IncorrectCredentialsException ice) {
			// 密码错误
			this.addFieldError("error", "密码错误");
			flag = false;
		} catch (LockedAccountException lae) {
			// 帐号被锁
			this.addFieldError("error", "帐号被锁");
			flag = false;
		}  catch (AuthenticationException ae) {
			// 没有权限
			this.addFieldError("error", "没有权限");
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		if(flag){
			return "success";
		}else{
			return "login";
		}
	}
	/**
	 * 获取所有权限
	 */
	public void getMenuList(){
	}
	/**
	 * 登出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		SecurityUtils.getSubject().logout();
		return "login";
	}
	
	public String welcome(){
		ShiroUser shiroUser = CommonUtil.getCurrendUser();
		String account = shiroUser.getAccount();
		customer = service.getCustomerByUsername(account);
		loginDate = shiroUser.getLoginTime();
		return "welcome";
	}
	/*
	 * 当前在线用户
	 */
	public void getOnlineLoginUsers(){
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
