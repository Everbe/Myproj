<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
<%
	if (session.getAttribute("user") == null) {
%>
<script type="text/javascript" language="javascript">
	alert("您还没有登录，请登录...");
	top.location.href = "index.jsp";
	


</script>
<%
	}
%> --%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户查询</title>


</head>
<body>
	参照データを入力
	<s:form action="userInfo">
 名前:<s:textfield name="uName" />
 携帯:<s:textfield name="uMobile" maxlength="11"
			oninput="value=value.replace(/[^\d]/g,'')" />
年齢:<s:textfield name="uAge1" oninput="value=value.replace(/[^\d]/g,'')"
			style="width:30px;" maxlength="3" /> 歳から<s:textfield name="uAge2"
			maxlength="3" style="width:30px;"
			oninput="value=value.replace(/[^\d]/g,'')" />歳 
			
		<s:submit value="検索" />	
		
	<div style="text-align: center">
		<c:if test="${not empty list }">
			<table border="1" width="440px;" id="showlist">
				<tr>
					<td width=30%>ユーザー名</td>
					<td width=30%>携帯電話</td>
					<td width=10%>年龄</td>
				</tr>
				<s:iterator value="list" var="adduser" status="ss">
					<tr>
						<td><s:property value="#adduser.user" /></td>
						<td><s:property value="#adduser.mobile" /></td>
						<td><s:property value="#adduser.age" /></td>
					</tr>
				</s:iterator>
			</table>

			<ul>
				<li>
				<s:property value="pageNow" />/<s:property
						value="totalPage" /> <s:url action="page1" var="firstPage">
						<s:param name="pageNow">1</s:param>
					</s:url> <s:a href="%{firstPage}">首页</s:a> <s:url action="page1"
						var="prePage">
						<s:param name="pageNow">
							<s:property value="pageNow-1" />
						</s:param>
					</s:url>
					<s:a href="%{prePage}">上一页</s:a> <s:url action="page1"
						var="nextPage">
						<s:param name="pageNow">
							<s:property value="pageNow + 1" />
						</s:param>
					</s:url>
					<s:a href="%{nextPage}">下一页</s:a> <s:url action="page1"
						var="lastPage">
						<s:param name="pageNow">
							<s:property value="totalPage" />
						</s:param>
					</s:url>
					<s:a href="%{lastPage}">末页</s:a></li>
			</ul>
			
		</c:if>
	</div>
	</s:form>
	<a href="index.jsp"><input type="button" value="ホームへ戻る" /></a>
</body>
</html>