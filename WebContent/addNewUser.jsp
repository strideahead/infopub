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
<title>添加用户  - InfoPub v6.0</title>
<link href="css/main.css" rel="stylesheet"/>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/jquery-ui-style.css" rel="stylesheet"/>
<link href="css/jquery-ui.css" rel="stylesheet"/>
<link href="css/jquery.ui.datepicker.css" rel="stylesheet"/>
<link href="css/pageControl.css" rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/jquery-ym-datePlugin-0.1.js"></script>
<style type="text/css">
.password {  
    width: 200px;  
    height: 24px;  
    font-size: 24px;  
    line-height: 24px;  
    bordeR: 1px solid #ccc;  
}
</style>
<script>
function check(form)
{
	return true;
}
function showPassword2()
{
	if($("#chk_showPassword").prop('checked')){
		$("#password_area").text("<input value='' name='txtPassword' type='text' maxlength='50' id='txtPassword' class='normal' style='width:220px;' />")
	}else{
		$("#password_area").text("<input value='' name='txtPassword' type='password' maxlength='50' id='txtPassword' class='normal' style='width:220px;' />")
	}
}
$(function(){ 
    var $password = $('#txtPassword'),   
        $passwordInput = $('<input type="text" maxlength="50"  style="width:220px;" name="' + $password.attr('name') + '" class="' + $password.attr('className') + '" />');  
   
    $('#chk_showPassword').click(function(){  
        if(this.checked){  
            $password.replaceWith($passwordInput.val($password.val()));  
        }else{  
            $passwordInput.replaceWith($password.val($passwordInput.val()));  
        }  
    });  
});   
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
                    <li><a href="#">管理员管理<span class="arrow"></span></a>
                        <ul class="erj_ui">
                            <li class="erji"><a href="showCompanies">公司管理</a></li>
                            <li class="erji"><a href="showUsers">用户管理</a></li>
                            <li class="erji"><a href="showVersions">版本管理</a></li>
                            <li class="erji"><a href="showSyslogs">日志管理</a></li>
                        </ul>
                    </li>
                    <li><a href="#">${user.username }<span class="arrow"></span></a>
                        <ul class="erj_ui">
                            <li class="erji"><a href="javascript：void(0);" onclick="resetpassword();">重设密码</a></li>
                            <li class="erji"><a href="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <form name="jspnetForm" id="jspnetForm" method="post" action="insertUserInfoIntoDB" onsubmit="return check(this);">
        <table style="width: 90%;margin: 0px auto;font-size:16px;letter-spacing: 1.5px;">
                <tr align="center">
                    <td background="../images/loginbg.jpg" style="height: 28px" colspan="2">
                        &nbsp; &nbsp;&nbsp;
                        <span id="lblHeader" class="header" style="display:inline-block;font-size:Large;width:215px;">添加新用户</span>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <div id="divAddUser" class="quote" style="width: 50%;">
                            <table style="width: 418px;" class="Table">
                                <tr>
                                    <td align="right" colspan="2">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 7px;" align="right">
                                        <span id="lblUserCode" class="label">用户名</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <input name="txtUserCode" type="text" required="required" maxlength="50" id="txtUserCode" class="normal" style="width:220px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px" align="right">
                                        <span id="lblUserName" class="label">姓  名</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px">
                                        <input name="txtUserName" id="txtUserName" max="5" type="text" maxlength="50" class="normal" required="required" style="width:220px;" />
                                    </td>
                                </tr>
                                <tr id="trPassword">
						<td style="width: 171px; height: 12px;" align="right">
                                        <span id="lblPassword" class="label">密码</span>
                                        :&nbsp;
                                    </td>
						<td style="width: 282px">
                                        	<input name="txtPassword" type="password" maxlength="50" id="txtPassword" class="normal" style="width:220px;" />
                                        <span id="reqValtxtPassword" style="display:inline-block;color:Red;width:1px;display:none;">*</span>
                                    </td>
					</tr>
					<tr id="trPassword" align="center">
						<td colspan="3">
                           <input style="margin-left: 80px;" type="checkbox" name="chk_showPassword" id="chk_showPassword" />显示密码 
                           <input type="checkbox" name="chk_resetPassword" id="chk_resetPassword" onclick="resetPassword();" />重设密码 (默认:password)
                        </td>
					</tr>
                    <tr>
                        <td style="width: 171px; height: 24px;" align="right">
                            <span id="lbl_user_belong_company" class="label">所属公司</span>
                            :&nbsp;
                        </td>
                        <td style="width: 282px;">
                        <s:select emptyOption="true" name="ddl_user_belong_company" id="ddl_user_belong_company"  list="#request.companyNameList" 
              		listKey="gguid" listValue="company_name" value="#request.select_user.belong_company_gguid"
              		 headerValue="#request.select_user.belong_company_gguid"  theme="simple" style="width:215px;"></s:select>
                        </td>
                    </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lblPermission" class="label">用户权限</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <select name="dpdPermission" id="dpdPermission" style="width:220px;">
											<option selected="selected" value="A">A:所有权限</option>
											<option value="B">B:数据编辑权限</option>
											<option value="C">C:信息查看权限</option>
										</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lbl_complaint_permission" class="label">投诉单权限</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <select name="ddl_complaint_permission" id="ddl_complaint_permission" style="width:220px;">
								<option selected="selected" value="1">1:查看/管理自己的投诉单</option>
								<option value="2">2:查看全部投诉单/管理自己的投诉单</option>
								<option value="3">3:查看/管理所有投诉单</option>
							</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lbl_email" class="label">Email</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <input name="txt_email" type="text" maxlength="50" id="txt_email" class="normal" style="width:220px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lbl_mobile" class="label">移动电话</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <input name="txt_mobile" type="text" maxlength="50" id="txt_mobile" class="normal" style="width:220px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lbl_password_type" class="label">密码类型</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <select name="ddl_password_type" id="ddl_password_type" style="width:220px;">
								<option selected="selected" value="1">固定密码</option>
								<option value="2">动态密码</option>
							</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 24px;" align="right">
                                        <span id="lbl_password_time" class="label">密码有效期</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <input name="txt_password_time" type="text" readonly="readonly" id="txt_password_time" class="normal" style="background-color:LightYellow;width:220px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 171px; height: 26px;" align="right">有效性</span>
                                        :&nbsp;
                                    </td>
                                    <td style="width: 282px;">
                                        <input id="chkIsValid" type="checkbox" name="chkIsValid" checked="checked" />
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" colspan="2">
                                        	<input type="submit" name="btnOK" value="确定"  id="btnOK" class="btn1" />
                                        &nbsp; &nbsp;&nbsp;
                                        <a href="showUsers"><input type="button" name="btnClose" value="返回" id="btnClose" class="btn1" /></a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
             </table>
             </form>
        <div>
		<div align="center" style="height: 23px;background-image:url('./images/fon02.gif');margin-top:20px;margin-bottom:20px; ">
		<span></span>
		</div>
	</div>
    </div>
    &nbsp;
    <script type="text/javascript" lang="javascript">setTimeout("window.top.location='logout';", 1200000);</script>
</body>
</html>