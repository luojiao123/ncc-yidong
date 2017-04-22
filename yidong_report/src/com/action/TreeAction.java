package com.action; 
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.help.tree.TreeModel;
import com.service.NodeService;

/**
 * TreeAction
 * @author Administrator
 *
 */
@Component("treeaction")
public class TreeAction  extends BaseAction{
	private static final long serialVersionUID = -8048426794217008452L;
	@Autowired
	private  NodeService nodeService;
	
	public void getNodeTreeList(){
		//查询树节点信息，获取list集合信息 
		List<TreeModel> treeModelList = nodeService.getNodeTreelist(); 
		OutputJson(treeModelList);//将list转换为json
		
	}
}
	
