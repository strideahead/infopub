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
    <c:if test="${is_revert==null }">密码申请 - InfoPub v6.0</c:if>
</title>
<link href="css/main.css" rel="stylesheet"/>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/jquery-ui-style.css" rel="stylesheet"/>
<link href="css/jquery-ui.css" rel="stylesheet"/>
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<link href="css/jquery-ui-1.9.2.custom.min8.css" rel="stylesheet"/>
<script src="js/jquery-3.0.0.js"></script>
<script src="js/jquery-ym-datePlugin-0.1.js"></script>
<script src="js/jquery-ui-1.8.4-resource.js"></script>
<script src="js/moduleset.js"></script>
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
            <li class="erji"><a href="javascript：void(0);" onClick="resetpassword();">重设密码</a></li>
            <c:if test="${user.permission eq 'A'}">
              <li class="erji"> <a href="showCompanies">进入管理</a> </li>
            </c:if>
            <li class="erji"><a  href="logout">退出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <div id="pnlMCCEdit" align="center" style="background-color: RGB(224,255,255);font-size: 16px;padding-top:20px;">
    <div style="padding-bottom:20px;"> <span id="lblTitleApply" style="font-weight: bolder;font-size:25px;">
      <c:if test="${is_revert != null }">密码撤销</c:if>
      <c:if test="${is_revert == null }">密码申请</c:if>
      </span> </div>
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
               	if(!"".equals(is_forevertmp_oldPass)&&is_forevertmp_oldPass.equals("Y")){
               		endDate_old="永久密码";
               	}
           	}
           	
           	String is_forevertmp=(String)map.get("is_forever");
           	boolean is_forever=false;
           	
           	if(is_forevertmp.equals("Y")){
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
    <form name="jspnetForm" id="jspnetForm" method="post" action="applyPassWithExistingCtrlno">
      <table style="color: black">
        <tr>
        <tr>
          <th align="left">产品</th>
          <td align="left" nowrap><input name="txt_controller_ver" id="txt_controller_ver" type="text" value="${mccpassData.controller_ver }"
                 	 readonly="readonly" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">机床编号</th>
          <td align="left" nowrap><input name="txt_machine_no" type="text" value="${mccpassData.machine_no }"
                  readonly="readonly" id="txt_machine_no" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">机床大类</th>
          <td align="left" nowrap="nowrap"><input name="txt_total_type" id="txt_total_type" type="text" value="${mccpassData.total_type }" 
					readonly="readonly" class="readonly" style="width:210px;" />
          </td>
          <th align="left">机床型号</th>
          <td align="left" nowrap="nowrap"><input name="txt_machine_type" id="txt_machine_type" type="text" value="${mccpassData.machine_type }" readonly="readonly" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">控制器号</th>
          <td align="left" nowrap="nowrap"><input name="txt_controller_no" id="txt_controller_no" type="text" value="${mccpassData.controller_no }" 
                  readonly="readonly" class="readonly" style="width:210px;" />
          </td>
          <th align="left">控制卡号</th>
          <td align="left" nowrap="nowrap"><input name="txt_card_no" type="text" value="${mccpassData.card_no }" readonly="readonly" id="txt_card_no" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">主机序列号</th>
          <td align="left"><input name="txt_ipc_sn" type="text" value="${mccpassData.ipc_sn }" readonly="readonly" id="txt_ipc_sn" class="readonly" style="width:210px;" />
          </td>
          <th align="left">密码版本</th>
          <td align="left"><input name="txt_password_ver" type="text" value="${mccpassData.password_ver }" readonly="readonly" id="txt_password_ver" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">事务所</th>
          <td align="left" ><input name="txt_office" type="text" value="${mccpassData.office_name }" readonly="readonly" id="txt_office" class="readonly" style="width:210px;" />
          </td>
          <th align="left">客户名</th>
          <td align="left" ><input name="txt_cust_name" type="text" value="${mccpassData.customer_name }" readonly="readonly" id="txt_cust_name" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">最新付款状态</th>
          <td align="left" ><input name="txt_is_pay" type="text" value="<%=is_pay %>" readonly="readonly" id="txt_is_pay" class="readonly" style="width:210px;color: red;" />
          </td>
          <th align="left">安装日</th>
          <td align="left" ><input name="txt_setup_date" type="text" value="${mccpassData.setup_date }" readonly="readonly" id="txt_setup_date" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">软件版本</th>
          <td align="left" ><input name="txt_ver" type="text" readonly="readonly" value="${mccpassData.soft_ver }" id="txt_ver" class="readonly" style="width:210px;" />
          </td>
          <th align="left">硬件版本</th>
          <td align="left" ><input name="txt_hard_ver" type="text"  value="${mccpassData.hard_ver }"  readonly="readonly" id="txt_hard_ver" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">机床选配</th>
          <td colspan="3" align="left"><input value="${mccpassData.mac_option }" name="txt_mac_option" type="text" readonly="readonly" id="txt_mac_option" class="readonly" style="width:420px;" />
          </td>
        </tr>
        <tr>
          <th align="left">系统配置</th>
          <td colspan="3" align="left"><input value="${mccpassData.lynuc_option }"  name="txt_lynuc_option" type="text" 
                        	readonly="readonly" id="txt_lynuc_option" class="readonly" style="width:420px;" />
          </td>
        </tr>
        <tr>
          <th align="left">当前密码</th>
          <td id="td_old_password" align="left" colspan="1"><input name="txt_old_password" type="text" value="${mcc_oldpassData.the_old_password}" 
          			readonly="readonly" id="txt_old_password" class="readonly" style="width:300px;font-size:14px;" />
          </td>
          <th align="left">结束日期</th>
          <td align="left"><input name="txt_old_password_period" id="txt_old_password_period" type="text" value="<%=endDate_old %>" readonly="readonly" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <td colspan="4"><hr/></td>
        </tr>
        <tr>
          <th align="left">模块</th>
          <td align="left" nowrap colspan="4">
          <table style="">
              <tr>
                <%
              		String[] moduleSet_array=Constant.module_set;
              		for(int i=0;i<moduleSet_array.length;i++){
              	%>
                <td style="padding:3px 13px;"><input id="module<%=i %>" type="checkbox" name="module<%=i %>" disabled="disabled"/>
                  <label id="label<%=i %>" for="module<%=i %>" style="font-weight: 550;"><%=moduleSet_array[i] %></label>
                </td>
                <% if((i+1)%5==0 && i!=0){ %>
              </tr>
              <tr>
                <%} }%>
                <td><input id="hidden_module_set" name="hidden_module_set" type="hidden" value="${mccpassData.module_set }"/>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="4"><hr/></td>
        </tr>
        <tr id="tr_valid_date">
          <th align="left">有效期</th>
          <td align="left" colspan="3">
          <div style="display:inline-block;padding-right:20px;">
              <input type="checkbox" value="<%=is_forever %>" id="chkForever"  name="chkForever" onClick="isforeverClick1();">
              <label for="chkForever">永久密码</label>&nbsp;&nbsp;&nbsp;&nbsp;
              <label id="lbl_enddate" style="font-weight: bold;"> 结束日期</label>
              <input id="enddate_set" name="enddate_set" readonly="readonly" style="width: 100px;padding-left:20px;" class="Date" type="text" value="${requestScope.enddatebat }"/>
            </div></td>
        </tr>
        <tr>
          <td align="left" colspan="4"><span>
            <input type="checkbox" value="<%=is_batch %>" id="chkBatch" name="chkBatch" onClick="isbatchClick();">
            <label for="chkBatch" style="font-weight:bold;font-size:20px;">批量</label>
            </span></td>
        </tr>
        <tr id="tr_batch_apply" style="display:none;">
          <td></td>
          <td align="left" nowrap="nowrap" colspan="3"><table>
              <tr>
                <td colspan="2"><table>
                    <tr>
                      <th>开始日期</th>
                      <td><input readonly="readonly" id="dp_startdate_batch_apply"
                                                    name="dp_startdate_batch_apply" type="text" value="${requestScope.fromdate }" style="width:100px;" />
                      </td>
                      <th>结束日期</th>
                      <td><input readonly="readonly" name="dp_enddate_batch_apply" type="text" value="${requestScope.enddatebat}" id="dp_enddate_batch_apply" class="Date" style="width:100px;" />
                      </td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <th>间隔:</th>
                <td><table id="rdl_period_apply">
                    <tr>
                      <%
											for(int i=0;i<6;i++){
										%>
                      <td><input id="rdl_period_apply_<%=i %>" type="radio" name="rdl_period_apply" value="<%=i+1 %>" />
                        <label for="rdl_period_apply_<%=i %>"><%=i+1 %>月</label>
                      <td><%} %>
                      <td><input id="rdl_period_apply_6" type="radio" name="rdl_period_apply" value="12" checked="checked"/>
                        <label for="rdl_period_apply_6">12月</label>
                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <th align="left">申请备注</th>
          <td colspan="3" align="left"><textarea id="ftb_memo_apply" name="ftb_memo_apply" COLS=68 ROWS=5 draggable="false" style="resize: none;border-style: ridge;font-size: 14px;"></TEXTAREA>
          </td>
        </tr>
        <tr>
          <th align="left">申请人</th>
          <td align="left" nowrap><input name="txt_request_user" type="text" value="${user.username}" readonly="readonly" id="txt_request_user" class="readonly"  style="width:210px;" />
          </td>
          <th align="left">申请时间</th>
          <td align="left" nowrap><jsp:useBean id="now" class="java.util.Date" />
            
            <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="bb"/>
            <input name="txt_request_time" type="text" value="${bb }" readonly="readonly" id="txt_request_time" class="readonly" style="width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">申请号</th>
          <td align="left" nowrap><input name="txt_apply_no" value="${max_apply_no }" type="text" readonly="readonly" id="txt_apply_no" class="readonly" style="width:210px;" />
            <input name="hidden_apply_no" value="${max_apply_no }" id="hidden_apply_no" type="hidden"/>
          </td>
          <th align="left">密码状态</th>
          <td align="left" nowrap><input name="txt_password_state_apply" type="text" readonly="readonly" id="txt_password_state_apply" class="readonly" style="color:Black;width:210px;" />
          </td>
        </tr>
        <tr>
          <th align="left">密码</th>
          <td align="left"><input name="txt_password_apply" type="text" readonly="readonly" id="txt_password_apply" class="readonly" style="width:210px;" />
          </td>
          <th align="left">结束日期</th>
          <td><input name="txt_cur_password_period_apply" type="text" readonly="readonly" 
                               	id="txt_cur_password_period_apply" class="readonly" style="width:210px;" /></td>
        </tr>
        <tr>
          <td colspan="4" align="center" style="padding-top:20px;">
          	<input name="btnApply" id="btnApply" type="submit" value="提交申请" class="button button-pill button-primary">
            <input name="btnCancelApply" id="btnCancelApply" type="button" value="取消" 
	                    			class="button button-pill button-primary" onClick="window.location='MccListAction';">
          </td>
        </tr>
      </table>
    </form>
  </div>
  <div style=";margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;"> <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766 </div>
</div>
</body>
<script>
$(function() {
	var module_set11=$("#hidden_module_set").val();
   	if(module_set11!=null){
   		var iModule_Set = parseInt(module_set11);
        fill_module_set(iModule_Set);
   	}
   	
	isforeverClick1();
	isbatchClick();
	//下拉年月
	$("#enddate_set").ymdateplugin({
		changeMonth: true,
		changeYear: true
	});
	$("#dp_startdate_batch_apply").ymdateplugin({
		changeMonth: true,
		changeYear: true
	});
	$("#dp_enddate_batch_apply").ymdateplugin({
		changeMonth: true,
		changeYear: true
	});
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
</script>
</html>
