<div id="BgDiv1" class="BgDiv"></div>
    <div id="DialogDiv1" style="display:none" class="DialogDiv">
        <div style="height:400px; width:970px; position:relative; overflow-y:scroll; border:1px solid #666;">
    
    <table border="0px" align="center" cellpadding="5" cellspacing="1" class="tac" style="height:20px;">
		<tr>
	    <td colspan="5">
	    	名称：<input type="text" id="gname"/>
			编号：<input type="text" id="gno"/>
			<input type="button" value="搜  索" onclick="getGroup();"/>
			<input type="button" name="cancel" id="btnClose1" value="关闭" />
		</td>
		</tr>
		
		<tr id="grouptr">
			<td>名称</td>
			<td>规格</td>
			<td>是否主商品</td>
			<td>数量</td>
			<td>操作</td>
		</tr>
	</table>
		</div>
</div>
<script type="text/javascript">
function getGroup(){
	var goodsgid = '';
	$("#DialogDiv1 input[name=trds]").each(function(){
		$(this).parent().parent().remove();
	});
	$("#cont_tow_7 input[name=groupids]").each(function(){
		goodsgid = goodsgid+$(this).val()+",";
	});
	jQuery.ajax({
	       type: "post",
	       url: " ${base}/goods/goods!goodsGroupList.action",	  
	       data:{"goodsf.shortname":$("#gname").val(),"goodsf.goodsno":$("#gno").val(),"goodsgid":goodsgid},
	       success: function(data){
	      	 $("#grouptr").after(data);
	       },error:function(){
	       		$alert('error',"网络异常!");
	       }
	       
	   }); 
}
var gindex = 0;
function addgroup(id,name,spec){
	$("#gtds"+id).html("成功!");
	var num = $("#num"+id).val();
	var ism = $("#ism"+id).val();
	var ismv = "";
	if("0"==ism){
		ismv = "否";
	}else if("1"==ism){
		ismv = "是";
		$("#goodsgidsm").val(id);
		$("#cont_tow_7 span[name=spismv]").each(function(){
			$(this).html("否");
		});
	}
	var trHtml ='<tr id="gtrv'+gindex+'"><td><input type="hidden" name="groupids" value="'+ id+'" num="'+num+'" >'+ name+'</td><td>'+ spec+'</td><td><span name="spismv">'+ismv+'</span></td><td>'+num+'</td><td><a href="javascript:;" onclick="delGroup(' + gindex + ',&quot;'+id+'&quot;,&quot;'+name+'&quot;,&quot;'+spec+'&quot;);">删除</a></td></tr>';
	$("#grouptrv").after(trHtml);
	gindex ++;
}
function delGroup(ind,id,name,spec){
	var html = "<a href='javascript:void(0)' onclick='addgroup(&quot;"+id+"&quot;,&quot;"+name+"&quot;,&quot;"+spec+"&quot;)'>添加</a>";
	$("#gtds"+id).html(html);
	$("#gtrv"+ind).remove();
}
</script>