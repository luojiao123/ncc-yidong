package com.action;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
 
import com.entity.Btmonitor;   
import com.service.BtmonitorService;  

@Component("btmonitor")
public class BtmonitorAction extends BaseAction {
 
	private static final long serialVersionUID = -3568597999198488820L;
	 
	private List<Btmonitor> list;
	private int id; 
	 
	
	
	@Autowired
	private BtmonitorService service;
   
	public List<Btmonitor> getList() {
		return list;
	}
	public void setList(List<Btmonitor> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
  
	
	/**
	 * 查询全部
	 * @method: query() -by fjt
	 * @TODO:  
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String query()
	{
		System.out.println("-----id- --"+id);
		list= service.query(id);  
		return "query";
	}
	
	
	/**
	 * 分页查询 hql 显示详细信息
	 * 
	 * @return
	 */
	public String pageQuery() {   
		 
	 
		//当前页
	    pager.setPageNumber(page);
	    //每页显示条数
	    pager.setPageSize(rows);
	    
	    pager = service.pageQuery(pager, id);
	    map = new HashMap<String, Object>();
	    List<Btmonitor> btmonitor = new ArrayList<Btmonitor>();
	    List<Object[]> list = (List<Object[]>) pager.getResult();
	    if(list.size() > 0)
	    {
	    	for(Object[] obj :list)
	    	{
	    		Btmonitor bt = new Btmonitor();
	    		bt.setContent((String)obj[0]);
	    		bt.setFlag((String)obj[1]);
	    		
	    		btmonitor.add(bt);
	    	}
	    	map.put("total", pager.getTotalCount());
	    	map.put("rows", btmonitor);  
	    } 
		return "pagequery";
	} 
	
	
}
