/**
 * @foundation JS限制上传图片大小/物理大小
 * @author LGP
 * @date 2016-07-13
 */
function $_checkfile(id, size)
{
	var $size = "MB";
	if(size.indexOf(".") > 0 && size < 1)
	{
		$size = size * 1000 + "KB";
	}
	else
	{
		$size = size + "MB";
	}
	var $maxsize = size*1024*1024;
	var $errMsg = "上传的图片不能超过"+$size+"！";
	var ua = window.navigator.userAgent;
	var $obj = document.getElementById(id);
	var $filesize = null;
	if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($obj.value))
	{
		$alert("warn","您上传的图片格式不正确！");
		$obj.value = "";
		return;
	}
	try
	{
		$filesize = $obj.files[0].size;
	}
	catch(e)
	{
		$alert("warn","您的浏览器版本过低！");
		return;
	}
    if($filesize > $maxsize)
	{
		$alert("warn",$errMsg);
		$obj.value = "";
	}
}

function $_random()
{
	var $num = ""; 
	for(var i=0; i<9; i++) 
	{ 
		$num += Math.floor(Math.random()*10); 
	} 
	return $num;
}

function isIE() 
{ 
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}  