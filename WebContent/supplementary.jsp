<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修复Mcc列表module_set字段</title>
<link href="css/button.css"  rel="stylesheet" />
</head>
<body>
<div>
<ul>
	<li><input type="button" id="repiremoduleset" name="repiremoduleset" value="一键Mcc列表module_set字段" 
	class="button button-pill button-primary" onclick="window.location.href='supplementaryAction';"></li>
</ul>
<br>
<span>${success }</span>
</div>
</body>
</html>