package com.shiro;

import java.io.Serializable;
import java.util.Date;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1748602382963711884L;
	private String userId;
	private String account;
	private Date loginTime;
	private String loginIp;

	

	public ShiroUser(String userId, String account, Date loginTime,
			String loginIp) {
		super();
		this.userId = userId;
		this.account = account;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public String toString() {
		return account;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	
}
