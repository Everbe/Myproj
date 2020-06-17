
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>

	<s:iterator value="list" var="adduser" status="stat">
					<tr>
						<td><s:property value="#adduser.id"/></td>
						<td><s:property value="#adduser.user" /></td>
						<td><s:property value="#adduser.mobile" /></td>
						<td><s:property value="#adduser.age" /></td>
					</tr>
</s:iterator>
<s:property value="bestTutorialSitet" />
</body>
</html>
