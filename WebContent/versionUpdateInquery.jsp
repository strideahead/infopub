<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Information Public System v5.0</title>
    <script src="lib/jquery-1.6.1.min.js" type="text/javascript"></script>
    <link href="lib/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="lib/jquery-ui-1.8.4-resource.js" type="text/javascript"></script>   
    <link href="lib/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery.autocomplete.pack.js" type="text/javascript"></script>    
    <script src="lib/utils.js" type="text/javascript"></script>
    <link href="style/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="aspnetForm" method="post" action="version_list.aspx" onkeypress="javascript:return WebForm_FireDefaultButton(event, 'btnQuery_password')" id="aspnetForm">    
    <div align="center">
        <table style="width: 1001px; table-layout: fixed;" cellspacing="0" cellpadding="0" border="0">
            <tr style="width: 1400px; background-color: #446D6B">
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
                                <input name="btnReturnFront" type="button" id="btnReturnFront" class="btn1" value="返回首页" style="width: 100px;" onclick="window.location='MccListAction';" />
                                <input name="btnMCC" type="button" id="btnMCC" class="btn1" value="机床/控制器/控制卡/密码/Ez" style="width: 200px" onclick="window.location='MccListAction';" />
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
    <div id="pnlVersionPasswordList">
        <table style="width: 995px" align="center">
            <tr>
                <td style="height: 25px;" align="left" background="images/loginbg.jpg" colspan="2">
                    <span id="lblHeader_password" style="font-size:Large;font-weight: bolder; color: dimgray;">密码查询</span><span id="lblCount_password" style="font-size:Large;font-weight: bolder;
                            color: dimgray;">【1条】</span>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="2">
                    <span id="lblMachineInfo" style="color: dimgray;">产品:<font color=blue>TR40C</font>&nbsp;机床编号:<font color=blue></font>&nbsp;机床大类:<font color=blue></font>&nbsp;机床型号:<font color=blue></font>&nbsp;控制器号:<font color=blue>LY00069</font>&nbsp;控制卡号:<font color=blue>S13010020007</font>&nbsp;事务所:<font color=blue>Lynuc</font>&nbsp;客户名:<font color=blue>上海莱必泰数控机床股份有限公司</font></span>
                </td>
            </tr>
            <tr>
                <td align="right" colspan="2">
                    <span id="lblKeyword_password">关键字</span>:&nbsp;<input name="txtKeyword_password" type="text" id="txtKeyword_password" style="width:100px;" />
                    
                    <select name="ddlState_password" id="ddlState_password">
						<option selected="selected" value="">&lt;状态&gt;</option>
						<option value="Y">已批准</option>
						<option value="N">拒绝</option>
						<option value="P">待批准</option>
					</select>
                    <select name="ddlPeriod_password" id="ddlPeriod_password">
						<option selected="selected" value="">&lt;间隔&gt;</option>
						<option value="expired">已过期</option>
						<option value="expire_in_1_month">一月内过期</option>
						<option value="expire_in_3_month">三月内过期</option>
						<option value="expire_in_more_3_month">超过三月过期</option>
					</select>
                    <input type="submit" name="btnQuery_password" value="查询" id="btnQuery_password" class="btn1" />
                </td>
            </tr>
            <tr>
                <td style="width: 995px" align="center" colspan="2">
                    <div>
		<table cellspacing="0" cellpadding="4" align="Center" border="0" id="listVersionPassword" style="color:#333333;width:990px;border-collapse:collapse;">
			<tr style="color:White;background-color:#5D7B9D;font-weight:bold;white-space:nowrap;">
				<th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$apply_no')" style="color:White;">申请号</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$controller_no')" style="color:White;">控制器号</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$card_no')" style="color:White;">控制卡号</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$state')" style="color:White;">密码状态</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$soft_ver')" style="color:White;">软件版本</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$hard_ver')" style="color:White;">硬件版本</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$request_user')" style="color:White;">申请人</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$check_user')" style="color:White;">批准人</a></th><th align="left" scope="col" style="white-space:nowrap;"><a href="javascript:__doPostBack('listVersionPassword','Sort$check_time')" style="color:White;">批准时间</a></th><th align="center" scope="col" style="white-space:nowrap;">操作</th>
			</tr><tr title="版本升级密码:
74DBC0D6" onmouseover="this.OriginalBackgroundColor=this.style.backgroundColor; this.style.backgroundColor='gold';" onmouseout="this.style.backgroundColor=this.OriginalBackgroundColor;" style="color:#333333;background-color:#F7F6F3;">
				<td align="left" style="white-space:nowrap;">6300</td><td align="left" style="white-space:nowrap;">LY00069</td><td align="left" style="white-space:nowrap;">&nbsp;</td><td align="left" style="color:Green;white-space:nowrap;">已批准</td><td align="left" style="white-space:nowrap;">3.5.3.F1</td><td align="left" style="white-space:nowrap;">1.10.1.51</td><td align="left" style="white-space:nowrap;">刘海丰</td><td align="left" style="white-space:nowrap;">刘海丰</td><td title="2014-07-10 11:51:19" align="left" style="white-space:nowrap;">14-07-10</td><td align="center" style="white-space:nowrap;">
                                    <table cellpadding="1" cellspacing="1" border="0">
                                        <tr>
                                        <td>
                                            <a href="versionUpdatePwdDetail.jsp"><img src="images/edit.gif" title="查看
[版本升级密码]" alt="查看"/></a>  
                                       		</td>                                        
                                            <td>
                                                <input type="image" name="DeleteVersionPassword" id="listVersionPassword_ctl02_ibDeleteVersionPassword" disabled="disabled" title="撤销申请
[版本升级密码]" src="images/delete_disable.gif" style="color:Blue;border-width:0px;cursor:default;" />
                                            </td>
                                            <td>
                                                <input type="image" name="PwdCheckVersionPassword" id="listVersionPassword_ctl02_ibPwdCheckVersionPassword" disabled="disabled" title="批准/拒绝
[版本升级密码]" src="images/pwd_check_disable.gif" style="color:Blue;border-width:0px;cursor:default;" />
                                            </td>
                                        </tr>
                                    </table>
                                </td>
			</tr>
		</table>
	</div>
                </td>
            </tr>
            <tr>
                <td style="width: 995px" align="left" colspan="2">
                    <table width="100%" border="0">
                        <tr>
                            <td>                               
                            </td>
                            <td align="right">
                                <input type="submit" name="btnReturn" value="返回" id="btnReturn" class="btn1" />
                            </td>
                        </tr>
                    </table>
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
    </form>
</html>