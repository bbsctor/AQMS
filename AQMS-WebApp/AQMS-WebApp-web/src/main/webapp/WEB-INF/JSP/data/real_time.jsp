<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%  String path = request.getContextPath();%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh"  content="50,url=${pageContext.request.contextPath }/RealData/QueryDataByTime">  
<title>实时数据——时间</title>
<link href="../css/historydata.css" type="text/css" rel="stylesheet">
<link href="../css/table.css" type="text/css" rel="stylesheet">
 
<style>
.panel{width:100%;  background-color:#BDE3F7; border: 0px solid #3b9fe1; width:100%; height:50px;
        vertical-align:center; font:"Times New Roman", Times, serif;}
.demo1{float:left; margin:10px 10px 10px 500px; }
.demo4,.demo2{float:left; margin:10px; }
</style>
 
</head>
<body>
<div class="historydata" >
 
  <div class="panel"  >
    <form action="${pageContext.request.contextPath }/RealData/QueryDataByTime" method="post" name="form1">
    <div class="demo1" style="float:left; ">
    开始日期：<input  type="text"  class="laydate-icon" id="start" name="StartTime"  value="${StartTime}" style="width:160px; font-size:14px;" > 
    </div>
    <div class="demo2" style="float:left;" >
    结束日期：<input  type="text" class="laydate-icon" id="end"  name="EndTime"   value="${EndTime}"  style="width:160px;  font-size:14px;"> 
    </div>
    <div class="demo4" >
   <input type="submit" style=" width:80px; height:20px;" value="查询" />
    </div>
  </form>
  
 </div>
 
      <div class="tab">
    <div class="tabhead">
      <ul id="tab2">
        <li class="tab_cutli" onmousemove="changeTab(2,1)">表格</li>
        <li onmousemove="changeTab(2,2)">图形</li>
      </ul>
    </div>
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
                                <c:forEach begin="0" step="1" items="${RealData}" var="data">
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
    <div style="margin:3px ">
    <center>
        <input type="hidden" value="${page.firstPage}" id="in_first"/><a id="a_first" href="#" onclick="first()">第一页</a>
        <input type="hidden" value="${page.nextPage}" id="in_next"/> <a  id="a_next" href="#" onclick="next()">下一页</a>
        <input type="hidden" value="${page.prePage}" id="in_pre"/> <a  id="a_pre" href="#" onclick="pre()">上一页</a>
        <input type="hidden" value="${page.lastPage}" id="in_last"/> <a  id="a_last" href="#" onclick="last()">最后页</a>
       <input type="hidden" value="${page.total}" id="in_total"/> 
   	</center>
   	<div style="margin:5px;float:right">每页<input type="text" value="${num}" style="width:25px" id="number" onkeyup="value=value.replace(/[^\d]/g,'')" onchange="change_num()"/> 条数据;
   	 当前第 ${page.pageNum} 页.总共 ${page.pages} 页.一共 ${page.total} 条记录  &nbsp;&nbsp; </div>
</div>
			
                    
                </div>
    
           <div class="tabcontent hidden">    
        <div  class="picture"  id="main" style=" border: 2px solid #3b9fe1; width:1150px; height:410px; margin:20px;" ></div>  <!-- 显示折图 -->  
       </div>
        </div>
    </div>
</div>
 
<script language="javascript" type="text/javascript"src="../js/historydata.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/echart/echarts.js"></script><!-- 图表显示 -->
<script type="text/javascript" src="../js/echart/graph.js"></script> 
 
<script type="text/javascript" src="../js/laydate/laydate.js"></script><!-- 时间段查询 -->
<script type="text/javascript" src="../js/laydate/time.js"></script>
 
<script type="text/javascript">
 
     function change_num()
     {
       var start=document.getElementById("start").value;
       var end=document.getElementById("end").value;
       var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByTime?StartTime='+start+'&EndTime='+end+'&num='+number;   
      window.location.href=url;   
      }
     
 
   function  first()
   {
        var start=document.getElementById("start").value;
        var end=document.getElementById("end").value;
       var in_first=$("#in_first").val();
        var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByTime?page='+"1"+'&StartTime='+start+'&EndTime='+end+'&num='+number; 
       window.location.href=url;   
   }
    function  next()
   {
      var start=document.getElementById("start").value;
        var end=document.getElementById("end").value;
       var in_next=$("#in_next").val();
        var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByTime?page='+in_next+'&StartTime='+start+'&EndTime='+end+'&num='+number;  
      window.location.href=url;   
   }
   
    function  pre()
   {
      var start=document.getElementById("start").value;
        var end=document.getElementById("end").value;
       var in_pre=$("#in_pre").val();
        var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByTime?page='+in_pre+'&StartTime='+start+'&EndTime='+end+'&num='+number;  
      window.location.href=url;   
   }
   
    function  last()
   {
       var start=document.getElementById("start").value;
        var end=document.getElementById("end").value;
     //  var in_last=$("#in_last").val();
      var number=$("#number").val();
       var in_total=$("#in_total").val();  
        var in_last=Math.ceil(in_total/number);
       var url='${pageContext.request.contextPath }/RealData/QueryDataByTime?page='+in_last+'&StartTime='+start+'&EndTime='+end+'&num='+number;   
      window.location.href=url;   
   }
</script>
</body>
</html>
</body>
</html>
