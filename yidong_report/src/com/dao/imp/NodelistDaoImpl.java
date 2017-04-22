package com.dao.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bean.Pager;
import com.dao.NodelistDao;
import com.entity.help.NodeList; 

@Component("nodeListDaoImpl")
@SuppressWarnings("unchecked")
public class NodelistDaoImpl extends BaseDaoImpl implements NodelistDao {

	@Override
	public Pager pageQueryList(Pager pager, int id) {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM Nodelist a INNER JOIN LIST b ON a.listid=b.id WHERE nodeid="+id+"");
		String sqlCount = sb.toString();
		
		StringBuffer sbsql = new StringBuffer(); 
		sbsql.append("SELECT b.title,to_char(b.modtime,'YYYY-MM-DD hh24:mi:ss'),a.flag,a.id FROM Nodelist a ");
		sbsql.append("INNER JOIN ");
		sbsql.append("LIST b ON a.listid=b.id WHERE nodeid="+id+"");
		
		String sql = sbsql.toString();
		return findPagerBySql(pager, sql, sqlCount);
		
	}

	@Override
	public List<NodeList> queryAll(String title){
		
		String temp = "";
		if(title != null && !"".equals(title)) temp += " and b.TITLE= '"+ title +"'";
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT count(*)  FROM Nodelist a INNER JOIN LIST b ON a.listid=b.id WHERE a.flag='S' ");
		sb.append(temp);
		sb.append("UNION ALL ");
		sb.append("SELECT count(*)  FROM Nodelist a INNER JOIN LIST b ON a.listid=b.id WHERE a.flag <> 'S' ");
	    sb.append(temp);
	 
		String sql = sb.toString();
		return (List<NodeList>) querysql(sql);
	  
	}
	
	@Override
	public Pager pageQuery(Pager pager, String title) {
		String temp = "";
		if (title != null && !"".equals(title)) temp += " where b.TITLE= '"+ title +"'";
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) from ( "); 
		sb.append("SELECT sum(case when(a.FLAG = 'S') then 1 else 0 end ) as S, ");
		sb.append("sum(case when(a.flag <> 'S')then 1 else 0 end) as N ");
		sb.append("from NODELIST a INNER JOIN list b on a.LISTID = b.id ");
		sb.append(temp);
		sb.append(" )");
		
		String sqlCount = sb.toString();
		
		
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT sum(case when(a.FLAG = 'S') then 1 else 0 end ) as S, ");
		sbsql.append("sum(case when(a.flag <> 'S')then 1 else 0 end) as N ");
		sbsql.append("from NODELIST a INNER JOIN list b on a.LISTID = b.id ");
		sbsql.append(temp);	
		
		String sql = sbsql.toString();
		return findPagerBySql(pager, sql, sqlCount);
	}

	
}
