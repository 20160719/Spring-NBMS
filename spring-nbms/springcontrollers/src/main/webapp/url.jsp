<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	basePath = "http://localhost:8080";
	String resoucesUrl = basePath +"/resources/js";
	
%>
<script type="text/javascript">
	var requestUrl = "<%=basePath%>";
	var serverUrl = "<%=basePath%>";
</script>

