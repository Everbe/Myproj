<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>Show All</title>
</head>
<body>
       <s:form action="showall">
           <input type="submit" value="全表示" />
   		</s:form>

	<div style="text-align: center">
		<c:if test="${not empty request.list_data }">
			<table border="1" width="440px;" id="showlist">
				<tr>
					<td width=30%>ユーザー名</td>
					<td width=30%>携帯電話</td>
					<td width=10%>年龄</td>
				</tr>
				<s:iterator value="#request.list_data" status="stat">
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


</body>
</html>