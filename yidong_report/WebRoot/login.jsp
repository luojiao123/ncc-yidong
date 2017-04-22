<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
	<head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Web 2.0 Login Form by Azmind.com</title>

	<!--- CSS --->
	<link rel="stylesheet" href="<%=basePath%>login/css/style.css" type="text/css" />


	<!--- Javascript libraries (jQuery and Selectivizr) used for the custom checkbox --->

	<!--[if (gte IE 6)&(lte IE 8)]>
		<script type="text/javascript" src="<%=basePath%>login/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>login/js/selectivizr.js"></script>
		<noscript><link rel="stylesheet" href="<%=basePath%>login/css/fallback.css" /></noscript>
	<![endif]-->

		<script type="text/javascript">

		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};

	</script>
	</head>

	<body bgcolor="#CCCCCC"> 
		<div id="container">
			<form action="system_load.action" method="post">
				<div class="login">门店音乐管理系统</div>
				<div class="username-text">用户名:</div>
				<div class="password-text">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
				<div class="username-field">
					<input type="text" name="userName" />
				</div>
				<div class="password-field">
					<input type="password" name="password" />
				</div>
				<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">欢迎登陆！</label>
				<div class="forgot-usr-pwd"><FONT color="red" size="2"><s:property value="errors.error[0]"/></FONT></div>
				<input type="submit" name="submit" value="登陆" />
			</form>
		</div>
		<div id="footer">
			逸东信息科技（上海）有限公司
		</div>
	</body>
</html>
