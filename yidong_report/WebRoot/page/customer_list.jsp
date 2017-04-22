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
	<%-- <jsp:include page="<%=basePath%>layout/script.jsp"></jsp:include> --%>
	<LINK rel=stylesheet type=text/css href="<%=basePath %>css/easyui.css">
	<LINK rel=stylesheet type=text/css href="<%=basePath %>css/base.css">
	<LINK rel=stylesheet type=text/css href="<%=basePath %>css/admin.css">
	<SCRIPT type=text/javascript src="<%=basePath %>js/jquery-1.8.0.js"></SCRIPT>
	<SCRIPT type=text/javascript src="<%=basePath %>js/jquery.easyui.min.js"></SCRIPT>
		<SCRIPT type=text/javascript src="<%=basePath %>js/easyui-lang-zh_CN.js"></SCRIPT>
	<SCRIPT type=text/javascript src="<%=basePath %>js/admin.js"></SCRIPT>
	<script type="text/javascript">
  
	 //报表不分页，详细信息分页
     $(function(){
			    
			$("#dg").datagrid({  
		        width:"auto",   
		        height:"auto",   
		        nowrap: false,   
		        striped: true,   
		        border: true,   
		        collapsible:false,//是否可折叠的   
		        fitColumns: true,//自动大小  
				height : $(this).height()-38,
		      	url:'<%= path %>/customer_list.action',
		        remoteSort:false,     
		        pagination:true,//分页控件   
		        rownumbers:true,//行号   
		        columns :[[ 
		                   	{field: 'id',checkbox:'true',width:30},
							{field : 'userName',title : '用户名',width : parseInt($(this).width()*0.1)},
							{field : 'loginName',title : '账号',width : parseInt($(this).width()*0.1)},
							{field : 'email',title : '邮箱',width : parseInt($(this).width()*0.1)},
							{field : 'phone',title : '客户',width : parseInt($(this).width()*0.1)},
							{field : '0',title : '操作',width : parseInt($(this).width()*0.1), formatter:
						  		function(value,row){
						  			var str='';
						  			str+='<a href="customer_edit.action?id='+row.id+'">[修改]</a>';
			            		  	return str;
			            		   
							  }}
		                   ]]
		       
		    });   
			  
		});
	   
	</script>
  </head>
  
   <body> 
   		<div class="listBar">
   			<a href="javascript:void(0);" id="addButten" url="customer_add.action" class="easyui-linkbutton" >添加</a>
    		<a href="javascript:void(0);" id="editButten" url="page/updateCustomer.jsp" class="easyui-linkbutton" >编辑</a>
   			<a href="javascript:void(0);" id="deleteButten" url="customer_delete.action" class="easyui-linkbutton" >删除</a>
   		
   		</div>
		<table id="dg"></table>   
		<!-- <div class="listBar">
        	<input onclick="history.back()" style="width: 70px" type="button" value="返回" />
        </div> -->
  </body>
</html>
