
$(document).ready(function() {
	
	var url = requestUrl+'/common/tree/booktype/queryBookTypeTree.action';

	$("#treeData").treegrid({
		url:url,
		method:'post',
		pagination : true,
		rownumbers : true,
		showFooter : true,
		idField : 'id',
		treeField : 'name',
		columns : [ [ {
			field : 'name',
			title : 'Task Name',
			width : 150
		}, {
			field : 'level',
			title : 'level',
			width : 100
		}, {
			field : 'seq',
			title : 'seq',
			width : 100
		}, {
			field : 'remark',
			title : 'remark',
			width : 100
		}, {
			field : 'id',
			title : 'operation',
			width : 100,
			formatter : operater
		} ] ],
		onClickCell : function(field, row) {
			
		},
		onLoadSuccess : function(row, data) {

		}
	});

	url = requestUrl+'/business/booktype/pageBookTypes.action';
//	$("#booktypeTable").datagrid({
//		url:url,    
//		method:'post',
//		width:600,
//		height:400,
//		pagination: true,
//		pageSize:15,
//		pageNumber:1,
//		pageList:[10,20,30,50],
//	    columns:[[    
//	        {field:'bookTypeName',title:'bookTypeName',width:100},    
//	        {field:'bookTypeRemark',title:'bookTypeRemark',width:100}
//	    ]]    
//
//	});
});

function operater(value, data, index) {
	return '<a href="javascript:void(0)" onclick="modify(\'' + data.id
			+ '\')">modify</a>';
}

function modify(id) {
	alert(id);
}

function save() {
	var data = $("#treeData").treegrid('getSelected');
	if (data != null) {
		alert(data.id);
	} else {
		alert("no selected");
	}
}

function cut() {
	var data = $("#treeData").treegrid('getSelected');
	if (data != null) {
		var edit = $("#treeData").treegrid('getEditor', {
			id : data.id,
			field : data.name
		});
	} else {
		alert("no selected");
	}

}