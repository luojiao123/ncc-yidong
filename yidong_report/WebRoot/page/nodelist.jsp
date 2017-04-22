<%@page import="com.entity.Nodedevice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--> 
	<link rel="stylesheet" href="<%=basePath %>css/easyui.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath %>css/icon.css" type="text/css"/>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script> 
	<link rel="stylesheet" href="<%=basePath %>css/base.css" type="text/css"/>
	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript">
  
	 //报表不分页，详细信息分页
     $(function(){ 

 	    var id = <%=request.getParameter("id") %> 
    	     
			$("#listTable").datagrid({  
		        width:"auto",   
		        height:"auto",   
		        nowrap: false,   
		        striped: true,   
		        border: true,   
		        collapsible:false,//是否可折叠的   
		        fitColumns: true,//自动大小  
		        //sortName: 'code',   
		        //sortOrder: 'desc',  
		        url:'<%= path %>/nodelist!pageQueryList?id='+id,
		        remoteSort:false,    
		        idField:'id',   
		  
		        pagination:true,//分页控件   
		        rownumbers:true//行号   
		       
		    });   
			  //设置分页控件   
		    var p = $("#listTable").datagrid('getPager');   
 
			    $(p).pagination({   
			        pageSize: 10,//每页显示的记录条数，默认为10   
			        pageList: [5,10,15,20],//可以设置每页记录条数的列表   
			        beforePageText: '第',//页数文本框前显示的汉字   
			        afterPageText: '页    共 {pages} 页',   
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'   
			   
			    });   
		}) 
	 
	 
	   
	</script>
 
  </head>
  
   <body>  
		<table id="listTable" class="easyui-datagrid">  
		    <thead>  
		        <tr>     
		            <th data-options="field:'title',width:100">列表名称</th>
		            <th data-options="field:'time',width:100">下发时间</th>    
		            <th data-options="field:'flag',width:100,formatter: function(value,row,index){
							if(value=='S'){
								return '下载成功';
							}else{
							    return '正在下载';
							}
							
						}">下载状态</th> 
						<th data-options="field:'a',formatter: function(value,row,index){
		                   return '<a href=page/btmonitor.jsp?id='+row.id+'>详细信息</a>';
		                }
					">媒资下载</th>
		        </tr>  
		    </thead>  
		</table>   
		<div class="listBar">
        	<input onclick="history.back()" style="width: 70px" type="button" value="返回" />
        </div>
  </body>
</html>
