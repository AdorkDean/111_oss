<!DOCTYPE html>
<html lang="en">
<head>
   <#include "/WEB-INF/pages/inc/taglibs.ftl">
    <meta charset="UTF-8">
    <title>后台管理系统页面</title>
    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">
    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="img/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="img/favicon-196x196.png">
    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="apple-touch-icon-precomposed" href="img/apple-icon-touch.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="img/win8-tile-icon.png">
    <meta name="msapplication-TileColor" content="#0e90d2">
    <style type="text/css">
        @charset"utf-8";
        a,abbr,address,article,aside,b,blockquote,body,caption,cite,code,dd,del,dfn,div,dl,dt,em,fieldset,figure,form,h1,h2,h3,h4,h5,h6,html,i,iframe,img,input,ins,kbd,label,legend,li,object,ol,p,pre,q,samp,small,span,strong,sub,sup,table,tbody,td,textarea,tfoot,th,thead,time,tr,ul,var{margin:0;padding:0;border:0;vertical-align:baseline;font-weight:400;font-size:100%}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}
        audio,canvas,video{display:inline-block}
        audio:not([controls]){display:none}
        html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}
        body{width:100%;background:#fff;color:#000;font-size:12px;font-family:"Microsoft YaHei",Arial,Helvetica,verdana,sans-serif}
        input,textarea{color:#000;font-family:"Microsoft YaHei",Arial,Helvetica,verdana,sans-serif}
        input:-moz-placeholder,textarea:-moz-placeholder{color:#999}
        input:-ms-input-placeholder,textarea:-ms-input-placeholder{color:#999}
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#999}
        a{color:#000;text-decoration:none;-webkit-tap-highlight-color:transparent;}
        a:hover{text-decoration:none}
        a:focus{outline:none;blr:expression(this.onFocus=this.blur());}
        a:active,a:hover{outline:0}
        img{display:block}
        input,textarea{outline:0;font-size:100%;resize:none;-webkit-tap-highlight-color:rgba(255,0,0,0)}
        textarea{resize:none;-webkit-appearance:none}
        ol,ul{list-style:none}
        em{font-style:normal}
        .clearfix:after{clear:both;display:block;visibility:hidden;overflow:hidden;height:0;content:".";font-size:0}
        .fl{float:left}
        .fr{float:right}
        .clear{clear:both;zoom:1}

        .backstage_wraper{position:relative;}
        .backstage_wrap{background-color: #eef2fb;}
        .backstage_header h1{height:55px; line-height:55px; font-weight: bold; background-color: #ffffff; padding-left:20px; font-size:18px; margin-top:6px; margin-bottom:18px;}
        .backstage_cont1{background-color:#ffffff; padding:38px 20px 18px 20px;}
        .backstage_cont1 .c1_title{margin-bottom:6px; height:60px;}
        .backstage_cont1 .c1_title ul li{width:25%; float:left; height:36px; background-color:#ffffff;}
        .c1_title ul li dl dt{float:left; margin-right:10px;}
        .title_money{font-size: 18px; font-weight:bold; height:18px; line-height: 18px;}
        .title_name{font-size: 12px; height: 12px; line-height: 12px; margin-top:6px;}
        .title_list span{display:block; height:20px; width:122px; background-color:#e2e8f6; text-align:center; line-height: 20px; border-radius:4px; -webkit-border-radius:4px; color:#8151ee; margin-top:5px;}
        .list_content{padding:6px 0 18px 0;}
        .list_cont{height:76px; border:1px solid #aec3e5; position:relative;}
        .list_top{height:38px; line-height: 38px;}
        .list_box{width:44%; height:38px; margin:0 auto;}
        .list_box li{width:20%; float:left; text-align:right;}
        .list_box li strong{padding-right:15%; padding-left:6px; font-size:16px;font-weight:bold;}
        .list_box li span{font-size: 12px; }
        .list_bottom{background-color:#f3f5f7;}
        .list_bottom ul li{width:25%;}
        .list_triangle{width:12px; height:12px; border:1px solid #aec3e5; -webkit-border:1px solid #aec3e5; position:absolute; left:54%; top:-7px; transform:rotate(45deg); background-color:#fff; border-bottom-color:transparent; border-right-color:transparent;}
        .yesterday_time{margin-top:18px;}
        .order_menu{height:80px; padding:30px 20px 0 20px; background-color: #ffffff;}
        .order_list{height:24px; line-height:24px;}
        .order_list ul li{height:22px; line-height:22px; border:1px solid #678def; float:left; padding:0 10px; border-radius:11px; -webkit-border-radius:11px; margin-right:15px; color:#678def; font-size: 14px;}
        .order_list ul li.order_cover{background-color:#678def; color:#ffffff;}
        .order_toplist{margin-bottom:14px;}
        .graph_box{padding:0 20px 24px 20px; background-color:#ffffff; margin-bottom:80px;}
        .graph_cont{height:530px; border:1px solid #aec3e5; -webkit-border:1px solid #aec3e5; position:relative;}
        .order_triangle{left:15px;}
    </style>
    <script type="text/javascript" src="${base}/web/js/echarts/echarts.common.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
</head>
<body>
<input type="hidden" id="dateType" value="${dateType?default(1)}" />
<input type="hidden" id="chartType" value="${chartType?default(1)}" />
    <div class="backstage_wraper">
        <div class="backstage_wrap">
            <header class="backstage_header clearfix">
                <h1>今日</h1>
            </header>
            <div class="backstage_cont1">
                <div class="c1_title clearfix">
                    <ul>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/4b6b1862f0f3a1aa64b7288b7845922b.jpg"/></dt>
                                <dd class="title_money">${statistic.todayOrderAmount?default(0)}</dd>
                                <dd class="title_name">销售金额（元）</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/0cf72fd3b22951ac7af6eef57108e74d.jpg"/></dt>
                                <dd class="title_money">${statistic.todayPCT?default(0)}</dd>
                                <dd class="title_name">客单价（元）</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/540bc8a43c027f25970953207fe9b656.jpg"/></dt>
                                <dd class="title_money">${statistic.todayOrderCount?default(0)}</dd>
                                <dd class="title_name">订单量</dd>
                                <dd class="title_list"><span>订单列表</span></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/3c5aee178f4fab026f62ed204ee1bd18.jpg"/></dt>
                                <dd class="title_money">${statistic.todayPayCount?default(0)}</dd>
                                <dd class="title_name">未确认订单量</dd>
                            </dl>
                        </li>
                    </ul>
                </div>
                <div class="list_content">
                    <div class="list_cont">
                        <div class="list_triangle"></div>
                        <div class="list_top">
                            <ul class="list_box">
                            <#list statistic.statisticSourceToday?if_exists as resc>
                                <li>
                                <#if resc.today?default(1)==1>
                                <span>PC</span><strong>${resc.todaySource?default(0)}</strong>
                                <#elseif resc.today?default(1)==2>
                                <span>WAP</span><strong>${resc.todaySource?default(0)}</strong>
                                <#elseif resc.today?default(1)==3>
                                <span>安卓</span><strong>${resc.todaySource?default(0)}</strong>
                                <#elseif resc.today?default(1)==4>
                                <span>IOS</span><strong>${resc.todaySource?default(0)}</strong>
                                <#elseif resc.today?default(1)==5>
                                <span>手动下单</span><strong>${resc.todaySource?default(0)}</strong>
                                </#if>
                                </li>
                            </#list>
                            </ul>
                        </div>
                        <div class="list_top list_bottom">
                            <ul class="list_box">
                             <#list statistic.statisticTodayDelivery?if_exists as resc>
                          		 <li><span>${resc.deliveryName?default('')}</span><strong>${resc.todayDelivery?default(0)}</strong></li>
                            </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <header class="backstage_header yesterday_time  clearfix">
                <h1>昨日</h1>
            </header>
            <div class="backstage_cont1">
                <div class="c1_title clearfix">
                    <ul>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/4b6b1862f0f3a1aa64b7288b7845922b.jpg"/></dt>
                                <dd class="title_money">${statistic.yesterdayOrderAmount?default(0)}</dd>
                                <dd class="title_name">销售金额（元）</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/0cf72fd3b22951ac7af6eef57108e74d.jpg"/></dt>
                                <dd class="title_money">${statistic.yesterdayPCT?default(0)}</dd>
                                <dd class="title_name">客单价（元）</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/540bc8a43c027f25970953207fe9b656.jpg"/></dt>
                                <dd class="title_money">${statistic.yesterdayOrderCount?default(0)}</dd>
                                <dd class="title_name">订单量</dd>
                                <dd class="title_list"><span>订单列表</span></dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="http://ui1.111yao.com/static/image/temp/20160201/3c5aee178f4fab026f62ed204ee1bd18.jpg"/></dt>
                                <dd class="title_money">${statistic.yesyterdayPayCount?default(0)}</dd>
                                <dd class="title_name">未确认订单量</dd>
                            </dl>
                        </li>
                    </ul>
                </div>
                <div class="list_content">
                    <div class="list_cont">
                        <div class="list_triangle"></div>
                        <div class="list_top">
                            <ul class="list_box">
                                <#list statistic.statisticSourceYesterday?if_exists as resc>
                                <li>
                                <#if resc.yesterday?default(1)==1>
                                <span>PC</span><strong>${resc.yesterdaySource?default(0)}</strong>
                                <#elseif resc.yesterday?default(1)==2>
                                <span>WAP</span><strong>${resc.yesterdaySource?default(0)}</strong>
                                <#elseif resc.yesterday?default(1)==3>
                                <span>安卓</span><strong>${resc.yesterdaySource?default(0)}</strong>
                                <#elseif resc.yesterday?default(1)==4>
                                <span>IOS</span><strong>${resc.yesterdaySource?default(0)}</strong>
                                <#elseif resc.yesterday?default(1)==5>
                                <span>手动下单</span><strong>${resc.yesterdaySource?default(0)}</strong>
                                </#if>
                                </li>
                            </#list>
                            </ul>
                        </div>
                        <div class="list_top list_bottom">
                            <ul class="list_box">
                               <#list statistic.statisticYesterdayDelivery?if_exists as resc>
                          		 <li><span>${resc.deliveryName?default('')}</span><strong>${resc.yesterdayDelivery?default(0)}</strong></li>
                            </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="order_menu">
                <div class="order_list order_toplist">
                    <ul>
                        <a href="###" ><li id="chart_1" class="order_cover" onclick="clickCharts('chart_1');">订单金额</li></a>
                        <a href="###" ><li id="chart_2" onclick="clickCharts('chart_2');">订单数量</li></a>
                    </ul>
                </div>
                <div class="order_list">
                    <ul>
                        <a href="###" ><li id="date_1" class="order_cover" onclick="clickDateCharts('date_1');">7日</li></a>
                       <a href="###" > <li id="date_2" onclick="clickDateCharts('date_2');">15日</li></a>
                        <a href="###" ><li  id="date_3" onclick="clickDateCharts('date_3');">30日</li></a>
                    </ul>
                </div>
            </div>
            <div class="graph_box">
                <div class="graph_cont" id="main">
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(function(){
   myChart= echarts.init(document.getElementById('main'));
    option = {
    title : {
        text: '订单统计折线图'
    },
    tooltip : {
        trigger: 'axis'
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : []
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} '
            }
        }
    ],
    series : [
            {
            name:'订单金额',
            type:'line',
            data:[]
        }
     
    ]
};
myChart.setOption(option);
      echartsData(1,1); 

});    


function clickCharts(el){
var date=$("#dateType").val();
var chart=$("#chartType").val();
if(el=="chart_1"){
	echartsData(date,1);
 $("#chart_1").addClass("order_cover");
 $("#chart_2").removeClass();
 $("#chartType").val(1);
}else{
echartsData(date,2);
 $("#chart_2").addClass("order_cover");
 $("#chart_1").removeClass();
  $("#chartType").val(2);
}
}
function clickDateCharts(el){
var date=$("#dateType").val();
var chart=$("#chartType").val();
if(el=="date_1"){
	echartsData(1,chart);
 $("#date_1").addClass("order_cover");
 $("#date_2").removeClass();
 $("#date_3").removeClass();
 $("#dateType").val(1);
}else if(el=="date_2"){
echartsData(2,chart);
 $("#date_2").addClass("order_cover");
 $("#date_1").removeClass();
 $("#date_3").removeClass();
 $("#dateType").val(2);
}else{
echartsData(3,chart);
 $("#date_3").addClass("order_cover");
 $("#date_1").removeClass();
 $("#date_2").removeClass();
 $("#dateType").val(3);
}
}
 	function echartsData(dateType,chartType){
 	$.ajax({
 		url:"${base}/orderstatistic/chart!echartsData.action",
 		type:"post",
 		data:{"dateType":dateType,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 		var newOption = myChart.getOption(); // 深拷贝  
		newOption.xAxis[0].data = back_data.ydate;
 		if(chartType==1){
		newOption.series[0].data = back_data.oa;
		newOption.series[0].name = "订单金额";
 		}else{
		newOption.series[0].data = back_data.oc;
		newOption.series[0].name = "订单数量";
 		}
		myChart.setOption(newOption,true);
 		}
 		}
 	});
 	}
        </script>
</html>