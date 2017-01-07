<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>注册码列表 - infopub</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link href="images/favicon.ico" rel="shortcut icon">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
	<link rel="stylesheet" href="bootstrap/css/rwd-table.min.css">
	<link href="css/button.css"  rel="stylesheet" />
    <script src="js/jquery-3.0.0.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/rwd-table.js"></script>
<style type="text/css">
.checkoutitems {
	display:inline-block;
    font-size: 20px;
    color: #ff9900;
    letter-spacing: 2px;
}
</style>
</head>
<body>
<div class="wrap" id="center" style="margin:0 3px;background-color:#d5f3f4;">
	<div align="left" style="padding-left:10px;
		font-weight: bold;font-style: italic;font-size:20px;height:30px;line-height: 30px;text-align: center;">
			Information Public System v6.0
	</div>
	<div style="font-size: 20px;padding-top:10px;">
		<div style="float:left;display:inline-block;color: red;padding-right:10px;">${user.username}</div>
		<c:if test="${(user.usercode eq tableproducts.pwd_checker_accounts) or (user.usercode eq 'liuhaifeng')}">
			<div style="float:right;padding-right:15px;">
				<a href="batchApproveAction">
					<input name="batApprove" id="batApprove" type="button" value="批量批准" 
                		class="button button-pill button-primary" 
                		style="width:100px;height:35px;text-align: left;padding-left:20px;"></a>
			</div>
		</c:if>
	</div>
	<div style="height: 20px;clear: left;padding-left:30px;">
		
	</div>
	<div style="clear:both;text-align: left;padding:10px 0;">
		<form id="MccForm" class="MccForm" method="post" action="mccpassword_m">
			<input name="btnSubmit" id="btnSubmit" type="submit" value="检索" 
                		class="button button-pill button-primary" 
                		style="width:5em;height:2.5em;text-align: left;padding-left:25px;">&nbsp;&nbsp;&nbsp;
			<s:textfield label="关键字" name="keyStrm" id="mccKeystrm" size="20px" theme="simple"
				 value="%{#session.mccKeystrm }"><label style="font-size: 15px;">关键字:</label></s:textfield>  
			<s:hidden name="isSearchBtn" value="true"></s:hidden>
		</form>
	</div>
<section id="demo">
		<div class="container" style="padding: 5px 1px;">
			<div class="table-responsive" data-pattern="priority-columns">
				<table id="tech-companies-1" class="table table-bordered table-striped">
			<thead align="center" >
				<tr 
					style="text-align:center;background-color:RGB(144,238,144) ;font-size: 15px;font-family: '宋体';color: black;font-weight: bolder;"> 
					<th align="center" style="width: 15%;white-space: normal;padding:2px 2px;text-align: center;">申请号</th>
					<th align="center" style="width: 22%;white-space: normal;padding:2px 2px;text-align: center;">控制器</th>
					<th align="center" style="width: 32%;white-space: normal;padding:2px 2px;text-align: center;">客户</th>
					<th align="center" style="width: 11%;white-space: normal;padding:2px 2px;text-align: center;">状态</th>
					<th align="center" style="width: 20%;white-space: normal;padding:2px 2px;text-align: center;">结束日期</th>
				</tr>
			</thead>
			<tbody>
			
			<s:iterator value="#request.mmList.dataList " id="m">			
			<c:if test="${state!='P'}">
               		<c:if test="${apply_no eq 0}">
               			<tr title="<s:property/>" 
						onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';"  
					 	onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;"
					 	onclick="window.location='applyOrCancPass_m?txt_controller_no=${controller_no}&is_revert=false';">
					</c:if>
					<c:if test="${apply_no gt 0}">
						<tr title="<s:property/>" 
							onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';"  
						 	onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;"
						 	onclick="window.location='applyOrCancPass_m?apply_no=${apply_no}&is_revert=false';">
						 	<%-- onclick="window.location='AddControlCardItem?controller_no=${controller_no }&mobile=yes';"> --%>
					</c:if>
               </c:if>
			<c:if test="${state=='P'}">
               		<%-- <c:if test="${user.id != request_user_id }">
                		<tr title="<s:property/>" >              			
               		</c:if> --%>
               		<%-- <c:if test="${user.id == request_user_id }"> --%>
               			<tr title="<s:property/>" 
						 	onclick="window.location='applyOrCancPass_m?apply_no=${apply_no}&is_revert=true';">       			
               		<%-- </c:if> --%>
             </c:if>
				<td style="font-size: 14px;padding:5px 1px;text-align: center;">${apply_no }</td>
                <td style="font-size: 14px;padding:5px 1px;text-align: center;color:red;">${controller_no}</td>
                <td style="font-size: 14px;padding:5px 1px;white-space:normal;text-align: center;">${customer_name}</td>
                <td style="font-size: 14px;padding:5px 1px;color: red;text-align: center;">
                	<c:if test="${state eq 'Y'}"><font color="green">√</font></c:if>
					<c:if test="${state eq 'N'}"><font color="red">×</font></c:if>
					<c:if test="${state eq 'P'}"><font color="Magenta">?</font></c:if>
                </td>
                <c:set var="fontcolor" scope="page" value="black"/>
                <c:if test="${is_forever eq 'Y' }">
                	<td style="font-size: 15px;">永久密码</td> 
                </c:if>
                <c:if test="${is_forever ne 'Y' }">
                	<c:set var="expirestate" value="${enddate }"></c:set>
	                <c:choose>
	                	<c:when test="${fn:contains(expirestate,'-') }">
	                		<c:set var="expirestate" value="${fn:substring(expirestate,2,10) }"></c:set>
	                	</c:when>
	                </c:choose>
                	<td style="font-size: 14px;padding:5px 1px;text-align: center;">${expirestate }</td> 
                </c:if>
		</s:iterator>
	</tbody>
