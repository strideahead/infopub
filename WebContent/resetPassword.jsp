<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<!DOCTYPE html>  
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重设密码 - InfoPub v6.0</title>
<link href="css/main.css" rel="stylesheet"/>
<link href="images/favicon.ico" rel="shortcut icon">
<link href="css/table.css" rel="stylesheet"/>
<link href="css/button.css"  rel="stylesheet" />
<link href="js/jquery-ui-1.8.4.custom.css" rel="stylesheet"/>
<script src="js/jquery-3.0.0.js"></script>
<script src="js/jquery-ui-1.8.4.custom.min.js"></script>
<script src="js/jquery-ui-1.8.4-resource.js"></script>
<style type="text/css">
div>div{padding-top:2px;padding-bottom:2px;}
.ensemble{
	width:300px;
	height:30px;
}
.characters{
	width:100px;
	float:left;
	text-align:left;
}
.control{
	width:200px;
	float:right;
	text-align:left;
}
</style>
</head>
<body>
<form name="form1" method="post" action="resetPasswordIntoDB" onsubmit="javascript:return WebForm_OnSubmit();" id="form1">
   <table class="Table" style="width: 400px; height: 230px; position:absolute;top:0px; left:0px;background-color:whitesmoke">
      <tr>
          <td align="right" colspan="2" style="height: 15px"></td>
      </tr>
      <tr>
          <td align="right" style="width: 126px; height: 7px"><span id="lblUserCode" class="label">用户名</span></td>
          <td style="width: 185px; height: 7px">
              <input name="txtUserCode" type="text" value="${user.usercode}" readonly="readonly" id="txtUserCode" class="normal" />
           </td>
      </tr>
      <tr>
          <td align="right" style="width: 126px">
              <span id="lblOldPassword" class="label">旧密码</span></td>
          <td style="width: 185px">
              <input name="txtOldPassword" type="password" id="txtOldPassword" class="normal" />
              <span id="ReqValtxtPassword" style="display:inline-block;color:Red;width:1px;display:none;">*</span>
          </td>
          <td>
          	<div style="display: inline" id="tip1"></div>
          </td>
      </tr> 
      <tr>
          <td align="right" style="width: 126px">
              <span id="lblNewPassword" class="label">密码</span></td>
          <td style="width: 185px">
              <input name="txtPassword" readonly="readonly" type="password" id="txtPassword" class="normal" />
              <span id="ReqValtxtPassword" style="display:inline-block;color:Red;width:1px;display:none;">*</span>
          </td>
          <td>
          	<div style="display: inline" id="tip2"></div>
          </td>
      </tr>
      <tr>
          <td align="right" style="width: 126px; height: 24px">
              <span id="lblPasswordAgain" class="label">密码确认</span></td>
          <td style="width: 185px; height: 24px">
              <input name="txtValidate" readonly="readonly" type="password" id="txtValidate" class="normal" />
           </td>
           <td>
          	<div style="display: inline" id="tip3"></div>
          </td>
      </tr>
      <tr>
          <td align="center" colspan="2" style="height: 30px;padding:15px">
          	<!-- <input name="btnOK" id="btnOK" type="button" value="确定" style="height:30px;width:50px;font-size:15px;text-align: center;"
                    			class="button button-primary">
            <input name="btnClose" id="btnClose" type="button" value="确定" 
                    			class="button button-pill button-primary" OnClick="window.close();" > -->
              <input type="button" name="btnOK" value="确定" onclick="" id="btnOK" class="btn1" />
              <input name="btnClose" type="button" id="btnClose" Class="btn1" value="关闭" OnClick="window.close();" />
            </td>
      </tr>
      <tr>
          <td align="center" colspan="2" style="height: 32px">
              <div id="ValidationSummary1" style="color:Red;height:1px;width:303px;display:none;">
		   </div>
          </td>
      </tr>
  </table>
</form>
<script>
$(document).ready(function(){                
    $("#txtOldPassword").blur(function(){
            var oldpass=$("#txtOldPassword").val();
            if(oldpass=="") return;
            $.ajax({
                url:"resetPassword?oldpassword="+oldpass,
                type:"get",
                //data:'oldpassword='+oldpass, 
                //datatype : "json",
                success:function(e){
                    if(e==1){     
                        $("#tip1").html("<font color=\"green\" size=\"2\">Correct</font>");
                        $("#txtPassword").removeAttr("readOnly");
                        $("#txtValidate").removeAttr("readOnly");
                    } 
                    else{                                 
                        $("#tip1").html("<font color=\"red\" size=\"2\">Wrong</font>");
                        $("#txtPassword").attr("readOnly",'readOnly');
                        $("#txtValidate").attr("readOnly",'readOnly');
                    }
                }                 
            });
       });
      $("#txtPassword").blur(function(){
          var num=$("#txtPassword").val();
          if(num=="" || num==null) return;
          var patrn=/^(\w){6,20}$/;   
          if (!patrn.exec(num)) {
        	  $("#tip2").html("<font color=\"red\" size=\"2\">6-20个字母、数字、下划线</font>"); 
        	  $("#txtPassword").val("");
          } else{
        	  $("#tip2").html("<font color=\"green\" size=\"2\"> Accept</font>");
          }
      }) ;
      $("#txtValidate").blur(function(){
          var tmp=$("#txtPassword").val();
          var numValid=$("#txtValidate").val();
          var patrn=/^(\w){6,20}$/; 
          if (!patrn.exec(numValid)) {
        	  $("#tip2").html("<font color=\"red\" size=\"2\">6-20个字母、数字、下划线</font>"); 
        	  $("#txtPassword").val("");
          }else{
        	  if(tmp!=numValid){
        		  $("#tip3").html("<font color=\"red\" size=\"2\">不一致</font>");  
        	  }else{
        		  $("#tip3").html("<font color=\"green\" size=\"2\">一致</font>"); 
        	  }
          }         
      });
          $("#btnOK").click(function(){
              var flag=true;
              var old=$("#txtOldPassword").val();
              var pass=$("#txtPassword").val();
              var pass2=$("#txtValidate").val();
              var tip1=$("#tip1").find('font').html();
              if(tip1!="Correct"){
            	  alert("旧密码输入错误");
            	  return;
              }
              var tip3=$("#tip3").find('font').html();
              if(tip3!="一致"){
            	  alert("密码输入有误，请检查！");
              }
              $.ajax({
                  url:"resetPasswordIntoDB?newpass="+pass2,
                  success:function(e){                         
                      $("#ValidationSummary1").show().html("<font color=\"green\" size=\"3\">  Edit Success!</font>");
                      $("#txtOldPassword").val("");
                      $("#txtPassword").val("");
                      $("#txtValidate").val("");
                      $("#tip1").empty();
                      $("#tip2").empty();
                      $("#tip3").empty();
                      $("#tip4").delay(2000).hide(0);   
                      window.close();
                 }
              });
          }
       );                  
    });
</script>
</body>
</html>