<%@page import="java.text.SimpleDateFormat"%>
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
<title>
<c:if test="${is_revert!=null }">密码撤销 - InfoPub v6.0</c:if>
<c:if test="${is_revert==null }">密码详情 - InfoPub v6.0</c:if>
</title>
<link href="css/main.css" rel="stylesheet"/>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script src="js/moduleset.js"></script>
<script>
$(function(){
	var module_set11=$("#hidden_module_set").val();
	if(module_set11 != 0){
   		var iModule_Set = parseInt(module_set11);
        fill_module_set(iModule_Set);
   	}
   	isforeverClick1();
	isbatchClick();
});
function isforeverClick1()
{
	if($("#chkForever").is(':checked')){
		$("#lbl_enddate").css("display","none");
		$("#dp_enddate").css("display","none");

	}else{
		$("#lbl_enddate").css("display","");
		$("#dp_enddate").css("display","inline-block");

	};
}
function isbatchClick()
{
	if($("#chkBatch").is(':checked')){
		$("#tr_valid_date").css("display","none");
		$("#tr_batch").css("display","");
	}else{
		$("#tr_valid_date").css("display","");
		$("#tr_batch").css("display","none");
	};
}
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
	table input{
		padding:2px 2px;
		font-size:15px;
		font-family: :sans-serif,宋体;
	}
	table tr{
		padding:5px 2px;
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
  <div id="pnlMCCEdit" align="center" style="background-color:#d5f3f4;padding-top:20px;">
    <div style="padding-bottom:20px;"> <span id="lblTitleApply" style="font-size:Large;font-weight: bolder;">
      <c:if test="${is_revert!=null }">密码撤销</c:if>
      <c:if test="${is_revert==null }">密码详情</c:if>
      </span> </div>
    <%
        	HashMap  map=(HashMap)request.getAttribute("mccpassData");

        	String state=(String)map.get("state");
        	if(state.equals("Y")){
        		state="已批准";
        	}else if(state.equals("N")){
        		state="拒绝";
        	}else if(state.equals("P")){
        		state="未批准";
        	}else{
        		state="";
        	}
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
        	String enddate_checked="";
        	String endDate=(String)map.get("enddate");
        	if(is_forevertmp.equals("Y")){
        		enddate_checked="checked='checked'";
        		endDate="永久密码";
        	}else{
        		is_forever=false;
        	}
        	String is_forever_old=(String)map.get("is_forever_old");
        	String endDate_old = (String)map.get("enddate_old");
        	if(null != is_forever_old && "Y".equals(is_forever_old)){
        		endDate_old = "永久密码";
        	}
        	String is_batchtmp=(String)map.get("is_batch");
        	boolean is_batch=false;
        	String batch_checked="";
        	if(is_batchtmp.equals("Y")){
        		is_batch=true;
        		batch_checked="checked='checked'";
        	}else{
        		is_batch=false;
        	}
    %>
    <table style="color: black">
      <tr>
        <th align="left">产品 </th>
        <td align="left" nowrap><input name="controller_ver" type="text" value="${mccpassData.controller_ver }" maxlength="50" readonly="readonly" id="txt_controller_ver" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">机床编号</th>
        <td align="left" nowrap><input name="machine_no" type="text" value="${mccpassData.machine_no }" maxlength="50" readonly="readonly" id="txt_machine_no" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">机床大类</th>
        <td align="left" nowrap="nowrap"><input name="total_type" type="text" value="${mccpassData.total_type }" maxlength="50" readonly="readonly" id="txt_total_type" class="readonly" style="width:200px;" />
        </td>
        <th align="left">机床型号</th>
        <td align="left" nowrap="nowrap"><input name="machine_type" type="text" value="${mccpassData.machine_type }" maxlength="50" readonly="readonly" id="txt_machine_type" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">控制器号</th>
        <td align="left" nowrap="nowrap"><input name="txt_controller_no" type="text" value="${mccpassData.controller_no }" maxlength="50" readonly="readonly" id="txt_controller_no" class="readonly" style="width:200px;" />
        </td>
        <th align="left">控制卡号</th>
        <td align="left" nowrap="nowrap"><input name="txt_card_no" type="text" value="${mccpassData.card_no }" maxlength="50" readonly="readonly" id="txt_card_no" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">主机序列号</th>
        <td align="left" nowrap="nowrap"><input name="txt_ipc_sn" type="text" value="${mccpassData.ipc_sn }" maxlength="50" readonly="readonly" id="txt_ipc_sn" class="readonly" style="width:200px;" />
        </td>
        <th align="left">密码版本</th>
        <td align="left" nowrap="nowrap"><input name="txt_password_ver" type="text" value="${mccpassData.password_ver }" maxlength="50" readonly="readonly" id="txt_password_ver" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">事务所</th>
        <td align="left" nowrap><input name="txt_office" type="text" value="${mccpassData.office_name }" maxlength="50" readonly="readonly" id="txt_office" class="readonly" style="width:200px;" />
        </td>
        <th align="left">客户名</th>
        <td align="left" nowrap><input name="txt_cust_name" type="text" value="${mccpassData.customer_name }" maxlength="50" readonly="readonly" id="txt_cust_name" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">最新付款状态</th>
        <td align="left" nowrap><input name="txt_is_pay" type="text" value="<%=is_pay %>" maxlength="50" readonly="readonly" id="txt_is_pay" class="readonly" style="width:200px;color: red;" />
        </td>
        <th align="left">安装日</th>
        <td align="left" nowrap><input name="txt_setup_date" type="text" value="${mccpassData.setup_date }" maxlength="50" readonly="readonly" id="txt_setup_date" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">软件版本</th>
        <td align="left" nowrap><input name="txt_ver" type="text" maxlength="50" readonly="readonly" value="${mccpassData.soft_ver }" id="txt_ver" class="readonly" style="width:200px;" />
        </td>
        <th align="left">硬件版本</th>
        <td align="left" nowrap><input name="txt_hard_ver" type="text" maxlength="50"  value="${mccpassData.hard_ver }"  readonly="readonly" id="txt_hard_ver" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">机床选配</th>
        <td colspan="3" align="left"><input value="${mccpassData.mac_option }" name="txt_mac_option" type="text" maxlength="200" readonly="readonly" id="txt_mac_option" class="readonly" style="width:420px;" />
        </td>
      </tr>
      <tr id="trLynucApply5">
        <th align="left">系统配置</th>
        <td colspan="3" align="left"><input value="${mccpassData.lynuc_option }"  name="txt_lynuc_option" type="text" maxlength="200" readonly="readonly" id="txt_lynuc_option" class="readonly" style="width:420px;" />
        </td>
      </tr>
      <tr>
        <th align="left">当前密码</th>
        <td id="td_old_password" align="left" nowrap="nowrap" colspan="1"><input name="txt_old_password" type="text" value="${mccpassData.the_password_old }" readonly="readonly" id="txt_old_password" class="readonly" 
	              	style="width:280px;" />
        </td>
        <th align="left" style="padding-left:10px;">结束日期</th>
        <td align="left" nowrap><input name="txt_old_password_period" type="text" value="<%=endDate_old %>" maxlength="50" readonly="readonly" id="txt_old_password_period" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr valign="top">
        <td colspan="4"><hr/></td>
      </tr>
      <tr>
        <th align="left">模块</th>
        <td align="left" colspan="3"><table>
            <tr>
              <%
           		String[] moduleSet_array=Constant.module_set;
           		for(int i=0;i < moduleSet_array.length;i++){
              %>
              <td style="padding:3px 13px;"><input id="module<%=i %>" type="checkbox" name="module<%=i %>" disabled="disabled"/>
                <label id="label<%=i %>" for="module<%=i %>" style="font-size:15px;"><%=moduleSet_array[i] %></label>
              </td>
              <% if((i+1)%5==0 && i!=0){ %>
            </tr>
            <tr><%} }%>
              <td><input id="hidden_module_set" name="hidden_module_set" type="hidden" value="${mccpassData.module_set }"/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr valign="top">
        <td colspan="4"><hr/></td>
      </tr>
      <tr id="tr_valid_date">
        <th align="left">有效期</th>
        <td align="left" colspan="3" style="font-size:18px;font-weight:bold;"><input type="checkbox" <%=enddate_checked %> id="chkForever"  name="chkForever" disabled="disabled">
          <label for="chkForever" style="font-weight: bold;">永久密码</label>
          &nbsp;&nbsp;&nbsp;&nbsp; <span id="lbl_enddate">结束日期</span>
          <input name="txt_enddate" type="text" value="${mccpassData.enddate_new.substring(0,10) }" maxlength="50" 
               		readonly="readonly" id="dp_enddate" class="readonly" style="width:90px;" />
        </td>
      </tr>
      <tr>
        <td align="left" colspan="4" style="font-size:20px;"><span>
          <input type="checkbox" <%=batch_checked %> id="chkBatch"  name="chkBatch" onclick="isbatchClick();" 
             	disabled="disabled" style="font-size: 20px;">
          <label for="chkBatch" style="font-weight: bold;">批量</label>
          </span> </td>
      </tr>
      <tr id="tr_batch" style="display:none;">
        <td></td>
        <td align="left" nowrap="nowrap" colspan="4"><table>
            <tr>
              <c:choose>
                <c:when test="${fn:contains(mccpassData.batch_startdate,'-') }">
                  <c:set var="batch_startdate" value="${fn:substring(mccpassData.batch_startdate,0,10) }"></c:set>
                </c:when>
              </c:choose>
              <c:choose>
                <c:when test="${fn:contains(mccpassData.batch_enddate,'-') }">
                  <c:set var="batch_enddate" value="${fn:substring(mccpassData.batch_enddate,0,10) }"></c:set>
                </c:when>
              </c:choose>
              <th valign="middle">开始日期</th>
              <td valign="middle"><input name="txt_startdate_batch" type="text" value="${batch_startdate }" maxlength="50" readonly="readonly" id="txt_startdate_batch" class="readonly" style="width:90px;" />
              </td>
              <th valign="middle">结束日期</th>
              <td valign="middle"><input name="txt_enddate_batch" type="text" value="${batch_enddate }" maxlength="50" readonly="readonly" id="txt_enddate_batch" class="readonly" style="width:90px;" />
              </td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <th align="left">申请备注</th>
        <td colspan="3" align="left"><textarea rows="3" cols="3" class="readonly" readonly="readonly" draggable="true" 
                            	style="resize: none;display:inline-block;border-width:2px;border-style:ridge;width:590px;heig">${mccpassData.request_memo }</textarea>
        </td>
      </tr>
      <tr>
        <th align="left">申请人</th>
        <td align="left" nowrap><input name="txt_request_user" type="text" value="${mccpassData.request_name }" maxlength="50" readonly="readonly" id="txt_request_user" class="readonly" style="width:200px;" />
        </td>
        <th align="left">申请时间</th>
        <td align="left" nowrap><input name="txt_request_time" type="text" value="${mccpassData.request_time }" maxlength="50" readonly="readonly" id="txt_request_time" class="readonly" style="width:200px;" />
        </td>
      </tr>
      <tr>
        <th align="left">申请号</th>
        <td align="left" nowrap><input name="txt_apply_no" type="text" value="${mccpassData.apply_no }" maxlength="50" readonly="readonly" id="txt_apply_no" class="readonly" style="width:200px;" />
        </td>
        <th align="left">密码状态</th>
        <td align="left" nowrap><input name="password_state" type="text" value="<%=state %>" maxlength="50" readonly="readonly" id="txt_password_state" class="readonly" style="color:Green;width:200px;" />
        </td>
      </tr>
      <tr id="cpassword">
        <th align="left">密码</th>
        <td align="left" colspan="3"><input name="txt_password" type="text" value="${mccpassData.the_password_new }" maxlength="50" readonly="readonly" id="txt_password" class="readonly" style="width:590px;" />
        </td>
      </tr>
      <c:if test="${is_revert == null }">
        <tr>
          <th align="left">批准备注</th>
          <td colspan="3" align="left"><textarea rows="4" cols="3" draggable="false" class="readonly" readonly="readonly" style="resize: none;display:inline-block;border-width:2px;border-style: ridge;width:590px;">${mccpassData.check_memo }</textarea>
          </td>
        </tr>
        <tr>
          <th align="left">批准人</th>
          <td align="left" nowrap="nowrap"><input name="txt_check_user" type="text" value="${mccpassData.check_name }" maxlength="50" readonly="readonly" id="txt_check_user" class="readonly" style="width:200px;" />
          </td>
          <th align="left">批准时间</th>
          <td align="left"><input name="txt_check_time" type="text" value="${mccpassData.check_time }" maxlength="50" readonly="readonly" id="txt_check_time" class="readonly" style="width:200px;" />
          </td>
        </tr>
      </c:if>
      <tr>
        <td colspan="4" align="center" style="padding:15px 0"><c:choose>
            <c:when test="${is_revert !=null && loc==null}">
              <input type="button" value="撤销申请" 
                    			class="button button-pill button-primary" 
                    			onclick="window.location='applyPassWithExistingCtrlno?txt_apply_no=${mccpassData.apply_no}&hidden_revert=true';">
              <input type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='MccListAction';">
            </c:when>
            <c:when test="${ loc==null}">
              <input type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='MccListAction';">
            </c:when>
            <c:when test="${!empty loc}">
              <input name="btnCancelApply" id="btnCancelApply" type="button" value="返回" 
                    			class="button button-pill button-primary" onclick="window.location='passwordRetrieve?controller_no=${mccpassData.controller_no}'">
            </c:when>
          </c:choose>
        </td>
      </tr>
    </table>
  </div>
  <div>
    <div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;"> <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766 </div>
  </div>
</div>
</body>
</html>
