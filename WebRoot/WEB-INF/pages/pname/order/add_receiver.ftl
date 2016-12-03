<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>添加收货地址</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
	<link rel="stylesheet" type="text/css" href="${base}/web/css/commonNew.css">
	 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=51293f48fbe2e1e51de5299446404273&plugin=AMap.PlaceSearch,AMap.Geocoder"></script>
</head> 

<body> 
	<div class="shop_main" id="shop_main"> 
    <form action="" method="post" name="form1" id="form1">
     <input type="hidden" name="tmemberreceiverlatlon.isDefault"  id="isDefault" value="0"/>
     <input type="hidden" name="tmemberreceiverlatlon.memberId"  id="memberId" value="${memberId?default('')}"/>
     <input type="hidden" name="memberReceiver_adcode"  id="memberReceiver_adcode" value=""/>
     <input type="hidden" name="tmemberreceiverlatlon.longitude" id="tmemberreceiver_longitude"  value=""/>
    <input type="hidden" name="tmemberreceiverlatlon.latitude" id="tmemberreceiver_latitude"  value=""/>
    <input type="hidden" name="tmemberreceiverlatlon.locationAddress" id="tmemberreceiver_locationAddress"  value=""/>
    	<div class="m-contents">
	    	<div class="labelnamese"><font color="red">*</font>收货人：</div>
	    	<input type="text" name="tmemberreceiverlatlon.receiver" class="i-text-i" id="receiver" value=""/>
	    	<span><font color="red" id="receiverSpanFont"></font></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;" >
	    	<div class="labelnamese"><font color="red">*</font>位置：</div>
	    	<input type="text" name="city_input_id" readonly="readonly" onclick="cityShow()" placeholder="请选择所在城市" class="i-text-i" id="city_input_id" value=""/>
	    	<input type="text" name="locationAddress_input_id" readonly="readonly" onclick="mapShow()" placeholder="请选择街道地址" class="i-text-i" id="locationAddress_input_id" value=""/>
			<span><font color="red" id="areaIdSpanFont"></font></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">*</font>楼层门牌号：</div>
	    	<input type="text" name="tmemberreceiverlatlon.address" class="i-text-i" id="address" value=""/>
	    	<span><font color="red" id="addressSpanFont"></font></span>
    	</div>
    	
    	<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese"><font color="red">*</font>联系电话：</div>
	    	<input type="text" name="tmemberreceiverlatlon.mobile" class="i-text-i" id="mobile" value="" maxlength="14"/>
	    	<span><font color="red" id="mobileSpanFont"></font></span>
    	</div>
    	
    	<!--<div class="m-contents" style="margin-top:10px;">
	    	<div class="labelnamese">邮编：</div>
	    	<input type="text" name="tmemberreceiver.zipCode" class="i-text-i" id="zipCode" value=""/>
	    	<span><font color="red" id="zipCodeSpanFont"></font></span>
    	</div>-->
    	<!-- 按钮 -->
    	<div class="m-contents" style="margin-top:20px;">
	    	<div class="labelnamese"></div>
	    	<input type="button" class="btn01" value="提交" onclick="saveReceiver()" style="margin-left:300px;"/>
    	</div>
    		
    </form>
    </div>  
    <!--遮罩层开始-->
<div id="shade">
    <!--新增收货人信息开始-->
    <div class="out out-box" id="out2" style="display:none">
    	<div class="double-out">
            <!---->
            <div class="second-out">
            	<h4 class="out-title city-title">
                    <a href="javascript:closeCity()" class="close-city"></a>
                    请选择你所在的城市
                </h4>
                <div class="city-main">
                    <div class="city-location-list clearfix clear">
                            <#list zimuMap?keys as key> 
                             <dl class="clearfix clear">
						       <dt>${key}</dt>
						       <dd>
                                <ul class="citytown-list clearfix clear">
	                               <#list zimuMap[key] as citylist> 
		                                <li><a href="javascript:selectCity('${citylist.citycode}','${citylist.name}')">${citylist.name}</a></li>
	                                </#list>
	                            </ul>
	                        </dd>
	                        </dl>
						    </#list> 
                    </div>
                </div>
            </div>
            <div class="third-out">
            	<a href="javascript:closeMap()" class="close-add"></a>
            	<div class="show-map2">
                	<div class="map" id="container">
                    	<div class="ab-location">
                        	<div class="fill-lication">
                            	<input class="ipt-lication" type="text" id="search_keyword" class="refund-price" value="请输入您的地址" onfocus="javascript:if(this.value=='请输入您的地址'){ this.value='';this.style.color='#333'}" onblur="javascript:if(this.value==''){ this.value='请输入您的地址';this.style.color='#999'}">
                            </div>
                            <a href="javascript:key_search()" class="search-location"></a>
                            <ul class="map-location-list" id="map_search_ul_id">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <style>.amap-container{height:350px;}</style>
        </div>
    </div>
    <!--新增收货人信息结束-->

</div>
<!--遮罩层结束-->
     
</body>
<script>


