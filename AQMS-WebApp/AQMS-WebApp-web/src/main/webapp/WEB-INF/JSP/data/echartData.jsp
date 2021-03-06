<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>- 观测数据 -</title>
</head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="js/echarts.js"></script>

	<body>
	<div> 
		<a href="${pageContext.request.contextPath }/RealData/RealDataListbyPage">表格显示</a>
	</div>
	<div>	
  <form id="search"	action="${pageContext.request.contextPath }/EchartController/echartView" method="post"> 
    监测站名：<select name="siteName" id="siteName">
			<c:forEach var="list" items="${AllSiteName}">
				<option value="${list}">${list}</option>
			</c:forEach>
		   </select> 
     <input type="submit" style=" width:50px; height:20px;" value="查询" />		
 </form>
	</div>
		<!-- 显示Echarts图表 -->
		<div style="height:410px;min-height:100px;margin:0 auto;" id="main"></div>			           		
  	  
		<script type="text/javascript" >
		
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
		//y2:140

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
         url : "Echart/QueryDataByMN",	//请求发送到ShowInfoIndexServlet处
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