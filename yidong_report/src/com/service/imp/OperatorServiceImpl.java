package com.service.imp;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OperatorDao;
import com.entity.Operator;
import com.service.OperatorService;

@Service("operatorservice")
public class OperatorServiceImpl extends BaseServiceImpl implements OperatorService {

	@Autowired
	private OperatorDao opdao;
	
	@Autowired
	private void setBaseDao(OperatorDao dao)
	{
		super.setBasedao(dao);
	}

	@Override
	public Operator login(Operator operator) {
		// TODO Auto-generated method stub
		return opdao.login(operator);
	}

}
