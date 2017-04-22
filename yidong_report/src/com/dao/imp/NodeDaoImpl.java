package com.dao.imp;

import java.util.List;
 
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.bean.Pager;
import com.dao.NodeDao; 
import com.entity.Node;
import com.entity.Nodedevice; 
@Component("nodeDaoImpl")
@SuppressWarnings("unchecked")//对被批注的代码元素内部的某些警告保持静默
public class NodeDaoImpl extends BaseDaoImpl implements NodeDao{
	/**
	 * 查询顶级节点
	 */
	@Override
	public List<Node> getParentAll() {
		String hql = "from Node as node where parentid=:id";
		Query query = getSession().createQuery(hql);
		query.setInteger("id", 0);
		return query.list();
	}

	/**
	 * 查询子集节点
	 */ 
	@Override
	public List<Node> getChildren(Integer parentid) {
		String hql = "from Node as node where parentid=:parentid";
		Query query = getSession().createQuery(hql);
		query.setInteger("parentid", parentid);
		return query.list();
	}

	@Override
	public int select(String queryString) {
		// TODO Auto-generated method stub
		
		return (Integer)getSession().createQuery(queryString).uniqueResult();
	}

	@Override
	public List<Nodedevice> queryByCustomerPhone(String phone) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT CAST(0 AS DECIMAL),COUNT(*) FROM Node t " );
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND t2.NAME='").append(phone).append("' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) IS NULL ");
		sbSql.append("UNION ALL ");
		sbSql.append("SELECT CAST(1 AS DECIMAL),COUNT(*) FROM Node t ");
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND t2.NAME='").append(phone).append("' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) <= 120 ");
		sbSql.append("UNION ALL ");
		sbSql.append("SELECT CAST(2 AS DECIMAL),COUNT(*) FROM Node t ");
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND t2.NAME='").append(phone).append("' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) >120");
		
		String sql= sbSql.toString();
		return  (List<Nodedevice>) querysql(sql);
	}

	@Override
	public List<Nodedevice> queryAll() {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT CAST(0 AS DECIMAL),COUNT(*) FROM Node t " );
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) IS NULL ");
		sbSql.append("UNION ALL ");
		sbSql.append("SELECT CAST(1 AS DECIMAL),COUNT(*) FROM Node t ");
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) <= 120 ");
		sbSql.append("UNION ALL ");
		sbSql.append("SELECT CAST(2 AS DECIMAL),COUNT(*) FROM Node t ");
		sbSql.append("LEFT JOIN Node t2 ON t.id=t2.parentid ");
		sbSql.append("LEFT JOIN node t3 ON t2.id=t3.parentid ");
		sbSql.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ");
		sbSql.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ");
		sbSql.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) >120");
		
		String sql= sbSql.toString();
		return (List<Nodedevice>) querysql(sql);
	} 
	

	@Override

	public Pager pageQuery(Pager pager, String customerPhone, String area, String city, String code,
			String storename, String clientele, String status) {
		
		/*****************************************************************/
		String temp = "";
		if (area != null && !"".equals(area)) temp += " and t3.NAME LIKE '%" + area + "%'";
		if (city != null && !"".equals(city)) temp += " and t4.NAME LIKE '%" + city + "%'";
		if (code != null && !"".equals(code)) temp += " and t5.CODE LIKE '%" + code + "%'";
		if (storename != null && !"".equals(storename)) temp += " AND t5.NAME LIKE '%" + storename + "%'";
		if (clientele != null && !"".equals(clientele)) temp += " AND t5.TRADEZONENAME_RSC LIKE '%" + clientele + "%'";
		if (status != null && !"".equals(status)) {
			//1代表在线，2代表离线，0代表无设备门店 
			if("1".equals(status)) temp += " AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) <=120";
			else if("2".equals(status)) temp += " AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) >120";
			else if("0".equals(status)) temp += " AND ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) is null";
		}
		/*****************************************************************/

		String sqlCount = "";
		String sql = "";
		
		if(customerPhone == null || "".equals(customerPhone)){
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT COUNT(*) FROM Node t ") 
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ") 
			.append("LEFT JOIN Node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid ")		
			.append("WHERE t.parentid='0' ")
			.append(temp);
			
			
			sqlCount = sb.toString();
		
			StringBuffer sbsql = new StringBuffer();
			/*SELECT t3. NAME AS AREA,t4. NAME AS city,t5.code AS CODE,t5. NAME AS storename,t5.TRADEZONENAME_RSC AS trademark,
			ROUND (TO_NUMBER (SYSDATE - t6.requesttime) * 24 * 60) AS STATUS,
			(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A 
			INNER JOIN LIST b ON A .listid = b. ID WHERE A .flag = 'S' AND nodeid = t6.nodeid ) AS downsucesslist,
			(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A
			INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid ) AS newestlist,
			(SELECT a.FLAG FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid and b.MODTIME= 
			(SELECT MAX(b.modtime) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid)) AS newestlistflag
			FROM Node T
			LEFT JOIN Node t2 ON T . ID = t2.parentid
			LEFT JOIN node t3 ON t2. ID = t3.parentid
			LEFT JOIN Node t4 ON t3. ID = t4.parentid
			LEFT JOIN Node t5 ON t4. ID = t5.parentid
			LEFT JOIN Nodedevice t6 ON t5. ID = t6.nodeid 
			WHERE T .parentid = '0'*/
			
			sbsql.append("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE A .flag = 'S' AND nodeid = t6.nodeid ) AS downsucesslist, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid ) AS newestlist, ")
			.append("(SELECT a.FLAG FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid and b.MODTIME= (SELECT MAX(b.modtime) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid)) AS newestlistflag ")
			.append("FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' ")
			.append(temp);
			sql = sbsql.toString() ;
		}else{
		
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT COUNT(*) FROM Node t ") 
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ") 
			.append("LEFT JOIN Node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid ")		
			.append("WHERE t.parentid='0' and t2.NAME='").append(customerPhone).append("'")
			.append(temp);
			
			
			sqlCount = sb.toString();
		
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE A .flag = 'S' AND nodeid = t6.nodeid ) AS downsucesslist, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid ) AS newestlist, ")
			.append("(SELECT a.FLAG FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid and b.MODTIME= (SELECT MAX(b.modtime) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid)) AS newestlistflag ")
			.append("FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' and t2.NAME='").append(customerPhone).append("'")
			.append(temp);
			sql = sbsql.toString() ;
		}
		return findPagerBySql(pager, sql,sqlCount);
	}
 
	
	@Override
	public List query(String customerPhone){ 
		String sql = "";
		
		if(customerPhone == null || "".equals(customerPhone)){
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE A .flag = 'S' AND nodeid = t6.nodeid ) AS downsucesslist, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid ) AS newestlist, ")
			.append("(SELECT a.FLAG FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid and b.MODTIME= (SELECT MAX(b.modtime) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid)) AS newestlistflag ")
			.append("FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' "); 
			sql = sbsql.toString() ;
		}else{
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE A .flag = 'S' AND nodeid = t6.nodeid ) AS downsucesslist, ")
			.append("(SELECT MAX (TO_CHAR (b.modtime,'YYYY-MM-DD hh24:mi:ss')) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid ) AS newestlist, ")
			.append("(SELECT a.FLAG FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid and b.MODTIME= (SELECT MAX(b.modtime) FROM Nodelist A INNER JOIN LIST b ON A .listid = b. ID WHERE nodeid = t6.nodeid)) AS newestlistflag ")
			.append("FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' and t2.NAME='").append(customerPhone).append("'");
		
			sql = sbsql.toString() ;
		}
		return querysql(sql);
	}
	
	
	
	@Override
	public List searchName(String customerPhone) { 
		String sql = "";
		
		if(customerPhone == null || "".equals(customerPhone)){
			  
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("SELECT t5.name AS name,t5.code AS CODE,t5.id AS id FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' ");
			 
			sql = sbsql.toString() ;
		}else{ 
			
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("SELECT t5.name AS name,t5.code AS CODE,t5.id AS id FROM Node t ")
			.append("LEFT JOIN Node t2 ON t.id=t2.parentid ")
			.append("LEFT JOIN node t3 ON t2.id=t3.parentid ")
			.append("LEFT JOIN Node t4 ON t3.id=t4.parentid ")
			.append("LEFT JOIN Node t5 ON t4.id=t5.parentid ")
			.append("LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' and t2.NAME='").append(customerPhone).append("'");
			 
			sql = sbsql.toString() ;
		}
		return (List)querysql(sql);
	}

 

	
	
}
