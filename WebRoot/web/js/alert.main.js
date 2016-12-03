/**
 * 样式变量
 * @date 2015年8月13日
 */
var mask =  "<div id='mask' style='width:100%;height:100%;background:#000000;top:0;left:0;position:fixed;_position:absolute;opacity:.2;filter:alpha(opacity=20);z-index:88;'></div>"
		    +"<div id='content' style='width:auto;height:auto;text-align:center;padding:20px 30px 20px 65px;position:fixed;_position:absolute;left:42%;top:35%;border-radius:10px;font-family:'Microsoft YaHei';font-size:15px;border:#000 1px solid;z-index:9999999999999999999;'><span style='font-size:15px;font-family:'Microsoft YaHei';color:#ffffff;z-index:99;'><a style='color:#ffffff;text-decoration:none;' id='aHTML'>"
		    +"</a></span></div>";
/**
 *  提示信息框函数
 *  type: 传入类型: warn:警告、success:成功、error:错误
 *  content：提示内容
 */		    
function $alert(type,content)
{
    if(document.getElementById('mask') == null)
    {
		$("body").append(mask);
    }
	if(type == 'warn')
	{
		$("#content").css("background","#000000 url('../web/images/warn.png') 32px 15px no-repeat");
	}
	if(type == 'success')
	{
		$("#content").css("background","#000000 url('../web/images/success.gif') 27px 14px no-repeat");
	}
	if(type == 'error')
	{
		$("#content").css("background","#000000 url('../web/images/error.png') 32px 10px no-repeat");
	}
	$("#aHTML").html(content);
	$("#mask").fadeIn();
 	$("#content").fadeIn();
	window.setTimeout(function(){
		$("#mask").fadeOut().css("display","none");
		$("#content").fadeOut().css("display","none");
	},1500);
}

