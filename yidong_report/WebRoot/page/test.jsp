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

	<script type="text/javascript" src="<%=basePath %>js/admin.js"></script>
	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript">
  
	 //报表不分页，详细信息分页
     $(function(){
		
			 
    	 
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
		        url:'test!test',
		        remoteSort:false,    
		        idField:'id',   
		  
		        pagination:true,//分页控件   
		        rownumbers:true//行号   
		       
		        
		    });   
			  //设置分页控件   
		    var p = $("#listTable").datagrid('getPager');   
 
			    $(p).pagination({   
			        pageSize: 10,//每页显示的记录条数，默认为10   
			        pageList: [5,10,15],//可以设置每页记录条数的列表   
			        beforePageText: '第',//页数文本框前显示的汉字   
			        afterPageText: '页    共 {pages} 页',   
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'   
			   
			    });  
			}) 
	</script>
  </head>
  
   <body>
        <div id="bar_3D"></div>
        <hr color="blue"> 
		 
		<table id="listTable" class="easyui-datagrid">  
		    <thead>  
		        <tr> 
		        	<th data-options="field:'id',width:100,checkbox:true"></th>
		            <th data-options="field:'loginName',width:100">比例</th>  
		            <th data-options="field:'userName',width:100">比例</th>  
		            <th data-options="field:'password',width:100">比例</th>  
		            <th data-options="field:'email',width:100">比例</th>   
		            <th data-options="field:'a',formatter: function(value,row,index){
		                   return '<a href=<%=path %>/charts?id='+row.id+'>详细信息</a>';
		                }
					">操作</th>
		        </tr>  
		    </thead>  
		</table>   
  </body>
</html>
