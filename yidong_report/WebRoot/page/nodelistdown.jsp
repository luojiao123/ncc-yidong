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
	
	<link rel="stylesheet" href="<%=basePath %>css/base.css" type="text/css"/>

	<script type="text/javascript" src="js/FusionCharts.js"></script>
	<script type="text/javascript"> 
	function searchButten(){ 
		var title=$("input[name='title']").val(); 
		$.ajax( {
			type :"POST",
			url :"<%= path %>/nodelist!query",		
	        data:[
	              title
		          ],
			dataType :"json",
			success : function(data) { 
				var xml = "<chart caption='下载情况统计报表' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
				var temp;
				for(var i=0;i<data.length;i++){ 
					if(i==0)
						{
						     temp="下载成功数";
						}else
						{ 
							temp="下载失败数";
						} 
				xml+="<set label='"+temp+"' value='"+data[i]+"'  />";
				}
				xml+="</chart>"; 
				//下面第一个参数：swf的路径； 第二个参数："ChartId"； 第三个参数：宽； 第四个参数：高； 后面2个参数直接 0 就好了
				var chart = new FusionCharts("FusionCharts/Pie3D.swf", "ChartId", "500", "300", "0", "0");
				//直接把xml当参数传进去，如果你有.xml文件，也可以把路径传进去 
				chart.setDataXML(xml);
				//在ID为 bar_2D 的地方显示
				chart.render("bar_3D"); 
			},
			error : function() {
				alert("请求失败");
			}
		}); 
		  
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
		        url:'<%= path %>/nodelist!pageQuery',
		        queryParams:{ 
		        	title:title
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
	 
    /*  $(function(){  
    	   $("input[type='button']").click(function() {  
    	    //查找是兄弟节点，且为文本框的元素，并清空其值.      
    	    $(this).siblings(':text').val('');  
    	   });  
    	});  */  
	 
	</script> 
  </head>
  
   <body>
   <TABLE border=0 cellSpacing=10 cellPadding=0 align=center>
		<TBODY>
			<TR>
				<TD><IMG src="<%=basePath%>img/list_tit.gif" width=19
					height=19></TD>
				<TD class=title>下载情况统计报表</TD>
			</TR>
			
		</TBODY>
	</TABLE> 
   <div class="listBar" align="center">		 
		    请输入列表名称：
		    <input type="text" name="title" /> 		 
		    <input type="button" onclick="searchButten()" value="搜  索" />
		   
  </div> 
        <div id="bar_3D"></div>
        <hr color="blue"> 
		
		<table id="listTable" class="easyui-datagrid">  
		    <thead>  
		        <tr>      
		           <th data-options="field:'s',width:100,formatter: function(value,row,index){
		                   return '<a href=page/nodelistdown_detel.jsp?flag=S&title='+row.title+'>'+value+'</a>';
		                }
					">列表下载完成数</th>
					  <th data-options="field:'n',width:100,formatter: function(value,row,index){
		                   return '<a href=page/nodelistdown_detel.jsp?flag=N&title='+row.title+'>'+value+'</a>';
		                }
					">列表下载未完成数</th> 
					  
		        </tr>  
		    </thead>  
		</table>  
  </body>
</html>

