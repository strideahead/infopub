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
	<title>批量批准 - InfoPub v6.0</title>
	<link href="images/favicon.ico" rel="shortcut icon">
    <link href="css/main.css" rel="stylesheet"/>
    <link href="css/table.css" rel="stylesheet"/>
    <script src="js/jquery-3.0.0.js"></script>
    <link href="css/pageControl.css" rel="stylesheet" />
    <link href="css/button.css" rel="stylesheet" />
<%
	String context=request.getContextPath();
%>
<script>
$(function () { 
    $("#chk_SelectAll").click(function(){    
        if(this.checked){    
            $("#approveList :checkbox").attr("checked", true);   
        }else{    
            $("#approveList :checkbox").attr("checked", false); 
        }    
     });  
    $("#btn_selectAll").click(function () { 
         $("#approveList :checkbox,#chk_SelectAll").attr("checked", true);   
    });   
    $("#unSelect").click(function () {   
         $("#approveList :checkbox,#chk_SelectAll").attr("checked", false);   
    });   
    $("#btn_unSelect").click(function () {  
         $("#approveList :checkbox").each(function () {   
              $(this).attr("checked", !$(this).attr("checked"));   
         }); 
         allchk(); 
    }); 
    $("#approveList :checkbox").click(function(){ 
        allchk(); 
    }); 
    $("#getValue").click(function(){ 
        var valArr = new Array; 
        $("#approveList :checkbox[checked]").each(function(i){ 
            valArr[i] = $(this).val(); 
        }); 
        var vals = valArr.join(','); 
          alert(vals); 
    }); 
    $(".table_content table tr").each(function(){    
        $(this).children().click(function(e){    
            $(e.target).parent("tr.item").each(function(){  
                if($(this).find(":checkbox").is(":checked")){  
                    $(this).find(":checkbox").attr("checked",false);  
                }else{  
                    $(this).find(":checkbox").attr("checked",true);  
                }  
            });  
        });    
    });  
});  
function allchk(){ 
    var chknum = $("#approveList :checkbox").size();
    var chk = 0; 
    $("#approveList :checkbox").each(function () {   
        if($(this).attr("checked")==true){ 
            chk++; 
        } 
    }); 
    if(chknum==chk){
        $("#chk_SelectAll").attr("checked",true); 
    }else{
        $("#chk_SelectAll").attr("checked",false); 
    } 
}
function checkoutValues()
{
    var compatibility = "";
    $(":checked").each(function(i){ 
    	compatibility += $(this).val() + ",";
    }); 
    compatibility = compatibility.substring(0,compatibility.lastIndexOf(","));
    $("#hidden_selectValue").val(compatibility);
}
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
	table tr td{
		border-bottom:1px solid black;
		text-align: center;
	}
</style>
</head>
<body>
<div class="wrap" id="center">
	<div class="header" style="background-color:#0c3365;">
		<h1 class="header_style"> InforPub v6.0</h1>
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
						<li class="erji"><a href="javascript：void(0)" onclick="resetpassword();return false;">重设密码</a></li>
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
<div align="center" style="background-color:#d5f3f4;width:100%;">
	<div class="themControl" >
		<div  class="themControl_left" style="font-size:20px;">
			<span class="themShow">未批准【<span class="checkoutitems">${totalRecord }</span>条】</span>
		</div>
	</div>	
	<c:if test="${totalRecord == 0 }">
				<div align="center" style="font-size:20px;font-weight: bold;height:100px;">暂时没有未批准条目</div>
				<div align="center">
				<input name="btnCancelApply" id="btnCancelApply" type="button" value="返回主页" onclick="window.location='MccListAction';"
                    			class="button button-pill button-primary" >
			 </div>
			</c:if>
	<c:if test="${totalRecord > 0 }">
	<div style="clear:both;"></div>
	<form action="batchApproveResultActionpc" method="post" onsubmit="checkoutValues();"><!-- batchApproveResultActionpc -->
	<div align="center">
		<table style="border-collapse:collapse;" id='approveList'>
				<thead>
				<tr style="background-color:RGB(144,238,144);font-family: '宋体';
              	color: black;font-weight: bolder;height:2rem;line-height:1.5rem;vertical-align: middle;font-size:1rem;"> 
					<th style="width: 10%;padding:0.2rem 0.1rem;">申请号</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">产品</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">机床编号</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">控制器号</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">控制卡号</th>
					<th style="width: 15%;padding:0.2rem 0.1rem;">事务所</th>
					<th style="width: 15%;padding:0.2rem 0.1rem;">客户名</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">密码状态</th>
					<th style="width: 10%;padding:0.2rem 0.1rem;">结束日期</th>
				</tr>
			</thead>
			<tbody>
			<%! int index=1; %>
			<s:iterator value="#request.mmList " id="m">
			<tr title="<s:property />" class="item"
				onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';"  
			 	onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" 
			 	style="padding:5px;height:25px;font-size:15px;line-height:20px;">
				<td>
			      <input type='checkbox' name='chk_select<%=index++ %>' value='${apply_no }' /> ${apply_no }
			    </td>
                <td>${controller_ver}</td>
                <td>${machine_no}</td>
                <td style="color:red;">${controller_no}</td>
                <td>${card_no}</td>
                <td style="white-space: normal;">${company_name}</td>
                <td>${cust_company_name}</td>
                <td align="center" style="white-space:nowrap;">待批准 </td>
                <c:set var="expirestate" value="${enddate }"></c:set>
                <c:choose>
                	<c:when test="${is_forever eq 'Y' }">
	                		<c:set var="expirestate" value="永久密码"></c:set>
	                </c:when>
                	<c:when test="${fn:contains(expirestate,'-') }">
                		<c:set var="expirestate" value="${fn:substring(expirestate,2,10) }"></c:set>
                	</c:when>
                </c:choose>
                <td style="color:red;">${expirestate }</td> 
		</tr>
		</s:iterator>
		<tr style="height:30px;"></tr>
		<tr style="margin-top: 30px;">
			<th colspan="2" align="center" style="font-size: 20px;">批准批注</th>
			<td align="left" colspan="7" style="border-collapse: separate;border:none;padding:0.2rem 0.1rem;">
				<textarea id="ftxt_memo" name="ftxt_memo" draggable="false" rows="4" 
                        		style="border:1px solid red;width:80%;padding:0.5rem;resize:none;border-style: ridge;font-family: ‘宋体’;font-size: 1rem;"></TEXTAREA>
			</td>
		</tr>
		<tr style="height:10px;"></tr>
	</tbody>
</table>
	<div align="left" >
        	<span>
        		<input name="chk_SelectAll" id="chk_SelectAll" type="checkbox" value="全选" 
                    			style="font-size: 50px;width:50px;" >
        		<input name="btn_selectAll" id="btn_selectAll" type="button" value="全选" 
                    			class="button button-pill button-primary" >
        	</span>
        	<input name="btn_unSelect" id="btn_unSelect" type="button" value="全不选" 
                    			class="button button-pill button-primary" >
        </div>
        <div align="right" style="padding-right:20px;">
        	<input name="btn_submit" id="btn_submit" type="submit" value="批准" 
                    			class="button button-pill button-primary" >
		 	<input type="hidden" value="" id="hidden_selectValue" name="hidden_selectValue">
		 	<input name="btnCancelApply" id="btnCancelApply" type="button" value="返回主页" onclick="window.location='MccListAction';"
                   			class="button button-pill button-primary" >
		</div>
		<div style="height: 20px;"></div>
	</div>
	</form>
	</c:if>
</div>
	<div style=";margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	</div>
</div>
</body>
</html>
