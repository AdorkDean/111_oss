<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>官网订单地理位置统计</title>
    <script type="text/javascript" src="${base}/web/js/My97DatePicker/WdatePicker.js"></script>
	<link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" />	
	<#include "/WEB-INF/pages/inc/common.ftl">
	<#include "/WEB-INF/pages/inc/taglibs.ftl">
	<style>
		html,body{ margin:0px; height:100%;}
	</style>
</head>
<body>
	<div class="" style="padding-top:5px;padding-left:40px;50px;padding-bottom:5px;border: 120px solid transparent;border-width:0 0 1px;display:block;border-color:rgb(218, 218, 218);z-index:999999;"  id="">
		<table >
			<tr>
				<td>订单开始日期:&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="beginDate" name="beginDate" class=" Wdate " value=""  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" /> &nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>订单结束日期:&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="endDate" name="endDate" class=" Wdate " value=""  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_XIANGGUANTONGJI_DINGDANDILIWEIZHITONGJI_CHAXUN">
						<input type="button" id="searchBtn" class="btn01"  value="查询" onclick="queryOrder();"></input>
						<input type="button" id="searchBtnBackup" class="btn01" style="background:gray;width:88px;"  value="查询中..." ></input>
					</@security.authorize>
				</td>
			</tr>
		</table>
	</div>

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:90%;"></div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script src="${base}/web/js/map/baidu/map_baidu_geoCoord.js"></script>
	
    <script type="text/javascript">
    	var subtext_str = "时间（" + $("#beginDate").val() + " - " + $("#endDate").val() + "）";
    	var myChart;
    	var option = {
				    backgroundColor: "#1b1b1b",
				    color: ["gold","aqua","lime"],
				    title : {
				        text: "官网订单地理位置统计",
				        subtext:subtext_str,
				        x:"center",
				        textStyle : {
				            color: "#fff"
				        }
				    },
				    tooltip : {
				        trigger: "item",
				        formatter: "{b}"
				    },
				    legend: {
				        orient: "vertical",
				        x:"left",
				        data:["北京"],
				        selectedMode: "multi",
				        selected:{
				        },
				        textStyle : {
				            color: "#fff"
				        }
				    },
				    toolbox: {
				        show : true,
				        orient : "vertical",
				        x: "right",
				        y: "center",
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    dataRange: {
				        min : 0,
				        max : 100,
				        calculable : true,
				        color: ["#ff3333", "orange", "yellow","lime","aqua"],
				        textStyle:{
				            color:"#fff"
				        }
				    },
				    series: [
				        {
				            name: "北京 ",
				            type: "map",
				            mapType: "china",
				            roam:true,
			                itemStyle: {
			                    normal: {
			                        borderColor: "rgba(100,149,237,1)",
			                        borderWidth: 0.5,
			                        areaStyle: {
			                            color: "#1b1b1b"
			                        }
			                    }
			                },
				            data:[],
				            geoCoord: geoCoord_all_cities,
				            markLine : {
				                smooth:true,
				                effect : {
				                    show: true,
				                    scaleSize: 1,
				                    period: 30,
				                    color: "#fff",
				                    shadowBlur: 100
				                },
				                itemStyle : {
				                    normal: {
				                        borderWidth:1,
				                        lineStyle: {
				                            type: "solid",
				                            shadowBlur: 10
				                        }
				                    }
				                },
				                data : []
				            },
				            markPoint : {
				                symbol:"emptyCircle",
				                symbolSize : function (v){
				                    return 10 + v/10
				                },
				                effect : {
				                    show: true,
				                    shadowBlur : 0
				                },
				                itemStyle:{
				                    normal:{
				                        label:{show:false}
				                    },
				                    emphasis: {
				                        label:{position:"top"}
				                    }
				                },
				                data : []
				            }
				        }
				    ]
				};
				
				
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                    
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        $("#beginDate").val('${beginDate}');
        //初次进入
        queryOrder();
        
		function queryOrder(){
			$("#searchBtn").hide();
			$("#searchBtnBackup").show();
			
			var $beginDate = $("#beginDate");
			var $endDate = $("#endDate");
			
			
			$.ajax({
				url:"${base}/orderstatistic/orderstatistic!orderAddressStatistic.action",
				type:"post",
				data:{"beginDate":$beginDate.val(),"endDate":$endDate.val()},
				dataType:"json",
				success:function(data){
					$("#searchBtnBackup").hide();
					$("#searchBtn").show();
					
					var dataArr = eval(data);
					//alert(data.markPointdata[0].value);
					
					option.series[0].markLine.data = data.markLinedata;
					option.series[0].markPoint.data = data.markPointdata;
					
		    		subtext_str = "时间（" + $("#beginDate").val() + " - " + $("#endDate").val() + "）";
		    		option.title.subtext = subtext_str;
		    		myChart.clear();
					myChart.setOption(option); 
				}
			});
		}
		
    </script>
</body>
</html>
