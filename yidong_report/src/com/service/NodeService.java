package com.service;

import java.util.List;

import com.bean.Pager;
import com.entity.Customer;
import com.entity.Node;
import com.entity.Nodedevice;
import com.entity.help.StoreMessage;
import com.help.tree.TreeModel;
 
/**
 * Service接口
 * @author aa
 *
 */
public interface NodeService extends BaseService{
	 
	List<TreeModel> getNodeTreelist ();
	int select(String queryString);
	List<Nodedevice> queryAll(String customerAccount); 
	
	public Pager pageQuery(Pager pager,String customerPhone,String area,String city,String code,String storename,String clientele,String status);

	List searchName(String customerPhone);
	public List query(String customerPhone);
}
