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
<title>版本列表 - InfoPub v6.0</title>
<link href="images/favicon.ico" rel="shortcut icon">
	<link href="css/main.css" rel="stylesheet"/>
    <link href="css/pageControl.css" rel="stylesheet" />
    <link href="css/button.css"  rel="stylesheet" />
    <script src="js/jquery-3.0.0.js"></script>
<script>
function change(){
	var pageSize=$("#pageSize").val();
	if(parseInt(pageSize) != -1){
		window.location.href="showVersions?pageSize="+pageSize;
	}
	return false;
}
function deleteAlert(gguid,version_name)
{
	if (window.confirm("删除是不可恢复的，你确认要删除吗？\n删除版本是" + version_name)) {
		window.location = "deleteVersionInfo?versiongguid="+gguid;
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
                        	<li class="erji"><a href="showVersions">版本管理</a></li>
                            <li class="erji"><a href="showCompanies">公司管理</a></li>
                            <li class="erji"><a href="showUsers">用户管理</a></li>
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
        		<input name="btn_add_newversion" id="btn_add_newversion" type="button" onclick="window.location.href='addNewVersion.jsp';" 
        		value="添加新版本" style="width:100px;" class="btn1"/>                                  
        	</div>
            <div  class="themControl_right">
                <form id="MccForm" class="MccForm" action="showVersions">
                    <input type="text" name="versionKeystr" id="versionKeystr" maxlength="15" value="${versionkeyStr }"/>                  
                    <s:select list="#{'Soft':'Soft','Hard':'Hard'}"  headerValue="<is_soft>" headerKey="-1" 
                   	 name="issoft_type" id="issoft_type" value="%{#request.issoft_type }" theme="simple"/>
                	<s:select list="#{'No':'No','Yes':'Yes'}"  headerValue="<发布版本>" headerKey="-1" 
                		name="ispublish_type" id="ispublish_type" value="%{#request.ispublish_type }" theme="simple"/>
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
            <table id="listVersion" style="color:#333333;width:80%;border-collapse:separate;margin:0 auto">
            <thead>
			<tr style="color:White;background-color:#5D7B9D;font-weight:bold;white-space:nowrap;">
                    <th style="width: 80px;">No.</th>
				<th style="width: 150px;">版本名称</th>
				<th style="width: 150px;">是否为软件版本</th>
				<th style="width: 150px;">是否为用户发布版本</th>
				<th style="width: 100px;">操作</th>
			</tr>
                </thead>
                 <%
					int fromIndex=(int)request.getAttribute("fromIndex");
				%>
				<s:iterator value="#request.result.dataList " id="m">
                    <tr align="center" onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" 
				onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" 
				style="font-size:13px;font-family:'宋体';color:#333333;background-color:#F7F6F3;al">
				<td align="center" style="border:1px dotted #76EE00;white-space:nowrap;">
                	<%=String.format("%03d",fromIndex++) %>
                </td>
                <td align="center" style="white-space:nowrap;">${version_name}</td>
                <td align="center">${is_soft }</td>
                <td align="center">${is_publish }</td>                
                <td align="center" style="white-space:nowrap;" class="th1">
                    <table>
                        <tr> 
                        	<td>
                            	<a href="editVersionInfo?version_gguid=${gguid }"><img src="images/edit.gif" title="编辑" alt="编辑"/></a>  
                       		</td>                                        		
                            <td>
								<img name="VersionDelete" id="VersionDelete" onclick="deleteAlert('${gguid}','${version_name }');"
										 src="images/delete.gif" title="删除" />
                            </td>
                        </tr>
                    </table>
                </td>
			</tr>
            </s:iterator>
            <tr align="center">
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
	            <td colspan="10">
					<a class="hrefLink" href="showVersions?pageNum=1">首页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showVersions?pageNum=<%=nextpage %>">下一页</a>&nbsp;&nbsp; 
					<a class="hrefLink" href="showVersions?pageNum=<%=prevpage %>">上一页</a>&nbsp;&nbsp;
					<a class="hrefLink" href="showVersions?pageNum=<%=totalpage %>">尾页</a>&nbsp;&nbsp;
					共&nbsp;${result.totalRecord }&nbsp;条记录&nbsp;&nbsp;共&nbsp;${result.totalPage }&nbsp;页&nbsp;&nbsp;当前第&nbsp;${result.currentPage }&nbsp;页&nbsp;&nbsp;
					&nbsp;&nbsp;每页显示记录：
					<s:select id="pageSize" name="pageSize" label="选择每页显示数量：" title="选择每页显示数量" list="{'15','25','40','80','160'}" theme="simple"  headerKey="-1" headerValue="请选择显示数量"
	 				value="%{#request.pagesize_str }" onchange="change();"/>
	 			</td>
            </tr>
            </table>
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
