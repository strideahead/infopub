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
<title>日志列表 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/table.css" rel="stylesheet"/>  
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script type="text/javascript">
   function change(){
       pageSize=$("#pageSize").val();
       window.location.href="showSyslogs?pageSize="+pageSize;
   }
   function deleteAlert(gguid,company_name)
   {
       if (window.confirm("删除是不可恢复的，你确认要删除吗？\n删除公司是" + company_name)) {
           window.location = "companyDel?gguid="+gguid;
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
	font-size:15px;
	padding:2px;
	text-align:center;
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
                        	<li class="erji"><a href="showSyslogs">日志管理</a></li>
                            <li class="erji"><a href="showCompanies">公司管理</a></li>
                            <li class="erji"><a href="showUsers">用户管理</a></li>
                            <li class="erji"><a href="showVersions">版本管理</a></li>
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
            <div  class="themControl_right">
                <form id="MccForm" class="MccForm" action="showSyslogs">
                    <s:text name="lbl_loguser_name" >姓名:</s:text>&nbsp;
					<s:select name="ddl_log_user" id="ddl_log_user"  list="#request.loguserList" listKey="logusername1" 
						listValue="logusername1" value="#request.loguser_search"  headerValue="--所有---" headerKey="-1" theme="simple" style="width:150px;"></s:select>&nbsp;
					<s:text name="lbl_loguser_type" >日志类型:</s:text>&nbsp;
					<s:select name="ddl_loguser_type" id="ddl_log_user"  list="#request.logtypeList" listKey="log_type" 
						listValue="log_type_name" value="#request.logtype_search"  headerValue="--所有--" headerKey="-1" theme="simple" style="width:150px;"></s:select>&nbsp;
                	<s:submit id="btnQuery" value="查询" class="btn1" theme="simple"/>
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
        <div class="table_content" style="clear:both">
            <table id="listCompany" style="color:#333333;width:100%;border-collapse:separate;font-size:18px;padding:2px;">
            <thead>
			<tr style="color:White;background-color:#5D7B9D;font-weight:bold;white-space:nowrap;">
                    <th style="width: 100px;">姓名</th>
					<th style="width: 200px;">日志类型</th>
					<th style="width: 600px;">系统日志</th>
					<th style="width: 150px;">时间</th>
                </tr>
                </thead>
                <%
					int fromIndex=1;
                 	String color="#F7F6F3";
				%>
                <s:iterator value="#request.result.dataList " id="m">
                <%
                	
               		if(fromIndex%2==0) color="#fff";
               		else color="#ccc";
                	fromIndex++;
                %>
                <tr onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" 
					onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" 
					style="font-size:13px;font-family:'宋体';color:#333333;background-color:<%=color %>;">
					<td align="left" style="white-space:nowrap;">${log_user}</td>
	                <td align="left" class="th1">
	                	<c:if test="${log_type eq '1'}">登录系统</c:if>
						<c:if test="${log_type eq '2'}">资料</c:if>
						<c:if test="${log_type eq '3'}">投诉单</c:if>
						<c:if test="${log_type eq '4'}">论坛</c:if>
		                <c:if test="${log_type eq '5'}">升级包</c:if>
		                <c:if test="${log_type eq '6'}">机床/控制器/控制卡/密码</c:if>
	                </td>
	                <td align="left" style="text-align:left;">${log_memo }</td>
	                <c:set var="log_times" value="${fn:substring(log_time,0,19) }"></c:set>
	                <td align="left">${log_times }</td>
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
        	<a class="hrefLink" href="showSyslogs?pageNum=1">首页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showSyslogs?pageNum=<%=nextpage %>">下一页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showSyslogs?pageNum=<%=prevpage %>">上一页</a>&nbsp;&nbsp;
					<a class="hrefLink" href="showSyslogs?pageNum=<%=totalpage %>">尾页</a>&nbsp;&nbsp;
					共&nbsp;${result.totalRecord }&nbsp;条记录&nbsp;&nbsp;共&nbsp;${result.totalPage }&nbsp;页&nbsp;&nbsp;当前第&nbsp;${result.currentPage }&nbsp;页&nbsp;&nbsp;
					&nbsp;&nbsp;每页显示记录：
					<s:select id="pageSize" name="pageSize" label="选择每页显示数量：" title="选择每页显示数量" list="{'15','40','80','160','320','500'}" theme="simple"  headerKey="-1" headerValue="请选择显示数量"
	 				value="%{#request.pagesize_str }" onchange="change();"/>
        </div>
        </c:if>
		<div style="margin-top:10px;margin-bottom: 10px;line-height: 30px;font-family: '宋体';clear:left;">
			<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 		</div>
    </div>
    <script type="text/javascript" lang="javascript">setTimeout("window.top.location='logout';", 1200000);</script>
</body>
</html>
