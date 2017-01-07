<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*,net.sf.json.*" %>
<%@ page import="com.lynuc.def.*" %>
<%@ page import="com.lynuc.bean.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>
	<c:if test="${!empty ctrlnoexist }">编辑控制器 - InfoPub v6.0</c:if>
    <c:if test="${empty ctrlnoexist }">添加控制器 - InfoPub v6.0</c:if>
</title>   
	<link href="images/favicon.ico" rel="shortcut icon">
   <link href="css/main.css" rel="stylesheet">
    <link href="css/jquery-ui-style.css" rel="stylesheet">
    <link href="css/jquery-ui.css" rel="stylesheet"/>
    <link href="css/jquery-ui-1.8.4.custom.css" rel="stylesheet">
    <link href="css/jquery.ui.datepicker.css" rel="stylesheet">
    <link href="css/jquery-ui-1.9.2.custom.min8.css" rel="stylesheet">
    <link href="css/pageControl.css"  rel="stylesheet" />
    <link href="css/button.css"  rel="stylesheet" />
    <script src="js/jquery-3.0.0.js"></script>
    <script src="js/jquery-ym-datePlugin-0.1.js"></script>
    <script src="js/jquery.ui.core.js"></script>
    <script src="js/jquery-ui-1.8.4-resource.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/moduleset.js"></script>
<script>
	var card_no_ori="";
	var ipc_sn_ori="";
	var password_ver_ori="";
 $(function() {
    var module_set11=$("#hidden_module_set").val();
   	if(module_set11 != 0){
   		var iModule_Set = parseInt(module_set11);
        fill_module_set(iModule_Set);
   	}else{
   		$("#module0").attr("checked", "true");
   		$("#labelformodule0").css('color','red');
   		$("#module1").attr("checked", "true");
   		$("#labelformodule1").css('color','red');
   		$("#module2").attr("checked", "true");
   		$("#labelformodule2").css('color','red');
   		$("#module3").attr("checked", "true");
   		$("#labelformodule3").css('color','red');
   		$("#module5").attr("checked", "true");
   		$("#labelformodule5").css('color','red');
   		$("#module8").attr("checked", "true");
   		$("#labelformodule8").css('color','red');
   		$("#module4").attr("checked", "true");
   		$("#labelformodule4").css('color','red');
   	}
   	var card_no=$("#ddl_controller_no").val();
   	card_no_ori=$("#txt_card_no").val();
   	ipc_sn_ori=$("#txt_ipc_sn").val();
   	password_ver_ori=$("#txt_password_ver").val();
    fillblanksBycontrl_no(card_no);
    var controller_no=$('#ddl_controller_no').find('option:selected').text();
	$("#hiddentxt_controller_no").val(controller_no);
    var office_name=$('#ddl_office').find('option:selected').text();
	$("#hidden_office_name").val(office_name);
	var cust_name=$('#ddl_cust').find('option:selected').text();
	$("#hidden_customer_name").val(cust_name);
	
	$("#mySelect option:first").prop("selected", 'selected');
	
	$("input:checkbox").each(function() {
		$(this).bind('click',function(){
			if ($(this).is(':checked') ==true) {
		       $(this).next('label').css('color','red');
			}else{
				$(this).next('label').css('color','black');
			}
		});
	});
	
	//下拉年月
	$("#dp_setup_date").ymdateplugin({
		changeMonth: true,
		changeYear: true
	});
	
    $("#txt_copy_from").autocomplete("ajaxRequestContrlNoListFromMcc", {
        multiple: false,
        matchContains: true,
        cacheLength: 0 //不缓存关键字
    })
    try {
        bindDateControl();
    }
    catch (e) { } 
 });
 String.prototype.trim=function(){
	 return this.replace(/^\s*/,"").replace(/\s*$/,"");
 }
