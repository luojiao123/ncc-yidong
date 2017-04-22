<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<LINK rel=stylesheet type=text/css href="<%=basePath %>css/easyui.css">
<LINK rel=stylesheet type=text/css href="<%=basePath %>css/admin.css">
<SCRIPT type=text/javascript src="<%=basePath %>js/jquery-1.8.0.js"></SCRIPT>
<SCRIPT type=text/javascript src="<%=basePath %>js/easyui-lang-zh_CN.js"></SCRIPT>
<SCRIPT type=text/javascript src="<%=basePath %>js/jquery.easyui.min.js"></SCRIPT>