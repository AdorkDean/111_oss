<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>商品推荐</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<#include "/WEB-INF/pages/inc/common.ftl">
</head> 
<body data-pc="${listcount.pcnum}" data-wap="${listcount.wapnum}" data-app="${listcount.appnum}"> 
	 <!-- 顶部分页查询区域 Start -->
     <div id="order_form" class="order_form" style="float:left;width:100%;"> 
	     	<form name="form1" id="form1" action="${base}/goods/push!list.action" method="post">
		     	<input type="hidden" name="rs.p_curPage" value="${rs.p_curPage?default(1)}" id="p_curPage">
				<input type="hidden" name="rs.p_pageSize" value="${rs.p_pageSize?default(20)}" id="pageSize">
	            <h2 style="margin:10px;">商品推荐</h2>
	            <select id="platform"  name="platformType" style="margin:3px 10px;width:80px;height:25px;float:left">
					 <option <#if platformType?default(1)==1>selected</#if> value="1">官网PC</option>
					 <option <#if platformType?default(1)==2>selected</#if> value="2">wap</option>
					 <option <#if platformType?default(1)==3>selected</#if> value="3">app</option>
			 	</select>
	            <div class="lableName">
		            <a href="javascript:void(0)" onclick="tmOption.add()" style="margin-left:5px;margin-top:2px;" class="a-button">添加</a>
		            <a href="javascript:void(0)" onclick="tmOption.update()" style="margin-left:5px;margin-top:2px;" class="a-button">修改</a>
		            <a href="javascript:void(0)" onclick="tmOption.save()" style="margin-left:5px;margin-top:2px;" class="a-button">保存</a>
	            </div>
	      </form> 
     </div> 
     <!-- 顶部分页查询区域 End -->
    <div class="shop_main" style="font-family:'Microsoft Yahei';"> 
	    <table class="table-list" style="width:100%">
			 <!-- 标签标题  -->
		    <thead style="background:#dbf1fc;"> 
			   	 <tr style="height:40px;" class="content">
			        <td width="5%" align="center">海典编号</td>
			        <td width="15%" align="center">商品名称</td>
			        <td width="3%" align="center">排序(权重)</td>
			        <td width="6%" align="center">平台类型</td>
			        <td width="3%" align="center">提示</td>
			        <td width="8%" align="center">操作</td>
			   	 </tr>
			 </thead>
			 <!-- 查询内容  -->
		     <tbody data-count='${pw.pageInfo.count}'> 
				 <#list pw.result as tm>
				 <tr class="content" style="text-align:center;" data-opid="${tm.id}" id="tr_record_${tm_index+1}">
				 	<td id="params_${tm.id}">
				 		<input type="hidden" id="goods_${tm.id}" name="goodsSell.id" value="${tm.goodsId?if_exists}"/>
				 		<input type="text" style="line-height:32px;width:100%;text-indent:1em;" id="goodsno_${tm.id}" disabled="true" onblur="tmOption.validate(this);" maxlength="14" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" value="${tm.goodsno?default('')}"/>
				 	</td>
				 	<td id="change_${tm.id}">
						${tm.goodsname}
					</td>
				 	<td>
				 		<input type="text" id="change_order_${tm.id}" style="line-height:32px;width:100%;text-indent:1em;" name="goodsSell.weight" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')" disabled="true" value="${tm.weight?default(0)}"/>
				 	</td>
				 	<td>
				 		<select id="platform_${tm.id}" disabled="true" name="goodsSell.platformType" style="margin:3px 16px;width:80%;height:25px;float:left">
						 <option <#if tm.platformType?default(1)==1>selected="selected"</#if> value="1">官网PC</option>
						 <option <#if tm.platformType?default(1)==2>selected="selected"</#if> value="2">wap</option>
						 <option <#if tm.platformType?default(1)==3>selected="selected"</#if> value="3">app</option>
				 		</select>
				 	</td>
				 	<td id="tmtip_${tm.id}">
				 		<i id="success_${tm.id}" style="display:inline-block;display:none;width:24px;height:24px;background:url(${base}/web/images/list.gif) -150px -180px no-repeat;"></i>
				 		<i id="error_${tm.id}" style="display:inline-block;display:none;width:24px;height:24px;background:url(${base}/web/images/list.gif) -180px -180px no-repeat;"></i>
				 	</td>
				 	<td align="center">
				 		<input type="button" id="tmremove" class="btn01" onclick="tmOption.del(this)" value="删除" style="margin-left:5px;"/>
				 	</td>
				 </tr>
				 </#list>
			</tbody>
	    </table>   
     <!-- 分页 Start --> 
     <#include "/WEB-INF/pages/inc/pagebox_condition.ftl">
     <!-- 分页 End --> 
    </div>        
    <script type="text/javascript">
      /*给select#platform绑定change事件*/
      $("#platform").bind("change",function(){ 
	    if($(this).val()==""){
	      return;
	    }else{
	      $("#form1").submit();//触发form表单实现对应查询业务
	    } 
	  });
	  
	  /*商品推荐业务js代码*/
	  var tmOption = {
	  	num:$("tbody").data("count"),
	  	mark:true,//禁用新增按钮
	  	flag:true,//禁用保存按钮
	  	/*添加操作*/
	  	add:function(){
	  		if(tmOption.mark){
		  		tmOption.num = tmOption.num + 1;
	  			$("tbody td input").attr("disabled", false);
	  			$("tbody").prepend(
	  			"  <tr class='content' style='text-align:center;' data-opid='"+tmOption.num+"' id='tr_record_"+tmOption.num+"'>"+
				" 	<td id='params_"+tmOption.num+"'>"+
				" 		<input type='hidden' id='goods_"+tmOption.num+"' name='goodsSell.id' value=''/>"+
				" 		<input type='text' style='line-height:32px;width:100%;text-indent:1em;' maxlength='14' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d]/g,'')\" id='goodsno_"+tmOption.num+"' disabled='true' onblur='tmOption.validate(this);' value=''/>"+
				" 	</td>"+
				" 	<td id='change_"+tmOption.num+"'></td>"+
				" 	<td>"+
				" 		<input type='text' id='change_order_"+tmOption.num+"' style='line-height:32px;width:100%;text-indent:1em;' maxlength='2' onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^\\d]/g,'')\" name='goodsSell.weight' disabled='true' value=''/>"+
				" 	</td>"+
				"	<td>"+
				" 		<select id='platform_"+tmOption.num+"' name='goodsSell.platformType' style='margin:3px 16px;width:80%;height:25px;float:left'>"+
				"		 <option <#if platformType?default(1)==1>selected</#if> value='1'>官网PC</option>"+
				"		 <option <#if platformType?default(1)==2>selected</#if> value='2'>wap</option>"+
				"		 <option <#if platformType?default(1)==3>selected</#if> value='3'>app</option>"+
				"		</select>"+
				" 	</td>"+
				" 	<td id='tmtip_"+tmOption.num+"'>"+
				"	<i id='success_"+tmOption.num+"' style='display:inline-block;display:none;width:24px;height:24px;background:url(${base}/web/images/list.gif) -150px -180px no-repeat;'></i>"+
				" 	<i id='error_"+tmOption.num+"' style='display:inline-block;display:none;width:24px;height:24px;background:url(${base}/web/images/list.gif) -180px -180px no-repeat;'></i>"+
				"  </td>"+
				" 	<td align='center'>"+
				" 		<input type='button' id='tmremove' class='btn01' onclick='tmOption.del(this)' value='删除' style='margin-left:5px;'/>"+
				" 	</td>"+
				" </tr>");
				tmOption.mark = false;
				tmOption.flag = false;
				$("tbody td input").attr('disabled', false);
				$("tbody td select").attr('disabled', false);
	  		}else{
	  			$alert('warn',"您有必填的选项没有填写！");
	  		}
	  	},
	  	/*（批量）保存操作*/
	  	save:function(){
	  		if(tmOption.flag){
	  			$alert('warn',"没有做任何操作不能保存！");
	  			return;
	  		}
	  		var params = "";
  			var ops = $("tbody tr");
  			var pcnum=wapnum=appnum=0;
	  		for(var i=0;i<ops.length;i++){
	  			var tmopid = ops.eq(i).attr("data-opid");
  				var goodsId = $("#goods_"+tmopid).val();
  				var goodsno = $("#goods_"+tmopid).siblings().val();
  				if(!goodsno){$alert("warn","海典编号是必填项，请检查！");/*$("#goods_"+tmopid).siblings().focus();*/return;}
  				var weight = $("#change_order_"+tmopid).val();
	  			var platformType = $("#platform_"+tmopid).val();
	  			if(platformType==1){pcnum++;}
	  			if(platformType==2){wapnum++;}
	  			if(platformType==3){appnum++;}
	  			/*过滤最后一组数据后拼接的#*/
	  			if(i==ops.length-1&&i==0){
	  				params += tmopid+","+goodsId+","+weight+","+platformType;
	  			}else{
		  			params += tmopid+","+goodsId+","+weight+","+platformType+"#";
	  			}
	  		}
	  		var over = $("#platform").val();
	  		var pc = $("body").data("pc");
	  		var wap = $("body").data("wap");
	  		var app = $("body").data("app");
	  		if((over==1?(pcnum-wapnum-appnum):(pcnum+pc))>10){$alert('warn',"官网PC超过10条数据！");return;}
	  		if((over==2?(wapnum-pcnum-appnum):(wapnum+wap))>10){$alert('warn',"WAP超过10条数据！");return;}
	  		if((over==3?(appnum-pcnum-wapnum):(appnum+app))>10){$alert('warn',"APP超过10条数据！");return;}
	  		/*批量保存怎么把页面的值传给action层呢？*/
	  		$.ajax({
	  			type:"post",
	  			url:"${base}/goods/push!batchSave.action",
	  			data:{"params":params},
	  			success:function(data){
	  				if(data=="pcfail"){
	  					$alert('warn',"官网PC超过10条数据！");
	  				}else if(data=="wapfail") {
	  					$alert('warn',"WAP超过10条数据！");
					}else if(data=="appfail"){
	  					$alert('warn',"APP超过10条数据！");
					}else if(data=="warn"){
						$alert('warn',"有编号已被占用不能修改！");
					}else{
	  					if(data>0){
		  					$alert('success',"恭喜！保存成功。");
		  					tmOption.flag = true;
		  					$("#form1").submit();
		  				}else{
		  					$alert('error',"对不起！保存失败。");
		  				}
	  				}
	  			}
	  		});
	  	},
	  	/*修改操作*/
	  	update:function(){
	  		tmOption.flag = false;
	  		$("tbody td input").attr('disabled', false);
	  		$("tbody td select").attr('disabled', false);
	  	},
	  	/*通过id删除对应的记录*/
	  	del:function(obj){
	  		if(confirm("确定要删除吗？")){
		  		var opid = $(obj).parents("tr").data("opid");
		  		$.ajax({
		  			type:"post",
		  			url:"${base}/goods/push!ajaxDel.action",
		  			data:{"opid":opid},
		  			success:function(data){
		  				if(data>0){
		  					$alert('success',"恭喜！删除成功");
		  				}else{
		  					tmOption.mark = true;
		  				}
		  			}
		  		});
		  		$(obj).parents("tr").remove();
	  		}
	  	},
	  	/*给已经被其他平台占用了的海典编号提示过滤掉该编号*/
	  	isGoodsnoExist:function(goodsno,trs){
	  		var ops = trs.siblings();
	  		for(var i=0;i<ops.length;i++){
	  			var tmopid = ops.eq(i).attr("data-opid");
  				var goodsId = $("#goodsno_"+tmopid).val();
  				if(goodsno==goodsId){return false;}
  			}
  			return true;
	  	},
	  	/*通过海典编号对应input框失去焦点*/
	  	validate:function(obj){
	  		var goodsno = $(obj).val();
	  		var $tr = $(obj).parents("tr");
	  		var opid = $tr.data("opid");
	  		if(!goodsno){
	  			$alert('warn',"海典编号不能为空，请重新输入！");
	  			//$(obj).focus();
	  			return;
	  		}
	  		$.ajax({
	  			type:"post",
	  			url:"${base}/goods/push!ajaxGoodNameBycode.action",
	  			data:{"goodsno":goodsno},
	  			success:function(data){
					if(data){
						if(data==null||data==""){
							tmtip();
						}else{
							/*判断编号是否已经被其他平台（pc wap app）占用*/
							if(tmOption.isGoodsnoExist(data.goodsno,$tr)){//没有占用
								change(data.id,data.goods_name);//进行对应位置的相关商品信息更新
							}else{//已经被占用，给出相应提示
								$alert('warn',data.goodsno+"编号已被使用！");
							}
						}
					}else{
						tmtip();
					}
	  			}
	  		});
	  		/*如果有该编号就执行改变相关操作的change函数*/
	  		function change(goodsid,gname){
	  			$alert('success',"正确！请排序");
	  			$("#change_"+opid).text(gname);//根据海典编号更新商品名称
	  			$("#goods_"+opid).val(goodsid);//根据海典编号更新商品id
	  			$("#success_"+opid).css({"display":"block","margin-left":"33%"}).siblings().css("display","none");
	  			$("#change_order_"+opid).select();//选中排序列进行排序
	  			$("#change_order_"+opid).val(0);//将该编号的排序列设为0
	  			tmOption.mark = true;
	  		}
	  		/*如果没有该编号就执行提示信息的tmtip函数*/
	  		function tmtip(){
	  			$alert('error',"没有该编号请重新输入");
	  			$(obj).select();//将海典编号列无效编号选中
	  			$("#error_"+opid).css({"display":"block","margin-left":"33%"}).siblings().css("display","none");
	  			$("#change_"+opid).text("");//清空海典名称列文本
	  			$("#change_order_"+opid).val("");//清空排序列数据
	  			tmOption.mark = false;
	  		}
	  	}
	  };
	</script>
</body>
</html>