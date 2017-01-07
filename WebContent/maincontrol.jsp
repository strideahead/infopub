<%@page import="javax.swing.JOptionPane"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册码列表 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/table.css" rel="stylesheet"/>
<link href="css/pageControl.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
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
						<c:if test="${user.permission < 'B' and (user.company_type == '3')}">
							<li class="erji">
								<a href="batchEditController">批量修改</a>
							</li>
						</c:if>
						<c:if test="${user.usercode eq tableproducts.pwd_checker_accounts}">
							<li class="erji">
								<a href="batchApproveActionpc">批量批准</a>
							</li>
						</c:if>
					</ul>
				</li>
				<li style="min-width: 150px;">
					<a href="#" title="${user.username }"><c:out value="${fn:substring(user.username, 0, 5)}"/><span class="arrow"></span></a>
					<ul class="erj_ui">
						<li class="erji"><a href="javascript：void(0)" onclick="resetpassword();return false;">重设密码</a></li>
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
	<div class="themControl">
		【<span class="checkoutitems">${result.totalRecord }</span>条】
		<div  class="themControl_right">
			<form id="MccForm" class="MccForm" action="MccListAction">
				<s:textfield label="关键字" name="keyStr" id="mccKeystr" size="15px" theme="simple" value="%{#request.keyStrstr }">关键字: </s:textfield>               
				<s:select list="#{'Y':'已批准','N':'拒绝','P':'待批准' }" value="%{#request.statestr }" class="selectOption" style="width:80px;" name="state" headerValue="<状态>" headerKey="-1" />
				<s:select list="#{'N':'未付','Y':'已付'}"  headerValue="最新付款状态" headerKey="-1" class="selectOption" style="width:150px;" disabled="false" name="is_pay" value="%{#request.ispaystr }"/>
				<s:select list="#{'mach':'机床','ctrl':'控制器'}" headerValue="<所有>" headerKey="-1" class="selectOption" style="width:80px;" name="machorcontrl" value="%{#request.machorcontrlstr }" />
				<s:select list="#{'expired':'已过期','in1mon':'一月内过期','in3mon':'三月内过期','after3mon':'超过三月过期'}" class="selectOption" style="width:130px;" headerValue="<间隔>" headerKey="-1" name="expireState" value="%{#request.expireStatestr }"/>
				<s:hidden name="isSearchBtn" value="true"></s:hidden>
				<input name="btnSubmit" value="检索" id="btnSubmit" class="submitButton green big" type="submit"> 
			</form>
		</div>    
	</div>
	<c:if test="${fn:length(result.dataList) eq 0 }">
	<div class="table_content" style="clear:both">
			<div style="background-image: './images/prompt.gif';height:30px;width:880px;">
			<span id="gridEmpty" class="prompt" style="display:inline-block;height:24px;width:880px;">暂无纪录</span>
			</div>
	</div>
	</c:if>
	<c:if test="${fn:length(result.dataList) gt 0 }">	
	<div class="table_content" style="clear:both">
		<table class="tableClass" id="listMCC" style="color:#FF0000;width:100%;border-collapse:collapse;">
			<thead>
				<tr class="trth" style="height: 30px;font-size: 14px;font-family: '宋体';color: green;"> 
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_apply_no" onclick="changehref('apply_no__asc');return false;">申请号</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_controller_ver" onclick="changehref('controller_ver__asc');return false;">产品</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_machine_no" onclick="changehref('machine_no__asc');return false;">机床编号</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_machine_type" onclick="changehref('machine_type__asc');return false;">机床型号</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_controller_no" onclick="changehref('controller_no__asc');return false;">控制器号</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_card_no" onclick="changehref('card_no__asc');return false;">控制卡号</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_company_name" onclick="changehref('company_name__asc');return false;">事务所</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_customer_name" onclick="changehref('customer_name__asc');return false;">客户名</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_state" onclick="changehref('state__asc');return false;">密码状态</a></th>
					<th class="th1"><a style="color: white;" href="javascript:void(0);" id="order_enddate" onclick="changehref('enddate__asc');return false;">结束日期</a></th>
					<th class="th1">操作</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#request.result.dataList " id="m">
				<s:if test="%{#m.apply_no > 0}">
				 	<s:set name="title_ttt" value="'当前密码:\n'+#m.the_password+'\n当前密码有效期:\n'+#m.enddate"/>
				</s:if>
				<s:if test="%{#m.apply_no==0 or #m.the_password==''}">
				 	<s:set name="title_ttt" value="'无密码'" />
	    		</s:if>
			<tr title="<s:property value="#title_ttt" />" 
				onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';"  
			 	onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" style="background-color:${expireState};">
				<c:if test="${apply_no>0 }">
					<td class="th1"><a style="font-size: 13px;" id="lnkViewApply"  href="passwordDetail?apply_no=${apply_no}">${apply_no}</a>
                	</td>
				</c:if>
				<c:if test="${apply_no==0 }">
					<td></td>
				</c:if>
                <td class="th1">${controller_ver}</td>
                <td class="th1">${machine_no}</td>
                <td class="th1">${machine_type}</td>
                <td class="th1">${controller_no}</td>
                <td class="th1">${card_no}</td>
                <td class="th1" style="width:100px;white-space: normal;">${company_name}</td>
                <td class="th1">${cust_company_name}</td>
                <c:set var="stateColor" scope="page" value="Green"/>
                <c:if test="${state=='待批准' }">
                	<c:set var="stateColor" scope="page" value="Magenta"/>
                </c:if>
                <c:if test="${state=='拒绝' }">
                	<c:set var="stateColor" scope="page" value="Red"/>
                </c:if>
                <td align="center" class="th1" style="color:${stateColor};white-space:nowrap;">${state } </td>
                <c:set var="expirestate" value="$(enddate)"></c:set>
                <c:choose>
                	<c:when test="${fn:contains(expirestate,'-') }">
                		<c:set var="expirestate" value="${fn:substring(expirestate,2,20) }"></c:set>
                	</c:when>
                </c:choose>
                <c:set var="fontcolor" scope="page" value="black"/>
                <c:if test="${is_forever=='Y' }">
                <c:set var="fontcolor" scope="page" value="red"/>
                </c:if>
                <td title="${enddate }" class="th1"><font color="${fontcolor }">${enddate }</font></td> 
			<td class="th1" align="center">
				 <c:if test="${user.permission >= 'B'}">
                   <a href="checkOrEditPwd?controller_no=${controller_no }">
                   	<img src="images/edit.gif" title="查看/编辑&#10;[机床/控制器/控制卡/密码/Ez]" alt="查看/编辑"/>
                   </a>
                 </c:if>
                 <c:if test="${user.permission < 'B'}">
                    <a href="AddControlCardItem?controller_no=${controller_no }"><img name="mccEdit" id="mccEdit" src="images/edit.gif" title="查看/编辑&#10;[机床/控制器/控制卡/密码/Ez]" alt="查看/编辑"/></a>
                </c:if>
                <c:if test="${user.permission<'B' or user.id == input_user_id}">
               		<img onclick="deleteAlert('${controller_no}','${card_no }');return true;" src="images/delete.gif" title="删除&#10;[机床/控制器/控制卡/密码/Ez]" />
               	</c:if>
                <c:if test="${user.permission>='B' and user.id!=input_user_id }">  
                	<input type="image" name="mccDelete" id="mccDelete" disabled="disabled" title="删除&#10;[机床/控制器/控制卡/密码/Ez]" src="images/delete_disable.gif" style="color:Blue;width:16px;border-width:0px;cursor:default;" />
                </c:if>
               	<img id="button_split" src="images/button_split.gif" style="border-width:0px;width:2px;" />
               	<c:if test="${state!='待批准' and user.permission < 'C'}">
               		<c:if test="${apply_no eq 0}">
                  		<a href="applyOrCancPass?txt_controller_no=${controller_no}"><img src="images/add.gif" title="申请/撤销申请&#10;[机床/控制器/控制卡/密码/Ez]" alt="申请/撤销申请"/></a>  
					</c:if>
					<c:if test="${apply_no gt 0}">
                  		<a href="applyOrCancPass?apply_no=${apply_no}"><img src="images/add.gif" title="申请/撤销申请&#10;[机床/控制器/控制卡/密码/Ez]" alt="申请/撤销申请"/></a>  
					</c:if>
               </c:if>
               <c:if test="${state=='待批准'  or user.permission >= 'C'}">
               		<c:if test="${user.id != request_user_id }">
                		<input type="image" name="mccAdd" id="mccAdd" disabled="disabled" title="申请/撤销申请&#10;[机床/控制器/控制卡/密码/Ez]" 
                			src="images/add_disable.gif" style="color:Blue;width:16px;border-width:0px;cursor:default;" />              			
               		</c:if>
               		<c:if test="${user.id == request_user_id }">
                		<a href="passwordDetail?apply_no=${apply_no}&is_revert=true"><img src="images/add.gif" 
                			title="申请/撤销申请&#10;[机床/控制器/控制卡/密码/Ez]" alt="申请/撤销申请"/></a>             			
               		</c:if>
               </c:if> 
                <c:if test="${state=='待批准' and user.usercode == tableproducts.pwd_checker_accounts}"> 
              		<a href="approveOrRefuseApplication?apply_no=${apply_no}">
              			<img src="images/pwd_check.gif" title="批准/拒绝&#10;[密码]" />
              		</a>                         
                </c:if>
            	<c:if test="${state!='待批准' or user.usercode != tableproducts.pwd_checker_accounts}">
            	    <input type="image" name="mccPwdCheck" id="mccPwdCheck" disabled="disabled" title="批准/拒绝&#10;[密码]" src="images/pwd_check_disable.gif" style="color:Blue;width:16px;border-width:0px;cursor:default;" />
            	</c:if>
                 <a href="passwordRetrieve?controller_no=${controller_no}"><img src="images/pwd_query.gif" title="查询&#10;[密码]" alt="查询"/></a>
  			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
	</div>
	</c:if>
	<%
		int currentpage=1,totalpage=1;
		if(request.getAttribute("currentpage")!=null){
			currentpage=Integer.parseInt(request.getAttribute("currentpage").toString());
		}
		if(request.getAttribute("totalpage")!=null){
			totalpage=Integer.parseInt(request.getAttribute("totalpage").toString());
		}
		int prevpage=(currentpage==1)?1:(currentpage-1);
		int nextpage=(currentpage==totalpage)?totalpage:(currentpage+1);
	%>
	<div style="clear:right;margin-top: 10px;margin-bottom:10px;clear:both;">
		<div class="bottomHint" style="float:left;display: table;height:18px;font-size: 14px;">
			<div style="display: table-row;">
				<div style="display:table-cell;padding:2px 5px 2px 10px;">已过期</div>
				<div style="display:table-cell;width: 50px; background: #D6D3CE;"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">一月内过期</div>
				<div style="display:table-cell;width: 50px; background: #FCF7B8"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">三月内过期</div>
				<div style="display:table-cell;width: 50px; background: #CAEAFC"></div>
				<div style="display:table-cell;padding:2px 5px 2px 10px;">超过三月过期</div>
				<div style="display:table-cell;width: 50px; background: RGB(176,196,222)"></div>
			</div>
		</div>
		<div class="PagerCss" style="float:right;font-size: 14px;">
			共<span style="padding:0 2px;">${result.totalRecord }</span>条记录
			<s:select id="pageSize" name="pageSize" list="{'15','20','40','80','200','500','2000'}" theme="simple"  headerKey="-1" headerValue=""
					value="%{#request.pageSizeStr }" onchange="change();" style="padding-left: 2px;"/>
			<span style="padding-left:4px;">${result.currentPage }/${result.totalPage }</span>
			<a class="hrefLink" href="MccListAction?pageNum=1" style="padding-left: 3px;font-size: 15px;">首页</a>
			<a class="hrefLink" href="MccListAction?pageNum=<%=nextpage %>" style="padding-left: 3px;font-size: 15px;">下一页</a>
			<a class="hrefLink" href="MccListAction?pageNum=<%=prevpage %>" style="padding-left: 3px;font-size: 15px;">上一页</a>
			<a class="hrefLink" href="MccListAction?pageNum=<%=totalpage %>" style="padding:0 15px 0 3px;font-size: 15px;">尾页</a>
			<div style="display:inline;">
				<input id="pageNum" type="hidden" value="1" />
			</div>
		</div>
	</div>
	<div style=";margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div>
