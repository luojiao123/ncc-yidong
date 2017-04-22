package com.dao.imp;
 
import java.util.List; 
import org.springframework.stereotype.Component; 
import com.bean.Pager; 
import com.dao.NodeplaylistDao;  
@Component("nodeplaylistDaoImpl")
@SuppressWarnings("unchecked")//对被批注的代码元素内部的某些警告保持静默
public class NodeplaylistDaoImpl extends BaseDaoImpl implements NodeplaylistDao{
	 
    
	@Override
	public Pager recordQuery(Pager pager, String name, String date) { 

		StringBuffer sbsql = new StringBuffer();
//		sbsql.append("SELECT a.filename,to_char(a.CREATETIME,'yyyy-mm-dd hh24:mi:ss'),c.code from NODEPLAYLIST a ")
//		.append("INNER JOIN NODELIST b on a.nodelistid = b.id INNER JOIN NODE c on b.nodeid = c.id ")
//		.append("WHERE c.code = '").append(name).append("' ")
//		.append("and a.CREATETIME >= to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss') and a.CREATETIME < to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss')+1");
		 
        sbsql.append("SELECT FILENAME,to_char(CREATETIME, 'yyyy-mm-dd hh24:mi:ss') from NODEPLAYLIST WHERE stb_num = (SELECT stb_num from STB where DES = '").append(name).append("') ")
        .append(" and to_char(CREATETIME,'yyyy-mm-dd')=to_char(to_date('"+date+"','yyyy-mm-dd'),'yyyy-mm-dd') order by CREATETIME ");
		

		String sql = sbsql.toString(); 
		
		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT count(*) from NODEPLAYLIST a ")
//		.append("INNER JOIN NODELIST b on a.nodelistid = b.id INNER JOIN NODE c on b.nodeid = c.id ")
//		.append("WHERE c.code = '").append(name).append("' ")
//		.append("and a.CREATETIME >= to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss') and a .CREATETIME < to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss')+1");
		
		sb.append("SELECT COUNT(*) from NODEPLAYLIST WHERE stb_num = (SELECT stb_num from STB where DES = '").append(name).append("') ")
		.append("and to_char(CREATETIME,'yyyy-mm-dd')=to_char(to_date('"+date+"','yyyy-mm-dd'),'yyyy-mm-dd')");
		
		String sqlCount = sb.toString();

		return findPagerBySql(pager, sql, sqlCount);
	}

	@Override
	public List Querysql(String name, String date) {

		StringBuffer sbsql = new StringBuffer();
//		sbsql.append("SELECT a.filename,to_char(a.CREATETIME,'yyyy-mm-dd hh24:mi:ss'),c.code from NODEPLAYLIST a ")
//		.append("INNER JOIN NODELIST b on a.nodelistid = b.id INNER JOIN NODE c on b.nodeid = c.id ")
//		.append("WHERE c.code = '").append(name).append("' ")
//		.append("and a.CREATETIME >= to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss') and a.CREATETIME < to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss')+1");
//		  
		 //此语句错误，会导致分页不正常
		 //sbsql.append("SELECT FILENAME,to_char(CREATETIME, 'yyyy-mm-dd hh24:mi:ss') from NODEPLAYLIST WHERE stb_num = (SELECT stb_num from STB where DES = '").append(name).append("') ")
	     //.append("and NODEPLAYLIST.CREATETIME >= to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss') and NODEPLAYLIST.CREATETIME < to_date('").append(date).append("','yyyy-mm-dd hh24:mi:ss')+1 ORDER BY CREATETIME");
		 sbsql.append("SELECT FILENAME,to_char(CREATETIME, 'yyyy-mm-dd hh24:mi:ss') from NODEPLAYLIST WHERE stb_num = (SELECT stb_num from STB where DES = '").append(name).append("') ")
	     .append(" and to_char(CREATETIME,'yyyy-mm-dd')=to_char(to_date('"+date+"','yyyy-mm-dd'),'yyyy-mm-dd') order by CREATETIME ");
				
		String sql = sbsql.toString();  

		return querysql(sql);
	}

	
	
}
