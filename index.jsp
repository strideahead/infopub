<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="images/favicon.ico" rel="shortcut icon">
</head>
<body>
<%
	String contextPath=request.getContextPath();
	out.println("contextPath====="+contextPath);
	response.sendRedirect(contextPath+"/indexAction");
%>
</body>
</html>