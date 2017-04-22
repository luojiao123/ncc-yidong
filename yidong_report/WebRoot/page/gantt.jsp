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
	<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript">
	 function lineBeginEdit() {
		 alert("ds");
		 $.ajax( {
				type :"POST",
				url :"<%= path %>/pie!queryAll ",
				dataType :"json",
				success : function(data) {
					//for(var i=0;i<data.length;i++){
					//	alert(data[i].country);
					//}
					
					//addMarker(data);
				   
					var xml = "<chart caption='国家分布比例' xAxisName='国家' yAxisName='规模' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
					for(var i=0;i<data.length;i++){
						xml+="<set label='"+data[i][0]+"' value='"+data[i][1]+"'  />";
					}
					xml+="</chart>";
					
					alert(xml);
					//下面第一个参数：swf的路径； 第二个参数："ChartId"； 第三个参数：宽； 第四个参数：高； 后面2个参数直接 0 就好了
					var chart = new FusionCharts("FusionCharts/Line.swf", "ChartId2", "500", "300", "0", "0");
					//直接把xml当参数传进去，如果你有.xml文件，也可以把路径传进去 
					chart.setDataXML(xml);
					//在ID为 bar_2D 的地方显示
					chart.render("bar_3D"); 
				},
				error : function() {
					alert("请求失败");
				}
			}); 
		} 

	</script>
  </head>
  
  <body onload="lineBeginEdit();">
        <div id="bar_3D"></div>
  </body>
</html>
