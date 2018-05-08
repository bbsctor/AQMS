<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/skin_/login.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.select.js"></script>
  </head>
 <body>
 
   <div id="container">
    <div id="bd">
   <form action="${pageContext.request.contextPath }/user/login" method="post"> 
      	<div id="main">
        	<div class="login-box">
                <div id="logo"></div>
                <h1></h1>             	
                <div class="input username" id="username">
                    <label for="userName">用户名</label>
                    <span></span>
                    <input type="text" id="userName" name="username"/>
                </div>
                <div class="input psw" id="psw">
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <span></span>
                    <input type="password" id="password" name="password" />
                </div>
                
                 <div class="input limit" id="limit">
                    <label for="limit">权&nbsp;&nbsp;&nbsp;&nbsp;限</label>
                </div>
                
               <div class="selectArea" >
                    <div class="selectWrap">
                    
                        <select name="usertype" >
                            <option value="管理员" >管理员</option>
                            <option value="普通用户">普通用户</option>
                        </select>
                    </div>
                </div>
             
                <div id="btn" class="loginButton">
                    <input  name="login" type="submit"  class="button" value="登录"  />
                </div>               	
            </div>          
        </div>
        </form>
        <div id="ft">CopyRight&nbsp;2017&nbsp;&nbsp;版权所有&nbsp;&nbsp;西安思坦科技有限公司&nbsp;&nbsp;</div>
    </div>
   
</div>

</body>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();
</script>
</html>
