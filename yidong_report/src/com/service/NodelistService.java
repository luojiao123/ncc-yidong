package com.service;

import java.util.List;

import com.bean.Pager;
import com.entity.help.NodeList;

public interface NodelistService extends BaseService {

	public Pager pageQueryList(Pager pager, int id);
	
	List<NodeList> queryAll(String title); 
	 
	public Pager pageQuery(Pager pager,String title);
	
}
