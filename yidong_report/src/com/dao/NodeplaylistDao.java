package com.dao;
 
import java.util.List; 
import com.bean.Pager;  

/**
 * dao接口 -Node
 * @author luojiao
 *
 */
public interface NodeplaylistDao extends BaseDao {
	  
	public Pager recordQuery(Pager pager, String name, String date);
	public List Querysql(String name, String date);
}
