package com.service;
 
import java.util.List;

import com.bean.Pager; 
 
/**
 * Service接口
 * @author aa
 *
 */
public interface NodeplaylistService extends BaseService{
	  
	public Pager recordQuery(Pager pager, String name, String date);
	public List Querysql(String name, String date);
}
