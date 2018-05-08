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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史数据</title>
<link href="../css/historydata.css" type="text/css" rel="stylesheet">
<link href="../css/table.css" type="text/css" rel="stylesheet">

<style>
.panel{width:100%;  background-color:#BDE3F7; border: 0px solid #3b9fe1; width:100%; height:50px;
		vertical-align:center; font:"Times New Roman", Times, serif;}

.demo3{float:right; margin:10px 10px 10px 600px; }
</style>
</head>
<body>

<div class="panel"  >
   <div class="demo3" >
       <form action="${pageContext.request.contextPath }/HistoryData/exportALL">
      <input  type="submit" value="导出数据" style="width:80px;height:30px" />
		</form>
	</div>
 </div>
<div class="historydata" style="padding:30px; >
  <!--效果开始-->
    
    <div id="tablist2" class="tablist2">
    <div class="tabcontent ">     <!-- 表格页面  2-->
       <div class="table_content">
             <div id="div_print" class="table_content_list">            
                   <table class="main_table">
            	<tr bgcolor="#e1f0fc">
									<th width="15%">时间</th>
									<th width="9%">站号</th>
									<th width="9%">SO2(ppb)</th>
									<th width="9%">NO2(ppb)</th>
									<th width="9%">O3(ppb)</th>
									<th width="9%">C0(ppm)</th>
									<th width="9%">PM10(ug/m3)</th>
									<th width="9%">PM2.5(ug/m3)</th>
									<th width="9%">TVOC(ppb)</th>

								</tr>
								<c:forEach begin="0" step="1" items="${HistoryData}" var="data">
									<tr class="alldata">
										<td class="td-time"><fmt:formatDate value="${data.time }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<c:if test="${data.mn==88888880000001}"><td class="td-MN">1</td></c:if>
										<c:if test="${data.mn==88888880000002}"><td class="td-MN">2</td></c:if>
										<c:if test="${data.mn==88888880000003}"><td class="td-MN">3</td></c:if>
										<c:if test="${data.mn==88888880000004}"><td class="td-MN">4</td></c:if>
										
										<c:choose>
 										  <c:when test="${data.so2Flag=='B'}"><td class="td-so2">#</td></c:when> 										  
 										  <c:when test="${data.so2Flag=='F'}"><td class="td-so2">F</td></c:when>
   										    <c:otherwise>    <td class="td-so2">${data.so2*1000}</td>   </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.no2Flag=='B'}"><td class="td-no2">#</td></c:when>
   										    <c:otherwise>   <td class="td-no2">${data.no2*1000}</td>   </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.o3Flag=='B'}"><td class="td-o3">#</td></c:when>
   										    <c:otherwise>   <td class="td-o3">${data.o3*1000}</td>   </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.coFlag=='B'}"><td class="td-co">#</td></c:when>
   										    <c:otherwise>   <td class="td-co">${data.co}</td>   </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.pm10Flag=='B'}"><td class="td-pm10">#</td></c:when>
   										    <c:otherwise>   <td class="td-pm10">${data.pm10}</td>   </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.pm25Flag=='B'}"><td class="td-pm25">#</td></c:when>
   										    <c:otherwise>   <td class="td-pm25">${data.pm25}</td>  </c:otherwise>
										</c:choose>
										
										<c:choose>
 										  <c:when test="${data.tvocFlag=='B'}"><td class="td-tvoc">#</td></c:when>
   										    <c:otherwise>  <td class="td-tvoc">${data.tvoc*1000}</td> </c:otherwise>
										</c:choose>
										
									</tr>
								</c:forEach>
                  </table> 
                 </div>
     </div>
 <div style="margin:10px ">
     <center>        
         <a   href="${pageContext.request.contextPath }/HistoryData/AllHistoryData?page="1" ">第一页</a>
         <a   href="${pageContext.request.contextPath }/HistoryData/AllHistoryData?page=${page.prePage}">上一页</a>         
         <a   href="${pageContext.request.contextPath }/HistoryData/AllHistoryData?page=${page.nextPage}">下一页</a>
         <input type="hidden" value="${page.lastPage}" id="in_last"/> <a  id="a_last" href="#" onclick="last()">最后页</a>       
   		<input type="hidden" value="${page.total}" id="in_total"/> 
   	</center>
   	<div style="margin:5px;float:right">每页<input type="text" value="${num}" style="width:25px" id="number" onkeyup="value=value.replace(/[^\d]/g,'')" onchange="change_num()"/> 条数据;
   	 当前第 ${page.pageNum} 页.总共 ${page.pages} 页.一共 ${page.total} 条记录  &nbsp;&nbsp; </div>
</div>
      </div>   
   </div> 

<script type="text/javascript" src="../js/historydata.js"></script>

<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/echart/echarts.js"></script><!-- 图表显示 -->
<script>
  function  last()
   {
        var number=$("#number").val();
        var in_total=$("#in_total").val();  
        var in_last=Math.ceil(in_total/number);
       var url='${pageContext.request.contextPath }/HistoryData/AllHistoryData?page='+in_last+'&num='+number; 
      window.location.href=url;   
   }

</script>
</body>
</html>
