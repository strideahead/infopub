<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司编辑 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js" type="text/javascript"></script>
<script type="text/javascript">
function check(form)
{
	
	return true;
}
</script>
<style type="text/css">
table{padding-top:30px; width: 70%;margin: 0px auto;background-color: RGB(144,238,144);}
table tr th,table tr td,table input,table select option,table select,table textarea{
	font-size: 18px;
}
table tr th{
	padding:3px 10px;
	text-align:right;
}
table tr td{
	text-align:left;
}
table select option{
	width:300px;
	padding:0 3px;
}
table input{
	width:300px;
	padding:0px 9px;
}
table textarea{
	width:300px;
	padding:0px 9px;
}
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
            <li class="erji"><a href="javascript：void(0);" onclick="resetpassword();">重设密码</a></li>
            <c:if test="${user.permission eq 'A'}">
              <li class="erji"> <a href="showCompanies">进入管理</a> </li>
            </c:if>
            <li class="erji"><a  href="logout">退出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <div align="center">
    <div style="font-size:30px;margin:20px auto;">公司编辑</div>
    <div align="center">
      <form name="jspnetForm" id="jspnetForm" method="post" action="changeCompanyInfo" onsubmit="return check(this);">
        <table  style="padding-left:100px;">
          <tr>
            <th>公司名称</th>
            <td><input value="${company_get.company_name }" name="txtCompanyName" type="text" id="txtCompanyName" /></td>
          </tr>
          <tr>
            <th>版本批准人</th>
            <td><s:select emptyOption="true" name="ddl_version_checker" id="ddl_version_checker"  list="#request.version_checkNameList" 
		                            		listKey="version_check_id" listValue="version_check__userinfo" value="#request.company_get.version_checker_id" theme="simple"></s:select></td>
          </tr>
          <tr>
            <th>公司地址</th>
            <td>
            	<textarea name="txtAddress" name="txtAddress" rows="4" cols="20" id="txtMemo" 
                       style="resize: none;border-style:ridge;height: 50px;">${company_get.address }</textarea>
            </td>
          </tr>
          <tr>
            <th>邮编</th>
            <td><input value="${company_get.zip }" name="txtZip" type="text" id="txtZip" /></td>
          </tr>
          <tr>
            <th>联系人</th>
            <td><input value="${company_get.contacter }" name="txtContacter" type="text" id="txtContacter" />
          </tr>
          <tr>
            <th>公司电话</th>
            <td><input value="${company_get.tel }" name="txtTel" type="text" id="txtTel" />
          </tr>
          <tr>
            <th>传真</th>
            <td><input value="${company_get.fax }" name="txtFax" type="text" id="txtFax" />
          </tr>
          <tr>
            <th>公司类型</th>
            <td><select name="ddlCompanyType" id="ddlCompanyType">
                <option selected="selected" value="1">客户</option>
                <option value="4">代理商</option>
                <option value="2">事务所</option>
                <option value="3">管理公司</option>
              </select></td>
          </tr>
          <tr>
            <th>备注</th>
            <td><textarea name="txtMemo" rows="2" cols="20" id="txtMemo" style="resize: none;border-style:ridge;height:120px;">${company_get.memo }</textarea></td>
          </tr>
          <tr style="height:60px;text-align: center;">
          	<th></th>
            <td align="center">
            	<input name="btnOK" id="btnOK" type="submit" value="确定" 
                    				class="button button-pill button-primary" style="width:150px;">
                <input type="hidden" value="${company_get.gguid }" name="hidden_gguid" id="hidden_gguid"/>
                <input type="hidden" value="${type }" name="hidden_type" id="hidden_type"/>
                <a href="showCompanies">
                   <input type="button" name="btnClose" value="返回" id="btnClose" 
                           class="button button-pill button-primary" style="width:150px;"/>
                </a></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <div>
    <div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;"> <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766 </div>
  </div>
</div>
</body>
</html>