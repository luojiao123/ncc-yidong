package com.dao;

import com.bean.Pager;
import com.entity.Customer;

public interface CustomerDao extends BaseDao {

   Customer login(Customer customer);
   Customer getCustomerByUsername(String username);
   Pager queryAll(Pager pager);
	
}
