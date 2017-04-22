package com.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.Pager;
import com.dao.NodelistDao;
import com.entity.help.NodeList;
import com.service.NodelistService;
 
@Component("nodelistService")
@SuppressWarnings("unchecked")
public class NodelistServiceImpl extends BaseServiceImpl implements NodelistService{
  
	private static final long serialVersionUID = 520589840973851464L;

	@Autowired
	private NodelistDao ndao;
	
	@Override
	public Pager pageQueryList(Pager pager, int id) {
		 
		return ndao.pageQueryList(pager, id);
	}

	@Override
	public	List<NodeList> queryAll(String title){
		return ndao.queryAll(title);
	}
	@Override
	public Pager pageQuery(Pager pager, String title) {
	 
		return ndao.pageQuery(pager, title);
	}

	
}
