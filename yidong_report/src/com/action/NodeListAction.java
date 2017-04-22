package com.action;
 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
  
import com.entity.help.NodeList;  
import com.service.NodelistService; 

@Component("nodelist")
public class NodeListAction extends BaseAction {
 
	private static final long serialVersionUID = -6043870646143146546L;
	 
	@Autowired
	private NodelistService service; 
	private int id; 
	private List list;
	private String title; 
	 
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}  
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
	
	
	/**
	 * 分页查询 hql 显示指定门店列表的详细信息
	 * 
	 * @return
	 */
	public String pageQueryList() { 
		 
		pager.setPageNumber(page);
		pager.setPageSize(rows);
		pager = service.pageQueryList(pager, id);
	 
		map = new HashMap<String, Object>();
		List<NodeList> nll = new ArrayList<NodeList>(); 
		List<Object[]> list  = (List<Object[]>) pager.getResult();
		if (list.size() > 0) {
			for (Object[] obj : list) {
				
				NodeList nl=new NodeList(); 
				nl.setTitle(obj[0]); 
				nl.setTime(obj[1]); 
				nl.setFlag(obj[2]);
				nl.setId(obj[3]);

				nll.add(nl);

			} 
			map.put("total", pager.getTotalCount());
			map.put("rows", nll);  
		} 
		return "pagequerylist";
	}
	
	
	/**
	 * 查询全部
	 * @method: query() 
	 * @TODO:  
	 * @return String
	 */
	public String query()
	{
		System.out.println("----------nodelistaction----------query--title:"+title);
		list = service.queryAll(title);  
		return "queryall";
	}
	
	
	/**
	 * 分页查询 hql 显示详细信息
	 * 
	 * @return
	 */
	public String pageQuery() { 
 
		pager.setPageNumber(page);
		pager.setPageSize(rows);
		pager = service.pageQuery(pager, title);

		map = new HashMap<String, Object>();
		List<NodeList> li = new ArrayList<NodeList>();
		List<Object[]> list = (List<Object[]>) pager.getResult();
		if (list.size() > 0) {
			for (Object[] obj : list) {
				
				NodeList nl=new NodeList(); 
				nl.setS(obj[0]); 
				nl.setN(obj[1]);
				nl.setTitle(title);
                System.out.println(obj[0]+"----obj----"+obj[1]+"===="+title);
				li.add(nl);

			} 
			map.put("total",pager.getTotalCount());
			map.put("rows", li); 
		} 
		return "pagequery";
	}
	
}
