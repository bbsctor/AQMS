<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="save" content="history"> 
<meta http-equiv="refresh"  content="120,url=${pageContext.request.contextPath }/RealData/QueryDataByMN">  
<title>实时数据——站点</title>
<link href="../css/historydata.css" type="text/css" rel="stylesheet">
<link href="../css/table.css" type="text/css" rel="stylesheet">

<style>
.panel {
	width: 100%;
	background-color: #BDE3F7;
	border: 0px solid #3b9fe1;
	width: 100%;
	height: 50px;
	vertical-align: center;
	font: "Times New Roman", Times, serif;
}

.demo3 {
	float: left;
	margin: 10px 10px 10px 600px;
}

.demo4 {
	float: right;
	margin: 10px;
}
</style>

</head>
<body>
	<div class="historydata">

		<div class="panel">
			<div class="demo3">
				<form  action="${pageContext.request.contextPath }/RealData/QueryDataByMN" method="post"  name="form1" >
					监测站号：<select name="MN" id="MN" onclick="setCookie('MN',this.selectedIndex)">
						<c:forEach var="list" items="${AllMN}">
							<option value="${list}" >${list}</option>
						</c:forEach>
					</select> <input type="submit" id="saveBtn" style=" width:90px; height:25px;" value="查询" />
				<input type="hidden" id="h_mn" name="h_mn"/>
				</form>				
			</div>
			<div class="demo4">
			   <input  type="button" value="导出数据" style="width:80px;height:25x" onclick="exportMN()"/>
			</div>
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
	<div style="margin:10px ">
	<center>
		<input type="hidden" value="${page.firstPage}" id="in_first"/><a id="a_first" href="#" onclick="first()">第一页</a>
        <input type="hidden" value="${page.nextPage}" id="in_next"/> <a  id="a_next" href="#" onclick="next()">下一页</a>
        <input type="hidden" value="${page.prePage}" id="in_pre"/> <a  id="a_pre" href="#" onclick="pre()">上一页</a>
        <input type="hidden" value="${page.lastPage}" id="in_last"/> <a  id="a_last" href="#" onclick="last()">最后页</a>
        <input type="hidden" value="${page.total}" id="in_total"/> 
   	</center>
   	<div style="margin:3px;float:right">每页<input type="text" value="${num}" style="width:25px" id="number" onkeyup="value=value.replace(/[^\d]/g,'')" onchange="change_num()"/> 条数据;
   	 当前第 ${page.pageNum} 页.总共 ${page.pages} 页.一共 ${page.total} 条记录  &nbsp;&nbsp; </div>
</div>
					
				</div>
	
	       <div class="tabcontent hidden">    
        <div  class="picture"  id="main" style=" border: 2px solid #3b9fe1; width:1150px; height:400px; margin:20px;" ></div>  <!-- 显示折图 -->  
       </div>
       
      </div><!--class="tablist2"  -->
</div><!--class="tab"  -->
		
		
</div><!-- history -->


	<script language="javascript" type="text/javascript" src="../js/historydata.js"></script>
	<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="../js/echart/echarts.js"></script><!-- 图表显示 -->	
	<script type="text/javascript" src="../js/echart/graph.js"></script>
	<script type="text/javascript" src="../js/cookie.js"></script>

