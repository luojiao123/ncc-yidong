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
	 //报表不分页，详细信息分页
     $(function(){ 
    	 $.ajax( {
				type :"POST",
				url :"<%= path %>/nodeaction!QueryAll",
				dataType :"json",
				success : function(data) {
					//for(var i=0;i<data.length;i++){
					//	alert(data[i].country);
					//}
					//addMarker(data); 
					var xml = "<chart caption='设备状况' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
					for(var i=0;i<data.length;i++){ 
						if(data[i][0]==0)
							{
							     data[i][0]="暂无设备";
							}else if(data[i][0]==1){
								
								data[i][0]="在线";
							}else if(data[i][0]==2){
								data[i][0]="离线";
							}
					xml+="<set label='"+data[i][0]+"' value='"+data[i][1]+"'  />";
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
		        url:'<%= path %>/nodeaction!pageQuery',
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
			})  
	 
	
	function searchButten(){
		 var area=$("input[name='area']").val(); 
   	     
		 var city=$("input[name='city']").val();
		 var code=$("input[name='code']").val();
		 var storename=$("input[name='storename']").val();
		 var clientele=$("input[name='clientele']").val();
		 var status=$("#status").val(); 
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
		        url:'<%= path %>/nodeaction!pageQuery',
		        queryParams:{
		        	area:area,
		        	city:city,
		        	code:code,
		        	storename:storename,
		        	clientele:clientele,
		        	status:status
		        	
		        },
		        
		        //?area='+area+'&city='+city+'&code='+code+'&storename='+storename+'&clientele='+clientele+'&status='+status+'',
		        
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
	 
     $(function(){  
    	   $("input[type='button']").click(function() {  
    	    //查找是兄弟节点，且为文本框的元素，并清空其值.      
    	    $(this).siblings(':text').val('');  
    	   });  
    	});   
	 
	</script> 
  </head>
  
   <body>
        <div id="bar_3D">
          
        </div>
        <hr color="blue"> 
		<div class="listBar">
		
		    地区:
		    <input type="text" name="area">
		    城市：
		    <input type="text" name="city" /> 
		    门店编号：
		    <input type="text" name="code" />
		    门店名称：
		    <input type="text" name="storename" /> 
		    品牌： 
		    <input type="text" name="clientele" /> 
		    状态： 
		    <select id="status" name="status">
		        <option value="">----状态----</option>
		        <option value="1">在线</option>
		        <option value="2">离线</option>
		        <option value="0">暂无设备</option>
		        
		    </select> 
		    <input type="button" onclick="searchButten()" value="搜  索" />
		
		</div>
		<table id="listTable" class="easyui-datagrid">  
		    <thead>  
		        <tr>     
		            <th data-options="field:'area',width:100">地区</th>
		            <th data-options="field:'city',width:100">城市</th>
		            <th data-options="field:'code',width:100">门店编号</th>     
		            <th data-options="field:'storename',width:100">门店名称</th>   
		            <th data-options="field:'clientele',width:100">品牌</th>   
		            <th data-options="field:'status',width:100,formatter: function(value,row,index){
							if(value==null){
								return '暂无设备';
							}else if(value<=120){
							    return '在线';
							}
							else{
								return '离线';
							}
						}">网络状态</th>
					 <th data-options="field:'a',formatter: function(value,row,index){
		                   return '<a href=page/nodelist.jsp?id='+row.id+'>详细信息</a>';
		                }
					">播放列表下载</th>
					 
		        </tr>  
		    </thead>  
		</table> 
	    <div class="listBar">
	        <form action="nodeaction!Excel" method="post">
        	     <input style="width: 70px" type="submit" value="导出" />
        	</form>
         <!--   
            <form action="nodeaction!ExportExcel" method="post">
        	     <input style="width: 70px" type="submit" value="导出2" />
        	</form> -->
        </div>
  
  </body>
</html>

