<html>
<head> 
  <#include "/WEB-INF/pages/inc/taglibs.ftl">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>健康领秀分享文章列表</title> 
  <link type="text/css" rel="stylesheet" href="${base}/frame/css/style03.css" /> 
  <script src="${base}/web/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>
  <script src="${base}/web/js/alert.main.js" type="text/javascript" language="javascript" ></script>
  <style>
  .content:hover {background:#e6e6e6;}
  *{padding:0;margin:0;}
  </style>
</head> 
<body> 
<div class="shop_main"> 
    <div class="sm_user"> 
    <div class="order">
    <!--查询条件开始-->
    <form name="form1" id="form1"  action="${base}/wxam/wxam!list.action" method="post" style="width:99.65%;">
    <div id="order_form" class="order_form" style="padding-bottom:25px;padding-top:20px;"> 
     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage" />
		<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize" />		
        <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
          <tbody> 
          <tr> 
           <td style="width:200px;">
           <span style="float:left;">创建时间：</span><input  type="text" class="input input_v1" value="${start_date?default('')}" name="start_date" id="start_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" style="float:left;width:140px;"/>
		   </td>
		   <td style="width:170px;">
		   <span style="float:left;">—</span><input  type="text" class="input input_v1"  value="${end_date?default('')}" name="end_date" id="end_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  style="float:left;width:140px;"/>
		   </td>
           <td style="width:210px;">文章标题： <input  name="title" type="text" value="${title?default('')}" class="input input_v1" style="width:140px;"/></td> 
           <td style="width:210px;">海典编码： <input  name="sn" type="text" value="${sn?default('')}" class="input input_v1" style="width:140px;"/></td> 
           <td style="width:250px;"> 
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_TONGZHI">
          		<input type="button" class="btn01" id="notify_btn" value="通知" onclick="notify()" style="float:left;margin-left:5px;"/>
           		</@security.authorize>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_CHAXUN">
           		<a href="javascript:;" onclick="submitForm()" style="float:left;margin-left:5px;margin-top:4px;color:#1779d2;text-decoration:none;">查询</a>
           		</@security.authorize>
           		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_TIANJIA">
          		<a href="javascript:;" onclick="window.location.href='/wxam/wxam!add.action';" style="float:left;margin-left:7px;margin-top:4px;color:#1779d2;text-decoration:none;">添加</a>
           		</@security.authorize>
          		<a href="javascript:;" style="float:left;margin-left:7px;margin-top:4px;color:#1779d2;text-decoration:none;" onclick="staticPages()">静态化</a>
          		<select style="width:60px;height:25px;border:#ccc 1px solid;float:left;background:#f5f5f5;margin-left:5px;" id="static_page_type">
            		<option value="0">请选择</option>
            		<option value="2">宽屏版</option>
            		<option value="1">普通版</option>
            	</select>
            </td> 
            </tr> 
            </tbody> 
        </table> 
        </div> 
      </div> 
      </form> 
     <!--查询条件结束->
     
     <!--显示列表开始-->
     <div class="order_tbl" style="width:99.7%;margin-top:-23px;border-top:none;"> 
        <table class="table-list"> 
        <thead style="background:#dbf1fc"> 
        <tr> 
         <th width="4%" class="b-l"> 
         	<input type="checkbox" onclick="checkOrNot()"/>
         	<input type="hidden" id="verify" value="1"/>
         </th> 
         <th width="5%" class="b-l"> 序号 </th> 
         <th width="6%">是否发布</th> 
         <th> 文章标题</th> 
         <th> 分享缩略图 </th> 
         <th> 关联商品</th> 
         <th> 分享语</th>
         <th> 创建时间</th> 
         <th> 修改时间</th> 
         <th> 发布时间</th> 
         <th> 权重</th> 
         <th width="27%" style="border-right:#ccc 1px solid;"> 操作 </th> 
        </tr> 
       </thead> 
       <tbody> 
        <#list pw.result?if_exists as resc>
        <tr class="content"> 
         <td align="center">
         	<input type="checkbox" name="checkname" value="${resc.title?default('')}"/>
         </td>
         
         <td align="center"> 
         ${resc_index+1?default('')}	
         </td> 
         
         <td align="center">
         	<#if resc.status == 0><span style='color:red;'>否</span></#if>
         	<#if resc.status == 1>是</#if>
         </td>
         
         <td align="center">
         ${resc.title?default('')}		
         </td>
         <td align="center"> 
         	<img src="${resc.share_image_url?default('')}" width=50 height=50/>		
         </td> 
         <td align="center"> 
          	<a href="javascript:;" onclick="lookUpGoods('${resc.id}','${resc_index+1}','${resc.title?default('')}')">查看商品</a>
         </td> 
         <td align="center"> 
         ${resc.share_title?default('')}	
         </td> 
         <td align="center"> 
         <#if resc.create_dt?exists>${resc.create_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>   
         <td align="center"> 
         <#if resc.update_dt?exists>${resc.update_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>   
         <td align="center"> 
         <#if resc.send_dt?exists>${resc.send_dt?string('yyyy-MM-dd HH:mm:ss')}</#if>		
         </td>   
         <td align="center">${resc.weight?default(0)}</td> 
                
         <td align="center"> 
            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_YULAN">
            <input type="button" class="btn01" value="预览" id="priew_btn_${resc_index+1}" style="float:left;margin-left:1px;" onclick="previewPage('${resc.id}','${resc_index+1}','${resc.title?default('')}')"/>
            </@security.authorize>

            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_FABU">
            <input type="button" class="btn01" value="发布" id="publish_btn_${resc_index+1}" style="float:left;margin-left:1px;" onclick="publishPage('${resc.id}','${resc_index+1}')"/>
            </@security.authorize>
            
            <@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_JIANKANGLINGXIUGUANLI_WEIXINWENZHANGLIEBIAO_BIANJI">
            <a href="javascript:;" onclick="window.location.href='/wxam/wxam!edit.action?aId=${resc.id}';" style="float:left;margin-left:1px;margin-top:2px;">编辑</a>
            </@security.authorize>
            
            <a href="javascript:;" style="float:left;margin-left:5px;margin-top:2px;" onclick="delPage('${resc.id}','${resc_index+1}')">删除</a>
            
            <select id="type_${resc_index+1}" style="width:60px;height:25px;border:#ccc 1px solid;float:left;background:#f5f5f5;margin-left:5px;">
            	<option value="2" <#if resc.format_type?exists><#if resc.format_type == 2>selected="selected"</#if></#if> >宽屏版</option>
            	<option value="1" <#if resc.format_type?exists><#if resc.format_type == 1>selected="selected"</#if></#if> >普通版</option>
            </select>
            
         </td> 
        </tr> 
        </#list>
        <tr> 
         <td colspan="5" class="blank"></td> 
        </tr> 
       </tbody> 
      </table>
      <br /> 
      <!--显示列表开始-->
      
      <div style="display:none;"></div>
       	<#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     </div> 
    </div> 
   </div> 
  </div>  
  
  <!----------------------查看商品--------------------------->
  <style>
  .lookGoods {display:none;position:fixed;width:400px;height:330px;top:5%;left:35%;background:#f9f9f9;border:#CCC 1px solid;z-index:2;overflow-y:auto;overflow-x:hidden;}
  .title_close {float:left;width:100%;height:35px;line-height:35px;background:#dbf1fc;border-bottom:#ccc 1px solid;}
  .title_close span {float:left;font-size:12px;color:#000;font-style:normal;margin-left:10px;width:340px;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow: hidden;}
  .title_close img {float:right;width:35px;height:35px;border-left:#CCC 1px solid;cursor:pointer;}
  .mask {display:none;position:fixed;top:0;left:0;width:99%;height:100%;background:#000;opacity:.1;z-index:1;}
  .title_close span.one {float:left;font-size:12px;color:#000;font-style:normal;width:70px;text-align:center;border-right:#e6e6e6 1px solid;}
  .title_close span.two {float:left;font-size:12px;color:#000;font-style:normal;width:120px;text-align:center;border-right:#e6e6e6 1px solid;margin-left:0px;}
  .title_close span.three {float:left;font-size:12px;color:#000;font-style:normal;width:70px;text-align:center;border-right:#e6e6e6 1px solid;margin-left:0px;}
  .title_close span.four {float:left;font-size:12px;color:#000;font-style:normal;width:70px;text-align:center;margin-left:0px;border-right:#e6e6e6 1px solid;}
  .title_close span.five {float:left;font-size:12px;color:#000;font-style:normal;width:50px;text-align:center;margin-left:0px;}
  .none {height:60px;border-right:#e6e6e6 1px solid;text-align:center;}
  .none img {float:left;width:50px;height:50px;margin-left:7px;margin-top:5px;}
  .ntwo {height:60px;border-right:#e6e6e6 1px solid;line-height:60px;}
  .nthree {height:60px;border-right:#e6e6e6 1px solid;line-height:60px;}
  .nfour {height:60px;border-right:#e6e6e6 1px solid;line-height:60px;}
  .nfive {height:60px;line-height:60px;}
  </style>
  <div class="mask"></div>
  <div class="lookGoods">
  	<div class="title_close">
  		<span id="title_GL"></span>
  		<img src="${base}/web/images/close.png" onclick="closeMask()"/>
  	</div>
  	
  	<div class="title_close" style="background:#f5f5f5;border-bottom:#e6e6e6 1px solid;">
  		<span class="one">推广图片</span>
  		<span class="two">商品名称</span>
  		<span class="three">海典编码</span>
  		<span class="four">返佣</span>
  		<span class="five">权重</span>
  	</div>
  	
  	<div id="contentAppend">
  	</div>
  	
  	
  </div>
  
  
 </body>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script>	
	//关闭遮罩层
	function closeMask()
	{
		$(".mask").fadeOut();
		$(".lookGoods").fadeOut();
	}
	
	function lookUpGoods(articleId, index, title)
	{
		$(".mask").fadeIn();
		$(".lookGoods").fadeIn();
		$("#title_GL").html("标题【"+title+"】的关联商品");
		$("#contentAppend").html("");
		var url = "/wxam/wxam!lookTuiJianGoodsByArticleId.action?articleId="+articleId;
		jQuery.ajax
		({
			type: "post",
			url: url,
			success: function(data) 
			{  
				if(data.length > 0)
				{
				    var htmlObj = "";
					for(var i=0; i<data.length; i++)
					{
						var imgUrl = data[i].imgurl;
						var goodsname = data[i].shortname; 
						var goodsno = data[i].goodsno; 
						var weight = data[i].weight;
						var rebate_amount = data[i].rebate_amount;
						htmlObj +=
						  	"<div class='title_close' style='background:#f9f9f9;border-bottom:#e6e6e6 1px solid;height:60px;'>"
						  		+"<span class='one none'><img src='"+imgUrl+"'/></span>"
						  		+"<span class='two ntwo'>"+goodsname+"</span>"
						  		+"<span class='three nthree'>"+goodsno+"</span>"
						  		+"<span class='four nfour'>"+rebate_amount+"</span>"
						  		+"<span class='five nfive'>"+weight+"</span>"
						  	+"</div>";
					}
					$("#contentAppend").append(htmlObj);
				}
	        },  
	        error: function() 
	        {  
	            console.log("Server error !"); 
	        }  
		});
		
	}
	
	function submitForm(){
	 	$("#p_curPage").val("1");
	 	$("#form1").submit();
	}
	
	//预览静态页
	function previewPage(articleId, index, title)
	{
		var type = $.trim($("#type_"+index).val());
		$("#priew_btn_"+index).val("...");
		$("#priew_btn_"+index).attr("disabled",true);
		var burl = "${base}/wxam/wxam!preview.action?articleId="+articleId+"&type="+type;
      	jQuery.ajax
		({
			type: "post",
			url: burl,
			success: function(url) 
			{  
			    $("#priew_btn_"+index).val("预览");
				$("#priew_btn_"+index).attr("disabled",false);
				
				url = "http://m.111yao.com/"+url;
				
				var img = document.getElementById("codePriview");  
   				img.src="${base}/static/inc/wxCode.jsp?url="+url+"&s="+ Math.random();
   				$(".title_c").html(title);
   				$(".imgCode").show();
   				$(".mask").show();
	        },  
	        error: function() 
	        {  
			    $("#priew_btn_"+index).val("预览");
				$("#priew_btn_"+index).attr("disabled",false);
	            console.log("Server error !"); 
	        }  
		});
	}
	
	//隐藏二维码
	function hideCode()
	{
	    $(".imgCode").hide();
   		$(".mask").hide();
	}
	
	//发布文章页面
	function publishPage(articleId, index)
	{
		if(confirm("您确定要发布此文章么？"))
		{
			var type = $.trim($("#type_"+index).val());
			$("#publish_btn_"+index).val("...");
			$("#publish_btn_"+index).attr("disabled",true);
			var burl = "${base}/wxam/wxam!publish.action?articleId="+articleId+"&type="+type;
	      	jQuery.ajax
			({
				type: "post",
				url: burl,
				success: function(url) 
				{  
				    $("#publish_btn_"+index).val("发布");
					$("#publish_btn_"+index).attr("disabled",false);
					window.top.open(url,"_blank");
					window.setTimeout(function(){
						window.location.reload();
					},1000);
		        },  
		        error: function() 
		        {  
				    $("#publish_btn_"+index).val("发布");
					$("#publish_btn_"+index).attr("disabled",false);
		            console.log("Server error !"); 
		        }  
			});
		}
	}
	
	//静态化文章
	function staticPages()
	{
		var v = $("#static_page_type").val();
		if(v == 0)
		{
			$alert("warn","请选择板式！");
			return;
		}
		if(confirm("您确定要生成全部文章吗？可能要花几分钟的时间，请耐心等待哦！"))
		{
			$(".loading-ui").show();
			$(".mask").show();
			var burl = "/wxam/wxam!staticPages.action?type="+v;
	      	$.ajax
			({
				type: "POST",
				url: burl,
				success: function() 
				{  
					$alert("success","已生成全部文章！");
					$(".loading-ui").hide();
					$(".mask").hide();
					window.setTimeout(function(){
						window.location.reload();
					},1000);
		        },  
		        error: function(e) 
		        {  
		            console.log("e=["+e+"]"); 
		            $(".loading-ui").hide();
					$(".mask").hide();
		        }  
			});
		}
	}
	
	//删除文章
	function delPage(articleId, index)
	{
		if(confirm("您确定要删除此文章么？"))
		{
			var burl = "${base}/wxam/wxam!delDate.action?id="+articleId;
	      	jQuery.ajax
			({
				type: "post",
				url: burl,
				success: function(data) 
				{  
					window.setTimeout(function()
					{
						window.location.reload(true);
					},500);
		        },  
		        error: function(e) 
		        {  
		        	console.log(e);
		        }  
			});
		}
	}
	
	//向健康领秀会员发送通知
	function notify()
	{
		var chk_value = []; 
		$('input[name="checkname"]:checked').each(function()
		{ 
			var v = $(this).val();
			v = "《"+v+"》";
			chk_value.push(v); 
		});
		if(chk_value.length == 0)
		{
			$alert("warn","你还没有选择任何内容！");
		}
		else
	    {
	    	//开启ＵＩ提示
	    	$("#notify_btn").val("...");
			$("#notify_btn").attr("disabled",true);
			$(".loading-ui").show();
			$(".mask").show();
	    	
	    	var s = ""; 
			for(var i=0; i<chk_value.length; i++)
			{
				s += chk_value[i] +"、";
			} 
			s = s.substring(0,s.length-1);
			//发送消息
			$.ajax
			({
				type: "post",
				url: "${base}/wxam/wxam!sendMessageToLeader.action?titles="+s,
				success: function(data) 
				{  
					$("input[type='checkbox']").attr("checked",false);
				    $("#notify_btn").val("通知");
					$("#notify_btn").attr("disabled",false);
					$(".loading-ui").hide();
					$(".mask").hide();
					$(".builtsuccess").show();
					$(".builtsuccess").find("span.r").html("恭喜已成功发送（"+data.ok+"）人，失败（"+data.fail+"）人！");
					window.setTimeout(function()
					{
						$(".builtsuccess").hide();
					},3000);
		        },  
		        error: function() 
		        {  
		            console.log("Server error !"); 
		            $("#notify_btn").val("通知");
					$("#notify_btn").attr("disabled",false);
					$(".loading-ui").hide();
					$(".mask").hide();
		        }  
			});
	    }
	} 
	
	
	//全选
	function checkOrNot()
	{
		var v = $.trim($("#verify").val());
		if(v == 1)
		{
	        $("input[type='checkbox']").attr("checked",true);
	        $("#verify").val("2");
		}
		else
		{
			$("input[type='checkbox']").attr("checked",false);
			$("#verify").val("1");
		}
    }
</script>
</html>
<div class="loading-ui"><img src="../web/images/loading_img.jpg"/></div>
<div class="mask-ui"></div>
<div class="builtsuccess" style="width:300px;">
	<img src="../web/images/success.gif" style="margin-top:10px;"/>
	<span class="r" style="margin-left:0px;"></span>
</div>

<!-- 二维码预览页面 -->
<div class="imgCode">
	<div class="closeImg">
		<span class="title_c"></span>
		<img class="close_i" src="${base}/web/images/error.png" onclick="hideCode()"/>
	</div>
	<img id="codePriview"/>
</div>

<style>
.imgCode {position:fixed;left:42%;top:30%;width:200px;display:none;z-index:99;}
.closeImg {float:left;width:200px;background:#fff;}
.close_i {float:right;width:25px;height:25px;cursor:pointer;}
.title_c {float:left;width:160px;height:25px;line-height:25px;font-style:normal;padding-left:10px;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;}
#codePriview {float:left;width:200px;height:200px;left:42%;top:30%;border:none;}

* {font-size:12px;}
.btn01 {font-size:12px;}
.mask-ui {display:none;position:fixed;_position:absolute;width:100%;height:100%;top:0;left:0px;background-color:#000;opacity:.1;filter:alpha(opacity=10);z-index:5;}
.loading-ui {display:none;position:fixed;_position:absolute;width:100%;height:auto;text-align:center;z-index:99;}
.loading-ui img {width:35px;height:35px;margin:auto;margin-top:180px;}
.builtsuccess {display:none;position:fixed;_position:absolute;border:#ccc 1px solid;background-color:#fff;width:385px;height:50px;box-shadow: 1px 1px 6px #999999;top:15%;left:35%;border-radius:5px;}
.builtsuccess img {float:left;margin-top:10px;margin-left:15px;}
.builtsuccess span.r {float:left;height:50px;line-height:50px;margin-left:4px;font-style:normal;}
</style>