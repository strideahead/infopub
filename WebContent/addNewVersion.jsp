<%@page import="java.util.ArrayList,com.lynuc.PO.VersionPO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加新版本 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet" />
<link href="css/button.css" rel="stylesheet" />
<link href="css/pageControl.css" rel="stylesheet" />
<link href="css/jquery-ui-style.css" rel="stylesheet" />
<link href="css/jquery-ui.css" rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<style type="text/css">
table{
	text-align:center;
	width: 70%; 
	margin: 0px auto; 
}
table th,table td,table td input{
	font-size:18px;
	padding:3px;
}
table tr{
	text-align: center;
	padding-left:100px;
}
table th{
	text-align:right;
}
table td{
	text-align:left;
}
table td input{

}
</style>
</head>
<body>
	<div class="wrap" id="center">
		<div class="header" style="background-color: #0c3365;">
			<h1 class="header_style">InfoPub v6.0</h1>
			<div class="nav_main">
				<ul>
					<li><a href="MccListAction">注册码管理<span class="arrow"></span></a>
						<ul class="erj_ui">
							<c:if test="${user.permission<'C'}">
								<li class="erji"><a href="AddControlCardItem">添加控制器</a></li>
							</c:if>
							<c:if test="${user.permission < 'C'}">
								<li class="erji"><a href="batchApply.action">批量申请</a></li>
							</c:if>
							<c:if test="${(user.usercode eq tableproducts.pwd_checker_accounts) or (user.usercode eq 'liuhaifeng')}">
								<li class="erji"><a href="batchApproveActionpc">批量批准</a></li>
							</c:if>
						</ul></li>
					<li><a href="#">管理员管理<span class="arrow"></span></a>
						<ul class="erj_ui">
							<li class="erji"><a href="showCompanies">公司管理</a></li>
							<li class="erji"><a href="showUsers">用户管理</a></li>
							<li class="erji"><a href="showVersions">版本管理</a></li>
							<li class="erji"><a href="showSyslogs">日志管理</a></li>
						</ul></li>
					<li><a href="#">${user.username }<span class="arrow"></span></a>
						<ul class="erj_ui">
							<li class="erji"><a href="javascript：void(0);"
								onclick="resetpassword();">重设密码</a></li>
							<li class="erji"><a href="logout">退出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
			<%
               	String is_soft="";
               	String is_publish="";
               	String is_editable="";
               	if(request.getAttribute("version_get")!=null){
               		VersionPO vp=(VersionPO)request.getAttribute("version_get");
               		is_editable="readonly";
               		if("Soft".equals(vp.getIs_soft())){
               			is_soft="checked='checked'";
               		}
               		if("Yes".equals(vp.getIs_publish())){
               			is_publish="checked='checked'";
               		}
               	}
            %>
            <div align="center" style="background-color:#d5f3f4;">
            <div style="padding:30px 0;"></div>
			<form name="jspnetForm" id="jspnetForm" method="post" action="insertVersionInfoIntoDB" onsubmit="return check(this);return false;">
				<table>
					<thead>
						<c:if test="${versiongguid == null }">
							<span id="lblHeader" class="header"
								style="display: inline-block; font-size: Large; width: 215px;">添加新版本</span>
						</c:if>
						<c:if test="${versiongguid != null }">
							<span id="lblHeader" class="header"
								style="display: inline-block; font-size: Large; width: 215px;">编辑版本</span>
						</c:if>
					</thead>
					<tbody>
					<tr>
						<th width="35%">版本名称</th>
						<td><input value="${version_get.version_name }"
							<%=is_editable %> name="txtVersionName" type="text"
							required="required" id="txtVersionName"
							class="normal"/></td>
					</tr>
					<tr>
                       <th></th>
                       	<td align="center">
      					<div style="display: inline;" id="tip1"></div>
   						</td>
                    </tr>
					<tr>
						<th></th>
						<td><input
							type="checkbox" name="chkIssoft" id="chkIssoft" <%=is_soft %> />
							<label for="chkIssoft">是否为软件版本</label></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="checkbox" name="chkIspublish"
							id="chkIspublish" <%=is_publish %> /><label for="chkIspublish">是否为用于生产的版本</label></td>
					</tr>
					<tr>
						<th></th>
						<td align="center"><c:if
								test="${versiongguid == null}">
								<input name="btnSubmit" id="btnSubmit" type="submit" value="提交" 
                    				class="button button-pill button-primary" style="width:100px;height:35px;text-align: left;padding-left:35px;padding-top:-5px;">
							</c:if> <c:if test="${versiongguid != null}">
								<input name="btnOK" id="btnOK" type="button" value="确定" 
                    				class="button button-pill button-primary" onclick="edit();"  style="width:100px;height:35px;text-align: left;padding-left:35px;padding-top:-5px;;">
							</c:if><input type="hidden" name="hidden_gguid" id="hidden_gguid"
							value="${versiongguid }">
							<a href="showVersions">
								<input name="btnBack" id="btnBack" type="button" value="返回" 
                    				class="button button-pill button-primary"  style="width:100px;height:35px;text-align: left;padding-left:35px;padding-top:-5px;;">	
								</a>
						</td>
					</tr>
					</tbody>
				</table>
			</form>
			</div>
			<div>
		<div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div> 
	</div>
		</div>
	<script type="text/javascript" lang="javascript">setTimeout("window.top.location='logout';", 1200000);</script>
</body>
<script>
$(document).ready(function(){                
    $("#txtVersionName").blur(function(){
            var version_name=$("#txtVersionName").val();
            check();
            $.ajax({
                url:"checkVersionName?version_name="+version_name,
                type:"get",
                success:function(e){
                    if(e==1){     
                    	$("#txtVersionName").val("");
                    	$("#tip1").html("<font color=\"red\" size=\"2\">数据库中已经有此版本</font>");
                    	$("#txtVersionName").focus();
                    	//alert("数据库中已经有此版本!");
                        return false;
                    } 
                    else{                                 
                    	$("#tip1").html("");
                    }
                }                 
            });
       });
    });
function edit()
{
	document.forms.jspnetForm.action="changeVersionInfo";
	if(true==check()){
		document.forms.jspnetForm.submit();
	}
}
function check(form)
{
	var version_name=$("#txtVersionName").val();
	var patrn=/^\w+\.\w+\.\w+\.\w+$/gi; 
	//var reg=new RegExp('^\w+\.\w+\.\w+\.\w+$','gi');
	//alert(version_name);
	if(version_name=="" || version_name==null){
		alert("版本名称不能为空!");
		return false;
	}
	if (!patrn.exec(version_name)) {
		alert("输入格式不正确!");
		return false;
	}
	return true;
}
</script>
</html>