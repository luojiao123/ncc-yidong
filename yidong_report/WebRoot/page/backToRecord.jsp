<%@page import="com.entity.Nodedevice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
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
    <SCRIPT type=text/javascript src="<%= basePath %>js/WdatePicker.js"></SCRIPT>
	
	<link rel="stylesheet" href="<%=basePath %>css/base.css" type="text/css"/>

	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript"> 
	function searchButten(){  
		 var code=$("#code").val();  
		 var date=$("input[name='date']").val(); 
		  //$("#listTable").datagrid("load");
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
		        url:' <%= path %>/nodeplaylistaction!RecordQuery',
		        queryParams:{ 
		        	code:code, 
		        	date:date
		        	
		        }, 
		       	data:[
		              ],
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
    	
	 }
	   
	</script> 
  </head>
  
   <body>
   <TABLE border=0 cellSpacing=10 cellPadding=0 align=center>
		<TBODY>
			<TR>
				<TD><IMG src="<%=basePath%>img/list_tit.gif" width=19
					height=19></TD>
				<TD class=title align="center">门店音乐播放记录</TD>
			</TR>
			
		</TBODY>
	</TABLE> 
   <div class="listBar" align="center">		 
		请选择门店名 
	    <select id="code" style="width:180px;">
		   	<s:iterator value="li" var="li">
				<option value='<s:property value="#li[1]"/>'>
	            <s:property value="#li[0]"/>
	            </option>
			</s:iterator> 
		</select>

	         请选择日期<input class="Wdate" type="text" name = "date" onClick="WdatePicker()">
	<input type="button" onclick="searchButten()" value="搜索"/> 
  </div>  
  <hr color="blue"> 
		
  <table id="listTable" class="easyui-datagrid">  
      <thead>  
          <tr>
             <th data-options="field:'filename',width:100">音乐名</th>
             <th data-options="field:'artist',width:100">播放时间</th>  
          </tr>
      </thead>  
  </table> 
	     <div class="listBar">
	        <form action="nodeplaylistaction!RecordExcel" method="post">
        	     <input style="width: 70px" type="submit" value="导出" />
        	</form> 
        </div> 
  </body>
</html>

