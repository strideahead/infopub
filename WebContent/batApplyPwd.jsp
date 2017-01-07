<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>批量申请密码  - InfoPub v6.0</title>
	<link href="images/favicon.ico" rel="shortcut icon">
    <link href="css/main.css" rel="stylesheet"/>
    <link href="css/jquery-ui-style.css" rel="stylesheet"/>
    <link href="css/jquery-ui.css" rel="stylesheet"/>
    <link href="css/jquery.ui.datepicker.css" rel="stylesheet"/>
    <link href="css/jquery-ui-1.9.2.custom.min8.css" rel="stylesheet"/>
    <link href="css/pageControl.css"  rel="stylesheet" />
    <link href="css/button.css"  rel="stylesheet" />
    <script src="js/jquery-3.0.0.js"></script>
    <script src="js/jquery-ym-datePlugin-0.1.js"></script>
    <script src="js/jquery-ui.js"></script>
<script>
function trim(str)
{
	return str.replace(/^\s*/,"").replace(/\s*$/,"");
}
function check(form)
{
	var minlen=${minlen_controllerno};
	var controller_no=form.txt_controller_no.value;
	if(controller_no==null || controller_no.length < minlen)
	{
		alert("控制器项输入长度不够!");
		return false;
	}
	if(controller_no.indexOf("，") > 0 ){
	    alert('输入含有非法字符(，)!');
	    return false;
	}
	return true;
}
</script>
<style type="text/css">
	table input{
		padding:2px 2px;
		font-size:15px;
		font-family: :sans-serif,宋体;
	}
	table textarea{
		padding:2px;
		font-size:15px;
		font-family: :sans-serif,宋体;
	}
	table tr th{
		padding:2px 2px;
		font-size:20px;
	}
	.table-content1 tr{
		padding-top:15px;
	}
</style>
</head>
<body>
<form name="jspnetForm" id="jspnetForm" method="post" action="finishBatApply" onsubmit="return check(this);">   
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
						<li class="erji"><a href="#" onclick="resetpassword();return false;">重设密码</a></li>
						<c:if test="${user.permission eq 'A'}">
							<li class="erji">
			 					<a href="showCompanies">进入管理</a>
	       					</li>
        				</c:if>
						<li class="erji"><a href="logout">退出</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>     
    <div class="table_content" style="font-size:18px;background-color:#d5f3f4;">
    	<div style="padding-top:20px;margin-bottom:15px;font-weight: bold;font-size:20px;">批量申请</div>
        <%-- <table>
            <tr>
                <td style="height: 25px;" align="left" background="images/loginbg.jpg">
                    <span id="lblTitleApply"  class="themSpan">批量申请</span>
                </td>
            </tr>
        </table> --%>
        <div style="width: 80%; border: solid 1px gray; margin-left: auto; margin-right: auto;padding: 8px 8px 8px 8px">
            <table class="table-content1" style="margin:5px auto;">
                <tr>
                    <th align="left" width="100" id="lbl_controller_no">控制器号</th>
                    <td align="left" colspan="3">
