<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>领秀统计</title>
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
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
</head>
<body>

<div class="backstage_wraper">
    <div class="userppt_wrap">
       <div class="newadd_cont">
           <div class="newadd_box clearfix">
               <ul class="newadd_list clearfix">
                   <li onclick="clickCharts('chart_1');" id="c1" style="margin-right:8px;" class="add_cover"><a href="###">领秀总数增长<span  id="chart_1" class="newadd_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/3bf02df17676b70b0a1138d1c0873037.jpg"/></span></a></li>
                  <li onclick="clickCharts('chart_2');" id="c2" style="margin-right:8px;"> <a href="###">秀粉总数增长<span id="chart_2" style="display:none;" class="newadd_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/3bf02df17676b70b0a1138d1c0873037.jpg"/></span></a></li>
                  <li onclick="clickCharts('chart_3');" id="c3" style="margin-right:8px;"> <a href="###">领秀秀粉增长<span id="chart_3" style="display:none;" class="newadd_triangle"><img src="http://ui1.111yao.com/static/image/temp/20160316/3bf02df17676b70b0a1138d1c0873037.jpg"/></span></a></li>
               </ul>
           </div>
           <div class="numpk_content">
               <div id="l1" class="num_pk clearfix">
                   <div class="numpk_title clearfix">
                       <div style="margin-left:20px;" class="numpk_time fl">
                          <input class="time_box" style="width:90px"  type="text"  value="${startDate?default('')}" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"  >
                          <label for="startDate" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate?default('')}" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate" class="time_down_right time_down"></label>
                       </div>
                      	  <button  type="button"  style="float:left;margin-left:10px;" onclick="submitCharts(1);">提交</button>
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
                       <button  type="button" style="float:left;margin-left:10px;" onclick="submitCharts(2);">提交</button>
                   </div>
                   <div class="numpk_pic" id="main1">
                   </div>
               </div>
                 <div id="l3" style="display: none;" class="num_pk clearfix">
                   <div class="numpk_title clearfix">
                       <span>时间</span>
                       <div class="numpk_time">
                       <input class="time_box" style="width:90px"  type="text"  value="${startDate2?default('')}" name="startDate2" id="startDate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endDate1\')}'})"  >
                          <label for="startDate1" class="time_down_left time_down"></label>
                          <b>至</b>
                          <input class="time_box" style="width:90px" type="text" value="${endDate2?default('')}" name="endDate2" id="endDate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate1\')}',maxDate:'%y-%M-{%d-1}'})"  >
                      	  <label for="endDate1" class="time_down_right time_down"></label>
                       </div>
                       <div class="numpk_time">
                      		 领秀手机：
                          <input class="time_box" style="width:90px" type="text" value="${mobile?default('')}" name="mobile" id="mobile">
                       </div>
                       <button  type="button" style="float:left;margin-left:10px;" onclick="submitCharts(3);">提交</button>
                   </div>
                   <div class="numpk_pic" id="main2">
                   </div>
               </div>
           </div>
       </div>
        <form name="form1" id="form1"  action="/static/leaderChart!exportDateList.action" method="post">
<input type="hidden" name="rs.p_curPage" value="1" id="p_curPage">
<input type="hidden" name="rs.p_pageSize" value="5" id="pageSize">	
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
                  <input type="button" style="width:40px;height:20px;float:left;margin-left:10px;margin-top:7px;border:1px solid #999;" onclick="clickStatistcForm();" value="提交"> 
                </li>
                <li class="adlist_first">
                    <span class="addlist_one">时间</span>
                    <span>领秀总人数</span>
                    <span>秀粉总人数</span>
                    <span>会员总人数</span>
                    <span>领秀新增人数</span>
                    <span>秀粉新增人数</span>
                    <span>会员新增人数</span>
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
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<script src="${base}/web/js/backstage.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   myChart= echarts.init(document.getElementById('main'));
    option = {
    title : {
        text: '健康领秀统计折线图'
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
            name:'领秀增长数量',
            type:'line',
            data:[]
        }
     
    ]
};
myChart.setOption(option);
echartsData1(GetDateStr(-15),GetDateStr(-1),1);
$("#startDate").val(GetDateStr(-15));
 $("#endDate").val(GetDateStr(-1));
 $("#startDate3").val(GetDateStr(-15));
 $("#endDate3").val(GetDateStr(-1));
