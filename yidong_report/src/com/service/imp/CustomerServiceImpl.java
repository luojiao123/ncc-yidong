package com.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Pager;
import com.dao.CustomerDao;
import com.entity.Customer;
import com.service.CustomerService;

@Service("customerservice")
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

	private static final long serialVersionUID = -4316645853455050667L;
	
	@Autowired
	private CustomerDao customerdao;
	@Autowired
	public void setBaseDao(CustomerDao dao){
		super.setBasedao(dao);
	}
	
	@Override
	public Customer login(Customer customer) {
		return customerdao.login(customer);
	}
	
	@Override
	public Customer getCustomerByUsername(String username) {
		return customerdao.getCustomerByUsername(username);
	}

	@Override
	public Pager queryAll(Pager pager) {
		return customerdao.queryAll(pager);
	}

}
