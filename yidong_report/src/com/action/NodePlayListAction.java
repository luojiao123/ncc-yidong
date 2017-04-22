package com.action; 
import java.io.OutputStream; 
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
import com.entity.Nodeplaylist;
import com.entity.help.StoreMessage;  
import com.service.CustomerService; 
import com.service.NodeplaylistService;  
@Component("nodeplaylistaction")
public class NodePlayListAction extends BaseAction {
	private static final long serialVersionUID = -2062477908939584376L;

	@Resource(name = "nodeplaylistservice")
	private NodeplaylistService nplService;
	@Autowired
	private CustomerService service;
 
	private String code;
	private String date;  
 
	
	/**
	 * 回传记录列表显示 音乐名   播放时间
	 * @method: RecordQuery() -by fjt
	 * @TODO:  
	 * @return String
	 */
	public String RecordQuery()
	{ 
		pager.setPageNumber(page);
		pager.setPageSize(rows);  
	    pager = nplService.recordQuery(pager, code, date);
		map = new HashMap<String, Object>();
		List<Nodeplaylist> li = new ArrayList<Nodeplaylist>();
		List<Object[]> list = (List<Object[]>) pager.getResult();  
		
		for(Object[] obj :list)
    	{
    		Nodeplaylist n = new Nodeplaylist();
    		n.setFilename((String)obj[0]);
    		n.setArtist((String)obj[1]); 	    		
    		li.add(n);
    	}			
		map.put("total", pager.getTotalCount());
		map.put("rows", li);
 
		return "recordquery";
	}
	
	 
	
	 
	/**
	 * 导出excel表格    门店音乐播放记录
	 * @return
	 */
	public String RecordExcel() 
	{ 
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
		    Label label=new Label(0,0,"音乐名"); 
		    Label labe2=new Label(1,0,"播放时间");  
		    sheet.addCell(label);
		    sheet.addCell(labe2); 
		    //得到数据
		   // List<Object[]> list =(List<Object[]>)nplService.querysql("SELECT t3.name AS AREA,t4.name AS city,t5.code AS CODE,t5.name AS storename,t5.TRADEZONENAME_RSC AS trademark,ROUND(TO_NUMBER(sysdate - t6.requesttime) * 24 * 60) AS STATUS,t5.id FROM Node t LEFT JOIN Node t2 ON t.id=t2.parentid LEFT JOIN node t3 ON t2.id=t3.parentid LEFT JOIN Node t4 ON t3.id=t4.parentid LEFT JOIN Node t5 ON t4.id=t5.parentid LEFT JOIN Nodedevice t6 ON t5.id=t6.nodeid WHERE t.parentid='0' AND t2.NAME='"+customer.getPhone()+"'");
		 
		    List<Object[]> list = nplService.Querysql(code, date);
			List<StoreMessage> storemessage = new ArrayList<StoreMessage>();
			
			if (list.size() > 0) {
				for (Object[] obj : list) {
					StoreMessage sm = new StoreMessage();
					sm.setArea(obj[0]);
					sm.setCity(obj[1]);

					storemessage.add(sm);
					
				}  
		    //System.out.println("-----excel-----"+list.size());
		   
		    Iterator it = storemessage.iterator(); 
			int i=1;
			while (it.hasNext()) {  				
 
				StoreMessage sm = (StoreMessage) it.next();
				Label labe = new Label(0, i,sm.getArea()==null?"":sm.getArea()+"");
				sheet.addCell(labe);
				labe = new Label(1, i, sm.getCity()==null?"":sm.getCity()+"");
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
	
	 
	
	public String getCode() {
		return code;
	} 
	public void setCode(String code) {
		this.code = code;
	}
 
	public String getDate() {
		return date;
	}
 
	public void setDate(String date) {
		this.date = date;
	}
  
  
}
