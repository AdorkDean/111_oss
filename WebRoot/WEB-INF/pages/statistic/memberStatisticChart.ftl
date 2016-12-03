<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户统计</title>
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
    <link rel="stylesheet" href="${base}/web/css/bacdstage.css" type="text/css"/>
    <script type="text/javascript" src="${base}/web/js/echarts/echarts.common.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
</head>
<body>

<div class="backstage_wraper">
    <div class="userppt_wrap">
        <header class="userppt_header clearfix">
            <h1>统计</h1>
        </header>
        <div class="userppt_cont">
            <div class="userppt_list clearfix">
                <ul>
                    <li class="user_on">用户增长<span class="userppt_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/960a87aba1545f82dff21acea761f26b.jpg"/></span></li>
                </ul>
            </div>
            <div class="province_content">
                <div style="height:170px;" class="provinces_distribution">
                    <div class="pro_distribution">昨日关键指标</div>
                    <div class="key_indicator clearfix">
                        <dl class="adduser_num clearfix">
                            <dt><span>新增加用户（人）</span><b>${baseCount.day_add_sum?default(0)}</b></dt>
                            <dd><span>日</span><b>${baseCount.dayAddGrowthRate?default(0)}%<#if baseCount.dayAddGrowthRate?default(0) &gt;=0><img class="arrow_sign" src="${base}/web/images/add_sign.png"/><#else><img class="arrow_sign" src="${base}/web/images/down_sign.png"/></#if></b></dd>
                        </dl>
                        <dl>
                            <dt><span>累计注册用户（人）</span><b>${baseCount.day_cumulative_sum?default(0)}</b></dt>
                            <dd><span>月</span><b>${baseCount.monthCumGrowthRate?default(0)}%<#if baseCount.monthCumGrowthRate?default(0) &gt;=0><img class="arrow_sign" src="${base}/web/images/add_sign.png"/><#else><img class="arrow_sign" src="${base}/web/images/down_sign.png"/></#if></b></dd>
                            <dd><span>周</span><b>${baseCount.weekCumGrowthRate?default(0)}%<#if baseCount.weekCumGrowthRate?default(0) &gt;=0><img class="arrow_sign" src="${base}/web/images/add_sign.png"/><#else><img class="arrow_sign" src="${base}/web/images/down_sign.png"/></#if></b></dd>
                            <dd><span>日</span><b>${baseCount.dayCumGrowthRate?default(0)}%<#if baseCount.dayCumGrowthRate?default(0) &gt;=0><img class="arrow_sign" src="${base}/web/images/add_sign.png"/><#else><img class="arrow_sign" src="${base}/web/images/down_sign.png"/></#if></b></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
       <div class="newadd_cont">
           <div class="newadd_box clearfix">
               <ul class="newadd_list clearfix">
                   <li onclick="clickCharts('chart_1');" id="c1" style="margin-right:8px;" class="add_cover"><a href="###">新增人数<span  id="chart_1" class="newadd_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/3bf02df17676b70b0a1138d1c0873037.jpg"/></span></a></li>
                  <li onclick="clickCharts('chart_2');" id="c2"> <a href="###">累计用户<span id="chart_2" style="display:none;" class="newadd_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/3bf02df17676b70b0a1138d1c0873037.jpg"/></span></a></li>
               </ul>
           </div>
           <div class="numpk_content">
               <div id="l1" class="num_pk clearfix">
                   <div class="numpk_title clearfix">
                       <div class="allsouc_box fl">
                           <div class="allsouc_content" id="dateType" >最近30天</div>
                           <ol class="allsouc_list">
                               <li><a href="###" onclick="clickDateCharts('date_1')">最近5天</a></li>
                               <li><a href="###" onclick="clickDateCharts('date_2')">最近15天</a></li>
                               <li><a href="###" onclick="clickDateCharts('date_3')">最近30天</a></li>
                           </ol>
                           <strong class="allsouc_up"></strong>
                           <strong class="allsouc_down"></strong>
                       </div>
                       <div style="margin-left:20px;" class="numpk_time fl">
                          <input class="time_box" style="width:90px"  type="text"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"  >
                          <label for="startDate" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate" class="time_down_right time_down"></label>
                       </div>
                      	  <button  type="button"  style="float:left;margin-left:10px;" onclick="clickInputCharts();">提交</button>
                       <div class="allsouc_box fl">
                           <div class="allsouc_content" id="sourceType">全部来源</div>
                           <ol class="allsouc_list">
                                <li><a href="###" onclick="clickSourceCharts(0)">全部来源</a></li>
                               <li><a href="###" onclick="clickSourceCharts(1)">PC</a></li>
                               <li><a href="###" onclick="clickSourceCharts(2)">WAP</a></li>
                               <li><a href="###" onclick="clickSourceCharts(3)">Android</a></li>
                               <li><a href="###" onclick="clickSourceCharts(4)">IOS</a></li>
                           </ol>
                           <strong class="allsouc_up"></strong>
                           <strong class="allsouc_down"></strong>
                       </div>
                       <strong class="cancel_pk" onclick="clickContrast(1);">按时间对比</strong>
                   </div>
                   <div class="numpk_pic" id="main">
                   </div>
               </div>
                 <div id="l2" style="display: none;" class="num_pk clearfix">
                   <div class="numpk_title clearfix">
                       <span>时间</span>
                       <div class="numpk_time">
                       <input class="time_box" style="width:90px"  type="text"  value="${startDate1?default('')}" name="startDate1" id="startDate1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate1\')}'})"  >
                          <label for="startDate1" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate1?default('')}" name="endDate1" id="endDate1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate1\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate1" class="time_down_right time_down"></label>
                       </div>
                       <span>对比</span>
                       <div class="numpk_time">
                             <input class="time_box" style="width:90px"  type="text"  value="${startDate2?default('')}" name="startDate2" id="startDate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate2\')}'})"  >
                          <label for="startDate2" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate2?default('')}" name="endDate2" id="endDate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate2\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate2" class="time_down_right time_down"></label>
                       </div>
                       <button  type="button" style="float:left;margin-left:10px;" onclick="clickContrastInputCharts();">提交</button>
                       <strong class="cancel_pk" onclick="clickContrast(2);">取消对比</strong>
                   </div>
                   <div class="numpk_pic" id="main1">
                   </div>
               </div>
           </div>
       </div>
       <form name="form1" id="form1"  action="${base}/static/chart!exportDateList.action" method="post">
