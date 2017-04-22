<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>回传记录查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <SCRIPT type=text/javascript src="<%= basePath %>js/WdatePicker.js"></SCRIPT>
  </head> 
  <body>
  <div class="search" align="center">
    <label for="search-text">请选择门店名</label> 
    <select id="li" name="storename" style="width:180px;">
	   	<s:iterator value="li" var="node">
			<option value='<s:property value="#node[2]"/>'>
            <s:property value="#node[0]"/>
            </option>
		</s:iterator> 
	</select>

	<label for="date">请选择日期</label><input class="Wdate" type="text" name = "date" onClick="WdatePicker()">
	<input type="button" onclick="searchButten()" value="搜索"/>
    </div>
  </body>
</html>
