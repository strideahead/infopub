<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码查询 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
 <link href="css/table.css" rel="stylesheet"/>
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script>
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
.table, .table * {margin: 5px  auto; padding: 0;font-size: 14px;font-family: Arial, 宋体, Helvetica, sans-serif;}
.table tr th,.table tr td{background-color:white;border:1px solid black;padding:5px;}
</style>
</head>
<body>
<div class="wrap" id="center">
	<div class="header" style="background-color:#0c3365;">
		<h1 class="header_style"> InfoPub v6.0</h1>
		<div class="nav_main">
			<ul>
				<li><a href="MccListAction">注册码管理<span class="arrow"></span></a>
					<ul class="erj_ui">
						<c:if test="${user.permission<'C'}">
							<li class="erji">
								<a href="AddControlCardItem">添加控制器</a>
							</li>
						</c:if>
						<c:if test="${user.permission < 'C'}">
							<li class="erji">
								<a href="batchApply.action">批量申请</a>
							</li>
						</c:if>
						<c:if test="${(user.usercode eq tableproducts.pwd_checker_accounts) or (user.usercode eq 'liuhaifeng')}">
							<li class="erji">
								<a href="batchApproveActionpc">批量批准</a>
							</li>
						</c:if>
					</ul>
				</li>
				<li><a href="#">${user.username }<span class="arrow"></span></a>
					<ul class="erj_ui">
						<li class="erji"><a href="javascript：void(0)" onclick="resetpassword();return false;">重设密码</a></li>
						<c:if test="${user.permission eq 'A'}">
							<li class="erji">
			 					<a href="showCompanies">进入管理</a>
	       					</li>
        				</c:if>
						<li class="erji"><a  href="logout">退出</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div align="center">
	<div class="themControl" style="clear:both;padding-top:20px;">
		<div style="clear:both;text-align:left;margin-left:20px;float:left;">
			【<span class="checkoutitems">${mccpassAmount}</span>条】
		</div>
		<div class="table" style="float:right;width:80%;" >
			<table style="border-collapse: collapse;width:80%;border:1px solid black;">
				<tr>
					<th>产品:</th>
					<td>${mcc.controller_ver }</td>
					<th>机床编号:</th>
					<td>${mcc.machine_no }</td>
					<th>机床大类:</th>
					<td>${mcc.total_type }</td>
				</tr>
				<tr>
					<th>机床型号:</th>
					<td>${mcc.machine_type }</td>
					<th>控制器号:</th>
					<td>${mcc.controller_no }</td>
					<th>控制卡号:</th>
					<td>${mcc.card_no }</td>
				</tr>
				<tr>
					<th>事务所:</th>
					<td colspan="2">${mcc.office_name }</td>
					<th>客户名:</th>
					<td colspan="2">${mcc.customer_name }</td>
				</tr>
			</table>

     </div>
	</div>
	<c:if test="${fn:length(mccpassList) eq 0 }">
	<div class="table_content" style="clear:both;">
			<div style="background-image: './images/prompt.gif';height:30px;width:880px;">
				<span id="gridEmpty" class="prompt" style="display:inline-block;height:24px;width:880px;">暂无纪录</span>
			</div>
	</div>
	</c:if>
	<c:if test="${fn:length(mccpassList) gt 0 }">	
	<div class="table_content" style="clear:both;padding-top:20px;">
		<table id="listPassword" style="color:#333333;width:100%;border-collapse:collapse;">
			<thead>
			<tr style="color:White;background-color:#5D7B9D;font-weight:bold;white-space:nowrap;font-size: 18px;">
				<th align="center" scope="col" style="white-space:nowrap;">申请号</th>
				<th align="center" scope="col" style="white-space:nowrap;">控制器号</th>
				<th align="center" scope="col" style="white-space:nowrap;">控制卡号</th>
				<th align="center" scope="col" style="white-space:nowrap;">密码状态</th>
				<th align="center" scope="col" style="white-space:nowrap;">结束日期</th>
				<th align="center" scope="col" style="white-space:nowrap;">申请人</th>
				<th align="center" scope="col" style="white-space:nowrap;">批准人</th>
				<th align="center" scope="col" style="white-space:nowrap;">批准时间</th>
				<th align="center" scope="col" style="white-space:nowrap;">操作</th>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="#request.mccpassList " id="m">
			<tr title="密码:&#10;${the_password }" class="trHint" 
				onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" 
				onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;"
				style="padding:3px;font-size:15px;line-height:20px;height:20px;background-color: ${expirestate}">
				<td align="center" style="white-space:nowrap;">${apply_no }</td>
				<td align="center" style="white-space:nowrap;">${controller_no }</td>
				<td align="center" style="white-space:nowrap;">${card_no }</td>
				<c:choose>
				  <c:when test="${state=='未批准'}">
				  	<c:set var="bgcolor" value="Magenta" />
				  </c:when>
				  <c:when test="${state=='已批准'}">
				  <c:set var="bgcolor" value="green" />
				  </c:when>
				  <c:when test="${state=='拒绝'}">
				  <c:set var="bgcolor" value="red" />
				  </c:when>
				</c:choose>
				<td align="center" style="color:${bgcolor};white-space:nowrap;">${state }</td>
				<td title="${enddate }" align="center" style="white-space:nowrap;">${enddate }</td>
				<td align="center" style="white-space:nowrap;">${request_user_name}</td>
				<td align="center" style="white-space:nowrap;">${check_user_name }</td>
				<td title="${check_time }" align="center" style="white-space:nowrap;">${check_time}</td>
				<td align="center" style="white-space:nowrap;">
                   <a id="lnkViewApply"  href="passwordDetail?apply_no=${apply_no}&loc=2"><img src="images/edit.gif" title="查看&#10;[密码]" alt="查看&#10;[密码]"/></a>
            	</td>
			</tr>
			</s:iterator>
	</tbody>
</table>
	</div>
	</c:if>
	<div style="clear:right;margin-top: 10px;margin-bottom:10px;clear:both;">
		<div class="bottomHint" style="float:left;display: table;font-size: 14px;">
			<div style="display: table-row;">
				<div style="display:table-cell;padding:2px 5px 2px 10px;">已过期</div>
				<div style="display:table-cell;width: 70px; background: #D6D3CE;"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">一月内过期</div>
				<div style="display:table-cell;width: 70px; background: #FCF7B8"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">三月内过期</div>
				<div style="display:table-cell;width: 70px; background: #CAEAFC"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">超过三月过期</div>
				<div style="display:table-cell;width: 70px; background: RGB(176,196,222)"></div>
			</div>
		</div>
		<div style="float:right;">
			 <a href="MccListAction" class="button button-primary button-small" style="margin-right:30px;">返回</a>
		</div>
	</div>	
</div>
	
	<div style="margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div>
</div>
</body>
</html>