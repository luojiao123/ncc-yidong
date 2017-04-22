package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Pager;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.handlers.image_gif;

public class BaseAction extends ActionSupport {
	/**
	 * josn 返回
	 */
	protected static final String JSON_RETURN = "json_return";
	
	protected static final String LIST = "list";
	protected static final String INPUT = "input";
	
	/**
	 * 分页实体类
	 */
	protected Pager pager = new Pager();
	/**
	 * 用于封装json格式的Key/Value数据
	 */
	protected Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 当前第几页
	 */
	protected int page;
	/**
	 * 每页显示的记录数
	 */
	protected int rows; 
	
	protected Integer id;
	protected String[] ids;
	
	

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到session对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * 得到applicaiton对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取ServletContext
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public void OutputJson(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			Gson g = new Gson();
			json = g.toJson(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	// 判断是否为添加
	public Boolean getIsAddAction() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}

	// 判断是否为编辑
	public Boolean getIsEditAction() {
		if (id != null) {
			return true;
		} else {
			return false;
		}
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	
	
}
