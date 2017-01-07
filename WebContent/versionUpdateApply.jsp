<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Information Public System v5.0</title>
</head>
<body>
<form name="aspnetForm" method="post" action="version_list.aspx" onkeypress="javascript:return WebForm_FireDefaultButton(event, 'btnQuery')" id="aspnetForm">
    <div align="center">
        <table style="width: 1001px; table-layout: fixed;" cellspacing="0" cellpadding="0" border="0">
            <tr style="width: 1001px; background-color: #446D6B">
                <td style="height: 46px;" align="left" nowrap>
                    <h1>
                        <font color="white">&nbsp;Information Public System v5.0</font></h1>
                </td>
			<c:if test="${user.permission<'B'}">
                <td style="height: 46px;" valign="bottom" align="right" nowrap>
					<input class="enterAdmin" id="EnterAdmin" name="EnterAdmin" type="button"  value=">>进入管理页面"  onclick="top.location='admin/AdminPage.htm';" width="130px" />
       		 </td>
       		 </c:if>
            </tr>
            <tr valign="middle" style="width: 1024px">
                <td style="width: 1001px" colspan="2">
                    <table style="width: 1001px;" cellspacing="0" cellpadding="0" border="0">
                        <tr>
                            <td background="images/fon03.gif" style="height: 27px;" align="left" nowrap valign="middle">
                                &nbsp;
                                <span id="lblUser" style="display:inline-block;height:24px;">${user.usercode}&nbsp;${user.username}</span>
                            </td>
                            <td background="images/fon03.gif" align="right" style="height: 27px;" nowrap>
                                <input name="btnExit" type="button" id="btnExit" class="btn1" value="退 出" style="width: 50px" onClick="window.location='login.jsp';" />
								<input name="btnSetPassword" type="button" id="btnSetPassword" class="btn1" value="重设密码"  onclick="window.open('setPassword.jsp','','height=230, width=400, top=250,left=360,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no'); " />
								<input name="btnVersion" type="button" id="btnVersion" class="btn1" value="版本升级密码"  onclick="window.location='verUpdatePwd';" />
								<input name="btnMCC" type="button" id="btnMCC" class="btn1" value="机床/控制器/控制卡/密码/Ez"  onclick="window.location='MccListAction';" />
								<input name="btnReturnFront" type="button" id="btnReturnFront" class="btn1" value="返回首页"  onclick="window.location='MccListAction';" />
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
    <div id="pnlApply">
        <table width="100%" border="0" align="center">
            <tr>
                <td align="center">
                    <table style="color: black" align="center">
                        <tr>
                            <td align="center" colspan="4">
                                <span id="lblTitleApply" style="font-size:Large;font-weight: bolder;">密码申请</span>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_controller_ver_apply">产品</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_controller_ver_apply" type="text" value="TR40C" maxlength="50" readonly="readonly" id="txt_controller_ver_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_machine_no_apply">机床编号</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_machine_no_apply" type="text" maxlength="50" readonly="readonly" id="txt_machine_no_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr id="trLynucApply1">
							<td align="left">
                                <span id="lbl_total_type_apply">机床大类</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_total_type_apply" type="text" maxlength="50" readonly="readonly" id="txt_total_type_apply" class="readonly" style="width:170px;" />
                            </td>
							<td align="left">
                                <span id="lbl_machine_type_apply">机床型号</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_machine_type_apply" type="text" maxlength="50" readonly="readonly" id="txt_machine_type_apply" class="readonly" style="width:170px;" />
                            </td>
						</tr>
                        <tr id="trLynucApply2">
							<td align="left">
                                <span id="lbl_controller_no_apply">控制器号</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_controller_no_apply" type="text" maxlength="50" readonly="readonly" id="txt_controller_no_apply" class="readonly" style="width:170px;" />
                            </td>
							<td align="left">
                                <span id="lbl_card_no_apply">控制卡号</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_card_no_apply" type="text" maxlength="50" readonly="readonly" id="txt_card_no_apply" class="readonly" style="width:170px;" />
                            </td>
						</tr>
                        <tr id="trLynucApply3">
							<td align="left">
                                <span id="lbl_ipc_sn_apply">主机序列号</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_ipc_sn_apply" type="text" value="16167A3D82ED" maxlength="50" readonly="readonly" id="txt_ipc_sn_apply" class="readonly" style="width:350px;" />
                            </td>
							<td align="left">
                                <span id="lbl_password_ver_apply">密码版本</span>
                            </td>
							<td align="left" nowrap="nowrap">
                                <input name="txt_password_ver_apply" type="text" value="3.0" maxlength="50" readonly="readonly" id="txt_password_ver_apply" class="readonly" style="width:170px;" />
                            </td>
						</tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_office_apply">事务所</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_office_apply" type="text" value="Lynuc" maxlength="50" readonly="readonly" id="txt_office_apply" class="readonly" style="width:170px;" />
                            </td>
                            <td align="left">
                                <span id="lbl_cust_name_apply">客户名</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_cust_name_apply" type="text" value="上海莱必泰数控机床股份有限公司" maxlength="50" readonly="readonly" id="txt_cust_name_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_is_pay">最新付款状态</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_is_pay" type="text" value="已付" maxlength="50" readonly="readonly" id="txt_is_pay" class="readonly" style="width:170px;" />
                            </td>
                            <td align="left">
                                <span id="lbl_setup_date_apply">安装日</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_setup_date_apply" type="text" value="2011-11-05" maxlength="50" readonly="readonly" id="txt_setup_date_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_ver_apply">软件版本</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_ver_apply" type="text" value="3.5.3.F1" maxlength="50" readonly="readonly" id="txt_ver_apply" class="readonly" style="width:170px;" />
                            </td>
                            <td align="left">
                                <span id="lbl_hard_ver_apply">硬件版本</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_hard_ver_apply" type="text" value="1.10.1.51" maxlength="50" readonly="readonly" id="txt_hard_ver_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr id="trLynucApply4">
							<td align="left">
                                <span id="lbl_mac_option_apply">机床选配</span>
                            </td>
							<td colspan="3" align="left">
                                <input name="txt_mac_option_apply" type="text" maxlength="200" readonly="readonly" id="txt_mac_option_apply" class="readonly" style="width:620px;" />
                            </td>
						</tr>
                        <tr id="trLynucApply5">
							<td align="left">
                                <span id="lbl_lynuc_option_apply">系统配置</span>
                            </td>
							<td colspan="3" align="left">
                                <input name="txt_lynuc_option_apply" type="text" maxlength="200" readonly="readonly" id="txt_lynuc_option_apply" class="readonly" style="width:620px;" />
                            </td>
						</tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_old_password_apply">当前密码</span>
                            </td>
                            <td id="td_old_password_apply" align="left" nowrap="nowrap" colspan="2">
                                <input name="txt_old_password_apply" type="text" maxlength="50" readonly="readonly" id="txt_old_password_apply" class="readonly" ondblclick="return OpenSmsWindow(this);" style="width:520px;" />
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_old_password_period_apply" type="text" maxlength="50" readonly="readonly" id="txt_old_password_period_apply" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_module_set_apply">模块</span>
                            </td>
                            <td align="left" nowrap colspan="3">
                                <input name="txt_module_set_apply" type="text" value="00000000" maxlength="50" readonly="readonly" id="txt_module_set_apply" class="readonly" style="width:620px;" />
                            </td>
                        </tr>
                        <tr valign="top">
                            <td colspan="4">
                                <hr />
                            </td>
                        </tr>         
                        <tr>
                            <td align="left" width="100">
                                <span id="lbl_soft_ver">软件版本</span>
                            </td>
                            <td align="left">
                                <input name="txt_soft_ver" type="text" id="txt_soft_ver" style="width: 170px" />
                            </td>
                            <td align="left" width="100">
                                <span id="lbl_hard_ver">硬件版本</span>
                            </td>
                            <td align="left">
                                <input name="txt_hard_ver" type="text" id="txt_hard_ver" style="width: 170px" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_memo_apply">申请备注</span>
                            </td>
                            <td colspan="3" align="left">
                                <textarea id="ftb_memo_apply" name="ftb_memo_apply" COLS=85 ROWS=7></TEXTAREA>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_request_user">申请人</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_request_user" type="text" value="董俊强" maxlength="50" readonly="readonly" id="txt_request_user" class="readonly" request_user_id="133" style="width:170px;" />
                            </td>
                            <td align="left">
                                <span id="lbl_request_time">申请时间</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_request_time" type="text" value="2016-06-12 15:52:13" maxlength="50" readonly="readonly" id="txt_request_time" class="readonly" style="width:170px;" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <span id="lbl_apply_no">申请号</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_apply_no" type="text" maxlength="50" readonly="readonly" id="txt_apply_no" class="readonly" style="width:170px;" />
                            </td>
                            <td align="left">
                                <span id="lbl_password_state_apply">密码状态</span>
                            </td>
                            <td align="left" nowrap>
                                <input name="txt_password_state_apply" type="text" maxlength="50" readonly="readonly" id="txt_password_state_apply" class="readonly" style="color:Black;width:170px;" />
                            </td>
                        </tr>
                        <tr id="tr_password_apply">
							<td align="left">
                                <span id="lbl_password_apply">版本升级密码</span>
                            </td>
							<td align="left" nowrap="nowrap" colspan="3">
                                <input name="txt_password_apply" type="text" maxlength="50" readonly="readonly" id="txt_password_apply" class="readonly" ondblclick="return OpenSmsWindow(this);" style="width:400px;" />
                                <input name="txt_cur_password_period_apply" type="text" maxlength="50" readonly="readonly" id="txt_cur_password_period_apply" class="readonly" style="width:170px;" />
                            </td>
						</tr>
                        <tr>
                            <td colspan="4" align="center">
                                <input type="submit" name="btnSmsApply" value="提交短信申请" id="btnSmsApply" class="btn1" style="width:100px;" />
                                <input type="submit" name="btnApply" value="提交申请" id="btnApply" class="btn1" style="width:100px;" />
                                
                                
                                
                                <input type="submit" name="btnCancelApply" value="取消" id="btnCancelApply" class="btn1" />
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
<script type="text/javascript" language="javascript">setTimeout("window.top.location='login.aspx';", 1200000);</script></form> 
</body>
</html>