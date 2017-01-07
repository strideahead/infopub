<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Information Public System v5.0</title>
	<link href="css/main.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery-ui-style.css" rel="stylesheet" type="text/css" />
    <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="js/jquery-ui.js" type="text/javascript"></script>
    <link href="js/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui-1.8.4-resource.js" type="text/javascript"></script>
    <link href="js/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery.autocomplete.pack.js" type="text/javascript"></script>
    <script src="js/utils.js" type="text/javascript"></script>
</head>
<body>
<form name="aspnetForm" method="post" action="version_list.aspx" onkeypress="javascript:return WebForm_FireDefaultButton(event, 'btnQuery')" id="aspnetForm">
    <div align="center">
        <table style="width: 1001px; table-layout: fixed;" cellspacing="0" cellpadding="0" border="0">
            <tr style="width: 1001px; background-color: #446D6B">
                <td style="height: 60px;" align="left" nowrap>
                    <h1>
                        <font color="white">&nbsp;Information Public System v5.0</font>
                    </h1>
                </td>               
                <td style="height: 46px;" valign="bottom" align="right" nowrap>
                    <input name="EnterAdmin" type="button" id="EnterAdmin" class="btn1" value=">>进入管理页面" onclick="top.location='admin/AdminPage.htm';" style="width: 130px" />&nbsp;
                </td>
            </tr>
            <tr valign="middle" style="width: 1024px">
                <td style="width: 1001px" colspan="2">
                    <table style="width: 1001px;" cellspacing="0" cellpadding="0" border="0">
                        <tr align="center">
                            <td background="images/fon03.gif" style="height: 35px;" align="left" nowrap valign="middle">
                                &nbsp;
                                <span id="lblUser" style="display:inline-block;height:24px;">${user.usercode}&nbsp;${user.username}</span>
                            </td>
                            <td background="images/fon03.gif" align="right" style="height: 27px;" nowrap>
                                <input name="btnReturnFront" type="button" id="btnReturnFront" class="btn1" value="返回首页" style="width: 100px;" onclick="window.location='maincontrol.jsp';" />
                                <input name="btnMCC" type="button" id="btnMCC" class="btn1" value="机床/控制器/控制卡/密码/Ez" style="width: 200px" onclick="window.location='maincontrol.jsp';" />
                                <input name="btnVersion" type="button" id="btnVersion" class="btn1" value="版本升级密码" style="width: 100px" onclick="window.location='verUpdatePwd';" />
                                <input name="btnSetPassword" type="button" id="btnSetPassword" class="btn1" value="重设密码" style="width: 80px;" onclick="window.open('setPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no'); " />
                                <input name="btnExit" type="button" id="btnExit" class="btn1" value="退 出" style="width: 50px" onclick="window.location='login.jsp';" />&nbsp;
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="height: 3px" colspan="2">
                </td>
            </tr>
            <tr style="width: 1001px">
                <td style="border-left: solid thin gainsboro; border-right: solid thin gainsboro;
                    width: 1001px" id="master_content" colspan="2">
    
    <script src="lib/utils.js" type="text/javascript"></script>
    <div id="pnlMCCList">
	
        <table style="width: 995px" align="center">
            <tr>
                <td style="height: 25px;" align="left" background="images/loginbg.jpg" nowrap>
                    <span id="lblHeader"  class="themSpan">版本升级密码</span>
                    <span id="lblCount"  class="themSpan">【${verUpdateList.totalRecord }条】</span>
                </td>
                <td align="right">
                    <input type="submit" name="btnApplyBatch" value="批量申请" id="btnApplyBatch" class="btn1" style="width:120px;" />
                </td>
            </tr>
            <tr>
                <td style="height: 28px" align="left">                   
                </td>
                <td align="right">
                    <span id="lblKeyword">关键字</span>:&nbsp;<input name="txtKeyword" type="text" id="txtKeyword" style="width:100px;" />
                    <select name="ddlState" id="ddlState">
					<option selected="selected" value="">&lt;状态&gt;</option>
					<option value="Y">已批准</option>
					<option value="N">拒绝</option>
					<option value="P">待批准</option>
				</select>
                    <select name="ddlIsPay" id="ddlIsPay" disabled="disabled">
					<option selected="selected" value="">&lt;最新付款状态&gt;</option>
					<option value="N">未付</option>
					<option value="Y">已付</option>
				</select>
                    <select name="ddlObjectType" id="ddlObjectType">
					<option selected="selected" value="">&lt;所有&gt;</option>
					<option value="machine">机床</option>
					<option value="controller">控制器</option>
				</select>
                    <select name="ddlPeriod" id="ddlPeriod">
					<option selected="selected" value="">&lt;间隔&gt;</option>
					<option value="expired">已过期</option>
					<option value="expire_in_1_month">一月内过期</option>
					<option value="expire_in_3_month">三月内过期</option>
					<option value="expire_in_more_3_month">超过三月过期</option>
				</select>
                    <input type="submit" name="btnQuery" value="查询" id="btnQuery" class="btn1" />
                </td>
            </tr>
            <tr>
                <td style="width: 995px" align="center" colspan="2">
                    <div>
		<table cellspacing="0" cellpadding="4" align="Center" border="0" id="listMCC" style="color:#333333;width:990px;border-collapse:collapse;">
			<tr style="color:White;background-color:#5D7B9D;font-weight:bold;white-space:nowrap;">
				<th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$apply_no')" style="color:White;">申请号</a>
				</th>
				<th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$controller_ver')" style="color:White;">产品</a>
				</th>
				<th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$machine_no')" style="color:White;">机床编号</a>
				</th>
				<th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$machine_type')" style="color:White;">机床型号</a>
				</th>
				<th class="th1"><a href="javascript:__doPostBack('listMCC','Sort$controller_no')" style="color:White;">控制器号</a>
				</th>
				<th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$card_no')" style="color:White;">控制卡号</a></th>
				<th class="th1"><a href="javascript:__doPostBack('listMCC','Sort$sale_company_name')" style="color:White;">事务所</a></th><th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$cust_company_name')" style="color:White;">客户名</a></th><th class="th1"><a href="javascript:__doPostBack('listMCC','Sort$state')" style="color:White;">密码状态</a></th>
				<th class="th1"><a href="javascript:__doPostBack('listMCC','Sort$soft_ver')" style="color:White;">软件版本</a></th><th class="th1">
				<a href="javascript:__doPostBack('listMCC','Sort$hard_ver')" style="color:White;">硬件版本</a></th>
				<th align="center" scope="col" style="white-space:nowrap;">操作</th>
			</tr>
			<s:iterator value="#request.verUpdateList.dataList " id="m">
			<tr onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" style="color:#333333;background-color:#F7F6F3;">
				<td align="left" style="white-space:nowrap;">
							
                                <a id="lnkViewApply" href="versionUpdatePwdDetail?apply_no=${m.apply_no }">${m.apply_no }</a>
                                </td>
                                <td align="left" style="white-space:nowrap;">${m.controller_ver}</td>
                                <td align="left" style="white-space:nowrap;">${m.machine_no }</td>
                                <td align="left" style="white-space:nowrap;">${m.machine_type }</td>
                                <td align="left" style="white-space:nowrap;">${m.controller_no }</td>
                                <td align="left" style="white-space:nowrap;">${m.card_no }</td>
                                <td align="left">${m.company_name }</td>
                                <td align="left">${m.customer_name }</td>
                                <td align="left" style="color:${m.state_color};white-space:nowrap;">${m.state }</td>
                                <td align="left" style="white-space:nowrap;">&nbsp;${m.soft_ver }</td>
                                <td align="left" style="white-space:nowrap;">&nbsp;${m.hard_ver }</td>
                                <td align="center" style="white-space:nowrap;">
                                    <table cellpadding="1" cellspacing="1" border="0">
                                        <tr> 
                                        	<td>
                                            <a href="versionUpdateApply.jsp"><img src="images/add.gif" title="申请/撤销申请&#10;[版本升级密码]" alt="申请/撤销申请"/></a>  
                                       		</td>                                       		
                                            <td>
                                                <input type="image" name="PwdCheck" id="PwdCheck" disabled="disabled" title="查询&#10;[版本升级密码]" src="images/pwd_check_disable.gif" style="color:Blue;width:16px;border-width:0px;cursor:default;" />
                                            </td>
                                            <td>
                                            <a href="versionUpdateInquery.jsp"><img src="images/pwd_query.gif" title="查询&#10;[版本升级密码]" alt="查询"/></a>  
                                       		</td>
                                        </tr>
                                    </table>
                                </td>
			</tr>
			</s:iterator>
			<tr class="PagerCss" align="center" style="color:Red;background-color:#5D7B9D;">
				<td colspan="12"><table border="0">
					<tr>
						<td><span>1</span></td><td><a href="javascript:__doPostBack('listMCC','Page$2')" style="color:Red;">2</a></td><td><a href="javascript:__doPostBack('listMCC','Page$3')" style="color:Red;">3</a></td><td><a href="javascript:__doPostBack('listMCC','Page$4')" style="color:Red;">4</a></td><td><a href="javascript:__doPostBack('listMCC','Page$5')" style="color:Red;">5</a></td><td><a href="javascript:__doPostBack('listMCC','Page$6')" style="color:Red;">6</a></td><td><a href="javascript:__doPostBack('listMCC','Page$7')" style="color:Red;">7</a></td><td><a href="javascript:__doPostBack('listMCC','Page$8')" style="color:Red;">8</a></td><td><a href="javascript:__doPostBack('listMCC','Page$9')" style="color:Red;">9</a></td><td><a href="javascript:__doPostBack('listMCC','Page$10')" style="color:Red;">10</a></td><td><a href="javascript:__doPostBack('listMCC','Page$11')" style="color:Red;">...</a></td>
					</tr>
				</table></td>
			</tr>
		</table>
	</div>
                </td>
            </tr>
        </table>
</div>    
                </td>
            </tr>
            <tr style="width: 1001px">
                <td align="center" background="images/fon02.gif" style="height: 23px; width: 1001px"
                    colspan="2">
                    <span style="font-size: 10pt"></span>
                </td>
            </tr>
        </table>
    </div>
    &nbsp;
<script type="text/javascript" language="javascript">setTimeout("window.top.location='login.jsp';", 1200000);</script></form> 
</body>
</html>