<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="3" id="pageSize">	
<input type="hidden" id="chartType" value="${chartType?default(1)}" />
<input type="hidden"  id="source" value="${source?default(0)}" />
<input type="hidden"  name="source" id="source1" value="${source1?default(0)}" />
<input type="hidden" id="contrast" value="1" />
        <div class="adduser_menu">
            <ul class="adduser_list">
                <li class="adlist_first border_first">
                    <div style="margin:6px 0 0 20px;" class="numpk_time time_bgcolor">
                          <input class="time_box" style="width:90px"  type="text"  value="${startDate3?default('')}" name="startDate3" id="startDate3" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate3\')}'})"  >
                          <label for="startDate3" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate3?default('')}" name="endDate3" id="endDate3" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate3\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate3" class="time_down_right time_down"></label>
                    </div>
                  <input type="button" style="width:40px;height:20px;float:left;margin-left:10px;margin-top:7px;border:1px solid #999;" onclick="clickDateListCharts();" value="提交"> 
                    <div style="margin-top:6px;" class="allsouc_box clearfix">
                        <div class="allsouc_content" id="sourceType1">全部来源</div>
                           <ol class="allsouc_list">
                                <li><a href="###" onclick="clickSource1List(0)">全部来源</a></li>
                               <li><a href="###" onclick="clickSource1List(1)">PC</a></li>
                               <li><a href="###" onclick="clickSource1List(2)">WAP</a></li>
                               <li><a href="###" onclick="clickSource1List(3)">Android</a></li>
                               <li><a href="###" onclick="clickSource1List(4)">IOS</a></li>
                           </ol>
                        <strong class="allsouc_up"></strong>
                        <strong class="allsouc_down"></strong>
                    </div>
                </li>
                <li class="adlist_first">
                    <span class="addlist_one">时间</span>
                    <span>新增加人数</span>
                    <span>活跃人数</span>
                    <span>购买人数</span>
                    <span>累积人数</span>
                    <b class="derived_form" onclick="submitForm()">导出表格</b>
                </li>
                <div id="list">
                </div>
            </ul>
        </div>
        </form>
        <div class="prbox_btm position_new clearfix">
            <div class="pages_list">
                <ol id="pageType">
                    <li class="page_up"><a href="###" onclick=""><img class="page_upsign" src="${base}/web/images/pgbg.jpg"/>上一页</a></li>
                    <li class="page_down"><a  href="###" onclick="">下一页<img class="page_downsign" src="${base}/web/images/pgbg.jpg"/></a></li>
                </ol>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${base}/web/js/jquery-1.8.3.js"></script>
