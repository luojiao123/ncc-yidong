<%@page import="com.entity.Customer"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<base href="<%=basePath%>"/>
<TITLE>main</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<jsp:include page="layout/script.jsp"></jsp:include>
<script type="text/javascript">
function logout(){
  if (confirm("您确定要退出控制面板吗？")){
	  
      //window.location.href = "login.jsp";
      window.location.href = "system_logout.action";
  }else{
	  top.location = "index.jsp";
  }
 
}  
</script>

<META name=GENERATOR content="MSHTML 8.00.7601.18448">
<style type="text/css">
li{

list-style:none; 
font-weight:bold;
color: #000066;
 
}
a{
color:#333399;
text-decoration:none;
 
}
.top{
   float: right;  
   padding-right: 20px;
   padding-top: 20px; 
}

.img{
   float: left;
   width: 100px;
}

</style>
</HEAD>
<BODY class=easyui-layout>
<DIV 
style="PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; BACKGROUND:#333333; HEIGHT: 60px; PADDING-TOP: 0px; overflow: hidden;" data-options="region:'north',border:false" >
  
   <div class="img" id="headerImage"> 
     <img  src="${customer.headerImageUrl}">
   </div>
   <div class="top"> 
      <a href="index.jsp"><font color="white">回到首页</font></a><br/>
      <a href="javascript:logout()"><font color="white">退出系统</font></a>
   </div>
</DIV> 
<DIV 
style="PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; WIDTH: 180px; PADDING-RIGHT: 0px; PADDING-TOP: 0px ;" 
data-options="region:'west',split:true,title:'报表信息'">

       <div class="easyui-accordion" data-options="fit:true,border:false">
       		<shiro:hasPermission name="admin">
				<div title="系统管理" style="padding:5px;overflow:auto;">
					 <div class="left_mune">
					      <ul>
					        <li class="left_mune_item"><a href="customer_add.action" target="mainForm">添加客户</a></li>
					        <li class="left_mune_item"><a href="page/customer_list.jsp" target="mainForm">客户列表</a></li>
			       
					      </ul>
	  			     </div>
				</div>
			</shiro:hasPermission>
			<div title="门店管理" data-options="selected:true" style="padding:10px;">
				<div class="left_mune">
			     <ul> 
			       <li class="left_mune_item"><a href="page/storemessage.jsp" target="mainForm">门店设备网络情况</a></li>
			       <li class="left_mune_item"><a href="nodeAction!selectStoreName" target="mainForm">门店设备播放记录</a></li>
			       
			       <shiro:hasPermission name="admin"> 
				        <li class="left_mune_item"><a href="page/nodelistdown.jsp" target="mainForm">下载情况统计报表</a></li>
			            <!-- 
			            <li class="left_mune_item"><a href="page/record.jsp" target="mainForm">门店设备播放记录查询</a></li>
			             <li class="left_mune_item"><a href="page/tree_test.jsp" target="mainForm" id = "aa">树形结构</a></li>
			             -->
				        <li class="left_mune_item"><a href="page/addNode.jsp" target="mainForm">组织录入</a></li>
				        <li class="left_mune_item"><a href="page/batchAdd.jsp" target="mainForm">批量录入</a></li>
			       </shiro:hasPermission>
			     </ul> 
				</div>
			</div> 
		</div>

</DIV>
 
<DIV style="PADDING-BOTTOM: 10px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND:#333333 ; color:white; HEIGHT: 60px; PADDING-TOP: 10px ;" 
data-options="region:'south',border:false">
 <div align="center" style="padding-top: 15px;">逸东信息科技（上海）有限公司&copy;(2015-2016)All Rights Reserved.</div>
</DIV>
<DIV data-options="region:'center',title:'显示'">
    <iframe name="mainForm" src="system_welcome.action" frameborder="0" marginheight="0" marginwidth="0" height="100%" width="100%"></iframe>
</DIV>

</BODY>
</HTML>
