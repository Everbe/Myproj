<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	if (session.getAttribute("user") == null) {
%>
<script type="text/javascript" language="javascript">
	alert("ログインしてください");
	top.location.href = "index.jsp";
	


</script>
<%
	}
%>

<%-- <%
	if (session.getAttribute("user") != null) {
%>
<script type="text/javascript" language="javascript">
	function logOut() {
		session.removeAttribute("user");
		alert("ログアウトしました");
		top.location.href = "index.jsp";
	}
	
	


</script>
<%
	}
%> --%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーページ</title>


</head>
<body>
	<s:form action="show">
		<s:submit value="全ユーザー表示" />
	</s:form>
	<s:form action="logout">
		<s:submit value="ログアウト" />
	</s:form>
	<s:if test="hasActionMessages()">
		<div class="logout">
			<s:actionmessage />
		</div>
	</s:if>
	参照データを入力
	<s:form action="info">
 名前:<s:textfield name="uName" />
 携帯:<s:textfield name="uMobile" maxlength="11"
			oninput="value=value.replace(/[^\d]/g,'')" />
年齢:<s:textfield name="uAge1" oninput="value=value.replace(/[^\d]/g,'')"
			style="width:30px;" maxlength="3" /> 歳から<s:textfield name="uAge2"
			maxlength="3" style="width:30px;"
			oninput="value=value.replace(/[^\d]/g,'')" />歳 
			
		<s:submit value="検索" onclick="viewList()" />
	</s:form>


	<!--  		<button type="button"  οnClick="javascript:logOut()">退出</button>  -->



	<!-- <select name="uAge">-->
	<!--<option value="0"></option>-->
	<!--<option value="1">0-20</option>-->
	<!--<option value="2">21-40</option>-->
	<!--<option value="2">41-60</option>-->
	<!--</select>-->




	<div style="text-align: center">
		<c:if test="${not empty request.check }">
			<table border="1" width="440px;" id="showlist">
				<tr>
					<td width=30%>ユーザー名</td>
					<td width=30%>携帯電話</td>
					<td width=10%>年龄</td>
				</tr>
				<s:iterator value="#request.check" status="stat">
					<tr>
						<td><s:property value="user" /></td>
						<td><s:property value="mobile" /></td>
						<td><s:property value="age" /></td>
					</tr>
					<div></div>
				</s:iterator>
			</table>
		</c:if>
	</div>



	<c:if test="${not empty list }">
		<table border="1">
			<tr>
				<td>名字</td>
				<td>电话</td>
				<td>年龄</td>
			</tr>

			<s:iterator value="list" var="user" status="ss">
				<tr>
					<td><s:property value="#user.user" /></td>
					<td><s:property value="#user.mobile" /></td>
					<td><s:property value="#user.age" /></td>
				</tr>
			</s:iterator>
		</table>

		<ul>
			<li><s:property value="pageNow" />/<s:property
					value="totalPage" /> <s:url action="page" var="firstPage">
					<s:param name="pageNow">1</s:param>
				</s:url> <s:a href="%{firstPage}">首页</s:a> <s:url action="page"
					var="prePage">
					<s:param name="pageNow">
						<s:property value="pageNow-1" />
					</s:param>
				</s:url>
				<s:a href="%{prePage}">上一页</s:a> <s:url action="page" var="nextPage">
					<s:param name="pageNow">
						<s:property value="pageNow + 1" />
					</s:param>
				</s:url>
				<s:a href="%{nextPage}">下一页</s:a> <s:url action="page"
					var="lastPage">
					<s:param name="pageNow">
						<s:property value="totalPage" />
					</s:param>
				</s:url>
				<s:a href="%{lastPage}">末页</s:a></li>
		</ul>
	</c:if>
	<a href="index.jsp"><input type="button" value="ホームへ戻る" /></a>
</body>
</html>