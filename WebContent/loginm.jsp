<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机登录 - InfoPub v6.0</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap/css/rwd-table.min.css">
<link rel="stylesheet" href="css/loginm.css">
<script src="js/jquery-3.0.0.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/rwd-table.js"></script>
<style>
.companylogo{
	FONT-SIZE: 26pt; 
	FILTER: dropshadow(color=#228B22,offX=5,offY=3,Positive=1); 
	WIDTH: 100%; 
	COLOR: #ff7f50; 
	LINE-HEIGHT: 150%;
	FONT-FAMILY: 华文行楷
}
</style>
</head>
<body>
<div class="login">
 	<div class="login-main">
 		<div class="login-top">
 			<div style="font-style: italic;font-size:1rem;margin-bottom:3px;">Information Public System v6.0</div>
 			<div class="companylogo">
 				<strong>LYNUC铼钠克</strong></div>
 			<form method='post' class='form-condensed' action="LoginServlet_m">
 			<input name="logAccount" id="logAccount" type="text" placeholder="用户名/手机号码" required="required">
 			<input name="password" id="password" type="password" placeholder="密码" required="required">
 			<div class="login-bottom">
 			</div>
 			<input type="submit" value="登录" />
 			</form>
 		</div>
 	</div>
 </div>
</body>
</html>