package com.service;

import com.bean.Pager;
import com.entity.Customer;

public interface CustomerService extends BaseService {

	public Customer login(Customer customer);
	
	public Customer getCustomerByUsername(String username);
	
	public Pager queryAll(Pager pager);
}
