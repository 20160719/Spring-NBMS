
$(document).ready(function() {
	var url = requestUrl + '/system/org/query.action';

	var data = {
		"opCode" : "11400",
		"tree.id" : "R1000000001"
	};
	$("#orgTree").treegrid({
		url:url,
		method:'post',
		queryParams : data,
		pagination : true,
		showFooter : true,
		width : 1200,
		idField : 'id',
		treeField : 'name',
		columns : [ [ {
			title : '组织名称',
			field : 'name',
			width : 200,
			editor : 'text'
		}, {
			title : '组织URL',
			field : 'value',
			width : 250,
			editor : 'text'
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
			title : '组织层级',
			field : 'level',
			width : 50
		}, {
			title : '组织序列',
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
		}/*, {
			field : 'id',
			title : '操作',
			width : 100,
			formatter : operator
		}*/ ] ],
		
	});
	
});

// function operator(value, data, index) {
// 	return '<a href="javascript:void(0)" onclick="modifies(\'' + value + '\')">修改</a>';
// }

//创建节点Map
var createNodeMap = new Map();
//删除节点Map
var deleteNodeMap = new  Map();
//更新节点Map
var updateNodeMap = new Map();

function adds() {
	var selected = getSelected();
	$("#orgTree").treegrid("append", createNode(selected));
}

function createNode(selected) {
	var node = null;
	var menu = null;
	var url = requestUrl + "/system/org/queryOrgSeq.action";
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
		$.messager.alert('提示','创建组织节点失败','info');
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
	$("#orgTree").treegrid("remove", selected.id);
	if('1' == selected.isNew) {
		createNodeMap.remove(selected.id);
	} else {
		deleteNodeMap.put(selected.id, selected);
	}
}

function edit() {
	var selected = getSelected();
	$("#orgTree").treegrid('beginEdit', selected.id);
}

function save() {
	var selected = getSelected();
	var tree = $("#orgTree");
	tree.treegrid('endEdit', selected.id);
	var node =tree.treegrid('find', selected.id);
	if("1" == node.isNew){
		createNodeMap.put(node.id, node);
	} else {
		updateNodeMap.put(node.id, node);
	}
}

function cancel() {
	var selected = getSelected();
	$("#orgTree").treegrid('cancelEdit', selected.id);
}

function commit() {
	var values = createNodeMap.values();
	var data = null;
	var flag = false;
	if(null != values){
		var url = requestUrl + "/system/org/create.action";
		data = {
			"opCode" : '11202',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				createNodeMap.clear();
				flag = flag || true;
			}
		});
	}
	values = updateNodeMap.values();
	if(null != values) {
		var url = requestUrl + "/system/org/modify.action";
		data = {
			"opCode" : '11203',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				updateNodeMap.clear();
				flag = flag || true;
			}
		});
	}
	values = deleteNodeMap.values();
	if(null != values) {
		var url = requestUrl + "/system/org/delete.action";
		data = {
			"opCode" : '11204',
			"targetJson" : JSON.stringify(values)
		};
		ajax(url, true, data, function (result, status, xhr) {
			if("1000" == result.retCode) {
				deleteNodeMap.clear();
				flag = flag || true;
			}
		});
	}
	if(flag) {
		$("#orgTree").treegrid("reload");
	}
}

function getSelected() {
	var selected =  $("#orgTree").treegrid("getSelected");
	if(null == selected) {
		$.messager.alert('提示','请选择一个节点','info');
	}
	return selected;
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










