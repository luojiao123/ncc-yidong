<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

 
<link rel="stylesheet" href="<%=basePath %>css/easyui.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath %>css/icon.css" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>

<script type="text/javascript">
  $(document).ready(function(){
	 
	//默认加载数据
		$('#tt').datagrid({
			url:'<%= path %>/charts!pageQuery?pageNumber=1&pageSize=10'			
		});
		
		//分页显示
		$.post("<%= path %>/charts!count",function(data){		
		

		$("#pp").pagination({
			
			total:data,
			pageList: [5,10,15,20,25],
			onSelectPage:function(pageNumber, pageSize){				
				$(this).pagination('loading');									
				$("#tt").datagrid({
					url:'<%= path %>/charts!pageQuery?pageNumber='+pageNumber+'&pageSize='+pageSize
				});					
				$(this).pagination('loaded');
				}
			});				
		 });
		
		var stu = $("#selectStu").val();
		$("#selectStu").focus(function(){
			$("#selectStu").val("");
			$("#selectStu").css("color","black");
		})	
	  
  });


</script>
</head>

<body> 
<table id="tt" title="我要练习" data-options="iconCls:'icon-edit',singleSelect:true,idField:'id'">
		<thead>
			<tr>                     
				 <th data-options="field:'id',width:100,checkbox:true"></th>
				<th data-options="field:'country',width:180">练习时长</th>
				<th data-options="field:'scale',width:180">发布时间</th>
				<th data-options="field:'a',width:180,formatter:function(value,row,index){
					return row.id;
				}">出卷人</th>
				
				<th data-options="field:'caozuo',width:180,formatter:function(value,row,index){
				return'<a href=page/lx/practise.jsp?uid='+row.id+'>我要练习</a>'}">我要练习</th>
			</tr>
		</thead>
	</table>
	<div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"></div>
</body>
</html>
