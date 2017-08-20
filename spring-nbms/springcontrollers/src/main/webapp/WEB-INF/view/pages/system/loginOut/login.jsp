<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<%@include file="../../../../../resources.jsp" %>
</head>
<body>
<form action="<%=basePath%>/system/loginOut/login.action" method="post">
	<%--<input type="hidden" name="token" value="${token}">--%>
	<input type="text" name="account"><br>
	<input type="password" name="password"><br>
	<input type="submit" value="Login">

</form>
	
</body>
</html>