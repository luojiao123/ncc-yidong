package com.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date; 
import java.util.List; 

import javax.annotation.Resource;

import jxl.read.biff.BiffException;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.common.CommonUtil;
import com.entity.help.NodeKey; 
import com.entity.Customer;
import com.entity.Node;
import com.excel.ImportExcel;  
import com.service.CustomerService;
import com.service.NodeService;
import com.shiro.ShiroUser;

@Component("node")
@Scope("prototype")
public class NodeYAction extends BaseAction {

	private static final long serialVersionUID = -7868417204236009837L;

	@Resource(name = "nodeservice")
	private NodeService nodeService;

	@Autowired
	private CustomerService service;

	private Node node;
	List<Node> list; 
	List li;
	// 封装单个上传文件域的属性
	private File upload;
	// 封装单个上传文件类型的属性
	private String uploadContentType;
	// 封装单个上传文件名的属性
	private String uploadFileName;
	// 动态设置上传文件保存地址
	private String savePath;
	

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 返回上传文件的保存位置
	 * 
	 * @return
	 */
	public String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public List<Node> getList() {
		return list;
	}

	public void setList(List<Node> list) {
		this.list = list;
	}
 
	public List getLi() {
		return li;
	}

	public void setLi(List li) {
		this.li = li;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * 组织输入
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String add() {
		System.out.println("---add---" + node.getName());
		if (node.getName() == null || "".equals(node.getName())) {
			this.addFieldError("error", "门店名字不能为空!");
			return selectAllId();
		}
		if (node.getParentid() == null || "".equals(node.getParentid())) {
			this.addFieldError("error", "父节点Id不能为空!");
			return selectAllId();
		}
		if (node.getScale() == null || "".equals(node.getScale())) {
			this.addFieldError("error", "树的等级不能为空!");
			return selectAllId();
		}
		System.out.println(node.getName() + "----" + node.getBrand() + "---"
				+ node.getCode() + "===" + node.getStatus() + "---"
				+ node.getOperator());

		node.setCreatetime(new Date());
		node.setModtime(new Date());

		String hql ="select * FROM Node WHERE NAME='"+node.getName()+"' AND parentid = '"+node.getParentid()+"'";
		System.out.println(node.getName()+"===="+node.getParentid());
		list = (List<Node>) nodeService.querysql(hql);
		System.out.println("=====list.size():" + list.size());
		if(list != null && list.size() > 0) {
			this.addFieldError("error", "该门店已存在！！");
			return "error";
		} else {  
			nodeService.save(node);
			this.addFieldError("error", "添加成功！！");
			return "error";
		}
	}

	public String selectAllId() {
		System.out.println("--------selectAllId------");
		String hql = "SELECT id,name FROM Node WHERE id IN (SELECT  DISTINCT parentid FROM Node)";
		List<Object[]> nodeList = (List<Object[]>) nodeService.query(hql);
		if (nodeList.size() > 0) {
			list = null != list ? list : new ArrayList<Node>();
			for (Object[] object : nodeList) {
				Node node = new Node();
				node.setId(Integer.parseInt(object[0].toString()));
				node.setName(object[1].toString());
				this.list.add(node);
			}
			System.out.println("-----" + nodeList.size());
			return "select";
		} else {
			return "select";
		}
	}

	/**
	 * 批量添加
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public String batchAdd() { 
		// 得到了文件流，直接把这个文件流拿去解析
		ImportExcel ie = new ImportExcel();
		try {
			List<NodeKey> listnk = ie.readExcel(inputBut(), 0);
			//list = new ArrayList<Node>();
			for (int i = 0; i < listnk.size(); i++) {

				// 添加客户名称
				Node noda = new Node();
				noda.setName(node.getBrand());
				noda.setParentid(38960);
				noda.setCreatetime(new Date());
				noda.setModtime(new Date());
				noda.setBrand(node.getBrand());
				noda.setScale(2);
				noda.setStatus(node.getStatus());
				noda.setOperator(node.getOperator());
				String hql ="select * FROM Node WHERE NAME='"+node.getBrand()+"' and brand='"+node.getBrand()+"' AND parentid = '"+38960+"'";
				list = (List<Node>) nodeService.querysql(hql);
				System.out.println("=====list.size():" + list.size());
				if(list == null || list.size() <= 0) {
					nodeService.save(noda);
					System.out.println(node.getBrand()+"===="+noda.getId());
				}
				//添加地区
				Node nod = new Node();
				int id = nodeService.select("select id FROM Node WHERE NAME='"+node.getBrand()+"' and brand='"+node.getBrand()+"' AND scale = '"+2+"'");
				
				nod.setName(listnk.get(i).getProvince());
				System.out.println(listnk.get(i).getProvince()+"id="+id);
				nod.setParentid(id); 
				nod.setCreatetime(new Date());
				nod.setModtime(new Date());
				nod.setBrand(node.getBrand());
				nod.setScale(3);
				nod.setStatus(node.getStatus());
				nod.setOperator(node.getOperator());
				String hql2 ="select * FROM Node WHERE NAME='"+listnk.get(i).getProvince()+"' and brand='"+node.getBrand()+"' AND parentid = '"+id+"'";
				System.out.println(listnk.get(i).getProvince()+"===="+id);
				list = (List<Node>) nodeService.querysql(hql2);
				System.out.println("=====list.size():" + list.size());
				NodeKey key = listnk.get(i);
				if(list == null || list.size() <= 0) {
					nodeService.save(nod);
				}
				//添加城市
				Node no = new Node();
				int id2= nodeService.select("select id FROM Node WHERE NAME='"+listnk.get(i).getProvince()+"' and brand='"+node.getBrand()+"' AND scale = '"+3+"'");
				no.setName(listnk.get(i).getCity());
				no.setParentid(id2);
				no.setCreatetime(new Date());
				no.setModtime(new Date());
				no.setBrand(node.getBrand());
				no.setScale(4);
				no.setStatus(node.getStatus());
				no.setOperator(node.getOperator());
				String hql3 ="select * FROM Node WHERE NAME='"+listnk.get(i).getCity()+"' and brand='"+node.getBrand()+"' AND parentid = '"+id2+"'";
				System.out.println(listnk.get(i).getCity()+"===="+id2);
				list = (List<Node>) nodeService.querysql(hql3);
				System.out.println("=====list.size():" + list.size());
				if(list == null || list.size() <= 0) {
					nodeService.save(no);
				}
				//添加门店
				Node n = new Node();
				int id3= nodeService.select("select id FROM Node WHERE NAME='"+listnk.get(i).getCity()+"' and brand='"+node.getBrand()+"' AND scale = '"+4+"'");
				n.setName(listnk.get(i).getCodeName());
				n.setParentid(id3);
				n.setCode(listnk.get(i).getCode());
				n.setTradezonenameRsc(listnk.get(i).getTradezonename_rsc());
				n.setCreatetime(new Date());
				n.setModtime(new Date());
				n.setBrand(node.getBrand());
				n.setScale(5);
				n.setStatus(node.getStatus());
				n.setOperator(node.getOperator());
				String hql4 ="select * FROM Node WHERE NAME='"+listnk.get(i).getCodeName()+"' and brand='"+node.getBrand()+"' AND parentid = '"+id3+"'";
				System.out.println(listnk.get(i).getCodeName()+"===="+id3);
				list = (List<Node>) nodeService.querysql(hql4);
				System.out.println("=====list.size():" + list.size());
				if(list == null || list.size() <= 0) {
					nodeService.save(n);
				} 
			}
 

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	// 得得到File对象的字节数数组
	public static byte[] getFileBuffer(final File file) throws IOException {
		byte[] fileByte = null;

		FileInputStream fis = new FileInputStream(file);
		fileByte = new byte[fis.available()];
		fis.read(fileByte);

		return fileByte;
	}

	public InputStream inputBut() throws IOException {

		// 得到上传文件的文件流
		InputStream ins = new ByteArrayInputStream(getFileBuffer(getUpload()));

		return ins;
	}

	// Test

	public String testActionMonth() {
		// 得到了文件流，直接把这个文件流拿去解析
		ImportExcel ie = new ImportExcel();
		try {
			List<NodeKey> listnk = ie.readExcel(inputBut(), 0);
			list = new ArrayList<Node>();
			for (int i = 0; i < listnk.size(); i++) {

				// 写入第I条数据
				System.out.println("=======codename"
						+ listnk.get(i).getCodeName());
				// 更具解析出来的客户id 得到第i条数据对应的客户
				List<Node> quer = (List<Node>) nodeService.query("from Node where brand= " + "'"+ node.getBrand() + "+'+");
				// 第一个添加：添加省
				Node ns = new Node();
				ns.setId(quer.get(0).getParentid());// 这个Id为当前新增省的ID;
				ns.setParentid(quer.get(0).getId());// 上级ID为客户的ID
				ns.setName(listnk.get(i).getProvince());// 解析出出来的省ID
				System.out.println("sheng==" + listnk.get(i).getProvince());
				list.add(ns);// 添加到集合

				// 添加市
				Node n = new Node();
				n.setId(ns.getParentid());// 这个Id为当前新增是事的ID;
				n.setParentid(ns.getId());// 上级ID为省的ID
				n.setName(listnk.get(i).getCity()); // 解析出来的城市ID
				list.add(n);

				// 添加门店
				Node nd = new Node();
				nd.setId(n.getParentid());// 这个Id为当前新增门店的ID;
				nd.setParentid(n.getId());// 上级Id为市的ID
				nd.setName(listnk.get(i).getCodeName());// 解析出来的门店名称
				nd.setCode(listnk.get(i).getCode());// 解析出来的门店编号
				nd.setTradezonenameRsc(listnk.get(i).getTradezonename_rsc());// 解析出来的品牌
				list.add(nd);

			}

			nodeService.save(list);// 执行添加

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}

	
	
	/**
	 * 查询门店名
	 * @return
	 */
	public String selectStoreName()
	{ 
		ShiroUser shiroUser = CommonUtil.getCurrendUser(); 
		Customer customer = service.getCustomerByUsername(shiroUser.getAccount());   
		li = nodeService.searchName(customer.getPhone());
	 
		return "selectstorename";
	}
	
	
	
}
