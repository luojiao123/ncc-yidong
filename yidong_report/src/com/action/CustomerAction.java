 package com.action;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.common.CommonUtil;
import com.common.Constants;
import com.entity.Customer;
import com.service.CustomerService;
import com.util.ImageUtil;

@Component("customeraction")
@Scope("prototype")
public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 6930556845826152137L;

	private static final String RETURN_JOSN = "customer_json";

	@Autowired
	private CustomerService service;
	private Customer customer;
	private String date;
	private File headerImg;
	

	public String list() {
		
		pager.setPageSize(rows);
		pager.setPageNumber(page);
		pager = service.queryAll(pager);
		
		map.put("total", pager.getTotalCount());
		map.put("rows",pager.getResult());
		
		return RETURN_JOSN;
	}

	public String add() {
		return INPUT;
	}

	public String save() { 
		
		if(customer.getLoginName() == null || "".equals(customer.getLoginName())){

			this.addFieldError("error", "登陆账号不能为空！");
			return INPUT;
			
		}
		Customer c = service.getCustomerByUsername(customer.getLoginName());
		
		if(c != null){
			this.addFieldError("error", "改账号已存在！");
			return INPUT;
		}
		
		String path = "";
		if (headerImg != null) {
			path = uploadHeaderImg(headerImg);
			customer.setHeaderImageUrl(path);
		}
		System.out.println("==================>" + path);
		
	    customer.setPassword(CommonUtil.md5(customer.getPassword()));
		service.save(customer);
		return LIST;
			
		
	}
	
	public String update(){
		
		Customer c = (Customer) service.queryById(Customer.class, id);
		customer.setId(c.getId());
		customer.setPassword(CommonUtil.md5(customer.getPassword()));
		
		if(headerImg != null){
			String path = uploadHeaderImg(headerImg);
			customer.setHeaderImageUrl(path);
		}else{
			customer.setHeaderImageUrl(c.getHeaderImageUrl());
		}
		
		// c = customer;
		service.update(customer);
		return LIST;
	}


	public String edit() {
	 
		customer = (Customer) service.queryById(Customer.class, id);
		customer.setPassword("********");
	    return INPUT;
	    
	}
	
	
	public String delete(){
		
		service.delete(new Customer(), ids);
		map.put("message", "删除成功：");
		return RETURN_JOSN;
	}

	/**
	 * 图片上传
	 * 
	 * @return 返回图片路径
	 */
	private String uploadHeaderImg(File img) {
		String imgPath = ImageUtil.copyImageFile(getServletContext(),
				Constants.CUSTOMER_HEADER_BASER_URL, img);
		return imgPath;
	}

	public File getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(File headerImg) {
		this.headerImg = headerImg;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
