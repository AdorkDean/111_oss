<!DOCTYPE html>
<html>
<head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>模板参数编辑</title> 
  <#include "/WEB-INF/pages/inc/common.ftl">
  <style>select{width:167px;background-color:#f5f5f5;}</style>
</head> 
<body> 
<div class="shop_main" style="font-family:'Microsoft Yahei';margin-left:10px;margin-top:10px;"> 
    <div class="sm_user"> 
    <div class="order"> 
    <!-- 模板基础参数开始-->
    <div id="order_form" class="order_form"> 
    <form action="" method="get" name="form1" id="form1">
      <#if cpositionlist?exists><input type="hidden" name="cpositionlist.id" value="${cpositionlist.id?default('')}"/></#if>
      
       <div class="tbl_form"> 
        <table cellpadding="0" cellspacing="0" border="0"> 
         <colgroup> 
          <col style="width:10%" /> 
          <col style="width:22%" /> 
          <col style="width:10%" /> 
          <col style="width:23%" /> 
          <col style="width:8%" /> 
          <col style="width:30%" /> 
         </colgroup> 
         <tbody> 
          <tr> 
           <td><p>位 置 名 称：</p></td> 
           <td><p><input name="cpositionlist.name" id="posname" <#if cpositionlist?exists>value="${cpositionlist.name?default('')}"</#if> class="input input_v1" /></p></td> 
           <td><p>模 板 路 径：</p></td> 
           <td><p><input  name="cpositionlist.mPath" id="posmPath" <#if cpositionlist?exists>value="${cpositionlist.mPath?default('')}"</#if>  class="input input_v1" /></p></td> 
           <td><p>模 板 名 称：</p></td> 
           <td><p><input name="cpositionlist.mName" id="posmName" <#if cpositionlist?exists> value="${cpositionlist.mName?default('')}" </#if> class="input input_v1" /></p></td> 
          </tr> 
          <tr> 
           <td><p>输出全路径：</p></td> 
           <td><p><input name="cpositionlist.outPath" <#if cpositionlist?exists > value="${cpositionlist.outPath}"</#if> id="posoutPath" class="input input_v1" /></p></td> 
           <td><p>是否需要发布：</p></td> 
           <td>
           <input type="radio" name="cpositionlist.isrelease" <#if cpositionlist?exists><#if cpositionlist.isrelease?default(1)==1>checked</#if><#else>checked</#if>  value="1" />是
     		&nbsp;&nbsp;&nbsp;&nbsp;
     		<input type="radio" name="cpositionlist.isrelease" <#if cpositionlist?exists> <#if cpositionlist.isrelease?default(1)==0>checked</#if></#if>  value="0" />否
     		</td> 
           <td><p>所 属 类 型：</p></td> 
           <td><p>
		    
		    <#if cpositionlist?exists>
		    	
			    <select id="posType" name="cpositionlist.genre" style="width:172px;">
					<#list pwPosType.result as pt>
						<option value="${pt.id}" <#if cpositionlist.genre == pt.id> selected="selected"</#if> >
							<#if cpositionlist.genre == pt.id>
							     ${pt.name}
							     <#else>
							     ${pt.name}
							</#if>
						</option>
					</#list>
				</select>
		    	
		    	<#else>
				    <select name="cpositionlist.genre" style="width:172px;">
						<#list pwPosType.result as pt>
						    <option value="${pt.id}">${pt.name}</option>
						</#list>
					</select>
		    </#if>
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
           </p></td> 
          </tr> 
          <tr> 
           <td><p>创 建 时 间：</p></td> 
           <td><p> <input type="txt" <#if cpositionlist?exists> value="${cpositionlist.createDt?string("yyyy-MM-dd HH:mm:ss")}"</#if> readonly class="input input_v1"/> </p></td> 
           <td><input type="button" class="btn01" id="savedata" value="保存" /></td> 
           <td><input type="button" class="btn01" id="goback" onclick="window.history.go(-1);" value="返回" /></td> 
          </tr> 
         </tbody> 
        </table> 
       </div> 
     </div> 
     <!--模板基础参数结束-->
     
     <!--模板规则详情开始-->
     <div class="order_tbl"> 
      <table class="table-list"> 
       <colgroup> 
        <col width="300" /> 
        <col width="300" /> 
        <col width="300" /> 
       </colgroup> 
       <thead style="background:#dbf1fc"> 
        <tr> 
         <th class="b-l">参数名称</th> 
         <th>名称</th> 
         <th class="b-r">操作</th> 
        </tr> 
       </thead> 
       <tbody> 
        <tr> 
         <td colspan="3" class="blank"></td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>img:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input name="rule.img.name" id="textfield2" <#if rule?exists> value="${rule.img.name?default('')}"</#if> class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
	       <select name="rule.img.ishow" style="width:167px">
	      	<option <#if rule?exists><#if rule.img.ishow?default("-1")=="0"> selected</#if></#if> value="0">不显示</option>
	        <option <#if rule?exists><#if rule.img.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
	      </select> 
          </div> </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>img_url:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.img_url.name" <#if rule?exists> value="${rule.img_url.name?default('')}"</#if> id="textfield3" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
		    <select name="rule.img_url.ishow" >
		      <option <#if rule?exists><#if rule.img_url.ishow?default("-1")=="0"> selected</#if></#if> value="0">不显示</option>
		      <option <#if rule?exists><#if rule.img_url.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
           </div>
          </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>title:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" type="text" name="rule.title.name" <#if rule?exists> value="${rule.title.name?default('')}"</#if> id="textfield4" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
           <select name="rule.title.ishow">
		      <option <#if rule?exists><#if rule.title.ishow?default("-1")=="0"> selected</#if></#if> value="0">不显示</option>
		      <option <#if rule?exists><#if rule.title.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select> 
          </div> </td> 
        </tr> 
         <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>title_all:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.title_all.name"  <#if rule?exists> value="${rule.title_all.name?default('')}"</#if> id="textfield5" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
          <select name="rule.title_all.ishow" >
		      <option <#if rule?exists><#if rule.title_all.ishow?default("-1")=="0"> selected</#if></#if> value="0">不显示</option>
		      <option <#if rule?exists><#if rule.title_all.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div>
         </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>title_url:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.title_url.name"  <#if rule?exists> value="${rule.title_url.name?default('')}"</#if> id="textfield5" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
          <select name="rule.title_url.ishow" >
		      <option <#if rule?exists><#if rule.title_url.ishow?default("-1")=="0"> selected</#if></#if> value="0">不显示</option>
		      <option <#if rule?exists><#if rule.title_url.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div>
         </td> 
        </tr> 
        
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>txt:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.txt.name" <#if rule?exists>value="${rule.txt.name?default('')}"</#if> id="textfield6" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
			<select name="rule.txt.ishow" >
		      <option <#if rule?exists><#if rule.txt.ishow?default("-1")=="0"> selected</#if></#if>  value="0">不显示</option>
		      <option <#if rule?exists><#if rule.txt.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div> </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>txt_url:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.txt_url.name" <#if rule?exists> value="${rule.txt_url.name?default()}"</#if> id="textfield7" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
			<select name="rule.txt_url.ishow" >
		      <option <#if rule?exists><#if rule.txt_url.ishow?default("-1")=="0"> selected</#if></#if>  value="0">不显示</option>
		      <option <#if rule?exists><#if rule.txt_url.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div> </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>price:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.price.name" <#if rule?exists> value="${rule.price.name?default()}"</#if> id="textfield7" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
			<select name="rule.price.ishow" >
		      <option <#if rule?exists><#if rule.price.ishow?default("-1")=="0"> selected</#if></#if>  value="0">不显示</option>
		      <option <#if rule?exists><#if rule.price.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div> </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>market_price:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.market_price.name" <#if rule?exists> value="${rule.market_price.name?default()}"</#if> id="textfield7" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
			<select name="rule.market_price.ishow" >
		      <option <#if rule?exists><#if rule.market_price.ishow?default("-1")=="0"> selected</#if></#if>  value="0">不显示</option>
		      <option <#if rule?exists><#if rule.market_price.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div> </td> 
        </tr> 
        <tr class="content"> 
         <td colspan=" 1 "> 
          <div class="p-img"> 
           <p>price_url:</p> 
          </div> </td> 
         <td> 
          <div class="p-value1">
           <input type="text" name="rule.price_url.name" <#if rule?exists> value="${rule.price_url.name?default()}"</#if> id="textfield7" class="input input_v1" />
          </div> </td> 
         <td class="t-c"> 
          <div class="discount"> 
			<select name="rule.price_url.ishow" >
		      <option <#if rule?exists><#if rule.price_url.ishow?default("-1")=="0"> selected</#if></#if>  value="0">不显示</option>
		      <option <#if rule?exists><#if rule.price_url.ishow?default("-1")=="1"> selected</#if></#if> value="1">显示</option>
		    </select>
          </div> </td> 
        </tr> 
       </tbody> 
       <tbody> 
        <tr> 
         <td colspan="8" class="blank"></td> 
        </tr> 
       </tbody>
       <tbody> 
       </tbody>
      </table> 
      <br /> 
      <div style="display:none;"> 
       <input type="hidden" id="skuIds" value="" /> 
      </div> 
     </div>
     <!--模板规则详情结束--> 
     </form>
    </div> 
   </div> 
  </div>   
 </body>
<script>	
	function resetCurPage()
	{
 		$("#p_curPage").val("1");
 		$("#subform").submit();
	}
 $(function()
 {
 	$("#savedata").click(function()
 	{
 		var posname=$("#posname").val();
 		if(posname==undefined || $.trim(posname).length<=0)
 		{
 			$alert("warn","位置名称不能为空！");
 			return false;
 		}
 		var formdata=$("#form1").serialize();
 		$("#savedata").val("..");
 		$("#savedata").attr("disabled",true);
 		$.ajax(
 		{
 			url:"${base}/position/position!managePostionList.action",
 			data:formdata,
 			type:"post",
 			success:function(data)
 			{
 				if(data==1)
 				{
 					$alert("success","模板参数添加成功！");
 					window.setTimeout(function(){
	 					window.location="${base}/position/position!selectPostionList.action";
 					},1000);
 				}
 				else if(data==2)
 				{
 					$alert("success","模板参数修改成功！");
 					window.setTimeout(function(){
	 					window.location="${base}/position/position!selectPostionList.action";
 					},1000);
 				}
 				else
 				{
 					$alert("error","模板参数设置失败，请稍后再试！");
 				}
 				$("#savedata").val("保存");
 				$("#savedata").attr("disabled",false);
 			},
 			error:function()
 			{
 				$alert("error","系统异常，请稍后再试！");
 				$("#savedata").val("保存");
 				$("#savedata").attr("disabled",false);
 			}
 		});
 	});
 });
</script>
</html>