function copyMccByContrlno()
{
	var url = "ajaxRequestGetMccByControNo";  
	var ctrl_no=$("#txt_copy_from").val();
	if((ctrl_no==null) || ctrl_no.trim()=="") return false;
	var params= {  
        'controller_no':ctrl_no  
    }; 
	$.getJSON(url, params, copyMccByContrlno_callback);
}
function copyMccByContrlno_callback(result,textStatus)
{
	if(textStatus == 'success'){
		if(typeof(result['code'])=="undefined"){ 
			$("#txt_total_type").val(result['total_type']);
			$("#txt_machine_type").val(result['machine_type']);
			$("#ddl_office").val(result['sale_company_gguid']);
			$("#ddl_cust").val(result['cust_company_gguid']);
			$("#txt_ver").val(result['ver']);
            $("#txt_hard_ver").val(result['hard_ver']);
            $("#txt_mac_option").val(result['mac_option']);
            $("#txt_lynuc_option").val(result['lynuc_option']);
            var iModule_Set = parseInt(result['module_set']);
            fill_module_set(iModule_Set);
		}else{
			alert(result['err_message']);
		}
	}
}
function fillblanksBycontrl_no(value)
{
	if(value==card_no_ori){
		$("#txt_card_no").val(card_no_ori);
    	$("#txt_ipc_sn").val(ipc_sn_ori);
    	$("#txt_password_ver").val(password_ver_ori);
    	var controller_no=$('#ddl_controller_no').find('option:selected').text();
    	$("#hiddentxt_controller_no").val(controller_no);
    	return true;
	}
	var controller_no=$('#ddl_controller_no').find('option:selected').text();
	$("#hiddentxt_controller_no").val(controller_no);
	
    var url = "ajaxRequestGetMccByCardNo";  
    var params = {  
        'card_no':value  
    }; 
    $.getJSON(url, params, fillblanksBycontrl_no_callback);  
}
function fillblanksBycontrl_no_callback(result,textStatus)
{  
    if(textStatus == 'success'){  
        if(result != null){  
        	$("#txt_card_no").val(result['card_no']);
        	$("#txt_ipc_sn").val(result['ipc_sn']);
        	$("#txt_password_ver").val(result['card_ver']);
        }  
    }  
}   
function clickwhichbutton(which_btn){
	$("#which_btn_click").val(which_btn);
	$("#ddl_is_pay").removeAttr("disabled"); 
	$("#jspnetForm").submit();
}
function office_input_change()
{
	var office_name=$('#ddl_office').find('option:selected').text();
	$("#hidden_office_name").val(office_name);
}
function cust_input_change()
{
	var cust_name=$('#ddl_cust').find('option:selected').text();
	$("#hidden_customer_name").val(cust_name);
}
function changeSelectOption()
{
	var object=document.getElementById("ddl_cust");
	alert(object[0]);
}
$(function(){
	
});
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
	table input,table select{
		padding:2px 2px;
		font-size:15px;
		font-family: :sans-serif,宋体;
	}
	table select option{
		padding:1px 5px;
		font-size:13px;
	}
	table tr th{
		padding:2px 2px;
		font-size:20px;
	}
