<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset='utf-8' />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录--infopub</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" href="bootstrap/css/rwd-table.min.css">
<link rel="stylesheet" href="css/loginm.css">
<script src="js/jquery-3.0.0.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/rwd-table.js"></script>

</head>
<body onload="document.getElementById('submitBtn').focus()">
<div class="login">
<%
	Cookie[] cookies = request.getCookies();
	String username="";
	
	if(cookies!=null && cookies.length>0){
		for(Cookie c :cookies ){
			if("username".equals(c.getName())){
				username=c.getValue();
			}
		}
	}
%>
 	<div class="login-main">
 		<div class="login-top">
 			<div style="font-style: italic;font-size:1.8rem;margin-bottom:3px;">Information Public System v6.0</div>
 			<img src="images/companylogo.gif" alt=""/>
 			<form method='post' class='form-condensed' action="LoginServlet">
 			<input name="logAccount" id="logAccount" type="text" placeholder="用户名/手机号码" required="required" value="<%=username %>">
 			<input name="password" id="password" type="password" placeholder="密码" required="required" value="">
 			<div class="login-bottom">
	 			<div class="clear" style="height:32px;width:280px;color:red;font-size:15px;">
	 				<c:if test="${error != null }">
	 					<ul>
	 						<li style="list-style: disc;">账号或密码输入有误!</li>
	 					</ul>
	 				</c:if>
	 			</div>
 			</div>
 			<input id="submitBtn" name="submitBtn" style="letter-spacing: 1em;" type="submit" value="登录" />
 			</form>
 		</div>
 		<div style="margin-top: 0;text-align:center;e-height: 24px;font-family: '宋体';">
 			<div style="margin-bottom:15px;"><a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a></div>
 			<div style="margin-bottom:15px;">地址：上海市徐汇区平福路279号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：021-61837766</div>
 </div>
 	</div>
 </div>
</body>
</html>