<script src="${base}/web/js/backstage.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   myChart= echarts.init(document.getElementById('main'));
    option = {
    title : {
        text: '用户统计折线图'
    },
       legend: {
       	orient: 'horizontal',      
        x: 'center',              
        y: 'top', 
        data:['最高数据']
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
            name:'用户数量',
            type:'line',
            data:[]
        }
     
    ]
};
myChart.setOption(option);
clickDateCharts('date_3');


 $("#startDate3").val(GetDateStr(-31));
 $("#endDate3").val(GetDateStr(-1));
 getDateList(GetDateStr(-31),GetDateStr(-1),1,3,0);
});    

function clickContrast(el){
if(el==1){
$("#contrast").val(2);
$("#l1").hide();
$("#l2").show();
   myChart1= echarts.init(document.getElementById('main1'));
    option1 = {
    title : {
        text: '用户统计折线图'
    },
    tooltip : {
        trigger: 'axis'
    },
     legend: {
      	orient: 'horizontal',      
        x: 'center',              
        y: 'top', 
        data:['最高数据','最低数据']
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
            name:'用户数量',
            type:'line',
            data:[]
        },
          {
            name:'用户增加',
            type:'line',
            data:[]
        }
    ]
};
myChart1.setOption(option1);
var startDate1=$("#startDate1").val();
var endDate1=$("#endDate1").val();
var startDate2=$("#startDate2").val();
var endDate2=$("#endDate2").val();
startDate1=GetDateStr(-15);
$("#startDate1").val(startDate1);
startDate2=GetDateStr(-30);
$("#startDate2").val(startDate2);
endDate1=GetDateStr(-1);
$("#endDate1").val(endDate1);
endDate2=GetDateStr(-16);
$("#endDate2").val(endDate2);
clickCharts('chart_1');
}else{
$("#contrast").val(1);
$("#l2").hide();
$("#l1").show();
clickCharts('chart_1');
}
$("#c1").addClass("add_cover");
$("#c2").removeClass();
}
function clickCharts(el){
var chart=$("#chartType").val();
var startDate=$("#startDate").val();
var endDate=$("#endDate").val();
var startDate1=$("#startDate1").val();
var endDate1=$("#endDate1").val();
var startDate2=$("#startDate2").val();
var endDate2=$("#endDate2").val();
var source=$("#source").val();
var contrast=$("#contrast").val();
if(el=="chart_1"){
if(contrast==1){
echartsData(startDate,endDate,source,1);
}else{
echartsContrastData(startDate1,endDate1,startDate2,endDate2,1);
}
 $("#chart_1").show();
 $("#chart_2").hide();
 $("#chartType").val(1);
}else{
if(contrast==1){
echartsData(startDate,endDate,source,2);
}else{
echartsContrastData(startDate1,endDate1,startDate2,endDate2,2);
}
 $("#chart_2").show();
 $("#chart_1").hide();
  $("#chartType").val(2);
}
}