<!--                     <input name="txt_controller_no" type="text" id="txt_controller_no" class="normal" style="width: 620px" />
 -->                    <textarea id="txt_controller_no" name="txt_controller_no" COLS=70 ROWS=5 
 							style="resize: none;border-style: ridge;"></TEXTAREA>
                    </td>
                </tr>
			     <tr id="tr_valid_date_apply">
						<th align="left">有效期</th>
						<td align="left" nowrap="nowrap" colspan="3">
			                 <table>
			                     <tr>
			                    <td>
			                        <span style="display:inline-block;height:23px;">
				                        <input type="checkbox" value="false" id="chkForever"  name="chkForever" onclick="isforeverClick1();">
				                        <label for="chkForever" style="font-size: 15px;">永久密码</label>
			                        </span>
			                    </td>
			                    <td id="lbl_enddate" valign="middle" style="padding-left: 15px;padding-right:10px;font-size: 15px;">结束日期</td>
			                    <td valign="middle">
			                        <input id="enddate_set" name="enddate_set" readonly="readonly" 
			                        style="line-height:20px;width: 100px;padding:0 10px;text-align: center;color: red;" class="Date" 
			                        type="text" value="${requestScope.enddate }"/>
			                    </td>
			                </tr>
			                 </table>
			             </td>
				</tr>
                <tr id="tr_batch_apply_label" valign="top">
					<th align="left" colspan="4">
						<input type="checkbox" id="chkBatch" name="chkBatch" value="true" onclick="isbatchClick();">
			        	<label for="chkBatch">批量</label>
			        </th>
				</tr>
			    <tr id="tr_batch_apply" style="display: none">
					<td></td>
					<td align="left" nowrap="nowrap" colspan="3">
			                        <table>
			                            <tr>
			                                <td colspan="2">
			                                    <table>
			                                        <tr style="">
			                                            <th valign="middle" style="">开始日期</th>
			                                            <td valign="top" style="padding-left:10px;">
			                                            	 <input name="dp_startdate_batch_apply" id="dp_startdate_batch_apply" type="text" 
			                                            	 value="${requestScope.fromdate}" readonly="readonly" style="width:100px;color:red;" />
			                                            </td>
			                                            <th valign="middle" style="padding-left:20px;">结束日期</th>                                            
			                                            <td valign="top" style="padding-left:10px;">
			                                            	<input name="dp_enddate_batch_apply" id="dp_enddate_batch_apply" type="text" 
				                                            	readonly="readonly"  value="${requestScope.enddate }" 
				                                            	style="width:100px;color:red;" />
			                                            </td>
			                                        </tr>
			                                    </table>
			                                </td>
			                            </tr>
			                            <tr  style="">
			                                <th style="padding-right:10px;">间隔:</th>
			                                <td>
			                               <table id="period_apply">
												<tr>
													<%
														for(int i=0;i<6;i++){
													%><td>
														<input id="period_apply_<%=i %>" type="radio" name="rdl_period_apply" value="<%=i+1 %>" />
														<label for="period_apply_<%=i %>"><%=i+1 %>月</label>
													<td>
														<%} %>
													<td>
														<input id="period_apply_6" type="radio" name="rdl_period_apply" value="12" checked="checked"/>
														<label for="period_apply_6">12月</label>
													</td>
												</tr>
											</table>
			                                </td>
			                            </tr>
			                        </table>
			                    </td>
				</tr>
                <tr>
                    <th align="left">申请备注</th>
                    <td colspan="3" align="left">
                        <textarea id="ftb_memo_apply" name="ftb_memo_apply" COLS=70 ROWS=5 style="resize: none;border-style: ridge;"></TEXTAREA>
                    </td>
                </tr>
                <tr id="tr1">
					<th align="left" colspan="4">
                        <input id="chkSendMessage" type="checkbox" name="chkSendMessage" checked="checked" /><label for="chkSendMessage" style="padding-left:20px;">是否给批准人发送通知？</label>
                    </th><td></td>
				</tr>
                <tr style="margin:15px auto;">
                    <th align="left">短信内容</th>
                    <td colspan="3" align="left">
                         <input name="txt_sms_content" id="txt_sms_content" type="text" value="${user.username}批量申请了密码，请尽快批准。" class="normal" style="width: 620px;" />
                    </td>
                </tr>
                <tr>
                    <th align="left">申请人</th>
                    <td align="left" nowrap>
                    	<input name="txt_request_user" type="text" value="${user.username}" maxlength="50" readonly="readonly" id="txt_request_user" class="readonly" style="width:170px;" />
                    </td>
                    <th align="left">申请时间</th>
                    <td align="left" nowrap>
                    	<jsp:useBean id="now" class="java.util.Date" />
						<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="bb"/>
                        <input name="txt_request_time" type="text" value="${bb }" maxlength="50" readonly="readonly" id="txt_request_time" class="readonly" style="width:170px;" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center" style="padding:15px 0;">
                     <input type="submit" value="提交申请" class="button button-pill button-primary">
                    <input name="btnCancelApply" id="btnCancelApply" type="button" value="取消" 
                    		class="button button-pill button-primary" onclick="window.location='MccListAction';">
                    </td>
                </tr>
            </table>
        </div>
        <br />
    
</div>
    </div>        
     <div style=";margin-top:10px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div>       
</form>    
<script>
//等待dom元素加载完毕.  
$(function() {
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
		$("#lbl_enddate").css("display","none");
		$("#enddate_set").css("display","none");

	}else{
		$("#lbl_enddate").css("display","");
		$("#enddate_set").css("display","inline-block");

	};
}
function isbatchClick()
{
	if($("#chkBatch").is(':checked')){
		$("#tr_valid_date_apply").css("display","none");
		$("#tr_batch_apply").css("display","");
	}else{
		$("#tr_valid_date_apply").css("display","");
		$("#tr_batch_apply").css("display","none");
	};
}
</script>
</body>
</html>