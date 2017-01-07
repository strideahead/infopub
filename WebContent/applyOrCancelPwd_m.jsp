<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.HashMap,com.lynuc.def.Constant" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>密码申请 - infopub</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/moduleset.js"></script>
<script>
var apply_no=${mccpassData.apply_no };
$(function(){
	var module_set11=$("#hidden_module_set").val();
   	if(module_set11!=null){
   		var iModule_Set = parseInt(module_set11);
        fill_module_set(iModule_Set);
   	}
   	isforeverClick1();
	$( "#enddate_set" ).datepicker( "option", "dateFormat", "yy-mm-dd");
	
});
function isforeverClick1()
{
	if($("#chkForever").is(':checked')){
		$("#lbl_enddate").css("visibility","hidden");
		$("#enddate_set").css("visibility","hidden");

	}else{
		$("#lbl_enddate").css("visibility","visible");
		$("#enddate_set").css("visibility","visible");

	};
}
function check()
{
	var isrevert=$("#hidden_isrevert").val();
	if(isrevert=="true"){
		document.getElementById('applyorcancelForm').action = "applyPassWithExistingCtrlno_m?hidden_revert=true&txt_apply_no="+apply_no;
	}
	document.getElementById("applyorcancelForm").submit();  
	return false;
}
</script>
<style type="text/css">
.checkoutitems {
	display:inline-block;
    font-size: 60px;
    color: #ff9900;
    letter-spacing: 2px;
    margin: 10px 5px;
    background-color: rgba(255, 255, 255, 0.9);
}
table tr td,table tr th{
	padding:0px;
	margin:0px;
}
table tr input{
	color:red;
}
table tr th{
	padding:0px;
	
	text-align: left;
}
table tr td{
	padding:0px;
	text-align: left;
}
</style>
</head>
<body style="background-color:#d5f3f4;">
<%
   	HashMap map=(HashMap)request.getAttribute("mccpassData");
    HashMap  mapOlpassword=(HashMap)request.getAttribute("mcc_oldpassData");        
    String state="";        
   	String is_pay=(String)map.get("is_pay");
	if(is_pay.equals("Y")) {
  		is_pay="已付";
  	}else if(is_pay.equals("N")){
  		is_pay="未付";
  	}else{
  		is_pay="";
  	}
  	String is_forevertmp_oldPass="";
  	String endDate_old="";
  	if(mapOlpassword!=null){
  		is_forevertmp_oldPass=(String)mapOlpassword.get("is_forever_oldpass");
      	endDate_old=(String)mapOlpassword.get("old_password_enddate");
      	if(is_forevertmp_oldPass.equals("Y")){
      		endDate_old="永久密码";
      	}
  	}
  	String is_forevertmp=(String)map.get("is_forever");
  	boolean is_forever=false;
       	
	String enddate_checked="";
	
   	if(is_forevertmp.equals("Y")){
   		enddate_checked="checked='checked'";
   		is_forever=true;
   	}else{
   		is_forever=false;
   	}
   	boolean is_batch=false;
   	String is_batchtmp=(String)map.get("is_batch");
   	if(is_batchtmp==null) is_batch=false;
   	else{
   		if(is_batchtmp.equals("Y")){
       		is_batch=true;
       	}else{
       		is_batch=false;
       	}
   	}
