 function deleteRecord(id)
 {
	 if(!window.confirm("您确定要删除该记录吗?删除的数据不能恢复！")){
		 return;
     }
	jQuery.ajax
    ({
       type: "post",
       url: "/position/uploadImage!del.action",	  
       data:{"id":id},
       success: function(data)
       {
          if(data>0)
          {
        	  $alert("success","操作成功");
          	  $('#tr_record_'+id).remove();
          }
          else
          {
        	  $alert("error","网络异常");
          }
       },
       error:function()
       {
    	   $alert("error","网络异常");
       }
       
     }); 
}
 
 
$(function()
{
		$("input[name=copy]").each(function()
		{
		ZeroClipboard.setMoviePath("/web/js/zeroclipboard-1.0.7/ZeroClipboard.swf");
		var clip = new ZeroClipboard.Client(); 	
		clip.setHandCursor(true); 
		var obj=$(this);
		var objId =$(this).attr("id");
		clip.glue(objId);
		clip.addEventListener("mouseDown", function(client) {  
			    clip.setText($(obj).attr("data")); 
			}); 
		clip.addEventListener("mouseOver", function(client) {  
		   $(obj).css("color","#f1680c");
		});   
		clip.addEventListener( "mouseOut", function(client) {         
		    $(obj).css("color","");   
		}); 
		clip.addEventListener( "complete", function(){  
		   $alert("success","复制成功！");
		});
		})
});
 
function query()
{
 	$("#p_curPage").val(1);
 	$("#form1").submit();
}

function uploadImg(id)
{
	var filName =$("#imgfile").val();
 	filName=filName.replace(/\s/g,"");
 	if(null==filName || filName.length==0){
 		$alert("warn","请选择要上传的文件！");
 		return false;
 	}
 	var imageName=$("#imgName").val();
 	if(imageName.length==0){
 		$alert("warn","上传图片的名称不能为空！");
 		return false;
 	}
	var qzs=$("#qzs").val();
 	var imgtype=$("#imgtype").val();
    var url="/position/uploadImage!uploadImage.action?imageName="+imageName+"&weight="+qzs+"&imgType="+imgtype;
    $("#upload-btn").val("..");
    $("#upload-btn").attr("disabled",true);
      $.ajaxFileUpload({
        url: url,
        secureuri: false,
        fileElementId: id,
        dataType: "json",
        success: function(data,status)
        {
          var flag=data.flag;
          if(flag==1){
          	$alert("success","图片上传成功！");
          	window.location.reload();
          }else{
          	$alert("error",data.msg);
          }
            $("#upload-btn").val("上传");
   			$("#upload-btn").attr("disabled",false);
        },
        error: function (data, status, e)
        {	 
            $alert("error","图片上传失败！");
            $("#upload-btn").val("上传");
   			$("#upload-btn").attr("disabled",false);
   			console.log("eeeee:::"+ e);
        }
    });
}

$("#form1").keydown(function(){
	if(event.keyCode==13){
	$("#querybut").click();
	}
});
