package com.service;

import java.util.List;

import com.bean.Pager;

public interface BtmonitorService extends BaseService {


	List query(int id);
	public Pager pageQuery(Pager pager,int id);
}
