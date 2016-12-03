<%@ page language="java"  pageEncoding="UTF-8"%>
<style type="text/css">
a.click:link , a.click:visited{color: #FF0000;}
a.click:hover,a.click:active{color: #000000;}
</style>
<div>
			 ${ pageResult.p_curPage }  / ${ pageResult.p_totalPages }  共 ${ pageResult.p_totalRecords } 记录
			<input value="上一页"  class="btn" size="6" type="button" onclick="dopage(-1)"/>
			<span id="pageStr"></span>			
			<input value="下一页"  class="btn" size="6" type="button"  onclick="dopage(1)"/>
			<input type="text" size="1" onblur="checktravelid(this.value);" id="goNo"><input type="button" value="GO" class="btn" onclick="gopage()">
</div>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pageNo.js"></script>
<script type="text/javascript">
function checktravelid(obj){
		var check_num='1234567890';
		var ff=0;
		for(k=0;k<obj.length;k++){
				if(check_num.indexOf(obj.substr(k,1))==-1){
						alert('含有非法字符或不为正数,请重新填写!');
						document.getElementById("goNo").value="";
						document.getElementById("goNo").focus();
						return false;
				}
			}						
		return true;
}

function gopage(){
	var gonovalue=document.getElementById("goNo").value;
	var tpage=${pageResult.p_totalPages };
	if(checktravelid(gonovalue)){
		if(gonovalue>0 && gonovalue<=tpage){
			jumppage(gonovalue);
		}else{
			alert("指定页码错误！");
			return false;
		}
	}else{
		return false;
	}
}

showpage1(${ pageResult.p_curPage },${pageResult.p_totalPages },${ pageResult.p_totalRecords },"jumppage","pageStr");
</script>	