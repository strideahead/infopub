<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/table.css" rel="stylesheet"/>  
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script type="text/javascript">
function change(){
    pageSize=$("#pageSize").val();
    window.location.href="showUsers?pageSize="+pageSize;
}
function deleteAlert(id,username)
{
    if (window.confirm("删除是不可恢复的，你确认要删除吗？\n删除用户是" + username)) {
        window.location = "deleteUserInfo?userid="+id;
        return true;
    } else {
        return false;
    }
}
function resetpassword()
{
	window.open('resetPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no');
}
</script>
<style type="text/css">
table td,table th{
	font-family:Arial,Helvetica, Verdana, Georgia, Comic Sans;
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
                    <li><a href="#">管理员管理<span class="arrow"></span></a>
                        <ul class="erj_ui">
                        	<li class="erji"><a href="showUsers">用户管理</a></li>
                            <li class="erji"><a href="showCompanies">公司管理</a></li>
                            <li class="erji"><a href="showVersions">版本管理</a></li>
                            <li class="erji"><a href="showSyslogs">日志管理</a></li>
                        </ul>
                    </li>
                    <li><a href="#">${user.username }<span class="arrow"></span></a>
                        <ul class="erj_ui">
                            <li class="erji"><a href="#" onclick="resetpassword();return false;">重设密码</a></li>
                            <li class="erji"><a href="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="themControl">
        	<div class="themControl_left">
        		<input name="btn_add_newuser" id="btn_add_newuser" type="button" onclick="window.location.href='addUserInfo';" 
        				value="添加新用户" style="width:100px;" class="btn1"/>                 
        	</div>
            <div  class="themControl_right">
                <form id="MccForm" class="MccForm" action="showUsers">
                    <span id="lblKeyword">关键字</span>:&nbsp;
                    <input type="text" name="txtKeyword" id="txtKeyword" maxlength="20" value="${keyStr }"/>&nbsp;&nbsp;                    
                    &nbsp;<s:submit id="btnQuery" value="查询" class="btn1" theme="simple"/>
                </form>
            </div>
        </div>
		<div class="table_content" style="clear:both">
			<c:if test="${fn:length(result.dataList) eq 0 }">
				<div class="table_content" style="clear:both">
					<div style="background-image: './images/prompt.gif';height:30px;width:880px;">
						<span id="gridEmpty" class="prompt" style="display:inline-block;height:24px;width:880px;">暂无纪录</span>
					</div>
				</div>
			</c:if>
		</div>
		<c:if test="${fn:length(result.dataList) gt 0 }">
        <div class="table_content" style="clear:both;word-wrap:break-word;">
            <table id="listUser" style="word-break:break-all;color:#333333;width:100%;border-collapse:separate;table-layout: fixed;">
                <thead>
                <tr style="color:White;font-size:16px;;background-color:#5D7B9D;font-weight:bold;white-space:normal;">
					<th width="40px">No.</th>
					<th width="40px">Id</th>
					<th width="100px">用户名</th>
					<th width="100px">姓名</th>
					<th width="150px">用户权限</th>
					<th width="160px">Email</th>
					<th width="80px">移动电话</th>
					<th width="70px">密码类型</th>
					<th width="180px">所属公司</th>
					<th width="60px">有效性</th>
					<th width="60px">操作</th>
                </tr>
               
                </thead>
                 <%
					int fromIndex=(int)request.getAttribute("fromIndex");
                 	String color="#F7F6F3";
				%>
                <s:iterator value="#request.result.dataList " id="m">
                	<%
                		if(fromIndex%2==0) color="#fff";
                		else color="#ccc";
                	%>
                    <tr onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" 
				onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" 
				style="font-size:13px;font-family:'宋体';color:#333333;background-color:<%=color%>;">
				
				<td align="center" style="border:1px dotted #76EE00;white-space:nowrap;color:red;">
                	<%=String.format("%03d",fromIndex++) %>
                </td>
                <td align="center" style="white-space:nowrap;">${id}</td>
                <td align="center" style="white-space:nowrap;">${usercode}</td>
                <td align="center">${username }</td>
                <td align="center" style="word-wrap:break-word;">
                	<c:if test="${permission eq 'C'}">信息查看权限</c:if>
					<c:if test="${permission eq 'B'}">数据编辑权限</c:if>
					<c:if test="${permission eq 'A'}">帐号管理、菜单管理、类别管理、数据管理</c:if>
					<c:if test="${permission eq '0'}">帐号管理、菜单管理、类别管理、数据管理、付款状态编辑</c:if>
                </td>
                <td align="center">${email }</td>
                <td align="center">${mobile }</td>
                <td align="center" style="white-space:nowrap;">
                	<c:if test="${password_type eq '1'}">固定密码</c:if>
					<c:if test="${password_type eq '2'}">动态密码</c:if>
                </td>
                <td align="center" style="word-break:break-all; word-wrap:break-word;">${company_name }</td>
                <td align="center" style="white-space:nowrap;">
                	<c:if test="${isvalid eq 'Y'}"><label style="color:green;">有效</label></c:if>
					<c:if test="${isvalid eq 'N'}"><label style="color:red;">无效</label></c:if>
                </td>
                <td align="center" style="white-space:nowrap;">
                    <table>
                        <tr> 
                        	<td>
                            	<a href="editUserInfo?userid=${id }&usercode=${usercode}"><img src="images/edit.gif" title="编辑" alt="编辑"/></a>  
                       		</td> 
                       		<td>
                            	<a href=""><img src="images/resetPassword.png" hidden="true" title="重设密码" alt="重设密码"/></a>  
                       		</td>                                         		
                            <td>
								<img name="PwdDelete" id="PwdDelete" onclick="deleteAlert(${id},'${usercode }');"
										 src="images/delete.gif" title="删除" />
                                <%-- <a href="deleteUserInfo?userid=${id }&usercode=${usercode}">
                                	<input type="image" name="PwdCheck" id="PwdCheck" title="删除" src="images/delete.gif" />
                                </a> --%>
                            </td>
                        </tr>
                    </table>
                </td>
			</tr>
                </s:iterator>
            </table>
        </div>
        <div style="padding-top:20px;">
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
        	<a class="hrefLink" href="showUsers?pageNum=1" onclick=";">首页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showUsers?pageNum=<%=nextpage %>" onclick=";">下一页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showUsers?pageNum=<%=prevpage %>" onclick=";">上一页</a>&nbsp;&nbsp;
					<a class="hrefLink" href="showUsers?pageNum=<%=totalpage %>" onclick=";">尾页</a>&nbsp;&nbsp;
					共&nbsp;${result.totalRecord }&nbsp;条记录&nbsp;&nbsp;共&nbsp;${result.totalPage }&nbsp;页&nbsp;&nbsp;当前第&nbsp;${result.currentPage }&nbsp;页&nbsp;&nbsp;
					&nbsp;&nbsp;每页显示记录：
					<s:select id="pageSize" name="pageSize" label="选择每页显示数量：" title="选择每页显示数量" list="{'10','20','40','80','160','320'}" theme="simple"  headerKey="-1" headerValue="请选择显示数量"
	 				value="%{#request.currentPageSize }" onchange="change();"/>
        </div>
        </c:if>
       <div style=";margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
		  <a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 	   </div>
    </div>
    &nbsp;
    <script type="text/javascript" lang="javascript">setTimeout("window.top.location='logout';", 1200000);</script>
</body>
</html>
