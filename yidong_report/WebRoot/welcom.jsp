<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="layout/script.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style type="text/css">
     img{
        width: 100%;
        height: 100%;
     }
     #userDetails{
     }
  </style>
  </head>
  
  <body>
    <!-- <img alt="Welcome to the software" src="img/a329efd8b16e3588dedf97f300d379d2.jpg"> -->
    <!--  <div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
         <img src="img/a329efd8b16e3588dedf97f300d379d2.jpg" width="100%"/>    
   </div>  -->  
   <div id="userDetails">
    <div id="p" class="easyui-panel" title="用户信息"     
        style="width:500px;height:150px;padding:10px;background:#fafafa;"   
        data-options="collapsible:true">   
	   <ul>
	       	<li>登陆用户:${customer.userName }</li>
	        <li>登陆时间:<fmt:formatDate value="${loginDate }"  type="both" /></li>
	    </ul>
   </div>
  </body>
</html>
