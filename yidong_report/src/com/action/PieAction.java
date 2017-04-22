package com.action;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;

import com.google.gson.Gson; 
import com.entity.*; 
import com.opensymphony.xwork2.ActionSupport; 
import com.service.OperatorService;

@Component("pie")
public class PieAction extends ActionSupport {
 
	@Autowired
	private OperatorService service;
	private Map map;
	private String page;//当前第几页
	private String rows;//每页显示的记录数
	private Nodedevice nd;
	private List<Nodedevice> list;
	private long count;
	
	
	
 
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Nodedevice getNd() {
		return nd;
	}

	public void setNd(Nodedevice nd) {
		this.nd = nd;
	}

	public List<Nodedevice> getList() {
		return list;
	}

	public void setList(List<Nodedevice> list) {
		this.list = list;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

 

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	} 

 
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/** 
	 * 查询全部
	 */
	public String query()
	{
 
		List<Object[]> lists= (List<Object[]>) service.querysql("SELECT a.ID,COUNT(brand) as brand FROM Node AS a INNER JOIN Nodedevice AS b ON a.id=b.nodeid WHERE activestatus='A' UNION ALL SELECT a.ID,COUNT(brand) as brand FROM Node AS a INNER JOIN Nodedevice AS b ON a.id=b.nodeid WHERE activestatus='W' UNION ALL SELECT a.ID,COUNT(brand) as brand FROM Node AS a LEFT JOIN Nodedevice AS b ON a.ID = b.NODEID WHERE b.ID IS NULL");
		Map<Object,Object> map=new HashMap<Object,Object>();
		
		List<AWScale> alist=new ArrayList<AWScale>();
		if(lists.size()>0)
		{
			for (Object[] obj : lists) {
			
				AWScale scale=new AWScale();
				scale.setId(obj[0]);
				scale.setBrand(obj[1]);
				
				alist.add(scale);
			}
			map.put("total",alist.size());
			map.put("rows",alist);
			this.map=map;
			//转换为json格式 
    		Gson g=new Gson();
    		String str=g.toJson(map);
    		System.out.println(str);
		} 
		return "query";
		 
	}

	
	/**
	 * 查询所有
	 * @return
	 */
	public String queryAll()
	{
		System.out.println("-----pie queryall------");
		list=(List<Nodedevice>)service.querysql("SELECT a.id,COUNT(brand) as brand FROM Node AS a INNER JOIN Nodedevice AS b ON a.id=b.nodeid WHERE activestatus='A' UNION ALL SELECT a.id,COUNT(brand) as brand FROM Node AS a INNER JOIN Nodedevice AS b ON a.id=b.nodeid WHERE activestatus='W' UNION ALL SELECT a.id,COUNT(brand) as brand FROM Node AS a LEFT JOIN Nodedevice AS b ON a.id = b.Nodeid WHERE b.id IS NULL");
		if(list.size()>0)
		{
			//转换为json格式
			Gson g=new Gson();
			String str=g.toJson(list);
			System.out.println(str);
		}
		return "queryall";
	}
	
	
	/**
	 * 分页查询
	 * @return
	 */
	public String pageQuery()
	{
		//当前页
		int intPage=Integer.parseInt((page == null || page == "0") ? "1" : page);
		//每页显示条数
		int number =Integer.parseInt((page == null || page == "0") ? "1" : rows);
		
		
		this.count=service.queryCount("SELECT count(*) FROM Node AS a,Nodedevice AS b where a.id=b.nodeid");
		String hql="SELECT a.id,a.name,a.dis,a.code,a.brand,b.devicetype,b.status FROM Node AS a,Nodedevice AS b where a.id=b.nodeid";
		//String hql="SELECT a.id,b.id,a.name,a.code,a.dis,b.devicetype,b.status FROM Node AS a , Nodedevice AS b WHERE a.id=b.nodeid AND activestatus='A' UNION ALL SELECT a.id,b.id,a.name,a.code,a.dis,b.devicetype,b.status FROM Node AS a , Nodedevice AS b WHERE a.id=b.nodeid AND activestatus='W' UNION ALL SELECT a.id,b.id,a.name,a.code,a.dis,b.devicetype,b.status FROM Node AS a LEFT JOIN Nodedevice AS b  ON a.id = b.nodeid WHERE b.id IS NULL";
		List<Object[]> list =(List<Object[]>) service.queryByParameter(hql, intPage, number);
		
		map=new HashMap<String, Object>();
		List<NDScale> ndscalelist=new ArrayList<NDScale>();
		if(list.size()>0){
			for(Object[] obj:list)
			{ 
				NDScale ns=new NDScale(); 
				ns.setBid(obj[0]);
				ns.setName(obj[1]);
				ns.setDis(obj[2]);
				ns.setCode(obj[3]);
				ns.setBrand(obj[4]);
				ns.setDevicetype(obj[5]);
				ns.setStatus(obj[6]); 
				
				ndscalelist.add(ns);
			}
			map.put("total", count);
			map.put("rows", ndscalelist);
			this.map=map;
			
			Gson g=new Gson();
			String str=g.toJson(map);
			System.out.println(str);
		}
		return "pagequery";
	}
}
