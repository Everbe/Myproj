<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>ホームページ</title>
</head>
<body>
<s:if test="hasActionMessages()"><div class="logout"><s:actionmessage/></div></s:if>
       <s:form action="login">
            名前:<s:textfield name="user" id="username" required="入力してください"/><br/>
           携帯:<s:textfield name="mobile" id="password" required="required" maxlength="11" oninput="value=value.replace(/[^\d]/g,'')" /><br/>
           <input type="submit" id="loginButton" value="ログイン" onclick="javascript:check();"/>	<a href="register.jsp"><input type="button" value="サインイン" /></a>
   		</s:form>




</body>
</html>