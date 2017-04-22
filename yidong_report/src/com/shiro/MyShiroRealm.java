package com.shiro;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext; 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.Constants;
import com.entity.Customer;
import com.service.CustomerService;
/**
 * realm
 * @author linzekuan
 *
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private CustomerService service;
	 // 用于获取用户信息及用户权限信息的业务接口 
	private SessionFactory hibernateSessionFactory;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		ShiroUser shiroUser = (ShiroUser) getAvailablePrincipal(principals);
		String username = shiroUser.getAccount();
		Customer ct=service.getCustomerByUsername(username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		System.out.println("ct.getPhone():"+ct.getPhone());
	 
		if(ct.getType() == 1){
			info.addStringPermission("admin");
			
		}else{
			
			info.addStringPermission("customer");
		}
		
		
		/*if(!username.isEmpty()){

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			List<Permission> permissionList = permissionService.getPermissionByUserName(username);
			
			for (Permission permission : permissionList) {
				info.addStringPermission(permission.getCode());
			}
			return info;
		}*/
		//SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	// 获取认证信息
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String accoun = token.getUsername();
		//验证码    if (accoun != null && !"".equals(accoun) && doCaptchaValidate(token))
		if (accoun != null && !"".equals(accoun)) {
			
			Customer ct= null;
			try {
				ct = service.getCustomerByUsername(accoun);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			if (ct != null) {
				Subject subject = SecurityUtils.getSubject();
				String lognIp = ServletActionContext.getRequest().getRemoteHost();
				ShiroUser shiroUser = new ShiroUser(ct.getId()+"",
													ct.getLoginName(),
													new Date(),
													lognIp);
				subject.getSession().setAttribute(Constants.SHIRO_USER,shiroUser);
				return new SimpleAuthenticationInfo(shiroUser,ct.getPassword(),ct.getLoginName());
			}
		}
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */

	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	// 验证码校验
//	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
//		String captcha = (String) ServletActionContext
//				.getRequest()
//				.getSession()
//				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
//			throw new IncorrectCaptchaException("验证码错误！");
//		}
//		return true;
//	}

	public SessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(SessionFactory hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}
	
	
}
