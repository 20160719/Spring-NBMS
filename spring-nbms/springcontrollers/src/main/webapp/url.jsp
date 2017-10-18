<%
	String basePath = "http://localhost:8080";
	String resoucesUrl = basePath +"/resources/js";
	String path = request.getContextPath();
<<<<<<< b33ba8c1b659b3ae89a08230535ed3984c2ecc41
	
	String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	
=======
	String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
>>>>>>> c99431d6b80e207b7d313d5201a2b327c49d2e87
%>
<script type="text/javascript">
	var requestUrl = "<%=basePath%>";
	var serverUrl = "<%=serverUrl%>";
</script>

