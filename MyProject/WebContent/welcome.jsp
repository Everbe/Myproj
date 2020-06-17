<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	いらっしゃいませ！
	<br></br> ユーザー名:
	<s:property value="uName" />
	<br /> 携帯:
	<s:property value="uMobile" />
	<br /> 年齢:
	<s:property value="uAge" />
	<br />
	<a href="index.jsp"><input type="button" value="ホームへ戻る" /></a>
</body>
</html>