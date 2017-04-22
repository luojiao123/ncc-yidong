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

	<%-- <link rel="stylesheet" type="text/css" href="styles.css">
	<script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script> --%>
	
 
<link rel="stylesheet" href="<%=basePath %>css/easyui.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath %>css/icon.css" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>

<link rel="stylesheet" href="<%=basePath %>css/base.css" type="text/css"/>

	<script type="text/javascript" src="<%=basePath %>js/admin.js"></script>
	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript">
	//报表和详细信息一起分页
	function lineBegin(data){
		
		var dt=data.rows;
		//addMarker(data);
	 
		var xml = "<chart caption='国家分布比例' xAxisName='国家' yAxisName='规模' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
		for(var i=0;i<dt.length;i++){
		xml+="<set label='"+dt[i][0]+"' value='"+dt[i][1]+"'  />";
		}
		xml+="</chart>";
		
		alert(xml);
		//下面第一个参数：swf的路径； 第二个参数："ChartId"； 第三个参数：宽； 第四个参数：高； 后面2个参数直接 0 就好了
		var chart = new FusionCharts("FusionCharts/Column3D.swf", "ChartId", "500", "300", "0", "0");
		//直接把xml当参数传进去，如果你有.xml文件，也可以把路径传进去 
		chart.setDataXML(xml);
		//在ID为 bar_2D 的地方显示
		chart.render("bar_3D"); 
	}
 
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
	        url:'pie!query',
	        remoteSort:false,    
	        idField:'id',   
	  
	        pagination:true,//分页控件   
	        rownumbers:true,//行号   
	        onLoadSuccess:function(data)
	        {
	        	lineBegin(data);
	        }
	        
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
  
  <body >
     <div id="bar_3D"></div>
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
		            <th data-options="field:'brand',width:100">比例</th>   
		            <th data-options="field:'a',formatter: function(value,row,index){
		                   return '<a href=<%=path %>/charts?id='+row.id+'>详细信息</a>';
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
