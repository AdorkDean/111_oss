<div id="BgDiv2" class="BgDiv"></div>
    <div id="DialogDiv2" style="display:none" class="DialogDiv">
        <div style="height:400px; width:970px; position:relative; overflow-y:scroll;border:1px solid #666;">
    
    <table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac" style="height:20px;">
		<tr>
	    <td colspan="5">
	    	名称：<input type="text" id="pname"/>
			编号：<input type="text" id="pno"/>
			<input type="button" value="搜  索" onclick="getpre();"/>
			<input type="button" name="cancel" id="btnClose2" value="关闭" />
		</td>
		</tr>
		
		<tr id="pretr">
			<td>名称</td>
			<td>规格</td>
			<td>数量</td>
			<td>操作</td>
		</tr>
	</table>
		</div>
</div>
<script type="text/javascript">
function getpre(){
	var preid = $("#goodsid").val()+',';
	$("#DialogDiv2 input[name=trds]").each(function(){
		$(this).parent().parent().remove();
	});
	$("#cont_tow_6 input[name=preids]").each(function(){
		preid = preid+$(this).val()+",";
	});
	jQuery.ajax({
	       type: "post",
	       url: " ${base}/goods/goods!goodsPremiumsList.action",	  
	       data:{"goodsf.shortname":$("#pname").val(),"goodsf.goodsno":$("#pno").val(),"goodspids":preid},
	       success: function(data){
	      	 $("#pretr").after(data);
	       },error:function(){
	       		$alert('error',"网络异常!");
	       }
	       
	   }); 
}
var pindex = 0;
function addpre(id,name,spec){
	$("#ptds"+id).html("成功!");
	var num = $("#num"+id).val();
	var trHtml ='<tr id="ptrv'+pindex+'"><td><input type="hidden" name="preids" id="pid'+id+'"  value="'+ id+'" num="'+num+'" gpid="">'+ name+'</td><td>'+ spec+'</td><td>'+num+'</td><td><a href="javascript:;" onclick="delpre(&quot;ptrv' + pindex + '&quot;,&quot;'+id+'&quot;,&quot;'+name+'&quot;,&quot;'+spec+'&quot;,0);">删除</a></td></tr>';
	$("#ptrv").after(trHtml);
	gindex ++;
	jQuery.ajax({
	       type: "post",
	       url: " ${base}/goods/goods!addPremiums.action",	  
	       data:{"gid":$("#goodsid").val(),"gpid":id,"gpnum":num},
	       success: function(data){
	          if(data>0){
	          $("#pid"+id).attr("gpid",data);
	          }else{
	          	$alert('error',"网络异常!");
	          }
	       },error:function(){
	       		$alert('error',"网络异常!");
	       }
	       
	   }); 
	
	
}
function delpre(ind,id,name,spec,ty){
		 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
			return;
		}
	var html = "<a href='javascript:void(0)' onclick='addpre(&quot;"+id+"&quot;,&quot;"+name+"&quot;,&quot;"+spec+"&quot;)'>添加</a>";
	$("#ptds"+id).html(html);
	if(ty==0){
		id=$("#pid"+id).attr("gpid");
	}
	$("#"+ind).remove();
	jQuery.ajax({
	       type: "post",
	       url: " ${base}/goods/goods!delPremiums.action",	  
	       data:{"id":id},
	       success: function(data){
	          if(data>0){
	          }else{
	          	$alert('error',"网络异常!");
	          }
	       },error:function(){
	       		$alert('error',"网络异常!");
	       }
	       
	   }); 
}
</script>