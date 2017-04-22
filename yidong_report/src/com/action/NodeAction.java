package com.action;
 
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;   

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.CommonUtil;
import com.entity.Customer;
import com.entity.Node;
import com.entity.Nodedevice; 
import com.entity.help.StoreMessage; 
import com.service.CustomerService;
import com.service.NodeService;
import com.shiro.ShiroUser;
import com.excel.*; 
@Component("nodeaction")
public class NodeAction extends BaseAction {
	private static final long serialVersionUID = -2062477908939584376L;

	@Resource(name = "nodeservice")
	private NodeService nodeService;
	@Autowired
	private CustomerService service;

	private Node node; 
	 
	private long count;
	private List<Nodedevice> list;
	private List<StoreMessage> store;
	private StoreMessage sm;
	private String area;
	private String city;
	private String code;
	private String storename;
	private String clientele;
	private String status;
 
		/**
	 * 查询全部
	 * 
	 * @return
	 */
	public String QueryAll() {    
		ShiroUser shiroUser = CommonUtil.getCurrendUser();
	    list = nodeService.queryAll(shiroUser.getAccount());
		return "queryall";
	}

	 
	/**
	 * 分页查询 hql 显示详细信息
	 * 
	 * @return
	 */
	public String pageQuery() {
		ShiroUser shiroUser = CommonUtil.getCurrendUser(); 
		Customer customer = service.getCustomerByUsername(shiroUser.getAccount());
		pager.setPageNumber(page);
		pager.setPageSize(rows); 
	    pager = nodeService.pageQuery(pager,customer.getPhone(), area,city,code,storename,clientele,status);
		map = new HashMap<String, Object>();
		List<StoreMessage> storemessage = new ArrayList<StoreMessage>();
		List<Object[]> list = (List<Object[]>) pager.getResult();
		if (list.size() > 0) {
			for (Object[] obj : list) {
				StoreMessage sm = new StoreMessage();
				sm.setArea(obj[0]);
				sm.setCity(obj[1]);
				sm.setCode(obj[2]);
				sm.setStorename(obj[3]);
				sm.setClientele(obj[4]);
				sm.setStatus(obj[5]);
				sm.setId(obj[6]); 
				sm.setDownsucesslist(obj[7]);
				sm.setNewestlist(obj[8]);
				sm.setNewestlistflag(obj[9]);

				storemessage.add(sm);

			} 
			map.put("total", pager.getTotalCount());
			map.put("rows", storemessage);
		}else{
			map.put("total", pager.getTotalCount());
			map.put("rows", storemessage);
		}

		return "pagequery";
	}

	
	/**
	 * 类似百度搜索查询
	 * @return
	 */
	public String searchName() {
		 
		String []words = {"amani","abc","apple","abstract","an","bike","byebye","beat","be","bing","come","cup","class","calendar","china"}; 
		if(super.getRequest().getParameter("search-text") != null) { 
			String key = super.getRequest().getParameter("search-text"); 
			if(key.length() != 0){ 
				String json="["; 
				for(int i = 0; i < words.length; i++) { 
					if(words[i].startsWith(key)){ 
						json += "\""+ words[i] + "\"" + ","; 
					} 
				} 
				json = json.substring(0,json.length()-1>0?json.length()-1:1); 
				json += "]"; 
				System.out.println("json:" + json); 
				try {
					PrintWriter out = super.getResponse().getWriter(); 
//					out.write(json);
					out.println(json);
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
  
		return "searchname";
	}
	
	
	
 
	/**
	 * 导出excel表格
	 * @return
	 */
	public String Excel() 
	{
		ShiroUser shiroUser = CommonUtil.getCurrendUser();
		Customer customer = service.getCustomerByUsername(shiroUser.getAccount());
	    try 
	    {  
	    	OutputStream os = super.getResponse().getOutputStream();// 取得输出流  
            super.getResponse().reset();// 清空输出流  

            super.getResponse().setHeader("Content-disposition", "attachment; filename="+new String("Book1".getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头  
            super.getResponse().setContentType("application/msexcel");// 定义输出类型
           
            //建立excel文件	 
		    WritableWorkbook book= Workbook.createWorkbook(os); 
		    //生成名为“第一页”的工作表，参数0表示这是第一页 
		    WritableSheet sheet=book.createSheet("第一页",0); 
		    //在Label对象的构造子中指名单元格位置是第一列第一行(0,0) 
		    //单元格内容
		    Label label=new Label(0,0,"地区"); 
		    Label labe2=new Label(1,0,"城市"); 
		    Label labe3=new Label(2,0,"门店编号"); 
		    Label labe4=new Label(3,0,"门店名称"); 
		    Label labe5=new Label(4,0,"品牌"); 
		    Label labe6=new Label(5,0,"音乐最近更新时间"); 
		    Label labe7=new Label(6,0,"最新音乐下发时间"); 
		    Label labe8=new Label(7,0,"最新音乐是否更新"); 
		    Label labe9=new Label(8,0,"网络状态");  
		    sheet.addCell(label);
		    sheet.addCell(labe2);
		    sheet.addCell(labe3);
		    sheet.addCell(labe4);
		    sheet.addCell(labe5);
		    sheet.addCell(labe6);
		    sheet.addCell(labe7);
		    sheet.addCell(labe8);
		    sheet.addCell(labe9);  
		    //得到数据
		    //List<Object[]> list =(List<Object[]>)nodeService.querysql("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id FROM Node t LEFT JOIN Node t2 ON t.id=t2.parentid LEFT JOIN node t3 ON t2.id=t3.parentid LEFT JOIN Node t4 ON t3.id=t4.parentid LEFT JOIN Node t5 ON t4.id=t5.parentid LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND t2.NAME='"+customer.getPhone()+"'");
		    List<Object[]> list = nodeService.query(customer.getPhone());
		    //List<Object[]> list = (List<Object[]>)nodeService.querysql("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id ,(SELECT count(*) from NODEPLAYLIST a INNER JOIN NODELIST b on a.nodelistid = b.id INNER JOIN NODE c on b.nodeid = c.id WHERE c.code = t5.code and a.CREATETIME = SYSDATE) AS count FROM Node t LEFT JOIN Node t2 ON t.id=t2.parentid LEFT JOIN node t3 ON t2.id=t3.parentid LEFT JOIN Node t4 ON t3.id=t4.parentid LEFT JOIN Node t5 ON t4.id=t5.parentid LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid  WHERE t.parentid='0' AND t2.NAME='"+customer.getPhone()+"'");
			List<StoreMessage> storemessage = new ArrayList<StoreMessage>();
			
			if (list.size() > 0) {
				for (Object[] obj : list) {
					StoreMessage sm = new StoreMessage();
					sm.setArea(obj[0]);
					sm.setCity(obj[1]);
					sm.setCode(obj[2]);
					sm.setStorename(obj[3]);
					sm.setClientele(obj[4]);
					sm.setStatus(obj[5]);
					sm.setId(obj[6]);
					sm.setDownsucesslist(obj[7]);
					sm.setNewestlist(obj[8]);
					sm.setNewestlistflag(obj[9]);
					 

					storemessage.add(sm);
					
				}  
		    System.out.println("-----excel-----"+list.size());
		   
		    Iterator it = storemessage.iterator(); 
			int i=1;
			while (it.hasNext()) {  				
 
				StoreMessage sm = (StoreMessage) it.next();
				Label labe = new Label(0, i,sm.getArea()==null?"":sm.getArea()+"");
				sheet.addCell(labe);
				labe = new Label(1, i, sm.getCity()==null?"":sm.getCity()+"");
				sheet.addCell(labe);
				labe = new Label(2, i, sm.getCode()==null?"":sm.getCode()+"");
				sheet.addCell(labe);
				labe = new Label(3, i, sm.getStorename()==null?"":sm.getStorename()+"");
				sheet.addCell(labe);
				labe = new Label(4, i, sm.getClientele()==null?"":sm.getClientele()+"");
				sheet.addCell(labe); 
				labe = new Label(5, i, sm.getDownsucesslist()==null?"未更新":sm.getDownsucesslist()+"");
				sheet.addCell(labe);
				labe = new Label(6, i, sm.getNewestlist()==null?"未下发":sm.getNewestlist()+"");
				sheet.addCell(labe);
				labe = new Label(7, i, sm.getNewestlistflag()==null?"未下发":(sm.getNewestlistflag()+"").equals("S")?"已更新":"未更新");
				sheet.addCell(labe);
				labe = new Label(8, i, sm.getStatus()==null?"暂无设备":(Integer.parseInt(sm.getStatus()+"")> 120 ?"离线":"在线"));
				sheet.addCell(labe); 
				i++;
			}
			}
		    //写入数据并关闭文件 
		    book.write(); 
		    book.close(); //最好在finally中关闭，此处仅作为示例不太规范
		    return null;
		    }
		    catch(Exception e) 
		    { 
		        System.out.println(e); 
		    }
	        return "success";
	} 
	 
	
	public String ExportExcel()
	{
	    ExportExcel ee=new ExportExcel();
		
		OutputStream os;
		try {
			os = super.getResponse().getOutputStream();
		// 取得输出流  
		super.getResponse().reset();// 清空输出流  

        super.getResponse().setHeader("Content-disposition", "attachment; filename="+new String("Book1".getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头  
        super.getResponse().setContentType("application/msexcel");// 定义输出类型
       
		 ArrayList<String> str1 = new ArrayList<String>();
			 str1.add("a1");
			 str1.add("a2");
			 str1.add("a3");
			 str1.add("a4");
			 str1.add("a5");
			 ArrayList<String> str2 = new ArrayList<String>();
			 str2.add("b1");
			 str2.add("b2");
			 str2.add("b3");
			 str2.add("b4");
			 str2.add("b5");
			 ArrayList<ArrayList<String>> array = new
			 ArrayList<ArrayList<String>>();
			 array.add(str1);
			 array.add(str2);
		     ee.doExcelExport(os, array);
		     return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	} 
 

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Nodedevice> getList() {
		return list;
	}

	public void setList(List<Nodedevice> list) {
		this.list = list;
	}

	public StoreMessage getSm() {
		return sm;
	}

	public void setSm(StoreMessage sm) {
		this.sm = sm;
	}

	public List<StoreMessage> getStore() {
		return store;
	}

	public void setStore(List<StoreMessage> store) {
		this.store = store;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getClientele() {
		return clientele;
	}

	public void setClientele(String clientele) {
		this.clientele = clientele;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
