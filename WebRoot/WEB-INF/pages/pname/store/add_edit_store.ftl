<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>编辑门店</title> 
    <#include "/WEB-INF/pages/inc/taglibs.ftl">
     <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" />
     <link rel="stylesheet" type="text/css" href="${base}/web/css/commonNew.css"> 
 <script type="text/javascript" src="${base}/web/js/jquery-1.7.2.min.js"></script>
   <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
<script type="text/javascript" src="${base}/web/js/alert.main.js"></script>
<style type="text/css">
textarea{line-height: 20px;}
table{table-layout: auto}

#container {
	position: absolute;
	top: 0;
	width: 48%;
	height: 75%;
}

.button-group {
	position: absolute;
	bottom: 20px;
	right: 20px;
	font-size: 12px;
	padding: 10px;
}

.button-group .button {
	height: 28px;
	line-height: 28px;
	background-color: #0D9BF2;
	color: #FFF;
	border: 0;
	outline: none;
	padding-left: 5px;
	padding-right: 5px;
	border-radius: 3px;
	margin-bottom: 4px;
	cursor: pointer;
}
.button-group .inputtext {
	height: 26px;
	line-height: 26px;
	border: 1px;
	outline: none;
	padding-left: 5px;
	padding-right: 5px;
	border-radius: 3px;
	margin-bottom: 4px;
	cursor: pointer;
}
 /*
.tip {
	position: absolute;
	bottom: 30px;
	right: 10px;
	background-color: #FFF;
	text-align: center;
	border: 1px solid #ccc;
	line-height: 30px;
	border-radius: 3px;
	padding: 0 5px;
	font-size: 12px;
}
*/
#tip {
	background-color: #fff;
	padding-left: 10px;
	padding-right: 10px;
	position: absolute;
	font-size: 12px;
	right: 10px;
	top: 20px;
	border-radius: 3px;
	border: 1px solid #ccc;
	line-height: 30px;
}

/*
#tip input[type='button'] {
	margin-top: 10px;
	margin-bottom: 10px;
	background-color: #0D9BF2;
	height: 30px;
	text-align: center;
	line-height: 30px;
	color: #fff;
	font-size: 12px;
	border-radius: 3px;
	outline: none;
	border: 0;
}
*/
.amap-info-content {
	font-size: 12px;
}

