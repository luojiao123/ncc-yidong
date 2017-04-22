package com.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.Pager;
import com.dao.CustomerDao;
import com.dao.NodeDao;
import com.entity.Customer;
import com.entity.Node;
import com.entity.Nodedevice;
import com.entity.help.StoreMessage;
import com.help.tree.TreeModel;
import com.service.CustomerService;
import com.service.NodeService; 
/**
 * service实现类 -node
 * @author aa
 *
 */
@Service("nodeservice")
public class NodeServiceImpl extends BaseServiceImpl implements NodeService {
	
	private static final long serialVersionUID = 9107621361426650326L;

	@Resource(name = "nodeDaoImpl")
	public void setBasedao(NodeDao nodeDao) {
		
		super.setBasedao(nodeDao);
	}
	@Resource(name = "nodeDaoImpl")
	private NodeDao nodeDao;
	@Resource(name = "customerdao")
	private CustomerDao customerDao;

	/**
	 * 查询树节点信息，返回list集合信息
	 */
	@Override
	public List<TreeModel> getNodeTreelist() {
		//所有父节点
		List<Node> pNode = nodeDao.getParentAll();
		List<TreeModel> pNodeTreeList = new ArrayList<TreeModel>();
		//遍历父节点，添加到集合中
		for (Node node : pNode) {
			TreeModel treeModel = new TreeModel();
			
			treeModel.setId(node.getId()+"");//节点Id
			treeModel.setName(node.getName());//节点名及文本
			treeModel.setPid(null);//节点的父节点Id
			
			
			Integer Pid = node.getId();
			
			 List<Node> cNodeList = nodeDao.getChildren(Pid);
			 List<TreeModel> cNodeTreeList = new ArrayList<TreeModel>();
			 for (Node cNode : cNodeList) { //如果有子节点，查询子节点
				TreeModel cTreeModel = new TreeModel();
				 
				cTreeModel.setId(cNode.getId()+"");
				cTreeModel.setName(cNode.getName());
				cTreeModel.setPid(node.getId()+"");
				Integer pId = cNode.getId();
				onloadNodeTree(pId, cTreeModel);//根据父节点id查询所有子节点
				cNodeTreeList.add(cTreeModel);
			 }
			 treeModel.setChildren(cNodeTreeList);//添加子节点
			 pNodeTreeList.add(treeModel);//添加子节点集合
		}
		
		return pNodeTreeList;
	}
	/**
	 * 根据父节点id查询所有子节点
	 * @param pId 父节点id
	 * @param pTreeModel 节点集合
	 */
	private void onloadNodeTree(Integer pId,TreeModel pTreeModel) {
		
		 List<Node> cNodeList = nodeDao.getChildren(pId);//获得子节点数据
		 //判断是否有子节点
		 if(cNodeList != null && cNodeList.size() >= 0){
			 List<TreeModel> cNodeTreeList = new ArrayList<TreeModel>();
			//遍历子节点
			 for (Node cNode : cNodeList) {
				TreeModel cTreeModel = new TreeModel();
				cTreeModel.setId(cNode.getId()+"");//节点Id
				cTreeModel.setName(cNode.getName());//节点名及文本
				cTreeModel.setPid(cNode.getParentid()+"");//父节点Id
				Integer id = cNode.getId();
				if(id != null){ //如果id不为空，说明，有子节点，则使用递归进行查询子节点
					onloadNodeTree(id,cTreeModel);
				}
				cNodeTreeList.add(cTreeModel);//添加到节点集合中
			 }
			 pTreeModel.setChildren(cNodeTreeList);//添加子节点集合
		 }
	}
	@Override
	public int select(String queryString) {
		// TODO Auto-generated method stub
		return nodeDao.select(queryString);
	}
	
	@Override
	public List<Nodedevice> queryAll(String customerAccount) {
		Customer customer = customerDao.getCustomerByUsername(customerAccount);
		
		List<Nodedevice> nodedeviceList = new ArrayList<Nodedevice>(); 
	    if(customer.getPhone()!=null && !"".equals(customer.getPhone())){
	    	nodedeviceList = nodeDao.queryByCustomerPhone(customer.getPhone());
	    }else{
	    	nodedeviceList = nodeDao.queryAll();
	    }  
		return  nodedeviceList;
	}
 

	@Override
	public Pager pageQuery(Pager pager, String customerPhone, String area, String city, String code,
			String storename, String clientele, String status) {
		return nodeDao.pageQuery(pager, customerPhone, area, city, code, storename, clientele, status);
	}
	@Override
	public List searchName(String customerPhone) {

		return nodeDao.searchName(customerPhone);
	}
	
    @Override
	public List query(String customerPhone){
		return nodeDao.query(customerPhone);
	}
}
