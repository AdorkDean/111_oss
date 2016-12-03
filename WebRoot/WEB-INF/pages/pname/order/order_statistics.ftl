<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <#include "/WEB-INF/pages/inc/common.ftl">
  <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${base}/web/js/highcharts.js"></script>
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <title>订单统计</title>
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
 </head> 
 <body> 
  <div class="shop_main" style="font-family: 'Microsoft Yahei';"> 
   <div class="sm_user"> 
    <div class="order">
    <!--查询条件开始-->
     <div id="order_form" class="order_form"> 
     <form name="form1" id="form1"   method="post">
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <tbody> 
          <tr> 
          <td colspan="2" >订单时间：
	        <input  type="text" class="input input_v1"   name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			至
			 <input  type="text" class="input input_v1"   name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
		  </td>
           <td>统计维度：
               <select name="tongjiType" >
					 <option value="1">按月</option>
					 <option value="2">按天</option>
					 <option value="3">按小时</option>
				 </select>
            </td> 
           <td>订单状态：
               <select name="orderStatus" >
					 <option value="">全部</option>
					 <option value="1">待支付</option>
					 <option value="2">待发货</option>
					 <option value="3">已完成</option>
					 <option value="4">已取消</option>
					 <option value="5">已过期</option>
			  </select>
            </td> 
          </tr>
          <tr>
            <td>确认状态：
               <select name="verifyStatus" >
					 <option value="">全部</option>
					 <option value="0">未确认</option>
					 <option value="1">已确认</option>
			   </select>
            </td>
             <td>配送方式：
               <select name="deliveryId">
					 <option value="">全部</option>
					 <#list deliveryWayList?if_exists as deliveryWay>
					    <option value="${deliveryWay.id}">${deliveryWay.name}</option>
					 </#list>
			   </select>
            </td>
              <td colspan="2">
                  <input type="button"   id="buttonsub" value="查询" class="btn05" onclick="ajaxOrder()" />
                  <input type="button"   id="buttonsub" value="柱状图" class="btn05" onclick="tongjiCheng('column')" />
                  <input type="button"   id="buttonsub" value="折线图" class="btn05" onclick="tongjiCheng('line')" />
          	  </td>
          </tr>
         </tbody> 
        </table> 
       </div> 
      </form> 
     </div> 
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl"> 
      <div id="container" style="min-width:400px;height:400px"></div>
      <div id="container_count" style="min-width:400px;height:400px"></div>
      <br /> 
    </div> 
   </div> 
  </div>  
 </body>
<script>	
//验证统计时间
function checkForm(){
    var startDate= $("#startDate").val();
    var endDate= $("#endDate").val();
    if($.trim(startDate)==''){
         $alert("warn","请填写统计起始时间！");
         return false;
    }
    
    if($.trim(endDate)==''){
         $alert("warn","请填写统计结束时间！");
         return false;
    }
    var start=new Date(startDate.replace("-", "/").replace("-", "/"));  
    var end=new Date(endDate.replace("-", "/").replace("-", "/"));  
    if(end<start){  
        $alert("warn","统计起始时间不能大于结束时间！");
        return false;  
    }  
    return true;
}


var options = {
        chart: {
	    plotBackgroundColor: null,
	    plotBorderWidth: null,
	    plotShadow: false,
	    renderTo:'container',
	    type:'column'
	},
        title: {
            text: '订单金额统计',
            x: -20 //center
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            title: {
                text: '价格(元)'
            }
        },
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: []
    };
var options_count = {
        chart: {
	    plotBackgroundColor: null,
	    plotBorderWidth: null,
	    plotShadow: false,
	    renderTo:'container_count',
	    type:'column'
	},
        title: {
            text: '订单个数统计',
            x: -20 //center
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            title: {
                text: '单'
            }
        },
        tooltip: {
            valueSuffix: '单'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: []
    };

//订单查询
	function ajaxOrder(){
	  if(checkForm()){
	    var formInfo=$("#form1").serialize();
		$.ajax({
			url:"${base}/order/order!ajaxOrderStatistics.action",
			type:"post",
			data:formInfo,
			success:function(data){
			   options.xAxis.categories=data.xname;
			   options.series=data.priceMapList;
               var chart = new Highcharts.Chart(options);  
               
               options_count.xAxis.categories=data.xname;
			   options_count.series=data.tongjiMapList;
                var chart_count = new Highcharts.Chart(options_count);  
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
	  }
	   
	}
	//更改 显示方式
	function tongjiCheng(tongjiType){
	    options.chart.type=tongjiType;
        var chart = new Highcharts.Chart(options);  
       
        options_count.chart.type=tongjiType;
        var chart_count = new Highcharts.Chart(options_count);
	}
	
</script>
</html>