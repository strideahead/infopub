<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>控制器详情 - InfoPub v6.0</title>
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
	table tr{
		padding:5px 5px;
		font-size:18px;
		font-family: :sans-serif,宋体;
		border:1px solid black;
	}
	table tr th,table tr td{
		padding:2px 2px;
		font-size:18px;
		aligh:center;
	}
</style>
</head>
<body>
<div class="wrap" id="center">
  <div class="header" style="background-color:#0c3365;">
    <h1 class="header_style"> InforPub v6.0</h1>
    <div class="nav_main">
      <ul>
        <li><a href="MccListAction">注册码管理<span class="arrow"></span></a>
          <ul class="erj_ui">
            <c:if test="${user.permission<'C'}">
              <li class="erji"> <a href="AddControlCardItem">添加控制器</a> </li>
            </c:if>
            <c:if test="${user.permission < 'C'}">
              <li class="erji"> <a href="batchApply.action">批量申请</a> </li>
            </c:if>
            <c:if test="${(user.usercode eq tableproducts.pwd_checker_accounts) or (user.usercode eq 'liuhaifeng')}">
              <li class="erji"> <a href="batchApproveActionpc">批量批准</a> </li>
            </c:if>
          </ul>
        </li>
        <li><a href="#">${user.username }<span class="arrow"></span></a>
          <ul class="erj_ui">
            <li class="erji"><a href="#" onclick="resetpassword();return false;">重设密码</a></li>
            <c:if test="${user.permission eq 'A'}">
              <li class="erji"> <a href="showCompanies">进入管理</a> </li>
            </c:if>
            <li class="erji"><a  href="logout">退出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <%
       	HashMap  map=(HashMap)request.getAttribute("mccpassData");
       	String is_pay=(String)map.get("is_pay");
       	if((is_pay.equals("Y"))) {
       		is_pay="已付";
       	}else if(is_pay.equals("N")){
       		is_pay="未付";
       	}else{
       		is_pay="";
       	}
       	String is_forevertmp=(String)map.get("is_forever");
       	boolean is_forever=false;
       	String endDate="";
       	if(is_forevertmp!=null){
       		if(is_forevertmp.equals("Y")){
        		is_forever=true;
        		endDate="永久密码";
        	}else{
        		endDate=(String)map.get("enddate");
        	}
       	}
    %>
  <div style="width:100%;padding:10px auto; margin-bottom:10px;background-color:#d5f3f4;">
    <div style="font-size:Large;font-weight: bolder;height:50px;line-height:40px;font-size:">机床/控制器/控制卡/密码/Ez</div>
    <div align="center">
      <table border="1" style="border-collapse: collapse;border-style:double; color: Black;padding:5px auto;">
        <tr>
          <th>产品:</th>
          <td>${mccpassData.controller_ver }</td>
        </tr>
        <tr>
          <th>机床编号:</th>
          <td>${mccpassData.machine_no }</td>
        </tr>
        <tr>
          <th>机床大类:</th>
          <td>${mccpassData.total_type }</td>
        </tr>
        <tr>
          <th>机床型号:</th>
          <td>${mccpassData.machine_type }</td>
        </tr>
        <tr>
          <th>控制器号:</th>
          <td>${mccpassData.controller_no }</td>
        </tr>
        <tr>
          <th>控制卡号:</th>
          <td>${mccpassData.card_no }</td>
        </tr>
        <tr>
          <th>主机序列号</th>
          <td>${mccpassData.ipc_sn }</td>
        </tr>
        <tr>
          <th>密码版本:</th>
          <td>${mccpassData.password_ver }</td>
        </tr>
        <tr>
          <th>最新付款状态:</th>
          <td><%=is_pay %></td>
        </tr>
        <tr>
          <th>事务所:</th>
          <td>${mccpassData.office_name }</td>
        </tr>
        <tr>
          <th>客户名:</th>
          <td>${mccpassData.customer_name }</td>
        </tr>
        <tr>
          <th>安装日:</th>
          <td>${mccpassData.setup_date }</td>
        </tr>
        <tr>
          <th>软件版本:</th>
          <td>${mccpassData.soft_ver }</td>
        </tr>
        <tr>
          <th>硬件版本:</th>
          <td>${mccpassData.hard_ver }</td>
        </tr>
        <tr>
          <th>机床选配:</th>
          <td>${mccpassData.mac_option }</td>
        </tr>
        <tr>
          <th>系统配置:</th>
          <td>${mccpassData.lynuc_option }</td>
        </tr>
        <tr>
          <th>当前密码:</th>
          <td>${mccpassData.the_password }</td>
        </tr>
        <tr>
          <th>当前密码有效期:</th>
          <td><%=endDate %></td>
        </tr>
        <tr>
          <th>备注:</th>
          <td>${mccpassData.request_memo }</td>
        </tr>
        <tr>
          <th>模块:</th>
          <td style="width:400px;height: 40px;word-break:break-all; word-wrap:break-all;">${mccpassData.module_set }</td>
        </tr>
        <tr>
          <th>登录人</th>
          <td>${mccpassData.input_user_name }</td>
        </tr>
        <tr>
          <th>登录时间</th>
          <td>${mccpassData.input_time }</td>
        </tr>
      </table>
    </div>
  </div>
  <div>
    <input name="btnCancelDetail" id="btnCancelDetail" type="button" value="返回" 
               class="button button-pill button-primary" onclick="window.location='MccListAction';">
  </div>
  <div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;"> <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766 </div>
</div>
</body>
</html>