</style>
</head>
<body>
<div class="wrap" id="center">
	<div class="header" style="background-color:#0c3365;">
		<h1 class="header_style">InfoPub v6.0</h1>
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
						<c:if test="${(user.usercode eq tableproducts.pwd_checker_accounts) or 
									(user.usercode eq 'liuhaifeng')}">
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
	<div id="pnlMCCEdit" align="center" style="background-color:#d5f3f4;font-size: 16px;">
    <%
    	HashMap  map=(HashMap)request.getAttribute("mccMap");
    	User user=(User)session.getAttribute("user");
    	String actionDivert="MccListAction";
    	String mobile=request.getAttribute("is_mobile").toString();
    	if("yes".equals(mobile)){
    		actionDivert="mccpassword_m";
    	}
    	String controller_ver1="MR50";
    	String input_user_name="";
    	String input_time="";
    	String memo="";
    	if(map!=null){
    		input_user_name=user.getUsername();
    		if(null != map.get("memo")){
    			memo=map.get("memo").toString();
    		}
    	} 
    %>
    <div style="padding-top:20px;margin-bottom:15px;font-weight: bold;font-size:20px;">
    	<c:if test="${!empty ctrlnoexist }">编辑控制器</c:if>
    	<c:if test="${empty ctrlnoexist }">添加控制器</c:if>
   </div>
    <s:form name="jspnetForm" id="jspnetForm" method="post" action="AddControlCardItemSubmit" theme="simple">
        <table style="color: black;">
           <tr id="trController_ver">
					<th align="left">产品</th>
					<td align="left" nowrap="nowrap">
						<s:select name="ddl_controller_ver" id="ddl_controller_ver" list="#request.contrl_verMapList" listKey="key" 
						listValue="value" value="#request.mccMap.controller_ver" headerValue="" class="selectOption" style="width:215px;" theme="simple"></s:select>                               
				    </td>
				  <% if(map == null){%>
				    <th align="left">拷贝自</th>
					<td align="left" nowrap="nowrap">
				        <input type="text" style="width:133px;font-size: 15px;" id="txt_copy_from" name="txt_copy_from" list="ide" />
						<datalist id="ide">	
							<s:iterator value="#request.ctrl_noInMccList " id="m">
								<option value="${m }"/>
							</s:iterator>
						</datalist>	
                           <input name="btnCopy" id="btnCopy" type="button" class="btn1" value="拷贝" onclick="copyMccByContrlno();" style="width:70px" />
                      </td>
                   <%} %>
					</tr>
                  <tr>
                      <th align="left">机床编号</th>
                      <td align="left">
                          <input value="${requestScope.mccMap.machine_no }" name="txt_machine_no" type="text" maxlength="50" id="txt_machine_no" 
                          	style="width:211px;" />
                      </td>
                  </tr>
                  <tr>
					 <th align="left">机床大类</th>
				  	<td align="left" nowrap="nowrap">
                          <input value="${requestScope.mccMap.total_type }" name="txt_total_type" type="text" maxlength="50" id="txt_total_type" style="width:211px;" />
                    </td>
					<th align="left">机床型号</th>
					<td align="left" nowrap="nowrap">
                          <input value="${requestScope.mccMap.machine_type }" name="txt_machine_type" type="text" maxlength="50" id="txt_machine_type" style="width:211px;" />
                    </td>
				  </tr>
                  <tr>
					<th align="left">控制器号</th>
					<td align="left" nowrap="nowrap">
						<c:if test="${!empty ctrlnoexist }">
							<input type="text" name="ddl_controller_no" id="ddl_controller_no" readonly="readonly" class="readonly" value="${mccMap.controller_no }" style="width:211px;">
						</c:if>
						<c:if test="${empty ctrlnoexist }">
							<s:select list="#request.contrl_noList" listKey="card_no" listValue="controller_no" value="#request.mccMap.card_no" name="ddl_controller_no" id="ddl_controller_no" 
							theme="simple" style="width:215px;" onchange="fillblanksBycontrl_no(this.value);"></s:select>
							<div style="display:inline;">
								<input id="hiddentxt_controller_no" name="hiddentxt_controller_no" type="hidden"/>
							</div>
						</c:if>
				    </td>
					<th align="left">控制卡号</th>
					<td align="left" nowrap="nowrap">
                         <input name="txt_card_no" type="text" value="${requestScope.mccMap.card_no }" maxlength="50" readonly="readonly" id="txt_card_no" class="readonly" style="width:210px;" />
                      </td>
				</tr>
                   <tr>
						<th align="left">主机序列号</th>
						<td align="left" nowrap="nowrap">
		                          <input name="txt_ipc_sn" id="txt_ipc_sn" type="text" value="${requestScope.mccMap.ipc_sn }" readonly="readonly" class="readonly" style="width:210px;" />
		                      </td>
						<th align="left">密码版本</th>
						<td align="left" nowrap="nowrap">
                          <input name="txt_password_ver" type="text" value="${requestScope.mccMap.password_ver }" maxlength="50" readonly="readonly" id="txt_password_ver" class="readonly" style="width:210px;" />
                      </td>
				</tr>
                  <tr>
                      <th align="center">最新付款状态</th>
                      <td align="left" nowrap>
                      	<%
                      		String disabled="disabled=\"disabled\"";
                      	%>
                      	<c:if test="${user.usercode == tableproducts.pay_edit_accounts}">
                      		<%
                      			disabled="";
                      		%>
                      	</c:if>
                     <select name="ddl_is_pay"  <%=disabled %> id="ddl_is_pay" style="width:215px;">
						<c:choose>
							<c:when test="${requestScope.mccMap.is_pay=='Y' }">
								<option value="N">未付</option>
				    		<option value="Y" selected="selected">已付</option>
							</c:when>
							<c:when test="${requestScope.mccMap.is_pay=='N' or empty map}">
								<option value="N" selected="selected">未付</option>
					    	<option value="Y">已付</option>
							</c:when>
						</c:choose>
				</select></td>
                      <th align="left">安装日</th>
                      <td align="left" nowrap>	
                      	<input type="text" name="dp_setup_date"  id="dp_setup_date" readonly="readonly" value="${requestScope.setup_date }" style="width:210px;"/>
                      </td>
                  </tr>
                  <tr>
                      <th align="left">事务所</th>
                      <td align="left" nowrap>
                      	<s:select emptyOption="true" name="ddl_office" id="ddl_office" list="#request.officeList" listKey="gguid" listValue="company_name" value="#request.mapdefault_office.gguid" headerValue="" onkeyup="changeSelectOption();" onchange="office_input_change();" theme="simple" style="width:215px;"></s:select>
                      	
                      	<input type="hidden" id="hidden_office_name" name="hidden_office_name" value=""/>
                      </td>
                      <th align="left">客户名</th>
                      <td align="left" nowrap>
                      	 <s:select emptyOption="true" name="ddl_cust" id="ddl_cust"  list="#request.custnameList" 
                      		listKey="gguid" listValue="company_name" value="#request.mapdefault_custname.gguid"
                      		 headerValue="b49c6c95-b7bf-4cb8-b910-4981793377e3" onchange="cust_input_change();" theme="simple" style="width:215px;"></s:select>
                      	<input type="hidden" id="hidden_customer_name" name="hidden_customer_name" />
                      </td>
                  </tr>
                  <tr>
                      <th align="left">软件版本</th>
                      <td align="left" nowrap>
                          <input value="${requestScope.mccMap.ver }" name="txt_ver" type="text" maxlength="50" id="txt_ver" style="width:210px;" />
                      </td>
                      <th align="left">硬件版本</th>
                      <td align="left" nowrap>
                          <input value="${requestScope.mccMap.hard_ver }" name="txt_hard_ver" type="text" maxlength="50" id="txt_hard_ver" style="width:210px;" />
                      </td>
                  </tr>
                  <tr>
						<th align="left">机床选配</th>
						<td colspan="3" align="left">
                          <input value="${requestScope.mccMap.mac_option }" name="txt_mac_option" type="text" maxlength="50" id="txt_mac_option" style="width:540px;" />
                      	</td>
				</tr>
                  <tr>
						<th align="left">系统配置</th>
						<td colspan="3" align="left">
                          <input value="${requestScope.mccMap.lynuc_option }" name="txt_lynuc_option" type="text" maxlength="200" id="txt_lynuc_option" style="width:540px;" />
                      </td>
				</tr>   
                  <tr>
                      <th align="left">当前密码</th>
                      <td align="left">
                          <input name="txt_password" type="text" maxlength="25" readonly="readonly" id="txt_password" class="readonly" ondblclick="return OpenSmsWindow(this);" style="width:320px;" />
                      </td>
					  <th align="left">结束日期</th>
                      <td align="left">
                          <input name="txt_password_period" type="text" maxlength="25" readonly="readonly" id="txt_password_period" class="readonly" style="width:210px;" />
                      </td>
                  </tr>
                  <tr>
                      <th align="left">备注</th>
                      <td colspan="4" align="left">
                        	<textarea id="ftxt_memo" name="ftxt_memo" COLS=84 ROWS=4 draggable="false" 
                        		style="resize:none;padding:5px 5px;border-style: ridge;;font-size: 14px;font-family: serif 宋体;"><%=memo %></TEXTAREA>
                      </td>
                  </tr>
                  <tr valign="top">
			        <td colspan="4"><hr/></td>
			      </tr>
                  <tr>
                      <th align="left">模块</th>
                      <td align="left" nowrap colspan="4">
                          <table>
                              <tr id="modulesetlist">
                          	<%
                          		String[] moduleSet_array=Constant.module_set;
                          		for(int i=0;i<moduleSet_array.length;i++){
                          	%>
                          	<td style="padding:3px 14px;">
                          		<input id="module<%=i %>" type="checkbox" name="module<%=i %>"/>
                          		<label for="module<%=i %>" id="labelformodule<%=i %>"><%=moduleSet_array[i] %></label>
                          	</td>
                       		<% if((i+1)%5==0 && i!=0){ %></tr><tr><%} }%>
                       		<td>
                              	<input id="hidden_module_set" name="hidden_module_set" type="hidden" value="${requestScope.mccMap.module_set }"/>
                            </td></tr>
                          </table>
                      </td>
                  </tr>
                  <tr valign="top">
			        <td colspan="4"><hr/></td>
			      </tr>
                  <tr>
                      <th align="left">登录人</th>
                      <td align="left">
                          <input value="<%=input_user_name %>" name="txt_input_user_id" type="text" readonly="readonly" id="txt_input_user_id" class="readonly" style="width:199px;" />
                      </td>
                      <th align="left">登录时间</th>
                      <td align="left">
                          <input value="${requestScope.mccMap.input_time }" name="txt_input_time" type="text" readonly="readonly" id="txt_input_time" class="readonly" style="width:199px;" />
                      </td>
                      <td><input id="is_mobile" name="is_mobile" type="hidden" value="<%=mobile %>"></td>
                  </tr>
                  <tr>
                      <td colspan="5" align="center" style="padding:15px 0;">
                      	<% if(map == null){%>
                      		<input name="btnSubmitAndApply" id="btnSubmitAndApply" type="button" value="确定并申请密码" 
                    			class="button button-pill button-primary"  onclick="clickwhichbutton(0);">
                    		<input name="btnSubmit" id="btnSubmit" type="button" value="确定" 
                    			class="button button-pill button-primary"  onclick="clickwhichbutton(1);">
                    		<input name="btnCancelEdit" id="btnCancelEdit" type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='MccListAction';">
                    		<div style="display:inline;">
							 <input id="which_btn_click" name="which_btn_click" type="hidden" value="0" />
						  </div>	
                      	<%}else{ %>
                      	
                      		<input name="btnSubmitAndApply" id="btnSubmitAndApply" type="button" value="确定" 
                    			class="button button-pill button-primary"  onclick="clickwhichbutton(3);">
                    		<input name="btnSubmit" id="btnSubmit" type="button" value="删除" 
                    			class="button button-pill button-primary"  onclick="clickwhichbutton(4);">
                    		<input name="btnCancelEdit" id="btnCancelEdit" type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='<%=actionDivert %>';">
                      	<div style="display:inline;">
							<input id="which_btn_click" name="which_btn_click" type="hidden" value="1" />
						</div>
                     <%} %>
                      </td>
                  </tr>
		</table>
	</s:form>
	</div>
	<div>
		<div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div> 
	</div>
</div>
</body>
</html>
