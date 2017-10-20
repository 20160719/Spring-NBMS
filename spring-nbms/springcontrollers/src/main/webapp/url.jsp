<%
	String basePath = "http://localhost:8080";
	String resoucesUrl = basePath +"/resources/js";
	String path = request.getContextPath();
	String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	
%>
<script type="text/javascript">
	var requestUrl = "<%=basePath%>";
	var serverUrl = "<%=serverUrl%>";
</script>

