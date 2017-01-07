function validateLogin() 
{ 
  var userName = document.getElementById("ctl00_ContentPlaceHolder1_txtUserCode").value;
  var userPassword = document.getElementById("ctl00_ContentPlaceHolder1_txtPassword").value;
  if( userName == "" ) 
  { 
   alert("请输入账号！") ; 
   return false; 
  } 
  if(userPassword == "") 
  { 
   alert("请输入密码！") ; 
   return false; 
  } 
  document.getElementsByName("jspnetForm").submit();
} 