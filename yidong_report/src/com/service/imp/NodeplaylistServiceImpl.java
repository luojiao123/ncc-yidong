package com.service.imp;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.Pager;
import com.dao.CustomerDao; 
import com.dao.NodeplaylistDao; 
import com.service.NodeplaylistService;
/**
 * service实现类 -node
 * @author aa
 *
 */
@Service("nodeplaylistservice")
public class NodeplaylistServiceImpl extends BaseServiceImpl implements NodeplaylistService {
	
	private static final long serialVersionUID = 9107621361426650326L;

	@Resource(name = "nodeplaylistDaoImpl")
	public void setBasedao(NodeplaylistDao nodeDao) {
		
		super.setBasedao(nodeDao);
	}
	@Resource(name = "nodeplaylistDaoImpl")
	private NodeplaylistDao nplDao;
	@Resource(name = "customerdao")
	private CustomerDao customerDao; 
	 
	@Override
	public Pager recordQuery(Pager pager, String name, String date) {
		// TODO Auto-generated method stub
		return nplDao.recordQuery(pager, name, date);
	}
	@Override
	public List Querysql(String name, String date) {
		// TODO Auto-generated method stub
		return nplDao.Querysql(name, date);
	}
}
