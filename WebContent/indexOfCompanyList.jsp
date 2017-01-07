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
<title>公司列表 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/main.css" rel="stylesheet"/>
<link href="css/table.css" rel="stylesheet"/>  
<link href="css/pageControl.css"  rel="stylesheet" />
<link href="css/button.css"  rel="stylesheet" />
<script src="js/jquery-3.0.0.js"></script>
<script type="text/javascript">
   function change(){
       pageSize=$("#pageSize").val();
       window.location.href="showCompanies?pageSize="+pageSize;
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
		padding:2px;
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
                            <li class="erji"><a href="showCompanies">公司管理</a></li>
                            <li class="erji"><a href="showUsers">用户管理</a></li>
                            <li class="erji"><a href="showVersions">版本管理</a></li>
                            <li class="erji"><a href="showSyslogs">日志管理</a></li>
                        </ul>
                    </li>
                    <li><a href="#">${user.username }<span class="arrow"></span></a>
                        <ul class="erj_ui">
                            <li class="erji"><a href="#" onclick="resetpassword();return false;">重设密码</a></li>
                            <c:if test="${user.permission<'B'}">
                                <li class="erji">
                                    <a href="supplementary.jsp">修复模块</a>
                                </li>
                            </c:if>
                            <li class="erji"><a href="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="themControl">
        	<div class="themControl_left">
        		<input name="btn_add_newcompany" id="btn_add_newcompany" type="button" value="添加新公司" 
                		 onclick="window.location.href='addCompanyInfo';" style="width:100px;" class="btn1"/>
        	</div>
            <div  class="themControl_right">
                <form id="MccForm" class="MccForm" action="showCompanies">
                    <s:text name="lbl_searchKey" >关键字:</s:text>&nbsp;
                    <s:textfield label="关键字" name="companyKeystr" id="companyKeystr" size="20px" theme="simple" value="%{#request.keyStr }"></s:textfield>
                    &nbsp;&nbsp; <s:text name="lbl_companyType" >公司类型:</s:text>&nbsp;
                    <s:select name="company_type" id="company_type" list="#{'1':'客户','2':'事务所','3':'管理公司','4':'代理商'}" headerValue="<公司类型>" headerKey="-1" value="%{#request.company_type_str}" theme="simple" />
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
        <div class="table_content" style="clear:both">
            <table id="listCompany" style="color:#333333;width:100%;border-collapse:separate;
            	font-size:14px;font-family:'宋体';table-layout: fixed;word-break:break-all; word-wrap:break-word;">
                <thead>
                <tr style="color:White;background-color:#5D7B9D;
                	font-weight:bold;white-space:nowrap;font-size: 16px;line-height:20px;">
                    <th width="30px">No.</th>
                    <th width="250px">公司名称</th>
                    <th width="260px">公司地址</th>
                    <th width="100px">联系人</th>
                    <th width="100px">电话号码</th>
                    <th width="60px">邮编</th>
                    <th width="100px">传真</th>
                    <th width="70px">公司类型</th>
                    <th width="70px">操作</th>
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
				onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" style="background-color:<%=color %>;">
						<td width="5%" align="left" style="border-bottom:1px solid black;white-space:nowrap;">
                            <%=String.format("%03d",fromIndex++) %></td>
                        <td align="center" style="color:green;white-space:normal;">${company_name}</td>
                        <td align="center">${address }</td>
                        <td align="center">${contacter }</td>
                        <td align="center" style="color:green;">${tel }</td>
                        <td align="center" style="color:red;">${zip }</td>
                        <td align="center" style="white-space:nowrap;color:green;">${fax }</td>
                        <td align="center" style="white-space:nowrap;">${company_type }</td>
                        <td align="center" style="white-space:nowrap;">
                             <a href="editCompany?company_gguid=${gguid }"><img src="images/edit.gif" title="编辑" alt="编辑"/></a>
                             <img onclick="deleteAlert('${gguid}','${company_name }');" src="images/delete.gif" title="删除" />
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <div style="padding-top:20px;font-size:15px;">
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
        	<a class="hrefLink" href="showCompanies?pageNum=1">首页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showCompanies?pageNum=<%=nextpage %>">下一页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showCompanies?pageNum=<%=prevpage %>">上一页</a>&nbsp;&nbsp;
					<a class="hrefLink" href="showCompanies?pageNum=<%=totalpage %>">尾页</a>&nbsp;&nbsp;
					共&nbsp;${result.totalRecord }&nbsp;条记录&nbsp;&nbsp;共&nbsp;${result.totalPage }&nbsp;页&nbsp;&nbsp;当前第&nbsp;${result.currentPage }&nbsp;页&nbsp;&nbsp;
					&nbsp;&nbsp;每页显示：
					<s:select id="pageSize" name="pageSize" label="每页显示数量：" title="每页显示数量" list="{'15','40','80','160','320'}" 
						theme="simple"  headerKey="-1" headerValue="显示数量"
	 				value="%{#request.pagesize_str }" onchange="change();"/>
        </div>
        </c:if>
        <div style="margin-top:20px;margin-bottom: 10px;line-height: 40px;font-family: '宋体';clear:left;">
			<a href="http://www.lynuc.cn" target="_blank">上海铼钠克数控科技股份有限公司</a>&nbsp;&nbsp;地址：上海市徐汇区平福路279号&nbsp;&nbsp;电话：021-61837766
 		</div>
    </div>
    <script type="text/javascript" lang="javascript">setTimeout("window.top.location='logout';", 1200000);</script>
</body>
</html>
