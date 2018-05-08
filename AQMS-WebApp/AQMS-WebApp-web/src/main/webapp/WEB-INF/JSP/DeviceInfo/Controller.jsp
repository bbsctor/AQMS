<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Controller.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <link rel="stylesheet" type="text/css" href="css/jquery.selectlist.css">
   <link href="css/historydata.css" type="text/css" rel="stylesheet">
  </head>
  <body>
  
  <div id="main_demo" style="margin-left:100px;margin-top: 20px">
</div>
  <table id="table" >
	<thead>
		<tr>
		    <th align="center">监测站号</th>
			<th align="center">命令</th>
			<th align="center">参数值</th>
			<th align="center">发送指令</th>
		</tr>
	</thead>
	<tbody>

    <tr>
    	<form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;" >
		<!--  	<c:forEach var="list" items="${AllMNName}">
				<option value="${list}"  <c:if test="${mn_name == list }"> selected</c:if> >${list}</option>
			</c:forEach>-->
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td><input type="text" name="CN" value="设置上报时间间隔" readonly="readonly" style="width:150px;height:30px;border: 0px" /></td>
        <td><input type="text" name="Para" style="width:200px;height:30px;" value="30"  onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字">(S)</td>
        <td><input  class="refresh_time" type="submit" name="submit" value="发送指令"  style="width:150px;height:30px;">
       </form>
     </tr>   
     
     
     <tr>
     <form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;" >
		<!--  	<c:forEach var="list" items="${AllMNName}">
				<option value="${list}"  <c:if test="${mn_name == list }"> selected</c:if> >${list}</option>
			</c:forEach>-->
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td ><input type="text" name="CN" style="width:150px;height:30px;border: 0px" readonly="readonly" value="设置现场机时间" /> </td>
        <td><input  type="text" name="Para"  class="laydate-icon" id="start" name="StartTime" value="${Para}" style="width:200px;height:30px;" ></td>
        <td><input  class="refresh_time" type="submit" name="submit" value="发送指令"   style="width:150px;height:30px;">
    </form>
     </tr>    
     
     
     <tr>
      <form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;">
		<!--  	<c:forEach var="list" items="${AllMNName}">
				<option value="${list}"  <c:if test="${mn_name == list }"> selected</c:if> >${list}</option>
			</c:forEach>-->
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td><input type="text" name="CN" value="获取现场机时间" readonly="readonly" style="width:150px;height:30px;border: 0px" /></td>
        <td><input type="text" name="Para" value="NULL" readonly="readonly" style="width:200px;height:30px;"></td>
         <td><input  class="refresh_time" type="submit" name="submit" value="发送指令"   style="width:150px;height:30px;">
        </form>
     </tr>
     
      <tr>
      <form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;">
		<!--  	<c:forEach var="list" items="${AllMNName}">
				<option value="${list}"  <c:if test="${mn_name == list }"> selected</c:if> >${list}</option>
			</c:forEach>-->
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td><input type="text" name="CN" value="开始采集实时数据" readonly="readonly" style="width:150px;height:30px;border: 0px" /></td>
        <td><input type="text" name="Para" value="NULL" readonly="readonly" style="width:200px;height:30px;"></td>
         <td><input  class="refresh_time" type="submit" name="submit" value="发送指令"   style="width:150px;height:30px;">
        </form>
     </tr>  
   
       <tr>
      <form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;">
		<!--  	<c:forEach var="list" items="${AllMNName}">
				<option value="${list}"  <c:if test="${mn_name == list }"> selected</c:if> >${list}</option>
			</c:forEach>-->
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td><input type="text" name="CN" value="停止采集实时数据" readonly="readonly" style="width:150px;height:30px;border: 0px" /></td>
        <td><input type="text" name="Para" value="NULL" readonly="readonly" style="width:200px;height:30px;"></td>
         <td><input class="refresh_time" type="submit" name="submit" value="发送指令" style="width:150px;height:30px;">
        </form>
     </tr>
     <!-- 
      <tr>
      <form action="${pageContext.request.contextPath }/webConnect/Connect" method="post">
    	<td><select id="edu" name="mn_name" style="width:140px;height:30px;">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select> </td>
    	<td><input type="text" name="CN" value="远程校准" readonly="readonly" style="width:150px;height:30px;border: 0px" /></td>
        <td>参数：<select name="Para" style="width:60px;height:30px;">
         					<option value="SO2">SO2</option>
         					<option value="SO2">NO2</option>
         					<option value="SO2">CO</option>
         					<option value="SO2">O3</option>
         					<option value="SO2">TVOC</option>
        			</select>
          <input type="text" name="data" style="width:50px;height:30px;" value="0"  onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字">(ppb)
          </td>
         <td><input class="refresh_time" type="submit" name="submit" value="发送指令" style="width:150px;height:30px;">
        </form>
     </tr>
       -->   
	</tbody>
</table>
  <div id="main_demo" style="margin-left:100px;margin-top: 20px">
  返回结果： <input type="text" id="result" value="${result}" readonly="readonly"  style="width:350px;height:30px;color:red" >
</div>
    
<script type="text/javascript" src="js/jquery-1.9.1.js" ></script>
<script type="text/javascript" src="js/jquery.selectlist.js"></script>

<script type="text/javascript" src="js/laydate/laydate.js"></script><!-- 时间 -->
<script type="text/javascript" src="js/laydate/time.js"></script>
<script type="text/javascript">

$(document).ready(function () {    

    $(".refresh_time").click(function () {
    	alert("确定要发送指令吗？");
        if (!canSubmit) {
            updateTimeLabel(30);
        }
    });
    var canSubmit;//标志
    function updateTimeLabel(time) {
        var btn = $(".refresh_time");
        btn.fadeIn(1000);
       // btn.val(time <= 0 ? "发送指令" : ("" + (time) + "秒后可发送指令"));
        var hander = setInterval(function () {
            if (time <= 0) {                
                canSubmit = false;                
                clearInterval(hander);
                btn.val("发送指令");
                btn.removeAttr("disabled");
            }
            else {
                canSubmit = true;
                btn.attr({ "disabled": "disabled" });
           //     btn.val("" + (time--) + "秒后可发送指令");
            }
        }, 1000);
        
    }

});
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
 
document.getElementById("start").value = today();
</script>
  </body>
</html>
