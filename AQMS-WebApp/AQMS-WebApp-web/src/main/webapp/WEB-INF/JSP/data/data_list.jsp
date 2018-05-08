<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>data_list</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--  <meta http-equiv="refresh"  content="200,url=${pageContext.request.contextPath }/RealData/QueryDataBySiteName">  
-->
<link href="css/table.css" type="text/css" rel="stylesheet">
<style>
.demo1{float:left; margin:5px 10px 10px 0px; }
.demo4,.demo2{float:left; margin:5px 10px 10px 10px; }
</style>
</head>

<body onload="refresh()">
	<form	action="${pageContext.request.contextPath }/RealData/QueryDataBySiteName">
	<!-- <a href="${pageContext.request.contextPath }/EchartController/echart">图表显示</a><br/> -->
		监测站名：<select name="siteName" id="sitename">
			<c:forEach var="list" items="${AllSiteName}">
				<option value="${list}"  <c:if test="${siteName == list }"> selected</c:if> >${list}</option>
			</c:forEach>
		</select> <input type="submit" style=" width:50px; height:20px;" value="查询" />
		<input name="print" type="button" id="bt" value="点击打印" /> 
	</form>	
 
<div class="panel"  >
   <form action="${pageContext.request.contextPath }/RealData/QueryDataByTime" method="post">
    <div class="demo1" style="float:left; ">
	开始日期：<input  type="text"  class="laydate-icon" id="start" name="StartTime" value="2018-01-01 00:00:00" style="width:180px; font-size:14px;" > 
	</div>
	<div class="demo2" style="float:left;" >
	结束日期：<input  type="text" class="laydate-icon" id="end"  name="EndTime"  style="width:180px;  font-size:14px;"> 
	</div>
    <div class="demo4" >
   <input type="submit" style=" width:50px; height:20px;" value="查询" />
	</div>
  </form>
  <input  type="button"  id="export"  name="export" value="导出Excel" onclick="exportExcel()"> 
 </div>
		<div class="table_content">
			<div id="div_print" class="table_content_list">			
				 <table class="main_table">
                     <tr bgcolor="#e1f0fc">
                         <th width="4%">ID</th>
                         <th width="15%">时间</th>
                         <th width="9%">站号</th>
                         <th width="9%">站名</th>
                         <th width="9%">SO2</th>
                         <th width="9%">NO2</th>
                         <th width="9%">O3</th>
                         <th width="9%">C0</th>
                         <th width="9%">PM10</th>
                         <th width="9%">PM2.5</th>
                         <th width="9%">TVOC</th>
                        
                     </tr>
                         <c:forEach begin="0" step="1" items="${RealData}" var="data">
                             <tr class="alldata">
                                 <td class="td-id">${data.id}</td>
                                 <td class="td-time"><fmt:formatDate value="${data.time }"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                 <td class="td-siteNum">${data.siteNum}</td>
                                 <td class="td-siteName">${data.siteName }</td>
                                 <td class="td-so2">${data.so2}</td>
                                 <td class="td-no2">${data.no2}</td>
                                 <td class="td-o3">${data.o3}</td>
                                 <td class="td-co">${data.co}</td>
                                 <td class="td-pm10">${data.pm10}</td>
                                 <td class="td-pm25">${data.pm25}</td>
                                 <td class="td-tvoc">${data.tvoc}</td>
                             </tr>
                         </c:forEach>
                 </table> 
				</div>
	</div>
<div style="margin:10px ">
	<center>
		一共${page.pages}页&nbsp;&nbsp;&nbsp; 
		  <a  href="${pageContext.request.contextPath }/RealData/RealDataListbyPage?page=${page.firstPage}" >第一页</a>
         <a   href="${pageContext.request.contextPath }/RealData/RealDataListbyPage?page=${page.nextPage}">下一页</a>
         <a   href="${pageContext.request.contextPath }/RealData/RealDataListbyPage?page=${page.prePage}" >上一页</a>
         <a   href="${pageContext.request.contextPath }/RealData/RealDataListbyPage?page=${page.lastPage}">最后页</a>
   	</center>
</div>
  <div style="height:410px;min-height:100px;margin:0 auto;" id="main"></div><!-- 显示折线图 -->
  
	
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/echart/echarts.js"></script><!-- 图表显示 -->
<script type="text/javascript" src="js/echart/graph_real.js"></script>

<script type="text/javascript" src="js/laydate/laydate.js"></script><!-- 时间段查询 -->
<script type="text/javascript" src="js/laydate/time.js"></script>

 
  <script type="text/javascript">  
function changePage(obj)
{
 obj.href=obj.href+"&siteName="+document.getElementById("sitename").value;
 alert(obj.href);
}
  function refresh()
  {
    var myselect=document.getElementById("sitename");
    var index=myselect.selectedIndex ; 
    var value=myselect.options[index].value;
    var url = "${pageContext.request.contextPath }/RealData/QueryDataBySiteName"; 
    window.location.href = url+"?siteName="+value;
    setTimeout('refresh()',1000);
  }
  //打印
function printdiv(printpage) {
		var newstr = printpage.innerHTML; 
		var oldstr = document.body.innerHTML; 
		document.body.innerHTML =newstr; 
		window.print(); 
		document.body.innerHTML=oldstr; 
		return false;
 	} 
	window.onload=function(){
		var bt=document.getElementById("bt");
		var div_print=document.getElementById("div_print");
		bt.onclick=function(){
			printdiv(div_print);
		}
	}
	
//悬停显示全部内容
jQuery(function(){
    // 使用class选择器;例如:main_table对象->tbody->td对象.
   $(".main_table td").each(function(i){
                //给td设置title属性,并且设置td的完整值.给title属性.
              $(this).attr("title",$(this).text());
     });
});
//获取并格式化当前时间
function today(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    var hour = today.getHours();
	var minute = today.getMinutes();
	var second = today.getSeconds();
	 if (m>= 1 && m <= 9) {
        m = "0" + m;
    }
    if (d>= 1 && d <= 9) {
        d = "0" + d;
    }
    if (hour>= 1 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minute>= 1 && minute <= 9) {
        minute = "0" + minute;
    }
     if (second>= 1 && second <= 9) {
        second = "0" + second;
    }   
    return h+"-"+m+"-"+d+" "+hour+":"+minute+":"+second;
}
document.getElementById("end").value = today();
function todaydate(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
	 if (m>= 1 && m <= 9) {
        m = "0" + m;
    }
    if (d>= 1 && d <= 9) {
        d = "0" + d;
    }
  
    return h+"-"+m+"-"+d+" 00:00:00";
}
document.getElementById("start").value = todaydate();

//数据导出

function exportExcel()
{  
 $.ajax({	//使用JQuery内置的Ajax方法         
         type : "post",		//post请求方式
       //  contentType:"application/json;charset=utf-8",
         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "${pageContext.request.contextPath }/RealData/toexcel",	//请求发送到ShowInfoIndexServlet处
         data : {},		//提交数据到后台
       //  dataType : "json",		//返回数据形式为json
         
         success : function(result) {
         alert("123"); 
        window.open(basePath + data); 
         },
  	    error : function(errorMsg) {
     		//请求失败时执行该函数
         	alert("失败"); 	
     	}
    });
    
 }  
</script>
  
</body>
</html>