function clickDateCharts(el){
var chart=$("#chartType").val();
var startDate=$("#startDate").val();
var endDate=$("#endDate").val();
var source=$("#source").val();
if(el=="date_1"){
 $("#dateType").html("最近5天");
	startDate=GetDateStr(-5);
 $("#startDate").val(GetDateStr(-5));
endDate=GetDateStr(-1);
 $("#endDate").val(GetDateStr(-1));
}else if(el=="date_2"){
 $("#dateType").html("最近15天");
 startDate=GetDateStr(-15);
 $("#startDate").val(GetDateStr(-15));
endDate=GetDateStr(-1);
 $("#endDate").val(GetDateStr(-1));
}else{
startDate=GetDateStr(-30);
 $("#startDate").val(GetDateStr(-30));
endDate=GetDateStr(-1);
 $("#endDate").val(GetDateStr(-1));
 $("#dateType").html("最近30天");
}
echartsData(startDate,endDate,source,chart);
}


function clickInputCharts(){
var chart=$("#chartType").val();
var startDate=$("#startDate").val();
var endDate=$("#endDate").val();
var source=$("#source").val();
echartsData(startDate,endDate,source,chart);
}
function clickContrastInputCharts(){
var chart=$("#chartType").val();
var startDate1=$("#startDate1").val();
var endDate1=$("#endDate1").val();
var startDate2=$("#startDate2").val();
var endDate2=$("#endDate2").val();
echartsContrastData(startDate1,endDate1,startDate2,endDate2,chart);
}
function clickDateListCharts(){
var startDate3=$("#startDate3").val();
var endDate3=$("#endDate3").val();
var source=$("#source1").val();
getDateList(startDate3,endDate3,1,3,source);
}
function clickSourceCharts(el){
var chart=$("#chartType").val();
var startDate=$("#startDate").val();
var endDate=$("#endDate").val();
var source=$("#source").val();
if(el==1){
 $("#sourceType").html("PC");
 source=1;
}else if(el==2){
 $("#sourceType").html("WAP");
 source=2;
 }else if(el==3){
 $("#sourceType").html("Android");
 source=3;
}else if(el==4){
 $("#sourceType").html("IOS");
 source=4;
}else{
 $("#sourceType").html("全部来源");
 source=0;
}
$("#source").val(source);
echartsData(startDate,endDate,source,chart);
}
function clickSource1List(el){
var startDate3=$("#startDate3").val();
var endDate3=$("#endDate3").val();
var source=$("#source1").val();
if(el==1){
 $("#sourceType1").html("PC");
 source=1;
}else if(el==2){
 $("#sourceType1").html("WAP");
 source=2;
 }else if(el==3){
 $("#sourceType1").html("Android");
 source=3;
}else if(el==4){
 $("#sourceType1").html("IOS");
 source=4;
}else{
 $("#sourceType1").html("全部来源");
 source=0;
}
$("#source1").val(source);
getDateList(startDate3,endDate3,1,3,source);
}



