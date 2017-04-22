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
<TITLE>添加客户</TITLE>
<base href="<%=basePath%>"/>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<META name=GENERATOR content="MSHTML 8.00.7601.18595">

</HEAD>
<BODY>    
	<TABLE border=0 cellSpacing=10 cellPadding=0 align=center>
		<TBODY>
			<TR>
				<TD><IMG src="<%=basePath%>img/list_tit.gif" width=19
					height=19></TD>
				<TD class=title>${isAddAction?"新增客户":"修改客户"}</TD>
			</TR>
		</TBODY>
	</TABLE> 
<TR>
	<TD align=middle>
		<!-- 页面分割线 -->
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" background=<%=basePath%> img/line_01.gif align=center>
	</TABLE>
<BR>
	<FORM method=post name=setPlayForm action="${isAddAction?"customer_save.action":"customer_update.action"}" enctype="multipart/form-data">
	     <INPUT value=V type="hidden" readOnly maxLength=10 size=13 name=node.status>
	      <INPUT value="${customer.id}" type="hidden" name="id">
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

					<TD class=list_bg2 width="15%" align=right>客户名称:</TD>
					<TD class=list_bg1>
					     <INPUT class="text form_inputTable" maxLength=20 size=13 name=customer.userName value="${customer.userName}"> 
					</TD>
                    <TD class=list_bg2 width="15%" align="right">登陆名称:</TD>
					<TD class=list_bg1 width="25%">
					     <INPUT class=text maxLength=10 size=13 name=customer.loginName value="${customer.loginName }"> 
					</TD>
				</TR>

				<TR>
					<TD class=list_bg2 align=right>密码:</TD>
					<TD class=list_bg1>
					     <INPUT class=readonly maxLength=20 size=13 name=customer.password value="${customer.password }">
					</TD>
					<TD class=list_bg2 width="15%" align=right>客户:</TD>
					<TD class=list_bg1> 
					     <INPUT class=text maxLength=10 size=13 name=customer.phone value="${customer.phone }">
					</TD>
				</TR>
				<TR>
					<TD class=list_bg2 align=right>状态:</TD>
					<TD class=list_bg1>
					    <select id="scale" name="customer.status">
						  <option value="v">v</option> 
						</select>
                    </TD>
					<TD class=list_bg2 align=right>类型:</TD>
					<TD class=list_bg1> 
						<select id="scale" name="customer.type">
						  <option value=0>客户</option>
						  <option value=1>管理员</option> 
						</select>
				</TR>
				<TR>
					<TD class=list_bg2 align=right>客户logo:</TD> 
					<TD class=list_bg1>
						<s:if test="!(customer.headerImageUrl == null)">
							<img src="${customer.headerImageUrl }" width="100px" height="50px"/>
						</s:if>
					   <input type="file" name="headerImg">
                    </TD>
					<TD class=list_bg2 align=right>邮件:</TD> 
					<TD class=list_bg1>
					   <INPUT class=text maxLength=10 size=13 name=customer.email value="${customer.email }">
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
					<TD class=list_bg1 colSpan=4 align="center">
						
						<s:if test="isAddAction">
							<input type="image" src="<%=basePath%>img/tianjia.gif" height="20">
						</s:if>
						<s:if test="isEditAction">
							<input type="image" src="<%=basePath%>img/add.gif" height="20">
						</s:if>
						
					</TD>
				</TR>
			</TBODY>
		</TABLE>

	</FORM>
</BODY>
</HTML>
