<%@page import="java.util.ArrayList"%>
<%@page import="com.lynuc.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户编辑 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
	<link href="css/main.css" rel="stylesheet"/>
	<link href="css/pageControl.css"  rel="stylesheet" />
    <link href="css/button.css"  rel="stylesheet" />
    <link href="css/jquery-ui-style.css" rel="stylesheet"/>
    <link href="css/jquery-ui.css" rel="stylesheet"/>
    <script src="js/jquery-3.0.0.js"></script>
<script>
function check(form)
{
	var email=$("#txt_email");
	if(email.val()!=""){
		if(!new RegExp("^[a-z\\d]+[\\w\\-\.]*@([a-z\\d]+[a-z\\d\\-]*\.)+[a-z]{2,4}$","i").test(email.val())){
			alert('请输入正确的邮箱!');
			email.focus();
			return false;
		}
	}
	var chkPass=$("#chk_resetPassword").prop('checked');
	var txtPass=$("#txtPassword");
	if(chkPass){
		var patrn=/^(\w){6,20}$/;   
	    if (!patrn.exec(txtPass.val())) {
	    	alert("密码格式不对!");
	    	return false;
	    }
	}
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
        $passwordInput = $('<input type="text"  name="' + $password.attr('name') + '" class="' + $password.attr('className') + '" />');  
   
    $('#chk_showPassword').click(function(){  
        if(this.checked){  
            $password.replaceWith($passwordInput.val($password.val()));  
        }else{  
            $passwordInput.replaceWith($password.val($passwordInput.val()));  
        }  
    });  
});   
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
	table{padding-top:30px; width: 70%;margin: 0px auto;background-color: RGB(144,238,144);}
	.password {  
    width: 200px;  
    height: 24px;  
    font-size: 24px;  
    line-height: 24px;  
    bordeR: 1px solid #ccc;  
}
table tr th,table tr td,table input,table select option,table select{
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
						<li class="erji"><a href="javascript：void(0);" onclick="resetpassword();">重设密码</a></li>
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
		<div style="font-size:30px;margin:20px auto;">用户编辑</div>
		<div align="center">
	<form name="jspnetForm" id="jspnetForm" method="post" action="changeUserInfo"  onsubmit="return check(this);">
		<table style="padding-left:100px;">
                    <tr>
                        <th>用户名</th>
                        <td>
                            <input value="${select_user.usercode }" name="txtUserCode" type="text" id="txtUserCode" />
                        </td>
                    </tr>
                    <tr>
                        <th>姓  名</th>
                        <td>
                            <input value="${select_user.username }" name="txtUserName" type="text" id="txtUserName" />
                        </td>
                    </tr>
                   <tr>
						<th>密码</th>
						<td>
                             <input value="" name="txtPassword" id="txtPassword" type="password" />
                   		</td>
				   </tr>
					<tr>
						<th></th>
						<td>
                             <input type="checkbox" name="chk_showPassword" id="chk_showPassword" style="width: 20px;padding-left: 30px;"/>
                             <label for="chk_showPassword" style="font-weight: bold;">显示密码</label> 
                        
                             <input type="checkbox" name="chk_resetPassword" id="chk_resetPassword" style="width: 20px;"/>
                             <label for="chk_resetPassword" style="font-weight: bold;">修改密码</label>
                        </td>
					</tr>
                    <tr>
                        <th>所属公司</th>
                        <td>
                        <s:select emptyOption="true" name="ddl_user_belong_company" id="ddl_user_belong_company"  list="#request.companyNameList" 
	                		listKey="gguid" listValue="company_name" value="#request.select_user.belong_company_gguid"
	                		 headerValue="#request.select_user.belong_company_gguid"  theme="simple"></s:select>
                        </td>
                    </tr>
                     <tr>
                         <th>用户权限</th>
                         <td>
                             <select name="dpdPermission" id="dpdPermission">
								<option selected="selected" value="A">A所有权限</option>
								<option value="B">B数据编辑权限</option>
								<option value="C">C信息查看权限</option>
							</select>
                          </td>
                     </tr>
                      <tr>
                          <th>投诉单权限</th>
                          <td>
                              <select name="ddl_complaint_permission" id="ddl_complaint_permission">
									<option selected="selected" value="1">1:查看/管理自己的投诉单</option>
									<option value="2">2:查看全部投诉单/管理自己的投诉单</option>
									<option value="3">3:查看/管理所有投诉单</option>
								</select>
                          </td>
                      </tr>
                      <tr>
                          <th>Email</th>
                          <td>
                              <input value="${select_user.email }" name="txt_email" id="txt_email" type="text" />
                          </td>
                      </tr>
                      <tr>
                          <th>移动电话 </th>
                          <td>
                              <input value="${select_user.mobile }" name="txt_mobile" type="text" id="txt_mobile" />
                          </td>
                      </tr>
                      <tr>
                          <th>密码类型</th>
                          <td>
                              <select name="ddl_password_type" id="ddl_password_type">
								<option selected="selected" value="1">固定密码</option>
								<option value="2">动态密码</option>
							</select>
                           </td>
                       </tr>
                       <tr>
                           <th>密码有效期  </th>
                           <td>
                               <input value="${select_user.password_time }" name="txt_password_time" type="text" readonly="readonly" id="txt_password_time" style="background-color:LightYellow;" />
                           </td>
                       </tr>
                       <tr>
                           <th></th>
                           <td style="text-align:left;">
                           		<%
                           			Object obj=request.getAttribute("select_user");
                           			String checked="";
                           			User user=null;
                           			if(obj!=null){
                           				user=(User)obj;
                           				if("Y".equals(user.getIsvalid())){
                           					checked="checked='checked'";
                           				}
                           			}
                           		%>
                               	<input id="chkIsValid" type="checkbox" 
                               		name="chkIsValid" <%=checked %> style="width: 20px;padding-left: 30px;"/>
                               	<label for="chkIsValid" style="font-weight: bold;">有效性</label>
                           </td>
                       </tr>
                        <tr style="height:60px;text-align: center;">
                        	<th></th>
                            <td align="center">
                            	<input name="btnOK" id="btnOK" type="submit" value="确定" 
                    				class="button button-pill button-primary" style="width:150px;">
                               	<input type="hidden" value="${select_user.id }" name="hidden_id" id="hidden_id"/>
                               	<input type="hidden" value="${type }" name="hidden_type" id="hidden_type"/>
                               	<a href="showUsers">
                               		<input type="button" name="btnClose" value="返回" id="btnClose" 
                               			class="button button-pill button-primary" style="width:150px;"/>
                               	</a>
                            </td>
                        </tr>
                </table>
        </form>
        </div>
	</div>
	<div>
		<div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div> 
	</div>
</div>
</body>
</html>