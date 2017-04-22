<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tree - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/demo.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#tt2').tree({
				checkbox: true,
				state:closed,
				url: 'tree!getNodeTreeList',
				onClick:function(node){
					$(this).tree('toggle', node.target);
					//alert('you dbclick '+node.text);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#tt2').tree('select', node.target);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			});
			
		}); 
	</script>
</head>
<body>
<hr></hr>
<p>树形列表</p>  
	<ul id="tt2"></ul> 
</body>
</html>