<script type="text/javascript">

 var selectedIndex = getCookie("MN");
    if(selectedIndex != null) {
        document.getElementById("MN").selectedIndex = selectedIndex;
   } 
   var options=$("#MN option:selected"); //获取选中的项
     document.getElementById("h_mn").value =options.val();
     
     function change_num()
     {
       var options=$("#MN option:selected"); //获取选中的项
       document.getElementById("h_mn").value =options.val();
       var h_mn=$(" #h_mn ").val();
       var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByMN?MN='+h_mn+'&num='+number; 
      window.location.href=url;   
      }
     
   function  first()
   {
     var options=$("#MN option:selected"); //获取选中的项
     document.getElementById("h_mn").value =options.val();
       var in_first=$("#in_first").val();
       var h_mn=$(" #h_mn ").val();
        var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByMN?page='+"1"+'&MN='+h_mn+'&num='+number; 
      window.location.href=url;   
   }
    function  next()
   {
     var options=$("#MN option:selected"); //获取选中的项
     document.getElementById("h_mn").value =options.val();
       var in_next=$("#in_next").val();
       var h_mn=$(" #h_mn ").val();
       var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByMN?page='+in_next+'&MN='+h_mn+'&num='+number; 
      window.location.href=url;   
   }
   
    function  pre()
   {
     var options=$("#MN option:selected"); //获取选中的项
     document.getElementById("h_mn").value =options.val();
       var in_pre=$("#in_pre").val();
       var h_mn=$(" #h_mn ").val();
       var number=$("#number").val();
       var url='${pageContext.request.contextPath }/RealData/QueryDataByMN?page='+in_pre+'&MN='+h_mn+'&num='+number; 
      window.location.href=url;   
   }
   
    function  last()
   {
     var options=$("#MN option:selected"); //获取选中的项
     document.getElementById("h_mn").value =options.val();
    //   var in_last=$("#in_last").val();          
       var h_mn=$(" #h_mn ").val();
        var number=$("#number").val();
        var in_total=$("#in_total").val();  
        var in_last=Math.ceil(in_total/number);
       // alert(in_last);
       var url='${pageContext.request.contextPath }/RealData/QueryDataByMN?page='+in_last+'&MN='+h_mn+'&num='+number; 
      window.location.href=url;   
   }
   
    function  exportMN()
   {
      var options=$("#MN option:selected"); //获取选中的项
      document.getElementById("h_mn").value =options.val();
       var h_mn=$(" #h_mn ").val();
       var url='${pageContext.request.contextPath }/RealData/exportMN?MN='+h_mn; 
       window.location.href=url;   
   }
   
   	// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var colors = ['orange', 'blue', 'black']; 
  	   
  	   
        var option = {
			color: colors,
		backgroundColor:'#fff',		//背景色
        title : {
        text: '空气质量-气体含量',
        subtext: 'www.stkj.com',
		x:'center'					//标题居中
    },
    
    dataZoom:{ show:'true',
    	type:'inside',
    	//moveOnMouseMove:false,
    	yAxisIndex:[0,1,2],
    	xAxisIndex:0,
    	},
	grid: {
        right: '20%',
		y2:140

    },
    tooltip : {
        trigger: 'axis',
		 axisPointer: {
            type: 'cross'
        }
    },
    legend: {
        data:['SO2','CO','NO2','O3','PM2.5','PM10','TVOC'],
		orient:'vertical ',			//图例垂直布局
		y:'center',					//图例垂直方向上居中
		x:'right',	
		selected:{
			'SO2':false,
			'CO':false,
			'NO2':false,
			}
    },
    toolbox: {
        show : true,				//显示工具箱
        feature : {
            mark : {show: true},	//辅助线标志
            magicType : {show: true, type: [ 'line', 'bar', 'stack', 'tiled']}, //动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换，
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
	
    calculable : true,		//是否启用拖拽重计算特性
     xAxis:  [{	//X轴       	
                type : 'category',
                data:[]	,
                axisLabel:{  
                    interval:0,//横轴信息全部显示  
                    rotate:-60,//-60度角倾斜显示  
               }  
            }],
            yAxis : [
                     {
                         type: 'value',
                         name: 'SO2、NO2、O3、TVOC',
                         min: 0,
                         max: 400,
                         position: 'left',
                         axisLine: {
                             lineStyle: {
                             color: colors[0]
                             }
                         },
                         axisLabel: {
                             formatter: '{value} ppb'
                         }
                     },
                     {
                         type: 'value',
                         name: 'CO',
                         min: 0,
                         max: 1.5,
                         position: 'right',
                         offset: 80,
                         axisLine: {
                             lineStyle: {
                                 color: colors[1]
                             }
                         },
                         axisLabel: {
                             formatter: '{value} ppm'
                         }
                     },
                     {
                         type: 'value',
                         name: 'PM2.5、PM10',
                         min: 0,
                         max: 150,
                         position: 'right',
                         axisLine: {
                             lineStyle: {
                                 color: colors[2]
                             }
                         },
                         axisLabel: {
                             formatter: '{value} μg/m3'
                         }
                     }
                 ],
                 color:[
                        'red',	//NO2曲线颜色
                        '#9B30FF',//SO2
                        'rgb(195,229,0)',	//O3曲线颜色                       
                        '#1C86EE',	//PM10图颜色
                        '#FF4500',	//PM2.5曲线颜色
                        '#FF83FA',	//O3图颜色
                        'orange' ,   //TVOC曲线颜色           
                        ],
            series : [	//系列（内容）列表                      
						{
		                    name:'NO2',
		                    type:'line',	//折线图表示（生成农曲线）
		                    smooth:'true',
		                    symbol:'emptycircle',	//设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形	                    
		                    data:[]			
		                },
		                
		                {
		                    name:'SO2',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'emptyrect',
		                    data:[]	
		                },		                
		                {
		                    name:'O3',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'circle',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:[]	
		                },		                
		                {
		                    name:'PM10',
		                    smooth:'true',
		                    type:'bar',		//柱状图表示
		                    yAxisIndex: 2,
		                    data:[]	
		                },		                
		                {
		                    name:'PM2.5',
		                    type:'bar',
		                    smooth:'true',
		                    yAxisIndex: 2,
		                    symbol:'emptydiamond',
		                    data:[]	
		                },
		                {
		                    name:'CO',
		                    yAxisIndex: 1,
		                    type:'line',
		                    smooth:'true',
		                    symbol:'diamond',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:[]	
		                },		
		                {
		                    name:'TVOC',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'rectangle',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:[]	
		                }		
            ]
        };
		 
	myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画
	//myChart.setOption(option);	
	
	 var NO2=[];		//NO2数组
	 var SO2=[];		//SO2数组
	 var O3=[];		//o3数组
	 var PM10=[];		//p10数组
	 var PM25=[];	//pm25数组
	 var CO=[];
	 var TVOC=[];
	 var dates=[];		//时间数组
		
		var MN=$("#MN").val();		 

         $.ajax({	//使用JQuery内置的Ajax方法         
         type : "post",		//post请求方式
       //  contentType:"application/json;charset=utf-8",
         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "${pageContext.request.contextPath}/Echart/QueryDataByMN",	//请求发送到ShowInfoIndexServlet处
         data : {"MN":MN},		//提交数据到后台
         dataType : "json",		//返回数据形式为json
         
         success : function(result) {
       
        	 //请求成功时执行该函数内容，result即为服务器返回的json对象
	         if (result != null && result.length > 0) {
	                for(var i=0;i<result.length;i++){       
	                   NO2.push(result[i].no2);		//挨个取出值并填入前面声明的数组
	                   SO2.push(result[i].so2);
	                   O3.push(result[i].o3);
	                   PM10.push(result[i].pm10);
	                   PM25.push(result[i].pm25);
	                   CO.push(result[i].co);
	                   TVOC.push(result[i].tvoc);
	                   dates.push(getNowFormatDate(result[i].time));
	                }
	                myChart.hideLoading();	//隐藏加载动画
	                
	                myChart.setOption({		//载入数据
	   		         xAxis: {
	   		             data: dates	//填入X轴数据
	   		         },
	   		         series: [	//填入系列（内容）数据
	   		               		{
			   		             // 根据名字对应到相应的系列
			   		             name: 'NO2',
			   		             data: NO2
	   		         		},
	   		               		{
			   		             name: 'SO2',
			   		             data: SO2
	   		         		},
	   		         			{
			   		             name: 'O3',
			   		             data: O3
	   		         		},
	   		         			{
			   		             name: 'PM10',
			   		             data: PM10
	   		         		},
	   		         			{
	   		         			name: 'PM2.5',
	   		         			data: PM25
  		         			},
   		         				{
  			   		             name: 'TVOC',
  			   		             data: TVOC
  	  		         		},
  	  		         			{
  	  		         			name:'CO',
  	  		         			data:CO
  	  		         		}
	   		        ]
	   		     });
	                
	         }
	         else {
	        	 //返回的数据为空时显示提示信息
	        	 alert("图表请求数据为空，可能服务器暂未录入近五天的观测数据，您可以稍后再试！");
	          	 myChart.hideLoading();
	         }
         
		},
     	error : function(errorMsg) {
     		//请求失败时执行该函数
         	alert("图表请求数据失败，可能是服务器开小差了");
         	myChart.hideLoading();    	
     	}
    });
  
    myChart.setOption(option);	//载入图表

	
	function getNowFormatDate(dates) {
	var date = new Date(dates);
     var seperator1 = "/";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours=date.getHours();
    var Minutes=date.getMinutes();
    var Seconds=date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 1 && hours <= 9) {
        hours = "0" + hours;
    }
    if (Minutes >= 1 && Minutes <= 9) {
        Minutes = "0" + Minutes;
    }
    if (Seconds >= 1 && Seconds <= 9) {
        Seconds = "0" + Seconds;
    }
    
    var Formatdate = date.getFullYear() + seperator1 + month + seperator1 + strDate 
    				 + " " + hours + seperator2 + Minutes + seperator2 + Seconds;
    				 
    return Formatdate;
    }
 
</script>
</body>
</html>
