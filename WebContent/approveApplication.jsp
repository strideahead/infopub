<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date,com.lynuc.def.Constant" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核申请 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/table.css" rel="stylesheet"/>
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script src="js/jquery-ui-1.8.4-resource.js"></script>
<script src="js/moduleset.js"></script>

<script>
$(function() {
	var module_set11=$("#hidden_module_set").val();
	if(module_set11 != 0){
   		var iModule_Set = parseInt(module_set11);
        fill_module_set(iModule_Set);
   	}
	isforeverClick1();
	isbatchClick();
	$("#chkForever").attr("disabled","disabled");
	$("#chkBatch").attr("disabled","disabled");
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
		$("#tr_batch_apply").css("display","");
	}else{
		$("#tr_valid_date").css("display","");
		$("#tr_batch_apply").css("display","none");
	};
}
$(function() {
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
    try {
        bindDateControl();
    }
    catch (e) { }
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});        
function getResponse(response) {
    $("#txt_machine_no").val(response);
}
function updateMachineNo() {
    $.post("GetMachineNo.aspx", "txt_ipc_sn=" + $("#txt_ipc_sn").val(), getResponse);
}
function clickwhichbutton(which_btn){
	$("#which_btn_click").val(which_btn);
	$("#jspnetForm").submit();
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
<form name="jspnetForm" id="jspnetForm" method="post" action="decideApplication.action">
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
              <li class="erji"><a href="#" onClick="resetpassword();return false;">重设密码</a></li>
              <c:if test="${user.permission eq 'A'}">
                <li class="erji"> <a href="showCompanies">进入管理</a> </li>
              </c:if>
              <li class="erji"><a href="logout">退出</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <div align="center" style="background-color:#d5f3f4;font-size: 16px;">
      <table style="color: black">
        <%
           	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
           	SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
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
        	String endDate=(String)map.get("enddate");
        	String endDate_sel="";
        	if(is_forevertmp.equals("Y")){
        		is_forever=true;
        		endDate="永久密码";
        	}else{
        		is_forever=false;
        		Date date=sdf.parse(endDate);
        		endDate_sel=sdf2.format(date);
        	}
        	String is_batchtmp=(String)map.get("is_batch");
        	boolean is_batch=false;
        	String checked="";
        	if(is_batchtmp.equals("Y")){
        		checked="checked='checked'";
        	}
		%>
        <tr>
          <th align="center" colspan="4"> <span id="lblTitleApply" style="font-size:Large;font-weight: bolder;">密码申请审核</span> </th>
        </tr>
        <tr>
          <th>产品</th>
          <td><input type="text" value="${mccpassData.controller_ver }" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>机床编号</th>
          <td><input value="${mccpassData.machine_no }" type="text" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>机床大类</th>
          <td><input value="${mccpassData.total_type }" type="text" readonly="readonly" class="readonly" />
          </td>
          <th>机床型号</th>
          <td><input value=""${mccpassData.machine_type } type="text" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>控制器号</th>
          <td><input value="${mccpassData.controller_no }" type="text" readonly="readonly" class="readonly" />
          </td>
          <th>控制卡号</th>
          <td><input type="text" value="${mccpassData.card_no }" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>主机序列号</th>
          <td><input type="text" value="${mccpassData.ipc_sn }" readonly="readonly" class="readonly" />
          </td>
          <th>密码版本</th>
          <td><input value="${mccpassData.password_ver }" type="text" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>事务所</th>
          <td><input type="text" value="${mccpassData.office }" readonly="readonly" class="readonly" />
          </td>
          <th>客户名</th>
          <td><input type="text" value="${mccpassData.cust_company_gguid }" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>最新付款状态</th>
          <td><input type="text" value="<%=is_pay %>" readonly="readonly" class="readonly" style="color:Magenta;" />
          </td>
          <th>安装日</th>
          <td><input type="text" value="${mccpassData.setup_date }" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>软件版本</th>
          <td><input value="${mccpassData.soft_ver }" type="text" readonly="readonly" class="readonly" />
          </td>
          <th>硬件版本</th>
          <td><input value="${mccpassData.hard_ver }" type="text" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>机床选配</th>
          <td colspan="3"><input value="${mccpassData.mac_option }" type="text" readonly="readonly" class="readonly" style="width:550px;" />
          </td>
        </tr>
        <tr>
          <th>系统配置</th>
          <td colspan="3"><input value="${mccpassData.lynuc_option }" type="text" readonly="readonly" class="readonly" style="width:550px;" />
          </td>
        </tr>
        <tr>
          <th>当前密码</th>
          <td><input type="text" value="${latestMccPassword.the_old_password }" readonly="readonly" 
                                	 class="readonly" style="width:320px;" />
          </td>
          <th>结束日期</th>
          <td><input  type="text" value="${latestMccPassword.old_password_enddate }" readonly="readonly" id="txt_old_password_period_apply" class="readonly" />
          </td>
        </tr>
        <tr valign="top"><td colspan="4"><hr/></td></tr>
        <tr>
        <th>模块</th>
	        <td colspan="3"><table>
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
        <tr valign="top" style="height:10px;"><td colspan="4"><hr/></td></tr>
        <tr id="tr_valid_date">
          <th>有效期</th>
          <td colspan="3"><table>
              <tr>
                <td valign="middle"><span style="display:inline-block;height:23px;">
                  <input id="chkForever" value="true" type="checkbox" name="chkForever" onClick="isforeverClick1();" />
                  <label for="chkForever">永久密码</label>
                  </span> </td>
                <td valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;<span id="lbl_enddate_apply">结束日期</span> </td>
                <td valign="middle">
                  <input name="txt_enddate_apply" type="text" value="<%=endDate_sel %>" readonly="readonly" id="txt_enddate_apply" class="readonly" style="width:90px;" />
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="4"><span>
            <input type="checkbox" <%=checked %> id="chkBatch" name="chkBatch" onclick="isbatchClick();">
            <label for="chkBatch" style="font-size:20px;font-weight: bold;">批量</label>
            </span></td>
        </tr>
        <tr id="tr_batch_apply" style="display: none;">
        		<th></th>
                <td colspan="3" style="font-weight: bold;">开始日期
                      <input readonly="readonly" type="text" value="${mccpassData.batch_startdate }" class="readonly" style="width:75px;"/>
                      	结束日期
                      <input readonly="readonly" type="text" value="${mccpassData.batch_enddate}" class="readonly" style="width:75px;"/>
                     	间隔:${mccpassData.batch_period }月
                </tr>
        <tr>
          <th>申请备注</th>
          <td colspan="3"><textarea id="lbl_memo_apply_value" name="lbl_memo_apply_value" rows="3" cols="3" draggable="false" class="readonly" readonly="readonly" 
                            		style="resize:none;display:inline-block;border-width:2px;border-style:Inset;width:600px;">${mccpassData.request_memo }</textarea>
          </td>
        </tr>
        <tr>
          <th>申请人</th>
          <td><input type="text" value="${mccpassData.request_name }" readonly="readonly" class="readonly" />
          </td>
          <th>申请时间</th>
          <td><input type="text" value="${mccpassData.request_time }" readonly="readonly" class="readonly" />
          </td>
        </tr>
        <tr>
          <th>申请号</th>
          <td><input id="txt_apply_no" name="txt_apply_no" type="text" value="${mccpassData.apply_no }" readonly="readonly" class="readonly" />
          </td>
          <th>密码状态</th>
          <td><input type="text" value="<%=state %>" readonly="readonly" class="readonly" style="color:Magenta;width:170px;" />
          </td>
        </tr>
        <tr>
          <th>密码</th>
          <td colspan="3"><input type="text" readonly="readonly" class="readonly" onDblClick="return OpenSmsWindow(this);" style="width:520px;" />
          </td>
        </tr>
        <tr>
          <th>批准备注</th>
          <td colspan="3"><textarea COLS=70 ROWS=5 style="width:600px;resize:none;"></TEXTAREA>
          </td>
        </tr>
        <tr>
          <th>批准人</th>
          <td><input type="text" value="${user.username}" readonly="readonly" class="readonly" />
          </td>
          <th>批准时间</th>
          <td><jsp:useBean id="now" class="java.util.Date" />
            
            <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="bb"/>
            <input type="text" value="${bb }" readonly="readonly" class="readonly" />
            <s:hidden name="which_btn_click" id="which_btn_click" value="2"></s:hidden>
          </td>
        </tr>
        <tr>
          <td colspan="5" align="center" style="padding:15px 0;"><input name="btnPass" id="btnPass" type="submit" value="批准" 
                    				class="button button-pill button-primary"  onclick="clickwhichbutton(0);">
            <input name="btnUnpass" id="btnUnpass" type="submit" value="拒绝" 
	                    			class="button button-pill button-primary"  onclick="clickwhichbutton(1);">
            <input name="btnCancelEdit" id="btnCancelEdit" type="button" value="返回" 
	                    			class="button button-pill button-primary" onClick="window.location='MccListAction';">
          </td>
        </tr>
      </table>
    </div>
  </div>
  <div>
    <div style="margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;"> <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766 </div>
  </div>
</form>
</body>
</html>