Date.prototype.format = function(format){ 
var o = { 
"M+" : this.getMonth()+1, //month 
"d+" : this.getDate(), //day 
"h+" : this.getHours(), //hour 
"m+" : this.getMinutes(), //minute 
"s+" : this.getSeconds(), //second 
"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
"S" : this.getMilliseconds() //millisecond 
} 

if(/(y+)/.test(format)) { 
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
} 

for(var k in o) { 
if(new RegExp("("+ k +")").test(format)) { 
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
} 
} 
return format; 
}
function GetDateStr(AddDayCount) { 
var dd = new Date(); 
dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 

return dd.format("yyyy-MM-dd"); 
} 






 	function echartsData(startDate,endDate,source,chartType){
 	$.ajax({
 		url:"${base}/static/chart!echartsData.action",
 		type:"post",
 		data:{"startDate":startDate,"endDate":endDate,"source":source,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 		var newOption = myChart.getOption(); // 深拷贝  
		newOption.xAxis[0].data = back_data.ydate;
		var name="";
		var sou="";
		var legends=new Array()
		if(source==1){
 		sou="PC";
 		}else if(source==2){
 		sou="WAP";
 		}else if(source==3){
 		sou="Android";
 		}else if(source==4){
 		sou="IOS";
 		}else{
 		sou="全部来源"
 		}
 		if(chartType==1){
 		name="新增人数-"+sou;
 		legends[0]=name;
		newOption.legend.data=legends;
		newOption.series[0].data = back_data.xzyh;
		newOption.series[0].name = name;
		
 		}else{
 		name="累计人数-"+sou;
 		legends[0]=name;
		newOption.legend.data=legends;
		newOption.series[0].data = back_data.ljyh;
		newOption.series[0].name = name;
 		}
		myChart.setOption(newOption,true);
 		}
 		}
 	});
 	}
 	
 	
 	
 	function echartsContrastData(startDate1,endDate1,startDate2,endDate2,chartType){
 	$.ajax({
 		url:"${base}/static/chart!echartsContrastData.action",
 		type:"post",
 		data:{"startDate1":startDate1,"endDate1":endDate1,"startDate2":startDate2,"endDate2":endDate2,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 		var newOption = myChart1.getOption(); // 深拷贝  
		newOption.xAxis[0].data = back_data.ydate;
		var name1="";
		var name2="";
		var legends=new Array()
 		if(chartType==1){
 		name1=startDate1+"至"+endDate1+"新增人数-";
 		name2=startDate2+"至"+endDate2+"新增人数-";
 		legends[0]=name1;
 		legends[1]=name2;
		//newOption.legend.data=legends;
		newOption.series[0].data = back_data.xzyh1;
		newOption.series[1].data = back_data.xzyh2;
		newOption.series[0].name = name1;
		newOption.series[1].name = name2;
		
 		}else{
 		
 		name1=startDate1+"至"+endDate1+"累计人数-";
 		name2=startDate2+"至"+endDate2+"累计人数-";
 		legends[0]=name1;
 		legends[1]=name2;
	//	newOption.legend.data=legends;
		newOption.series[0].data = back_data.ljyh1;
		newOption.series[1].data = back_data.ljyh2;
		newOption.series[0].name = name1;
		newOption.series[1].name = name2;
 		}
		myChart1.setOption(newOption,true);
 		}
 		}
 	});
 	}
 	function goPage(page){
 	var startDate3=$("#startDate3").val();
	var endDate3=$("#endDate3").val();
	var source=$("#source1").val();
	getDateList(startDate3,endDate3,page,3,source);
 	}
 	function getDateList(startDate3,endDate3,page,pageSize,source){
 	$.ajax({
 		url:"${base}/static/chart!getDateList.action",
 		type:"post",
 		data:{"startDate3":startDate3,"endDate3":endDate3,"rs.p_curPage":page,"rs.p_pageSize":pageSize,"source":source},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 			$("#p_curPage").val(back_data.pageInfo.page);
 			var str="";
 			for(var i=0;i<back_data.result.length;i++){
 				str+="<li><span class=addlist_one>"+back_data.result[i].date+"</span><span>"+back_data.result[i].xzyh+"</span><span>"+back_data.result[i].day_active+"</span><span>"+back_data.result[i].ps+"</span><span>"+back_data.result[i].day_cumulative_sum+"</span></li>";
 			}
 			$("#list").html(str);
 			var str1="";
 			if(back_data.pageInfo.page==1){
 				if(back_data.pageInfo.page+1<=back_data.pageInfo.pages){
 				str1="<li class=page_down><a  href='###' onclick='goPage("+back_data.pageInfo.nextPage+")'>下一页</a></li>";
 				}
 			}else if(back_data.pageInfo.page==back_data.pageInfo.pages){
 				if(back_data.pageInfo.pages!=1){
 				str1="<li class=page_up><a  href='###' onclick='goPage("+back_data.pageInfo.prePage+")'>上一页</a></li>";
 				}
 			}else{
 				str1="<li class=page_up><a  href='###' onclick='goPage("+back_data.pageInfo.prePage+")'>上一页</a></li><li class=page_down><a  href='###' onclick='goPage("+back_data.pageInfo.nextPage+")'>下一页</a></li>";
 			}
 				$("#pageType").html(str1);
 		}
 		}
 	});
 	}
 	
 	function submitForm() {
		    $('#form1').submit();
		}
        </script>
</html>