
$(document).ready(function() {
	var url = requestUrl + '/system/menu/query.action';

	var data = {
		"opCode" : "12100",
		"tree.id" : "M1000000001"
	};
	$("#menuTree").treegrid({
		url:url,
		method:'post',
		queryParams : data,
		pagination : true,
		showFooter : true,
		width : 1600,
		idField : 'id',
		treeField : 'name',
		columns : [ [ {
			title : '菜单名称',
			field : 'name',
			width : 200,
			editor : 'text'
		}, {
			title : '菜单URL',
			field : 'value',
			width : 250,
			editor : 'text'
		}, {
			title : '菜单类型',
			field : 'type',
			width : 250,
			editor : 'text',
			formatter : typeFmt
		}, {
			title : '创建时间',
			field : 'createTime',
			width : 150,
			formatter : createTimeFmt
		}, {
			title : '修改时间',
			field : 'modifyTime',
			width : 150,
			formatter : modifyTimeFmt
		}, {
			title : '是否有效',
			field : 'valid',
			width : 100,
			editor : 'text',
			formatter : validFmt
		}, {
			title : '是否可见',
			field : 'visible',
			width : 100,
			editor : 'text',
			formatter : seeFmt
		},{
			title : '菜单层级',
			field : 'level',
			width : 50,
			editor : 'text'
		}, {
			title : '菜单序列',
			field : 'seq',
			width : 50,
			editor : 'text'
		},{
			title : '操作码',
			field : 'opCode',
			width : 100,
			editor : 'text'
		},{
			title : '备注',
			field : 'remark',
			width : 100,
			editor : 'text'
		}] ]
		
	});
	
});

//创建节点Map
var createNodeMap = new Map();
//删除节点Map
var deleteNodeMap = new  Map();
//更新节点Map
var updateNodeMap = new Map();

function adds() {
	var selected = getSelected();
	$("#menuTree").treegrid("append", createNode(selected));
}

function createNode(selected) {
	var node = null;
	var menu = null;
	var url = requestUrl + "/system/menu/queryMenuSeq.action";
	ajax(url, false, {}, function (result, status, xhr) {
		if("1000" == result.retCode) {
			menu = createMenuNode(selected, result.strResult);
			node = getMenuNode(selected, menu);
			createNodeMap.put(menu.id, menu);
		}
	});
	return node;
}

function getMenuNode(selected, menu) {
	var node = {
		parent : selected.id,
		data : [menu]
	};
	if(null == node) {
		$.messager.alert('提示','创建菜单节点失败','info');
		return;
	}
	return node;
}

function createMenuNode(selected, id) {
	var node = {
		id : id,
		"parentId" : selected.id,
		"name" : "NodeName",
		"value" : "#",
		"createTime" : new Date(),
		"level" : selected.level + 1,
		"isNew" : "1"
	}
	return node;
}

function deletes() {
	var selected = getSelected();
	$("#menuTree").treegrid("remove", selected.id);
	if('1' == selected.isNew) {
		createNodeMap.remove(selected.id);
	} else {
		deleteNodeMap.put(selected.id, selected);
	}
}

function edit() {
	var selected = getSelected();
	$("#menuTree").treegrid('beginEdit', selected.id);
}

function save() {
	var selected = getSelected();
	var tree = $("#menuTree");
	tree.treegrid('endEdit', selected.id);
	var node =tree.treegrid('find', selected.id);
	if("1" == node.isNew){
		createNodeMap.remove(node.id);
		createNodeMap.put(node.id, node);
	} else {
		updateNodeMap.put(node.id, node);
	}
}

function cancel() {
	var selected = getSelected();
	$("#menuTree").treegrid('cancelEdit', selected.id);
}

function commit() {
	var values = createNodeMap.values();
	var data = null;
	var flag = 0;
	if(0 < createNodeMap.size()){
		var url = requestUrl + "/system/menu/create.action";
		data = {
			"opCode" : '11202',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				createNodeMap.clear();
				flag += 1;
			}
		});
	}
	
	var upKeys = updateNodeMap.keys();
	var deKeys = deleteNodeMap.keys();
	
	//移除既要更新又要删除的元素
	for(var i = 0; i < upKeys.length; i++) {
		for(var j = 0; j < deKeys.length; j++) {
			if(upKeys[i] == deKeys[j]) {
				updateNodeMap.remove(upKeys[i]);
			}
		}
	}
	
	values = updateNodeMap.values();
	if(0 < updateNodeMap.size()) {
		var url = requestUrl + "/system/menu/modify.action";
		data = {
			"opCode" : '11203',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				updateNodeMap.clear();
				flag += 1;
			}
		});
	}
	values = deleteNodeMap.values();
	if(0 < deleteNodeMap.size()) {
		var url = requestUrl + "/system/menu/delete.action";
		data = {
			"opCode" : '11204',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				deleteNodeMap.clear();
				flag += 1;
			}
		});
	}
//	if(flag > 0) {
//		$("#menuTree").treegrid("reload");
//	}
	searchs();
	$.messager.alert('提示','提交成功','info');
}

function searchs() {
	$("#menuTree").treegrid("reload");
}

function getSelected() {
	var selected =  $("#menuTree").treegrid("getSelected");
	if(null == selected) {
		$.messager.alert('提示','请选择一个节点','info');
	}
	return selected;
}

function typeFmt(value, data, index) {
	if(null == value || "0" == value) {
		return '<span style="color:green">显示权限</span>';
	}
	return '<span style="color:red">数据权限</span>';
}

function createTimeFmt(value, data, index) {
	if(null == value) {
		return "";
	}
	return dateFmt(value);
}

function modifyTimeFmt(value, data, index) {
	if(null == value) {
		return "";
	}
	return dateFmt(value);
}

function  validFmt(value, data, index) {
	if("1" == value){
		return '<span style="color:blue">有效</span>';
	}
	return '<span style="color:red">无效</span>';
}

function seeFmt(value, data, index) {
	if("1" == value){
		return '<span style="color:blue">可见</span>';
	}
	return '<span style="color:red">不可见</span>';
}

function print(node) {
	var str = "id:" + node.id + ",parentId:" + node.parentId + ",isNew:" + node.isNew;
	console.log(str);
}










