<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String currentUrl = basePath;
	basePath = "http://localhost:8080";
	String resoucesUrl = basePath +"/resources/js";
	
%>
<script type="text/javascript">
	var requestUrl = "<%=currentUrl%>";
	var serverUrl = "<%=currentUrl%>";
</script>

