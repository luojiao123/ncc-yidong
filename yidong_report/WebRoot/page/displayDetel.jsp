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
	<script type="text/javascript" src="<%=basePath %>js/admin.js"></script>
 <script type="text/javascript">

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
		        url:' ',
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
<div class="listBar">
	查找:
    <select id="searchBy" name="pager.searchBy">  
        <option value="grade.name">名称</option>  
        <option value="grade.director">班主任</option>    
    </select> 
    <input id="keyword" type="text" name="pager.keyword" />
    <input id="searchButten" type="button" value="搜  索" />
</div>
<table id="listTable" class="easyui-datagrid">  
    <thead>  
        <tr> 
        	<th data-options="field:'id',width:100,checkbox:true"></th>
            <th data-options="field:'country',width:100">班级</th>  
            <th data-options="field:'scale',width:100">班主任</th>  
            <th data-options="field:'a',formatter: function(value,row,index){
                   return '<a href=<%=path %>/charts?id='+row.id+'>编辑</a>';
                }
			">操作</th>
        </tr>  
    </thead>  
</table> 
<div class="listBar">
	<input id="deleteButten" url="<%=path %>/charts" type="button" value="删  除" />
</div>
 
	 
</body>
</html>
