package com.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OperatorDao; 
import com.entity.Operator;

@Transactional
@Component("operatordao")
public class OperatorDaoImpl extends BaseDaoImpl implements OperatorDao {

	@Override
	public Operator login(Operator operator) {
		try {
			String hql="FROM Operator WHERE login_name=? AND PASSWORD=?";
			Query q=super.getSession().createQuery(hql);
			//设置参数
			q.setString(0, operator.getLoginName());
			q.setString(1, operator.getPassword());
			//执行
			List<Operator> list=q.list();
			if(list.size()!=0 && list!=null)
			{				
				operator=list.get(0);
			}else{
			    operator=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			operator=null;
			System.out.println(operator=null);
		}
		return operator;
	}

}
