<div id="BgDiv2" class="BgDiv"></div>
    <div id="DialogDiv2" style="display:none" class="DialogDiv">
        <div style="height:400px; width:970px; position:relative; overflow-y:scroll;border:1px solid #666;" >
    
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
	var preid = '';
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
	var trHtml ='<tr id="ptrv'+pindex+'"><td><input type="hidden" name="preids" value="'+ id+'" num="'+num+'" >'+ name+'</td><td>'+ spec+'</td><td>'+num+'</td><td><a href="javascript:;" onclick="delpre(' + pindex + ',&quot;'+id+'&quot;,&quot;'+name+'&quot;,&quot;'+spec+'&quot;);">删除</a></td></tr>';
	$("#ptrv").after(trHtml);
	gindex ++;
}
function delpre(ind,id,name,spec){
	var html = "<a href='javascript:void(0)' onclick='addpre(&quot;"+id+"&quot;,&quot;"+name+"&quot;,&quot;"+spec+"&quot;)'>添加</a>";
	$("#ptds"+id).html(html);
	$("#ptrv"+ind).remove();
}
</script>