var map ;
var placeSearch;
var  geocoder;
//地图显示
function mapShow(){
    var memberReceiver_adcode = $("#memberReceiver_adcode").val();
    if(memberReceiver_adcode!=''){
        if(!map){
           map = new AMap.Map("container", {
		    resizeEnable: true
		   });
        }
        map.setZoom(12);
		map.setCity(memberReceiver_adcode);
		map.clearMap(); 
		var placeSearchOptions = { //构造地点查询类
		    pageSize: 10,
		    pageIndex: 1,
		    city: memberReceiver_adcode, //城市
		    map:map
		};
		  $("#shade").css({display:"block"})
		   $("#out2").show();
		   $('.third-out').show();	
	    placeSearch = new AMap.PlaceSearch(placeSearchOptions);
	    placeSearch.setCity(memberReceiver_adcode);
	    placeSearch.setCityLimit(true);
    }else{
       $alert("warn","请先选择城市!",null,null);
    }
}
//地图搜索
function key_search(){
	    var search_keyword = $("#search_keyword").val();
	    if($.trim(search_keyword)!=''){
	      placeSearch.search(search_keyword, callback);
	    }
}
	
//地图搜索回调函数
function callback(status, result) {
    $("#map_search_ul_id").html("");
    if (status === 'complete' && result.info === 'OK') {
	   if(result.poiList&&result.poiList.pois){
	      var pois = result.poiList.pois;
		   if(pois.length>0){
		       var map_search_ul_id_html="";
			  for(var i=0;i<pois.length;i++){
				    var pois_address=pois[i].address;
				    var pois_name=pois[i].name;
			        map_search_ul_id_html+="<li><a href=\"javascript:userSearchLocationAddress('"+pois_address+pois_name+"','"+pois[i].location.getLng()+"','"+pois[i].location.getLat()+"')\">";
				    map_search_ul_id_html+="<p class=\"location-title\">"+pois_name+"</p>";
				    map_search_ul_id_html+="<p class=\"location-text\">"+pois_address+"</p></a>";
				    map_search_ul_id_html+="</li>";
			  }
			  $("#map_search_ul_id").html(map_search_ul_id_html);
			  $('.map-location-list').show();	
		   }  
	   }
    }
}
	
//使用搜索地址
function userSearchLocationAddress(locationAddress,longitude,latitude){
    $("#tmemberreceiver_locationAddress").val(locationAddress);
    $("#tmemberreceiver_longitude").val(longitude);
    $("#tmemberreceiver_latitude").val(latitude);
    $("#locationAddress_input_id").val(locationAddress);
    $("#search_keyword").val('');
    $("#map_search_ul_id").hide();
    closeMap();
}	
 //显示城市弹出层 
function cityShow(){
   $("#shade").css({display:"block"})
   $("#out2").show();
   $('.second-out').show();	
}  
 //关闭选择城市弹出层
  function closeCity(){
     $('.second-out').hide();	
     $("#out2").hide();
     $("#shade").css({display:"none"});
  }
  //关闭地图弹出层
  function closeMap(){
     $('.third-out').hide();
     $("#out2").hide();
     $("#shade").css({display:"none"});
  }  
 //选择城市
  function selectCity(citycode,cityname){
      $("#memberReceiver_adcode").val(citycode);
      $("#city_input_id").val(cityname);
      $("#locationAddress_input_id").val("");
      $("#tmemberreceiver_longitude").val("");
      $("#tmemberreceiver_latitude").val("");
      $("#tmemberreceiver_locationAddress").val("");
      closeCity();
  } 
function saveReceiver(){
    if(checkReceiver()){
       var formInfo=$("#form1").serialize();
		$.ajax({
			url:"${base}/order/order!saveReceiver.action",
			type:"post",
			data:formInfo,
			success:function(data){
				if(data!=''){ //'1'更新,'2'修改
					$alert("success","添加成功!",null,null);
					window.opener.memberReceiver($("#memberId").val());
					window.close();
				}else{
					$alert("error","操作失败，请稍后再试!",null,null);
				}
			},
			error:function(){
				$alert("error","操作失败，请稍后再试!",null,null);
			}
		});
    }
}

function checkReceiver(){
var flag = true;
	// 收货人
	var receiver = $.trim($("#receiver").val());
	if (receiver == '') {
		$("#receiverSpanFont").html("收货人不能为空");
		flag = false;
	} else {
		$("#receiverSpanFont").html("");
	}

	// 位置
	var memberReceiver_adcode = $.trim($("#memberReceiver_adcode").val());
	
	var tmemberreceiver_longitude = $.trim($("#tmemberreceiver_longitude").val());
	if (memberReceiver_adcode == '' || tmemberreceiver_longitude=='') {
		$("#areaIdSpanFont").html("请完善位置信息");
		flag = false;
	} else {
		$("#areaIdSpanFont").html("");
	}

	// 楼层门牌号
	var address = $.trim($("#address").val());
	if (address == '') {
		$("#addressSpanFont").html("楼层门牌号不能为空");
		flag = false;
	} else {
		$("#addressSpanFont").html("");
	}

	// 手机号码
	var mobile = $.trim($("#mobile").val());
	if (mobile == '') {
		$("#mobileSpanFont").html("手机号码不能为空");
		flag = false;
	} else {
	    $("#mobileSpanFont").html("");
	}
	return flag;

}


</script>
</html>