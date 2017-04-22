<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<TITLE>门店管理</TITLE>
<base href="<%=basePath%>"/>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META name=GENERATOR content="MSHTML 8.00.7601.18595">
<LINK rel=stylesheet type=text/css href="<%= basePath %>css/easyui.css">
<SCRIPT type=text/javascript src="<%= basePath %>js/jquery-1.8.0.js"></SCRIPT>

<SCRIPT type=text/javascript src="<%= basePath %>js/jquery.easyui.min.js"></SCRIPT>

</HEAD>
<BODY>  
	<SCRIPT language=javascript>
		$(function(){ 
			$("#pid").combotree({
				width:120,
				url:"tree!getNodeTreeList",
				idFiled:'id',
			 	textFiled:'name',
			 	parentField:'pid',
			 	onSelect:function(node){
			 		$("#pname").val(node.text); 
			 	}
		    }); 
		}); 
	</SCRIPT>

	<TABLE border=0 cellSpacing=10 cellPadding=0 align=center>
		<TBODY>
			<TR>
				<TD><IMG src="<%=basePath%>img/list_tit.gif" width=19
					height=19></TD>
				<TD class=title>批量录入</TD>
			</TR>
		</TBODY>
	</TABLE> 
<TR>
	<TD align=middle>
		<!-- 页面分割线 -->
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
		background=<%=basePath%> img/line_01.gif align=center>
	</TABLE>
<BR>
	<form action="nodeAction!batchAdd" method="post" enctype="multipart/form-data" namespace="/">
	    <INPUT value=V type="hidden" readOnly maxLength=10 size=13 name=node.status>
         <INPUT type="hidden" value=99999 readonly maxLength=10 size=13 name=node.operator>
	 
		<TABLE class=list_bg border=0 cellSpacing=1 cellPadding=5
			width="100%" align=center>
			<TBODY>
				<TR> 
					<TD class=list_bg2 width="15%" align=right>门店名字:</TD>
					<TD class=list_bg1>
					     <input type="file"  name="upload">
					</TD>
                    <TD class=list_bg2 width="15%" align="right">客户名称:</TD>
					<TD class=list_bg1 width="25%">
					     <INPUT class=text maxLength=10 size=13 name=node.brand>
					</TD>
				</TR> 

			</TBODY>
		</TABLE>
		<TABLE class=list_bg border=0 cellSpacing=1 cellPadding=5 width="100%" align=center>
			<TBODY>
				<TR>
					<TD class=list_bg1 colSpan=4 align="center"><FONT
						color="red" size="2"><s:property value="errors.error[0]" /></FONT>
					</TD>
				</TR>
				<TR>
					<TD class=list_bg1 colSpan=4 align="center"><input
						type="image" src="<%=basePath%>img/tianjia.gif" height="20">
					</TD>
				</TR>
			</TBODY>
		</TABLE>

	</FORM>
</BODY>
</HTML>
