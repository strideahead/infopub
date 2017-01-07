<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量申请密码结果 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<link href="css/jquery-ui-1.9.2.custom.min8.css" rel="stylesheet"/>
<script src="js/jquery-3.0.0.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/jquery-ym-datePlugin-0.1.js"></script>
<script>
function trim(str)
{
	return str.replace(/^\s*/,"").replace(/\s*$/,"");
}
function check(form)
{
	var minlen=${minlen_controllerno};
	var controller_no=form.txt_controller_no.value;
	if(controller_no==null || controller_no.length < minlen)
	{
		alert("控制器项输入长度不够!");
		return false;
	}
	return true;
}
</script>
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
	<div align="center" style="padding-top:30px;">
		<div style="height: 35px;font-size: 30px;font-weight: bold;padding-bottom:20px;">批量插入结果(成功<font color="red"><c:out value="${fn:length(succeedList)}"/></font>
                	                条,失败<font color="red"><c:out value="${fn:length(failedList)}"/></font>条)
        </div>
      <div>
		<table id="listUser" style="color:#333333;width:100%;border-collapse:separate;border: 1px solid yellow">
			<tr style="color:White;background-color:#5D7B9D;height:40px;font-size:20px;font-weight:bold;white-space:nowrap;">
				<th style="width: 50px;">申请号</th>
				<th style="width: 60px;">产品</th>
				<th style="width: 80px;">机床型号</th>
				<th style="width: 120px;">控制器号</th>
				<th style="width: 150px;">控制卡号</th>
				<th style="width: 200px;">事务所</th>
				<th style="width: 200px;">客户名</th>
				<th style="width: 100px;">结果</th>
			</tr>
			<s:iterator value="#request.succeedList" id="m">	
			<tr title="${file1desc }" style="padding:3px;font-size:18px;height:25px;font-family:'宋体';color:#333333;background-color:#F7F6F3;">
                <td align="left" style="white-space:nowrap;">${input_user_id}</td>
                <td align="center">${controller_ver }</td>
                <td align="center">${machine_no }</td>
                <td align="center">${controller_no }</td>
                <td align="center" style="white-space:nowrap;">${card_no }</td>
                <td align="center" style="white-space:nowrap;">${sale_company_gguid }</td>
                <td align="center" style="white-space:nowrap;">${cust_company_gguid }</td>
                <td align="center" style="white-space:nowrap;"><span style="color:green;">申请成功</span></td>
            </tr>
                </s:iterator>
            <s:iterator value="#request.failedList" id="m">		
            <tr  style="height:25px;padding:3px;font-size:18px;font-family:'宋体';color:#333333;background-color:#F7F6F3;">
                <td></td><td></td><td></td>
                <td align="center" style="color: red;">${m }</td>
                <td></td><td></td>
                <td colspan="2" align="center" style="white-space:nowrap;"><span style="color:red;">失败：控制器不存在或最近申请未批准</span></td>
			</tr>
			 </s:iterator>
		</table>
	</div>
	<div align="center" style="font-size:18px;color:green;">注：失败原因有: 1.控制器不存在 2.最近申请未批准 3.控制器版本为空 4.控制器版本的详细内容未找到</div>
	</div>
	
    <div align="center" style="padding-top:30px;">
    	<input name="btnCancelEdit" id="btnCancelEdit" type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='MccListAction';">
	</div>
	<div style="margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div>
</div>
</body>
</html>