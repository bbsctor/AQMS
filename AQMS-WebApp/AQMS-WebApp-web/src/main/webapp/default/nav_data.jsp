<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页底部</title>
    
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
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">实时数据</span></a>
                    <ul class="subnav">
                    <!--  	<li class="subnav-li" href="echarts-2.2.7/doc/example/allmap.jsp" data-id="0"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">GIS地图</span></a></li> -->
                        <li class="subnav-li" href="${pageContext.request.contextPath }/RealData/QueryDataByMN" data-id="1"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">站点查询</span></a></li>
                        <li class="subnav-li" href="${pageContext.request.contextPath }/RealData/QueryDataByTime" data-id="2"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">时间段查询</span></a></li>
                          <li class="subnav-li" href="${pageContext.request.contextPath }/RealData/QueryByTimeAndMNInit" data-id="3"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">组合查询</span></a></li>
                       </ul>
                </li>
             
                <li class="nav-li">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">历史记录</span></a>
                	<ul class="subnav">
                	<li class="subnav-li " href="${pageContext.request.contextPath }/HistoryData/AllHistoryData" data-id="4"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">历史数据</span></a></li>
                    <li class="subnav-li " href="${pageContext.request.contextPath }/HistoryData/QueryDataByMN" data-id="5"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">站点查询</span></a></li>
                    <li class="subnav-li " href="${pageContext.request.contextPath }/HistoryData/HistoryDataByTimeInit"  data-id="6"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">时间段查询</span></a></li> 
                   <li class="subnav-li" href="${pageContext.request.contextPath }/HistoryData/QueryByTimeAndMNInit" data-id="7"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">组合查询</span></a></li>
                    </ul>
                </li>
            <!--     <li class="nav-li">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">报表分析</span></a>
                    <ul class="subnav">
                    	<li class="subnav-li"  href="DataShow/history_year.jsp"  data-id="7"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">年报表</span></a></li>
                        <li class="subnav-li"  href="DataShow/history_month.jsp"  data-id="8"><a  class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">月报表</span></a></li>
                        <li class="subnav-li"  href="DataShow/history_date.jsp"  data-id="9"><a class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">日报表</span></a></li>
                        
                       
                    </ul>
                </li>
                
          -->       
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
		defaultSelect: $('.nav').find('li[data-id="1"]')	
	});
	
	// 左侧树结构加载
	var setting = {};

		var zNodes =[
			{ name:"实时监控",
			   children: [
				 { name:"GIS地图",icon:'img/skin_/leftlist.png',},
				 { name:"实时数据",icon:'img/skin_/leftlist.png'}
				 
			]},
			{ name:"历史记录",
			   children: [
				 { name:"监测点查询",icon:'img/skin_/leftlist.png'},
				 { name:"时间段查询",icon:'img/skin_/leftlist.png'},
				 { name:"参数查询",icon:'img/skin_/leftlist.png'}
				 
			]},
			{ name:"报表分析", open:true,
			   children: [
				 { name:"年报表", checked:true,icon:'img/skin_/leftlist.png'},
				 { name:"月报表",icon:'img/skin_/leftlist.png'},
				 { name:"日报表",icon:'img/skin_/leftlist.png'}
				 
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