</table>
           </div>  
		</div>
	</section>
	<div class="PagerCss" align="center" style="font-size:15px;">
			共<font color="red">${mmList.totalRecord }</font>条记录&nbsp;&nbsp;共${mmList.totalPage }页&nbsp;当前第${mmList.currentPage }页
			&nbsp;&nbsp;<s:select id="pageSize" name="pageSize" list="{'15','20','40','80','160'}" theme="simple"  headerKey="-1" headerValue=""
 				value="%{#request.pageSize_m }" onchange="change();"/>
	</div>
	<div class="PagerCss" align="center" style="font-size:15px;">
		<%
		int currentpage=1,totalpage=1;
		if(request.getAttribute("currentpage")!=null){
			currentpage=Integer.parseInt(request.getAttribute("currentpage").toString());
		}
		if(request.getAttribute("totalpage")!=null){
			totalpage=Integer.parseInt(request.getAttribute("totalpage").toString());
		}
		int prevpage=(currentpage==1)?1:(currentpage-1);
		int nextpage=(currentpage==totalpage)?totalpage:(currentpage+1);
	%>
		<a class="hrefLink" href="mccpassword_m?pageNum=1&pageSize=${pageSize_m }" >首页</a>&nbsp;&nbsp; &nbsp;
		<a class="hrefLink" href="mccpassword_m?pageNum=<%=nextpage %>&pageSize=${pageSize_m }">下一页</a>&nbsp;&nbsp;&nbsp;
		<a class="hrefLink" href="mccpassword_m?pageNum=<%=prevpage %>&pageSize=${pageSize_m }">上一页</a>&nbsp;&nbsp;&nbsp;
		<a class="hrefLink" href="mccpassword_m?pageNum=<%=totalpage %>&pageSize=${pageSize_m }">尾页</a>&nbsp;&nbsp;&nbsp;
	</div>
	
	<div>
		<input id="pageNum_hidden" type="hidden" value="1" />
		<input id="pageSize_hidden" name="pageSize_hidden" type="hidden" value="${(!empty pageSize)?pageSize:15 }" />
	</div>
	<footer>
		<div align="center" style=";margin-top:5px;margin-bottom: 5px;line-height: 20px;font-family: '宋体';">
		上海铼钠克数控科技股份有限公司<br>地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话:<a href="tel:021-61837766">021-61837766</a>
 	</div>
	</footer>
 	<div align="center" style="padding-bottom:10px;">
 		<input name="btnExit" id="btnExit" type="button" value="退出" 
                class="button button-pill button-primary" 
                style="width:5em;height:2em;text-align: left;padding-left:25px;" onClick="window.location='logout';">
 	</div>
</div>
<script>
function change()
{
	pageSize=$("#pageSize").val();
	$("#pageSize_hidden").val(pageSize);
	//window.location.href="mccpassword_m?pageSize="+pageSize;
	window.location.href="mccpassword_m?pageSize="+pageSize+"&isSearchBtn=false";
}
</script>
</html>
