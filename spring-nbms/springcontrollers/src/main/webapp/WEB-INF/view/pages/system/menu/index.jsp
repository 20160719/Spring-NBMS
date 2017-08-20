<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Menu-Manage</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../../../../resources.jsp" %>
<script type="text/javascript" src="<%=resoucesUrl%>/date.js"></script>
<script type="text/javascript" src="<%=resoucesUrl%>/map.js"></script>
<script type="text/javascript" src="<%=resoucesUrl%>/ajax.js"></script>
<script type="text/javascript" src="<%=resoucesUrl%>/system/menu/menu_manage.js"></script>

</head>
<body>

	<div class="easyui-panel" style="padding: 5px 0;">

		<table id="menuTree" class="easyui-treegrid" style="width: 1600px; height: 600px" title="菜单管理" toolbar="#tb" >
			 
		</table>
	</div>

	<div id="tb" style="padding-left: 30px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="adds();">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletes();">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancel();">取消</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="commit();">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchs();">提交</a>
	</div>
	
</body>
</html>