#myPageTop {
	position: absolute;
	top: 5px;
	right: 10px;
	background: #fff none repeat scroll 0 0;
	border: 1px solid #ccc;
	padding:6px;
	font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
	font-size: 14px;
}
#myPageTop label {
	margin: 0 20px 0 0;
	color: #666666;
	font-weight: normal;
}
#myPageTop input {
	width: 170px;
}
#myPageTop .column2{
	padding-left: 25px;
}
</style>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=62f3e45f67b6f5503019973950b8e8f4&plugin=AMap.Autocomplete,AMap.PolyEditor,AMap.CircleEditor"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head> 
 <body> 
   <table width="100%">
   <tr>
   <td width="50%"><form action="" method="post" name="form1" id="form1">
   <input type="hidden" name="store.id" id="storeid" value="<#if store.id?exists>${store.id?default(-1)}</#if>"/>
   <input type="hidden" id="status"  value="1"/>
     <table border="1" cellspacing="0" cellpadding="0" width="100%" style="border-color:rgba(218, 228, 243, 0.44);"> 
          <tr style="height:40px"> 
			<td align="center" colspan="2"><span style="color: #2b2b2b;font-weight: bold;">门店信息</span></td>
          </tr>
           <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;">门店名称:</td>
			<td align="left" height="40" style="padding-left:2%;"><input type="text" class="input input_v1" name="store.name"  id="name"  value="<#if store.name?exists>${store.name?default('')}</#if>"/></td>
          </tr>
           <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">门店海典ID:</td>
			<td align="left" height="40" style="padding-left:2%;"><input type="text" class="input input_v1" name="store.storeHdId"  id="storeHdId"  value="<#if store.storeHdId?exists>${store.storeHdId?default('')}</#if>"/></td>
          </tr>
           <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">门店地址:</td>
			<td align="left" height="40" style="padding-left:2%;"><input type="text" class="input input_v1" name="store.address"  id="address"  value="<#if store.address?exists>${store.address?default('')}</#if>"/></td>
          </tr>
           <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">门店经营日期:</td>
			<td align="left" height="40" style="padding-left:2%;">
			<input  type="text" class="input input_v1"  value="<#if store.startDt?exists>${store.startDt?string('yyyy-MM-dd')}</#if>" name="store.startDt" id="startDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  ><br>
			至<br>
			<input  type="text" class="input input_v1"  value="<#if store.endDt?exists>${store.endDt?string('yyyy-MM-dd')}</#if>" name="store.endDt" id="endDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  >
			</td>
          </tr>
          <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">选择门店城市:</td>
			<td align="left" height="40" style="padding-left:2%;">
			<input type="text" class="input input_v1" readonly="readonly"name="area" id="area" onclick="cityShow()" placeholder="请选择所在城市"  value="${area?default('')}"/>
			<input type="hidden" class="input input_v1"  name="cityCode"  id="cityCode"  value="${cityCode?default('')}"/>
			</td>
          </tr>
          <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">门店经纬度 :</br><font color="red">（经度与纬度之间用,号分隔）</font></td>
			<td align="left" height="40" style="padding-left:2%;"><input type="text" class="input input_v1" readonly="readonly" name="store.storeLatLon"  id="storeLatLon"  value="<#if store.storeLatLon?exists>${store.storeLatLon?default('')}</#if>"/><input class="btn04" type="button" onclick="openMap('map',1);" value="地图点选"/></td>
          </tr>
           <tr> 
			<td align="right" width="20%" style="padding-right:2%;"">门店范围经纬度:</br><font color="red">(经度与纬度之间用,号分隔  两个经纬度之间用;分隔)</font></td>
			<td align="left"  style="padding:2%;"><textarea type="text" class="input input_v1" readonly="readonly" name="store.latLonRange" style="height:120px;line-height: 20px;"  id="latLonRange" >${store.latLonRange?if_exists}</textarea><input type="button" class="btn04" onclick="openMap('map',2);" value="地图点选"/></td>
          </tr>
           <tr> 
			<td align="right" height="40" width="20%" style="padding-right:2%;"">排序 :</td>
			<td align="left" height="40" style="padding-left:2%;"><input type="text" class="input input_v1" name="store.sort"  id="sort" maxlength="3"  value="<#if store.sort?exists>${store.sort?default('')}</#if>"/></td>
          </tr>
           <tr> 
			<td align="center"height="40"  colspan="2">
			<input type="button" class="btn01" value="提交" onclick="saveOrder()" style="margin-left:10px;"/>
			<input type="button" class="btn01" value="返回" onclick="javascript:history.go(-1)" style="margin-left:10px;"/>
			</td>
          </tr>
          </table>
    </form>
    </td>
   <td width="50%">
   <div id="map" style="display:none">
   	<div id="container"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
				<input type="button" class="button" value="划定区域" onClick="getstart();"/>
				<input type="button" class="button" value="确定" onClick="getend()"/>
				<input type="button" class="button" value="清除描点" onClick="clearMap()"/>
            </td>
        </tr>
    </table>
</div>
   </div>
   </td>
   </tr> 
   </table>
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
        </div>
    </div>
    <!--新增收货人信息结束-->

</div>
<!--遮罩层结束-->
 </body>