getDateList(GetDateStr(-15),GetDateStr(-1),1,5) 
});    



 function echartsData1(startDate,endDate,chartType){
 	$.ajax({
 		url:"${base}/static/leaderChart!getLeaderDate.action",
 		type:"post",
 		data:{"startDate":startDate,"endDate":endDate,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 		var newOption = myChart.getOption(); // 深拷贝  
		newOption.xAxis[0].data = back_data.ydate;
		var name="";
		var legends=new Array()
 		name="领秀新增人数";
 		legends[0]=name;
		newOption.legend.data=legends;
		newOption.series[0].data = back_data.lxxz;
		newOption.series[0].name = name;
		myChart.setOption(newOption,true);
 		}
 		}
 	});
 	}
 	
 	function initEcharts2(){
 	 myChart1= echarts.init(document.getElementById('main1'));
    option1 = {
    title : {
        text: '秀粉增长统计折线图'
    },
    tooltip : {
        trigger: 'axis'
    },
     legend: {
      	orient: 'horizontal',      
        x: 'center',              
        y: 'top', 
        data:['最高数据']
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
            name:'秀粉增长数量',
            type:'line',
            data:[]
        }
    ]
};
myChart1.setOption(option1);
 	}
 	
 function initEcharts3(){
  myChart2= echarts.init(document.getElementById('main2'));
    option2 = {
    title : {
        text: '领秀秀粉增长统计折线图'
    },
    tooltip : {
        trigger: 'axis'
    },
     legend: {
      	orient: 'horizontal',      
        x: 'center',              
        y: 'top', 
        data:['最高数据']
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
            name:'领秀秀粉增长数量',
            type:'line',
            data:[]
        }
    ]
};
myChart2.setOption(option2);
 }	
 
 
 
 function echartsData2(startDate,endDate,chartType){
 	$.ajax({
 		url:"${base}/static/leaderChart!getLeaderDate.action",
 		type:"post",
 		data:{"startDate":startDate,"endDate":endDate,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 		var newOption = myChart1.getOption(); // 深拷贝  
		newOption.xAxis[0].data = back_data.ydate;
		var name="";
		var legends=new Array()
 		name="秀粉新增人数";
 		legends[0]=name;
		newOption.legend.data=legends;
		newOption.series[0].data = back_data.xfxz;
		newOption.series[0].name = name;
		myChart1.setOption(newOption,true);
 		}
 		}
 	});
 	}
 	
 function echartsData3(startDate,endDate,mobile,chartType){
 	$.ajax({
 		url:"${base}/static/leaderChart!getLeaderDate.action",
 		type:"post",
 		data:{"startDate":startDate,"endDate":endDate,"mobile":mobile,"chartType":chartType},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 			if(back_data.flag>0){
 			var newOption = myChart2.getOption(); // 深拷贝  
			newOption.xAxis[0].data = back_data.ydate;
			var name="";
			var legends=new Array()
	 		name=back_data.mobile+"名下秀粉新增人数";
	 		legends[0]=name;
			newOption.legend.data=legends;
			newOption.series[0].data = back_data.yhxfxz;
			newOption.series[0].name = name;
			myChart2.setOption(newOption,true);
 			}else{
 			$alert('warn','请输入正确的手机号码!');
 			}
 			
 		
 		}
 		}
 	});
 	}
 	

function submitCharts(el){
if(el==1){
var startDate=$("#startDate").val();
var endDate=$("#endDate").val();
echartsData1(startDate,endDate,1)
}else if(el==2){
var startDate=$("#startDate1").val();
var endDate=$("#endDate1").val();
echartsData2(startDate,endDate,2)
}else{
var mobile=$("#mobile").val();
if($.trim(mobile)==""||mobile.length!=11){
$alert('warn','请输入正确的手机号码!');
return;
}
var startDate=$("#startDate2").val();
var endDate=$("#endDate2").val();
echartsData3(startDate,endDate,mobile,3)
}



}



function clickCharts(el){
if(el=="chart_1"){
 $("#l1").show();
 $("#l2").hide();
 $("#l3").hide();
$("#c1").addClass("add_cover");
$("#c2").removeClass();
$("#c3").removeClass();
echartsData1(GetDateStr(-15),GetDateStr(-1),1);
$("#startDate").val(GetDateStr(-15));
 $("#endDate").val(GetDateStr(-1));
 $("#chart_1").show();
 $("#chart_2").hide();
 $("#chart_3").hide();
}else if(el=="chart_2"){
 $("#l2").show();
 $("#l1").hide();
 $("#l3").hide();
$("#c2").addClass("add_cover");
$("#c1").removeClass();
$("#c3").removeClass();
initEcharts2();
echartsData2(GetDateStr(-15),GetDateStr(-1),2);
$("#startDate1").val(GetDateStr(-15));
 $("#endDate1").val(GetDateStr(-1));
 $("#chart_2").show();
 $("#chart_1").hide();
 $("#chart_3").hide();
 
}else{
 $("#l3").show();
 $("#l2").hide();
 $("#l1").hide();
$("#c3").addClass("add_cover");
$("#c1").removeClass();
$("#c2").removeClass();
initEcharts3();
$("#startDate2").val(GetDateStr(-15));
 $("#endDate2").val(GetDateStr(-1));
 $("#chart_3").show();
 $("#chart_1").hide();
 $("#chart_2").hide();
}
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


	function goPage(page){
 	var startDate=$("#startDate3").val();
	var endDate=$("#endDate3").val();
	getDateList(startDate,endDate,page,5);
 	}
	function clickStatistcForm(){
 	var startDate=$("#startDate3").val();
	var endDate=$("#endDate3").val();
	getDateList(startDate,endDate,1,5);
 	}


	function getDateList(startDate,endDate,page,pageSize){
 	$.ajax({
 		url:"${base}/static/leaderChart!getDateList.action",
 		type:"post",
 		data:{"startDate3":startDate,"endDate3":endDate,"rs.p_curPage":page,"rs.p_pageSize":pageSize},
 		success:function(data){
 		if(data!=null){
 			var back_data=JSON.parse(data);
 			$("#p_curPage").val(back_data.pageInfo.page);
 			var str="";
 			for(var i=0;i<back_data.result.length;i++){
 				str+="<li><span class=addlist_one>"+back_data.result[i].date_str+"</span><span>"+back_data.result[i].leader_sum+"</span><span>"+back_data.result[i].powder_sum+"</span><span>"+back_data.result[i].member_sum+"</span><span>"+back_data.result[i].leader_count+"</span><span>"+back_data.result[i].powder_count+"</span><span>"+back_data.result[i].member_count+"</span</li>";
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