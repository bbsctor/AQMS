<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页底部——扩展功能</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/global.js"></script>

  </head>
<body>
<div id="container">
	<div id="bd">
    	<div class="sidebar">
        	<div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>
            <h2><a href="javascript:;"><i class="h2-icon" title="切换到树型结构"></i><span>切换样式</span></a></h2>
            <ul class="nav">
                <li class="nav-li"  current>
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">报警管理</span></a>
                    <ul class="subnav">
                    	<li class="subnav-li" href="DataShow/ThreeDisplay.jsp" data-id="1"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">预警设置</span></a></li>
                        <li class="subnav-li" href="DataShow/History.jsp" data-id="2"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">短信报警</span></a></li>
                        <li class="subnav-li" href="DataShow/OverReport.jsp" data-id="3"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">邮件提示</span></a></li>
                        <li class="subnav-li" href="DataShow/OverReport.jsp" data-id="8"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">页面提示</span></a></li>
                    </ul>
                </li>
                <li class="nav-li" t>
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">仪器管理</span></a>
                    <ul class="subnav">
                    	<li class="subnav-li" href="<%=path%>/SchoolServlet?type=LogAll" data-id="4"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">远程控制</span></a></li>
                        <li class="subnav-li" href="<%=path%>/ParamServlet?type=LogAll" data-id="5"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">数据补遗</span></a></li>
                    </ul>
                </li>
               
            </ul>
            <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
        <div class="main">
        	<div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">
                   
                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div>
    </div>
</div>

<div class="more-bab-list">
	<ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>
</body>
<script type="text/javascript" src="js/nav.js"></script>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
	var menu = new Menu({
		defaultSelect: $('.nav').find('li[data-id="8"]')	
	});
	
	// 左侧树结构加载
	var setting = {};

		var zNodes =[
			{ name:"报警管理",
			   children: [
				 { name:"预警设置",icon:'img/skin_/leftlist.png'},
				 { name:"短信报警",icon:'img/skin_/leftlist.png'},
				 { name:"邮件提示",icon:'img/skin_/leftlist.png'},
				 { name:"页面提示",icon:'img/skin_/leftlist.png'}
			]},
			{ name:"仪器管理",
			   children: [
				 { name:"远程控制",icon:'img/skin_/leftlist.png'},
				 { name:"数据补遗",icon:'img/skin_/leftlist.png'}
			]}
			
		];

	$.fn.zTree.init($(".tree"), setting, zNodes);
	
	
	$('.sidebar h2').click(function(e) {
        $('.tree-list').toggleClass('outwindow');
		$('.nav').toggleClass('outwindow');
    });
	
	$(document).click(function(e) {
		if(!$(e.target).is('.tab-more')){
			 $('.tab-more').removeClass('active');
			 $('.more-bab-list').hide();
		}
    });
</script>
</html>
