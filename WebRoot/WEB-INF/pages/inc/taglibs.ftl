<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign works_img = "/static/image/works/"/> <!--艺术品图片path-->
<#assign artist_img = "/static/image/artist/"/> <!--艺术家图片path-->
<#assign gallery_img = "/static/image/gallery/"/> <!--华良图片path-->
<#assign scene_img = "/static/image/scene/"/> <!--实景图片path-->
<#assign public_img = "/static/image/public/"/> <!--公共图片path-->
<#assign contextPath = request.contextPath/>
<#assign www = "www.111yao.com/"/>    
<#assign www1 = "http://www.111yao.com/p/"/>    
<#assign ui = "http://ui.111yao.com/"/>  
<#assign ui1 = "http://img.zdfei.com/"/>    
<#assign _ui1 = "http://img.zdfei.com"/> 
<#assign www2 = "http://www.111yao.com"/> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<script>
var jsCtx = "${base}";
var CtxPath = "${contextPath}";
</script>
<!--定义一个宏-->
<#macro showMap map keyname>
		<#list map?keys as key>
			<#if key==keyname>	
				<#assign name=map[key]?default("")>
				${name?default("")}
			 </#if>
	     </#list>
</#macro>
<!--定义一个函数-->
<#function getMapValue mymap keyname>
		<#list mymap?keys as key>
			<#if key==keyname>	
				<#assign name=mymap[key]?default(0)>
				<#return name>
			 </#if>
	     </#list>
</#function>