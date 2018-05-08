	
   $(function () { 		 
		 var NO2=[];		//NO2数组
		 var SO2=[];		//SO2数组
		 var O3=[];		//o3数组
		 var PM10=[];		//p10数组
		 var PM25=[];	//pm25数组
		 var CO=[];
		 var TVOC=[];
		 var dates=[];		//时间数组
		 
		 
//		表格赋值 
//		 $(".alldata td").each(function(){
//			  if($(this).text()=='#'){
//			   $(this).text('0.0');
//			  }
//			 });
		
		 
		   $(".alldata").each(function () {  			  
			     
			       dates.push($(this).find(".td-time").html());  
			       
                    NO2.push($(this).find(".td-no2").html());  
                    SO2.push($(this).find(".td-so2").html());  
                    O3.push($(this).find(".td-o3").html());  
                    PM10.push($(this).find(".td-pm10").html());  
                    PM25.push($(this).find(".td-pm25").html());  
                    CO.push($(this).find(".td-co").html()); 
                    TVOC.push($(this).find(".td-tvoc").html()); 
              });  
              
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
    color:[
           'red',	//SO2曲线颜色
           'green',//CO
           'rgb(195,229,0)',	//NO2曲线颜色
           'rgb(0,229,235)',	//O3图颜色
           'black',	//PM2.5图颜色
           'black',	//PM10曲线颜色
           'orange' ,   //TVOC曲线颜色           
           ],
    toolbox: {
        show : true,				//显示工具箱
        feature : {
            mark : {show: true},	//辅助线标志
 //           dataView : {show: true, readOnly: false,
//			optionToContent : function(opt) {
//                                //console.info(opt);
//                                var axisData = opt.xAxis[0].data;
//                                var series = opt.series;
//                                var tdHeaders = '<td>时间</td>'; //表头
//                                series.forEach(function(item) {
//                                    tdHeaders += '<td style="border: 2px solid black; ">' + item.name + '</td>'; //组装表头
//                                });
//                                var table = '<div class="table-responsive"><table class="table table-bordered table-striped table-hover" style="width:100%; height:150px; margin-top:10px;border-collapse:collapse;font-size:20px;text-align:center; border:2px solid black;"><tbody><tr >' + tdHeaders + '</tr>';
//                                var tdBodys = ''; //数据
//                                for ( i = 0, l = axisData.length; i < l; i++) {
//                                    for (j = 0; j < series.length; j++) {
//                                        tdBodys += '<td style="margin-left:100px; padding: 0 100px ;border-collapse:collapse;font-size:20px;text-align:center; border:2px solid black;">' + series[j].data[i] + '</td>'; //组装表数据
//                                    }
//                                    table += '<tr><td style="padding: 0 100px ; border:2px solid black;">' + axisData[i] + '</td>' + tdBodys + '</tr>';
//                                    tdBodys = '';
//                                }
//    
//                                table += '</tbody></table></div>';
//                                return table;
//                            }
//			},//数据视图
            magicType : {show: true, type: [ 'line', 'bar', 'stack', 'tiled']}, //动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换，
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
	
    calculable : true,		//是否启用拖拽重计算特性
     xAxis:  [{	//X轴       	
                type : 'category',
                data :dates	,
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
		                    data:NO2	
		                },
		                
		                {
		                    name:'SO2',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'emptyrect',
		                    data:SO2
		                },		                
		                {
		                    name:'O3',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'circle',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:O3
		                },		                
		                {
		                    name:'PM10',
		                    smooth:'true',
		                    type:'bar',		//柱状图表示
		                    yAxisIndex: 2,
		                    data:PM10
		                },		                
		                {
		                    name:'PM2.5',
		                    type:'bar',
		                    smooth:'true',
		                    yAxisIndex: 2,
		                    symbol:'emptydiamond',
		                    data:PM25
		                },
		                {
		                    name:'CO',
		                    yAxisIndex: 1,
		                    type:'line',
		                    smooth:'true',
		                    symbol:'diamond',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:CO
		                },		
		                {
		                    name:'TVOC',
		                    type:'line',
		                    smooth:'true',
		                    symbol:'rectangle',	//标识符号为实心圆
		                  //  yAxisIndex: 1,		//与第二y轴有关
		                    data:TVOC
		                }		
            ]
        };
		 
		//myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画
    
	   myChart.setOption(option);	
//	   window.addEventListener("resize",function(){
//		    myChart.resize();
//		});
});//载入图表	
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
