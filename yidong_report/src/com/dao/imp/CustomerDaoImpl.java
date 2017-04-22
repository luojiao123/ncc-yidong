package com.dao.imp; 
 
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl; 
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Pager;
import com.dao.CustomerDao;
import com.entity.Customer; 

@Transactional
@Component("customerdao")
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {
   
	@Override
	public Customer login(Customer customer) {
		try {
			String hql="FROM Customer WHERE loginName=? AND password=?";
			Query q=super.getSession().createQuery(hql);
			//设置参数
			q.setString(0, customer.getLoginName());
			q.setString(1, customer.getPassword());
			//执行
			List<Customer> list=q.list();
			if(list.size()!=0 && list!=null)
			{				
				customer=list.get(0);
			}else{
				customer=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			customer=null;
			System.out.println(customer=null);
		}
		return customer;
	}
	
	@Override
	public Customer getCustomerByUsername(String username) {
		String hql = "FROM Customer WHERE login_name=:username";
		return (Customer) super.getSession().createQuery(hql).setParameter("username", username).uniqueResult();
	}

	@Override
	public Pager queryAll(Pager pager) {
		return findPager(Customer.class, pager);
	}
}
