<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.lynuc.def.Constant" %>
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
$(function() {
	$("input:checkbox").each(function() {
		$(this).bind('click',function(){
			if ($(this).is(':checked') ==true) {
		       $(this).next('label').css('color','red');
			}else{
				$(this).next('label').css('color','black');
			}
		});
	});
	$("#chk_is_pay").bind('click',function(){
		if ($(this).is(':checked') ==true) {
			$("#ddl_is_pay").removeAttr("disabled"); 
		}else{
			$("#ddl_is_pay").attr("disabled","disabled");
		}
	});
	$("#chk_sale_cust").bind('click',function(){
		if ($(this).is(':checked') ==true) {
			$("#ddl_office").removeAttr("disabled"); 
			$("#ddl_cust").removeAttr("disabled"); 
		}else{
			$("#ddl_office").attr("disabled","disabled");
			$("#ddl_cust").attr("disabled","disabled");
		}
	});
	$("#chk_module_set").bind('click',function(){
		if ($(this).is(':checked') ==true) {
			$("#table_module_set input").removeAttr("disabled"); 
		}else{
			$("#table_module_set input").attr("disabled","disabled");
		}
	});
 });
 
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
<form name="jspnetForm" id="jspnetForm" method="post" action="finishBatchEditController" onsubmit="return check(this);">   
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
				</li>txt_controller_no
			</ul>
		</div>
	</div>     
    <div class="table_content" style="font-size:18px;background-color:#d5f3f4;">
    	<div style="padding-top:20px;margin-bottom:15px;font-weight: bold;font-size:20px;">批量修改</div>
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
                    <th align="left" id="lbl_controller_no" style="padding:5px;">控制器号</th>
                    <td align="left" colspan="4">
<!--                     <input name="txt_controller_no" type="text" id="txt_controller_no" class="normal" style="width: 620px" />
 -->                    <textarea id="txt_controller_no" name="txt_controller_no" COLS=70 ROWS=5 
 							style="resize: none;border-style: ridge;"></TEXTAREA>
                    </td>
                </tr>
                <tr>
                    <th align="center" id="td_is_pay">
                    	<input id="chk_is_pay" name="chk_is_pay" type="checkbox">
                    </th>
                    <td align="left" colspan="1" style="padding:5px;">
                    	<label for="chk_is_pay" style="font-weight: bolder;font-size:1rem;">付款状态</label>
                    </td>
                    <td align="left" colspan="3">
                    <select name="ddl_is_pay" id="ddl_is_pay" class="selectOption" style="width:215px;" disabled="disabled">
					    <option value="Y">已付</option>
					    <option value="N">未付</option>
					</select></td>
                </tr>
                <tr>
                    <th align="center" id="td_sale_cust" colspan="1">
                    	<input id="chk_sale_cust" name="chk_sale_cust" type="checkbox">
                    </th>
                    <td align="left" colspan="1" style="padding:5px;">
                    <label for="chk_sale_cust" style="font-weight: bolder;font-size:1rem;">事务所</label>
                    </td>
                    <td align="left" colspan="1">
                    <select id="ddl_office" name="ddl_office" class="selectOption" disabled="disabled">  
	                           <c:forEach items="${officeList}" var="office" varStatus="vs">    
	                               <option value="${office.gguid}">${office.company_name}</option>  
	                           </c:forEach>  
	                  </select> 
                    <%-- <s:select emptyOption="true" name="ddl_office" id="ddl_office" list="#request.officeList" disabled=""
	                    listKey="gguid" listValue="company_name" value="#request.mapdefault_office.gguid" headerValue="" 
	                    onkeyup="changeSelectOption();" onchange="office_input_change();" theme="simple"
	                     style="width:215px;"></s:select> --%>
                      	
                      	<input type="hidden" id="hidden_office_name" name="hidden_office_name" value=""/>
                    </td>
					
					<td align="left" colspan="1" style="padding:5px;">
					<label for="chk_sale_cust" style="font-weight: bolder;font-size:1rem;">客户名</label></td>
					<td align="left" colspan="1">
					<select id="ddl_cust" name="ddl_cust" class="selectOption" disabled="disabled">  
	                           <c:forEach items="${custnameList}" var="custname" varStatus="vs">    
	                               <option value="${custname.gguid}">${custname.company_name}</option>  
	                           </c:forEach>  
	                  </select> 
                    <%-- <s:select emptyOption="true" name="ddl_cust" id="ddl_cust"  list="#request.custnameList" disabled="disabled"
                      		listKey="gguid" listValue="company_name" value="#request.mapdefault_custname.gguid"
                      		 headerValue="b49c6c95-b7bf-4cb8-b910-4981793377e3" onchange="cust_input_change();" theme="simple" style="width:215px;"></s:select> --%>
                      	<input type="hidden" id="hidden_customer_name" name="hidden_customer_name" />
                    </td>
                </tr>
                <tr id="tr_module_set">
                    <th align="center" id="td_module_set" colspan="1">
                    	<input id="chk_module_set" name="chk_module_set" type="checkbox">
                    </th>
                    <td align="left" colspan="1" style="padding:5px;">
                    	<label for="chk_module_set" style="font-weight: bolder;font-size:1rem;">模块</label>
                    </td>
                    <td colspan="3" align="left"><table id="table_module_set">
                              <tr id="modulesetlist">
                          	<%
                          		String[] moduleSet_array=Constant.module_set;
                          		for(int i=0;i<moduleSet_array.length;i++){
                          	%>
                          	<td align="left" style="padding:3px 5px 3px 0px;">
                          		<input id="module<%=i %>" type="checkbox" name="module<%=i %>" disabled="disabled"/>
                          		<label for="module<%=i %>" id="labelformodule<%=i %>"><%=moduleSet_array[i] %></label>
                          	</td>
                       		<% if((i+1)%5==0 && i!=0){ %></tr><tr><%} }%>
                       		</tr>
                          </table>
                </tr>
                <tr>
                    <td colspan="5" align="center" style="padding:15px 0;">
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
</script>
</body>
</html>