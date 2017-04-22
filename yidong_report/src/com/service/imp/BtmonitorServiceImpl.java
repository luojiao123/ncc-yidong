package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.Pager;
import com.dao.BtmonitorDao;
import com.service.BtmonitorService;

@Service("btmonitorservice")
public class BtmonitorServiceImpl extends BaseServiceImpl implements BtmonitorService {
   
	private static final long serialVersionUID = -8461244568271391426L;

	@Resource(name = "btmonitorDaoImpl")
	public void setBaseDao(BtmonitorDao btdao)
	{
		super.setBasedao(btdao);
	}
	@Resource(name = "btmonitorDaoImpl")
	private BtmonitorDao btdao;
	
	@Override
	public List query(int id) {
		// TODO Auto-generated method stub
		return btdao.query(id);
	}

	@Override
	public Pager pageQuery(Pager pager, int id) {
		// TODO Auto-generated method stub
		return btdao.pageQuery(pager, id);
	}

}
