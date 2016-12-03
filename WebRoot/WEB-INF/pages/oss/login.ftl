<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>111yao管理系统工作平台</title>
<script type="text/javascript" src="${base}/web/js/js.js"></script>
<link href="${base}/web/css/master.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="top"> </div>
<form id="login" name="login" action="${base}/j_spring_security_check" method="post">
<div id="center">&nbsp;
   		<div class="tip">
   			<#if errorMessage?default('')!='' & errorMessage?default('')!='999' & errorMessage?default('')!='888'>
   				${errorMessage?default('')}
   			<#elseif errorMessage?default('')=='999'>
   				用户名或密码不正确
   			<#elseif errorMessage?default('')=='888'>
   				验证码错误	
   			</#if>
   		</div>
   	
   <div class="user_box">
       <ul>
          <li><label>用户名：<input type="text" name="j_username" id="username" class="login_input" value="admin"/></label></li>
          <li><label>密　码：<input type="password" name="j_password" id="password" class="login_input" value="1"/></label></li>
          <li><span><img src="${base}/web/img/check/number1.jsp" name="safecode" width="44" height="22" id="safecode" /></span><label>验证码：<input name="j_code" type="text" id="j_code" maxlength="4" class="chknumber_input" autofocus="autofocus"/></label> </li>
          <#if doaminList?exists>
	          <li>
		          <label>平	台：
				          <select name="webid">
				          	<#list doaminList as domain>
				          		<option value="${domain.id?default(0)}">${domain.name?default('')}
				          	</#list>
				          </select>
		          </label>
	          </li>
          </#if>
          <li class="pl60"><input type="button" id="subotton" class="btn" value="登 录" /> <input type="button" id="reset" class="btn" value="重置" /></li>       
       </ul>
     
     
     
   
   </div>

</div>
</form>
<div id="footer"></div>
</body>
</html>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>
	var username=$("#username");
	var password=$("#password");
	var j_code=$("#j_code");
	var safecode=$("#safecode");
	var reset=$("#reset");
	var subotton=$("#subotton");
	var login=$("#login");
	reset.click(function(){
		username.attr("value","");
		password.attr("value","");
		j_code.attr("value","");
	});
	subotton.click(function(){		
		login.attr("action","${base}/j_spring_security_check");
		login.submit();
	});
	
	safecode.click(function(){
		$(this).attr("src","${base}/web/img/check/number1.jsp?random="+Math.random());			
	});
	
    if (document.addEventListener)
    {//如果是Firefox 
        document.addEventListener("keypress", fireFoxHandler, true); 
    } else
    { 
        document.attachEvent("onkeypress", ieHandler); 
    } 
    function fireFoxHandler(evt)
    { 
        //alert("firefox");
        if (evt.keyCode == 13)
        {
            document.forms[0].submit(); 
        }
    } 
    function ieHandler(evt)
    { 
        //alert("IE"); 
        if (evt.keyCode == 13){
            document.forms[0].submit();
        } 
    }	
</script>