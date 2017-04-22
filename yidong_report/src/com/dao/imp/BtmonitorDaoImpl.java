package com.dao.imp;

import java.util.List;
import org.springframework.stereotype.Component;
import com.bean.Pager;
import com.dao.BtmonitorDao;
 
@Component("btmonitorDaoImpl")
@SuppressWarnings("unchecked")//对被批注的代码元素内部的某些警告保持静默
public class BtmonitorDaoImpl extends BaseDaoImpl implements BtmonitorDao {

	@Override
	public List query(int id) { 
		
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT Btmonitor.flag,COUNT(*) AS COUNT FROM Btmonitor ");
		sbsql.append("INNER JOIN Nodelist ON Btmonitor.nodelistid=Nodelist.id WHERE Nodelist.flag='S' AND Btmonitor.nodelistid="+id+" GROUP BY Btmonitor.flag");
		String sql =sbsql.toString();
		System.out.println(sql);
		return (List)querysql(sql);
	}

	@Override
	public Pager pageQuery(Pager pager,int id) { 
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM Btmonitor a INNER JOIN Nodelist b ON a.NODELISTID=b.ID WHERE  a.nodelistid="+id+"");
		String sqlCount = sb.toString();
		
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT a.CONTENT,a.FLAG FROM Btmonitor a INNER JOIN Nodelist b ON a.NODELISTID=b.ID WHERE a.nodelistid="+id+"");
		String sql = sbsql.toString();
		return findPagerBySql(pager, sql, sqlCount);
	}

	
}
