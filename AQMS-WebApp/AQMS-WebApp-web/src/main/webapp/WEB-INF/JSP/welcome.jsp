<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>welcome</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  welcome: ${loginuser.username} <br>
  <ul>
     <li><a href="${pageContext.request.contextPath }/RealData/RealDataListbyPage">数据列表</a></li>
     <li></li>
  </ul>
  <br><br>
 <shiro:principal> ${successMsg} Welcome!  <shiro:principal/>  
    <br><br>  
      
    <shiroTag:hasAnyRoles name="user">  
        <a href="/WEB-INF/JSP/user.jsp">User Page</a>  
    </shiroTag:hasAnyRoles>  
      
    <br><br>  
      
    <shiroTag:hasAnyRoles name="admin">  
        <a href="/WEB-INF/JSP/admin.jsp">Admin Page</a>  
    </shiroTag:hasAnyRoles>  
      
    <br><br>  
    <a href="../test/logout.do">Logout</a>  

  </body>
</html>
