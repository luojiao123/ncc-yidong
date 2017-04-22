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
<TITLE>门店添加</TITLE>
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
				<TD class=title>组织录入</TD>
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
	<FORM method=post name=setPlayForm action="nodeAction!add">
	     <INPUT value=V type="hidden" readOnly maxLength=10 size=13 name=node.status>
         <INPUT type="hidden" value=99999 readonly maxLength=10 size=13 name=node.operator>
	
		<TABLE class=list_bg border=0 cellSpacing=1 cellPadding=5
			width="100%" align=center>
			<TBODY>
				<TR>
					<TD></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE class=list_bg border=0 cellSpacing=1 cellPadding=5
			width="100%" align=center>
			<TBODY>
				<TR>

					<TD class=list_bg2 width="15%" align=right>门店名字:</TD>
					<TD class=list_bg1>
					     <INPUT class=text maxLength=20 size=13 name=node.name>
					     <FONT color=red>*</FONT>
					</TD>
                    <TD class=list_bg2 width="15%" align="right">客户名称:</TD>
					<TD class=list_bg1 width="25%">
					     <INPUT class=text maxLength=10 size=13 name=node.brand>
						<FONT color=red>*</FONT></TD>
					</TD>
				</TR>

				<TR>
					<TD class=list_bg2 align=right>门店编号:</TD>
					<TD class=list_bg1>
					     <INPUT class=readonly maxLength=20 size=13 name=node.code>
					</TD>
					<TD class=list_bg2 width="15%" align=right>父节点Id:</TD>
					<TD class=list_bg1> 
					<!--  
						<select id="pid" name="node.parentid">
						<s:iterator value="list" var="li">
						   <option value="<s:property value="id"/>">
                                    <s:property value="name"/>
                           </option>
						</s:iterator>
						</select>
						 -->
						 <input type="text" name="node.parentid"  class="easyui-textbox" id="pid"/>
						 <input id="pname"  type="hidden" />
						<FONT color=red>*</FONT>
					</TD>
				</TR>
				<TR>
					<TD class=list_bg2 align=right>品牌:</TD>
					<TD class=list_bg1>
					    <INPUT class=text maxLength=20 size=13 name=node.tradezonenameRsc>
                    </TD>
					<TD class=list_bg2 align=right>树的等级:</TD>
					<TD class=list_bg1> 
						<select id="scale" name="node.scale">
						  <option value=1>1</option>
						  <option value=2>2</option>
						  <option value=3>3</option>
						  <option value=4>4</option>
						  <option value=5>5</option>
						</select>
						<FONT color=red>*</FONT></TD>
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