%>
<div align="center">
  <div align="left" style="padding-top:1px;padding-left:5px;padding-bottom:1px;
		font-weight: bold;font-style: italic;font-size:20px;height:20px;line-height: 20px;text-align: center;"> Information Public System v6.0 </div>
  <div style="margin:10px 10px;padding:10px auto;font-size: 20px;height:30px;">
    <div style="color: red;display:inline-block;float:left;">${user.username}</div>
    <div style="display:inline-block;float:right;"><%-- <a href="AddControlCardItem?controller_no=${mccpassData.controller_no }&mobile=yes">编辑</a> --%>
    	<button type="button" class="btn1 btn-default" style="height: 40px;font-size:20px;padding:5px;" 
			onclick="window.location='AddControlCardItem?controller_no=${mccpassData.controller_no }&mobile=yes';">编辑控制器</button>
    </div>
  </div>
  <div style="clear:left;">
    <form id="applyorcancelForm" name="applyorcancelForm" method="post" action="applyPassWithExistingCtrlno_m" 
			onsubmit="check();">
      <table class="table-data table-condensed table-borderless" >
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>产品</th>
          <td><input name="txt_controller_ver" id="txt_controller_ver"  type="text" readonly="readonly" 
					style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.controller_ver }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>机床编号</th>
          <td><input id="txt_machine_no" name="txt_machine_no" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.machine_no }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>机床大类</th>
          <td><input id="txt_total_type" name="txt_total_type" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.total_type }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>控制器号</th>
          <td><input id="txt_controller_no" name="txt_controller_no" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.controller_no }">
          </td>
          <td></td>
        </tr>
        <tr style="height:1.2rem;text-align: center;">
          <th style="font-size: 1rem;">主机序列号</th>
          <td><input id="txt_ipc_sn" name="txt_ipc_sn" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.ipc_sn }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>控制卡号</th>
          <td><input id="txt_controller_no" name="txt_controller_no" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.card_no }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>事务所</th>
          <td><input id="txt_office" name="txt_office" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.office_name }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>客户名</th>
          <td><input id="txt_cust_name" name="txt_cust_name" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${mccpassData.customer_name }">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;">
          <th colspan="1">付款状态</th>
          <td><input id="txt_is_pay" name="txt_is_pay" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="<%=is_pay %>">
          </td>
        </tr>
        <tr style="font-size: 1rem;text-align: center;">
          <th style="font-size: 1.1rem;">当前密码</th>
          <td><input type="text" readonly="readonly" size="35"
					style="background-color:#FFFF99;font-size: 0.7rem;" value="${mcc_oldpassData.the_old_password }">
          </td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>结束日期</th>
          <td><input id="txt_old_password_period" name="txt_old_password_period" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="<%=endDate_old %>">
          </td>
          <td></td>
        </tr>
        <tr style="height:1.2rem;text-align: center;height:5rem;">
          <th style="font-size: 1.2rem;">模块</th>
          <td colspan="2"><table>
              <tr>
                <%
                 		String[] moduleSet_array=Constant.module_set;
                 		for(int i=0;i<moduleSet_array.length;i++){
                 	%>
                <td align="left"><input id="module<%=i %>" type="checkbox" name="module<%=i %>" disabled="disabled"/>
                  <label id="label<%=i %>" for="module<%=i %>" style="font-size:0.8rem"><%=moduleSet_array[i] %></label>
                </td>
                <% if((i+1)%2==0 && i!=0){ %>
              </tr>
              <tr>
                <%} }%>
                <td><input id="hidden_module_set" name="hidden_module_set" type="hidden" value="${mccpassData.module_set }"/>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr style="font-size: 1.1rem;height:1.2rem;text-align: left;">
        
        <c:choose>
 				<c:when test="${is_revert eq 'false' }">
 					<td colspan="2"><input type='checkbox' id="chkForever" name="chkForever"
						onclick="isforeverClick1();" />
			            <label for="chkForever" >永久密码</label>
			            &nbsp;<span id="lbl_enddate">结束日期</span>
			            <input id="enddate_set" style="width:7rem;vertical-align: middle;font-size:1rem;"  name="enddate_set" type="date" value="${requestScope.enddatebat }">
			        </td>
 				</c:when>
 				<c:when test="${is_revert eq 'true'}">
 					<td align="left" colspan="1" style="font-size:18px;font-weight:bold;"><input type="checkbox" <%=enddate_checked %> id="chkForever"  name="chkForever" disabled="disabled">
			          <label for="chkForever" style="font-weight: bold;">永久密码</label>
			          &nbsp;&nbsp;&nbsp;&nbsp; <span id="lbl_enddate">结束日期</span>
			          <input id="enddate_set" type="text" value="${mccpassData.enddate_new.substring(0,10) }" maxlength="50" 
			               	readonly="readonly" id="dp_enddate" class="readonly" style="width:90px;" />
			        </td>
 				</c:when>
 			</c:choose>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>申请人</th>
          <td><input id="txt_request_user" name="txt_request_user" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${user.username}">
          </td>
          <td></td>
        </tr>
        <tr style="font-size: 1.2rem;height:1.2rem;text-align: center;padding: 0 0;">
          <th>申请时间</th>
          <td><jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="bb"/>
            <input id="txt_request_time" name="txt_request_time" type="text" readonly="readonly" 
				style="background-color:#FFFF99;font-size: 1.2rem;" value="${bb }">
          </td>
          <td></td>
        </tr>
        <tr style="height: 20px;"><td colspan="4"></td></tr>
        <tr align="center">
        <td colspan="4">
       			<c:choose>
       				<c:when test="${is_revert eq 'false' and (mccpassData.state ne 'P') and (user.permission < 'C')  }">
       					<button type="submit" class="btn1 btn-default"
			 			style="height: 40px;font-size:20px;padding:5px;">提交申请</button>
             		<input type="hidden" id="hidden_isrevert" name="hidden_isrevert" value="false">
       				</c:when>
       				<c:when test="${is_revert eq 'true' and (mccpassData.state eq 'P') and (user.id eq mccpassData.request_user_id)}">
       					<button type="submit" class="btn1 btn-default "
							 style="height: 40px;font-size:20px;padding:5px;">撤销申请</button>
			            <input type="hidden" id="hidden_isrevert" name="hidden_isrevert" value="true"> 
       				</c:when>
       			</c:choose>
       			
       			<%-- <button type="button" class="btn1 btn-default" style="height: 40px;font-size:20px;padding:5px;" 
							 onclick="window.location='AddControlCardItem?controller_no=${mccpassData.controller_no }&mobile=yes';">编辑</button> --%>
       			&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn1 btn-default "
							 style="height: 40px;font-size:20px;padding:5px;" onclick="window.location='mccpassword_m';"">返回主页</button>
       			<!-- <input name="btnBack" id="btnBack" type="button" value="返回主页" 
                		class="button button-pill button-primary" 
                		style="width:7em;height:2em;text-align: left;padding-left:25px;" onClick="window.location='mccpassword_m';">
                		
       			<input name="btnExit" id="btnExit" type="button" value="返回主页" 
                class="button button-pill button-primary" 
                style="width:7em;height:2em;text-align: left;padding-left:25px;" onClick="window.location='mccpassword_m';"> -->
                
       			<!-- <button type="button" class="btn1 btn-default" onclick="window.location='mccpassword_m';"
					 style="height: 30px;font-size:20px;padding:5px;">返回主页</button>  -->
		</td>
        </tr>
        <tr style="height: 20px;"></tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
