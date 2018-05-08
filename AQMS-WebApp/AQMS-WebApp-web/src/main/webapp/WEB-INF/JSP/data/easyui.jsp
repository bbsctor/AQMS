<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'easyui.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!-- 引入JQuery -->
  <script type="text/javascript" src="js/jquery-easyui-1.5.4.5/jquery.min.js"></script>
  <!-- 引入EasyUI -->
  <script type="text/javascript" src="js/jquery-easyui-1.5.4.5/jquery.easyui.min.js"></script>
  <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
  <script type="text/javascript" src="js/jquery-easyui-1.5.4.5/locale/easyui-lang-zh_CN.js"></script>
  <!-- 引入EasyUI的样式文件-->
  <link rel="stylesheet" href="js/jquery-easyui-1.5.4.5/themes/default/easyui.css" type="text/css"/>
  <!-- 引入EasyUI的图标样式文件-->
  <link rel="stylesheet" href="js/jquery-easyui-1.5.4.5/themes/icon.css" type="text/css"/>
  </head>
  
  <body>
     <div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="DataGrid标题" style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true,url:'RealData/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100">Product</th>
				<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
				<th data-options="field:'attr1',width:250">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th>
			</tr>
		</thead>
	</table>

<div class="easyui-panel" style="width:700px">
		<div class="easyui-pagination" data-options="total:100"></div>
	</div>
  </body>
</html>