<script>	
	var lock=true;
	//表单验证
	function validateForm(){
	   var name = $("#name").val();
	   if($.trim(name)==''){
	       $alert("warn","请填写门店名称！");
	       return false;
	   }else if(name.length>90){
	    	$alert("warn","门店名称不能超过90个字");
	      return false;
	   }
	   var storeHdId =$("#storeHdId").val();
	   if($.trim(storeHdId)==''){
	       $alert("warn","请填写门店海典ID！");
	       return false;
	   }else if(storeHdId.length>25){
	   $alert("warn","门店海典ID不能超过25个字");
	       return false;
	   }
	   var address =$("#address").val();
	   if($.trim(address)==''){
	       $alert("warn","请填写门店地址！");
	       return false;
	   }else if(address.length>150){
	   	$alert("warn","门店地址不能超过150个字");
	       return false;
	   }
	   
	   var cityCode =$("#cityCode").val();
	   if($.trim(cityCode)==''){
	       $alert("warn","请填写门店所在城市！");
	       return false;
	   }
	   var storeLatLon =$("#storeLatLon").val();
	   if($.trim(storeLatLon)==''){
	       $alert("warn","请填写门店经纬度！");
	       return false;
	   }else if(storeLatLon.length>90){
	   	$alert("warn","门店经纬度不能超过90个字");
	       return false;
	   }
	   var latLonRange= $("#latLonRange").val();
	   if($.trim(latLonRange)==''){
	       $alert("warn","请填写门店范围经纬度！");
	       return false;
	   }else if(latLonRange.length>1000){
	      $alert("warn","门店范围经纬度不能超过1000个字");
	       return false;
	   }
	   var startDt= $("#startDt").val();
	   if($.trim(startDt)==''){
	       $alert("warn","请填写门店经营开始日期！");
	       return false;
	   }
	   var endDt= $("#endDt").val();
	   if($.trim(endDt)==''){
	       $alert("warn","请填写门店经营结束日期！");
	       return false;
	   }
	   var re =/^[0-9]+$/;
	   var sort= $("#sort").val();
	       if($.trim(sort)==''){
	          $alert("warn","排序不能为空！");
	           return false;
	       }else{
	         if(!re.test(sort)){
	           $alert("warn","排序只能为大于0的整数");
	           return false;
	         }
	    }
	  return  true;
	}
	//提交订单
	function saveOrder(){
	if(lock){
	lock=false;
	   var formInfo=$("#form1").serialize();
	   if(!validateForm()){
	   	lock=true;
	      return ;
	   }
		$.ajax({
			url:"${base}/store/store!saveStore.action",
			type:"post",
			data:formInfo,
			success:function(data){
			lock=true;
				if(data!='0'){ 
					$alert("success","操作成功！");
					window.location.href="${base}/store/store!getDispatchList.action";
				}else if(data=='0'){
					$alert("error","操作失败，请稍后再试！");
				}
			},
			error:function(){
			      $alert("error","系统异常，请稍后再试！");
			}
		});
	}
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
   //选择城市
  function selectCity(citycode,cityname){
      $("#cityCode").val(citycode);
      $("#area").val(cityname);
      closeCity();
  }
	function closeMap(id){
	$("#"+id).hide();
	}
</script>
<script type="text/javascript">
    var  map= new AMap.Map("container", {
        resizeEnable: true,
        zoom:14
    });
    function mapCity(){
    map.clearMap();
    var cityCode=$("#cityCode").val();
    if($.trim(cityCode)==""){
     $alert("warn","请先选择城市!");
    }else{
	 map.setCity(cityCode);
    var cen=new Array();
	var str=$("#storeLatLon").val();
	if($.trim(str)==""){
	}else{
	var ss=str.split(",")
	cen[0]=[ss[0],ss[1]];
	 map.setZoom(14);
	var marker;
    marker=new AMap.Marker({
    		map:map,
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: cen[0]
        });
	}
    }
    }
	var markers = new Array();  
	var i=0;
	 // 实例化点标记
    function addMarker(obj) {
	var marker;
        marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [obj.lnglat.getLng(), obj.lnglat.getLat()]
        });
		markers[i++]=[obj.lnglat.getLng(), obj.lnglat.getLat()];
        marker.setMap(map);
    }

    //为地图注册click事件获取鼠标点击出的经纬度坐标范围
    var clickEventListener = map.on('click', function(e) {
	var status=$("#status").val();
    if(status==1){
    var marker;
    map.clearMap();
		map.remove(markers);
		markers=[];
		i=0;
    marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [e.lnglat.getLng(),e.lnglat.getLat()]
        });
      $("#storeLatLon").val(e.lnglat.getLng()+","+e.lnglat.getLat());
      marker.setMap(map);
    }else{
        addMarker(e);
    }
    });
   
   
 //在地图上绘制折线
    var editor={};
    map.setFitView();
	var polyedit;
	var  poly;
	
	function showPoly(){
	var storeid=$("#storeid").val();
	var cityCode=$("#cityCode").val();
	var strrang=$("#latLonRange").val();
	if($.trim(strrang)==""){
	markers=[];
	}else{
		var st=strrang.split(";");
		for(var j=0;j<st.length;j++){
		var l=st[j].split(",");
		markers[j]=[l[0],l[1]];
		}
	}
				poly= new AMap.Polygon({
					map: map,
					path: markers,
					strokeColor: "#0000ff",
					strokeOpacity: 1,
					strokeWeight: 3,
					fillColor: "#f5deb3",
					fillOpacity: 0.2
				});
		polyedit=new AMap.PolyEditor(map, poly);
		polyedit.open();
		map.remove(markers);
		
		$.ajax({
			url:"${base}/store/store!getStoreRangeByCity.action",
			type:"post",
			data:{"storeid":storeid,"cityCode":cityCode},
			success:function(data){
			var back_data=JSON.parse(data);
				if(back_data.code=="1"){ 
					var result=back_data.storeRange;
					for(var j=0;j<result.length;j++){
					  var polygonArr = new Array();//多边形覆盖物节点坐标数组
					  var tem=result[j].lat_lon_range.split(";");
					  for(var a=0;a<tem.length;a++){
					  	var b=tem[a].split(",");
					  	 polygonArr.push([b[0],b[1]]);
					  }
					  var polygon = new AMap.Polygon({
					        path: polygonArr,//设置多边形边界路径
					        strokeColor: "#FF33FF", //线颜色
					        strokeOpacity: 0.2, //线透明度
					        strokeWeight: 3,    //线宽
					        fillColor: "#1791fc", //填充色
					        fillOpacity: 0.35//填充透明度
   					 });
    				polygon.setMap(map);
    				
    				var m=result[j].store_lat_lon;
    				var c=m.split(",");
    				var n=result[j].storeName;
    				new AMap.Marker({
			    		map:map,
			            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			            position: [c[0],c[1]],
			            content:n
			        });
					}
				}
			},
			error:function(){
			}
		});
		
		
	}
	function getPolygon(){
			map.clearMap();
				poly= new AMap.Polygon({
					map: map,
					path: markers,
					strokeColor: "#0000ff",
					strokeOpacity: 1,
					strokeWeight: 3,
					fillColor: "#f5deb3",
					fillOpacity: 0.2
				});

	}

	function getstart(){
		getPolygon();
		polyedit=new AMap.PolyEditor(map, poly);
		polyedit.open();
		map.remove(markers);
	}

	function getend(){
	if($("#status").val()!=1){
		getPolygon();
		polyedit.close();
		var paths=poly;
		$("#latLonRange").val(paths);
	}
		closeMap('map');
	}
	function clearMap(){
	var sta=$("#status").val();
	if(sta==1){
	map.clearMap();
	}else{
		map.remove(poly);
		polyedit.close();
	}
		map.remove(markers);
		markers=[];
		i=0;
		}
		
	function openMap(id,sta){
	 var cityCode=$("#cityCode").val();
    if($.trim(cityCode)==""){
     $alert("warn","请先选择城市!");
    }else{
	$("#"+id).show();
	$("#status").val(sta);
	mapCity();
	if(sta==2){
	showPoly();
	}
	}
	}
</script>
</html>