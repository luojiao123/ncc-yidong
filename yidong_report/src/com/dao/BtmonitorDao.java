package com.dao;

import java.util.List;

import com.bean.Pager;

public interface BtmonitorDao extends BaseDao {

	List query(int id);
	public Pager pageQuery(Pager pager,int id);
}
