package com.dao;

import java.util.List;
 

import com.bean.Pager; 
import com.entity.Node;
import com.entity.Nodedevice;  
/**
 * dao接口 -Node
 * @author luojiao
 *
 */
public interface NodeDao extends BaseDao {
	/**
	 * 获得顶层节点数据
	 * @return
	 */
	List<Node> getParentAll();
	/**
	 *获得子节点数据
	 * @return
	 */
	List<Node> getChildren(Integer parentid);

	int select(String queryString); 
	
	List<Nodedevice> queryByCustomerPhone(String phone);
	
	List<Nodedevice> queryAll(); 
	 
	public Pager pageQuery(Pager pager, String customerPhone, String area, String city, String code,
			String storename, String clientele, String status);
	 
	List searchName(String customerPhone);
	public List query(String customerPhone);
}
