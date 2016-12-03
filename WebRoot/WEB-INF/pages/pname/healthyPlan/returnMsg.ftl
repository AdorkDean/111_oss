<input type="hidden" id="status" <#if status?exists> value="${status}"</#if> />
<script>
	var status=document.getElementById("status").value;
		window.parent.ReturnMsg(status);
</script>