<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>批量批准 - InfoPub v6.0</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" href="bootstrap/css/rwd-table.min.css">
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/rwd-table.js"></script>
<script type="text/javascript">
$(function () { 
    $("#select_button").click(function(){    
        if(this.checked){    
            $("#approveList :checkbox").attr("checked", true);   
        }else{    
            $("#approveList :checkbox").attr("checked", false); 
        }    
     });  
    $("#approveList :checkbox").click(function(){ 
        allchk(); 
    }); 
    $(".table-responsive table tr").each(function(){    
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
        $("#select_button").attr("checked",true); 
    }else{
        $("#select_button").attr("checked",false); 
    } 
}
function checkoutValues()
{
	var compatibility = "",
    input = document.getElementsByTagName("input");
	for (var i = 0; i < input.length; i++) {
	    if (input[i].type == "checkbox") {
	        if (input[i].checked) {
	            value = input[i].value;
	            compatibility += value + ",";
	        }
	    }
	}
	compatibility = compatibility.substring(0,compatibility.lastIndexOf(","));
	alert(compatibility);
	$("#hidden_selectValue").val(compatibility);
    alert($("#hidden_selectValue").val())
	//return compatibility;
    
	/* var valArr = new Array; 
    $("#approveList :checkbox[checked]").each(function(i){ 
    	var checkboxval=$(this).val(); 
    		 valArr[i] = checkboxval; 
    }); 
    var vals = valArr.join(','); 
    vals=vals.replace(/(^\s+)|(\s+$)/g, "");
    if(vals.charAt(0)==",") vals=vals.substr(1);
    $("#hidden_selectValue").val(vals);
    alert($("#hidden_selectValue").val());  */
}
</script>
<style type="text/css">
.checkoutitems {
	display:inline-block;
    font-size: 20px;
    color: #ff9900;
    letter-spacing: 2px;
    margin: 10px 5px;
    background-color: rgba(255, 255, 255, 0.9);
}
table{
	width:100%;
}
table tr th,table tr td{
	text-align:center;
	valign:center;
	padding:0px 0px;
}
</style>
</head>
<body style="background-color:#d5f3f4;">
<div class="wrap" id="center" style="margin:0 3px;">
	<div align="left" style="padding-top:1px;padding-left:5px;padding-bottom:1px;
		font-weight: bold;font-style: italic;font-size:20px;height:20px;line-height: 20px;text-align: center;">
			Information Public System v6.0
	</div>
	<div style="float:right;padding-top:10px;font-size: 20px;clear:right;">
		<div style="color: red;padding-right:10px;display:inline-block;">${user.username}</div>
	</div>
	<div>【<span class="checkoutitems">${totalRecord }</span>条】</div>
	<c:if test="${totalRecord == 0 }">
		<div align="center" style="font-size:20px;font-weight: bold;margin:100px;">暂时没有未批准条目</div>
		<div align="center">
			<input name="btnExit" id="btnExit" type="button" value="返回主页" 
                class="button button-pill button-primary" 
                style="width:7em;height:2em;text-align: left;padding-left:25px;" onClick="window.location='mccpassword_m';">
    </div>
  </c:if>
  <c:if test="${totalRecord > 0 }"><!--  -->
    <div class="container" style="padding: 0 0;margin-top:20px;width:100%;">
      <form action="batchApproveResultAction" method="post" onsubmit="checkoutValues();"><!-- batchApproveResultAction -->
        <div class="table-responsive" data-pattern="priority-columns" >
          <table class='table table-bordered' id="approveList">
            <thead>
              <tr style="background-color:RGB(144,238,144);font-family: '宋体';
              	color: black;font-weight: bolder;height:4rem;line-height:3rem;vertical-align: middle;font-size:2rem;">
                <th style="width: 10%;padding:0.2rem 0.1rem;"><input type="checkbox" value="-1" id='select_button' name="select_button"/></th>
                <th style="min-width: 25%;padding:0.2rem 0.1rem;">控制器</th>
                <th style="min-width: 46%;padding:0.2rem 0.1rem;">客户</th>
                <th style="width: 6%;padding:0.2rem 0.1rem;">状态</th>
                <th style="width: 13%;padding:0.2rem 0.1rem;">结束日期</th>
              </tr>
            </thead>
            <tbody>
              <%! int index=0; %>
              <s:iterator value="#request.mmList " id="m">
                <tr title="<s:property/>" class="item">
                  <td align="center" style="font-size:1.6rem;"><input type='checkbox' id='chk_select<%=index %>' name='chk_select<%=index %>'  value='${apply_no }'/></td>
                  <td align="center" style="padding:0.2rem 0.1rem;vertical-align: middle;color:blue;">${controller_no}</td>
                  <td align="center" style="white-space:normal;padding:0.2rem 0.1rem;vertical-align: middle;">${cust_company_name}</td>
                  <td style="color:red;"><c:if test="${state eq 'Y'}">√</c:if>
                    <c:if test="${state eq 'N'}">×</c:if>
                    <c:if test="${state eq 'P'}">?</c:if></td>
                  <c:set var="expirestate" value="${enddate }">
                    </c:set>
                  <c:choose>
                    <c:when test="${is_forever eq 'Y' }">
                      <c:set var="expirestate" value="永久密码">
                        </c:set>
                    </c:when>
                    <c:when test="${fn:contains(expirestate,'-') }">
                      <c:set var="expirestate" value="${fn:substring(expirestate,2,10) }">
                        </c:set>
                    </c:when>
                  </c:choose>
                  <c:set var="fontcolor" scope="page" value="black"/>
                  <td style="font-size: 14px;">${expirestate }</td>
                  <% index++; %>
                </tr>
              </s:iterator>
              <tr style="width:60px;">
                <th colspan="2" align="center" style="padding:0.2rem 0.1rem;vertical-align: middle;font-size:2rem;">批准批注</th>
                <td colspan="3" style="padding:0.2rem 0.1rem;vertical-align: middle;"><textarea id="ftxt_memo" name="ftxt_memo" draggable="false" rows="4"
                        		style="width:95%;resize:none;border:1px solid red;border-style: ridge;font-family: ‘宋体’;font-size: 13px;"></TEXTAREA></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div align="center">
          <input id="btn_submit" type="submit" value="批准" style="width: 100px;height:40px;">
          &nbsp;&nbsp;
          <input type="hidden" value="" id="hidden_selectValue" name="hidden_selectValue">
          <input type="button" value="返回" style="width: 100px;height:40px;font-size:15px;" onclick="window.location='mccpassword_m';">
        </div>
        <div style="height:20px;"></div>
      </form>
    </div>
  </c:if>
</div>
</body>
</html>
