<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${base}/web/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="${base}/web/js/ajaxfileupload.js"></script>

<style>
	#formDiv{
		background-color:#eeeeee;
		border:2px solid #ffffff;
		margin:50px 0px 5px 50px;
		width:80%;
		height:150px;
	}
	
	#exc_div{
		margin:50px 0px 5px 50px;
	}
	
	#importDiv{
		text-align:center;
	}
	
	#fileTip{
		border:1px solid #eeeeee;
		margin-left:20px;
		color:red;
	}
	#filePath{
		border:1px solid #eeeeee;
		margin-left:20px;
		color:red;
	}
</style>
</head>
<body>
	<div id="formDiv">
		<div id="exc_div">
			<form id="submitForm" action="" method="post" enctype="multipart/form-data">
				<input type="file" id="memberExcel" name="memberExcel" value=""/>
			</form>
			<br>
			<input type="button" id="submitBtn" name="submitBtn" value="上传附件"/>
			<label id="fileTip"></label>
			<label id="filePath"></label>
			<label id="fileType" style="display:none;"></label>
		</div>
		
		<div id="importDiv">
			<input type="button" id="importData" name="importData" disabled value="导入商品搜索词"/>
		</div>
	</div>
		
</body>
<script type="text/javascript">
	//document.write(new Date().getTime());

	function ajaxFileUpload() {
	    $.ajaxFileUpload
	    (
	        {
	            url: "importGoodsSearchWord!importExcel.action", //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'memberExcel', //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function (data, status)  //服务器成功响应处理函数
	            {
	            		$("#fileTip").html(data.msg);
	            		$("#filePath").html(data.imgUrl);
	            		$("#fileType").html(data.fileType)
	            		submitBtnAbled();
	            		importDataBtnAbled();
	            },
	            error: function (data, status, e)//服务器响应失败处理函数
	            {
	            	submitBtnAbled();
	            	importDataBtnDisAbled();
	                alert(e);
	            }
	        }
	    )
	    return false;
	}
	
	function uploadFile(){
		var fileName = $("#memberExcel").val();
		fileName = fileName.replace(/\s/g,"");
		if(null==fileName || fileName.length == 0){
			alert("请选择要上传的文件!");
			return;
		}
		
		submitBtnClick();
		
		ajaxFileUpload();
		
		//document.getElementById("submitForm").submit();
		
		/*$.ajax({
			url:"member/test!test.action",
			type:"POST",
			contentType:'multipart/form-data',
			data:$("#submitForm").serialize(),
			success:function(){
				alert("success");
			},
			error:function(){
				alert("error");
			},
		});*/
	}
	
	function submitBtnClick(){
		$("#submitBtn").attr('disabled',true);
	}
	function submitBtnAbled(){
		$("#submitBtn").attr('disabled',false);
	}
	function importDataBtnAbled(){
		$("#importData").attr('disabled',false);
	}
	function importDataBtnDisAbled(){
		$("#importData").attr('disabled',true);
	}
	
	function importData(){
		importDataBtnDisAbled();
		window.open("${base}/goods/importGoodsSearchWord!importData.action?filePath="+$('#filePath').html()+"&fileType="+$("#fileType").html());
		//window.location.href="${base}/goods/importGoodsSearchWord!importData.action?filePath="+$('#filePath').html()+"&fileType="+$("#fileType").html();
		return;
		$.ajax({
			url:"importGoodsSearchWord!importData.action",
			data:{'filePath':$("#filePath").html(),'fileType':$("#fileType").html()},
			success:function(data){
				importDataBtnAbled();
				alert("success");
			},
			error:function(){
				importDataBtnAbled();
				alert("导入数据异常!");
			}
			
		});
	}
	
	document.getElementById("submitBtn").onclick=uploadFile;
	document.getElementById("importData").onclick=importData;
</script>
</html>