</div>
</body>
<script>
var isfail=${fail};
window.onload=function()
{ 
	if(isfail == 'true') alert("添加控制器失败,已经添加过此控制器!");
} 
var orderingSqu="${orderingSqu}";
var currentPage = ${result.currentPage};// 当前第几页数据
if(currentPage="") currentPage=1;
var totalPage = ${result.totalPage};// 总页数
function change(){
	pageSize=$("#pageSize").val();
	window.location.href="MccListAction?pageSize="+pageSize+"&isSearchBtn=false";
}
function changehref(ordering_squSrc)
{
	var setorder=ordering_squSrc;
	var strs= new Array(); 
	strs=orderingSqu.split("__"); 
	var strs2= new Array(); 
	strs2=ordering_squSrc.split("__");
	if(strs[0]==strs2[0]){
		if(strs[1]=='asc'){
			setorder=strs[0]+"__desc";
		}else{
			setorder=strs[0]+"__asc";
		}
	}
	window.location.href = "MccListAction?orderingWord="+setorder; 
}
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
function deleteAlert(controller_no,card_no)
{
	if (window.confirm("删除是不可恢复的，你确认要删除吗？\n删除记录是controller_no=" + controller_no)) {
		window.location = "passwordDel?controller_no="+controller_no+"&card_no="+card_no;
		return true;
	} else {
		return false;
	}
}
</script>
</html>
