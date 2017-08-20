// JavaScript Document
// JavaScript Document
Ext.define('Demo.view.MenuDataGrid', {
	extend: 'Ext.grid.Panel',
	title: '菜单列表',
	alias:'widget.MenuDataGrid',
	id:'menuManage',
	closable: true,
	store:'MenuStore',
	forceFit:true,
	columns: [
		{ header: '菜单ID',  dataIndex: 'id' },
		{ header: '菜单名称', dataIndex: 'name'},
		{ header: '父菜单ID', dataIndex: 'parentId'},
		{ header: '菜单URL',  dataIndex: 'value'},
		{ header: '菜单层级', dataIndex: 'level'},
		{ header: '菜单序列', dataIndex: 'seq'},
		{ header: '创建时间',  dataIndex: 'createTime'},
		{ header: '修改时间', dataIndex: 'modifyTime'},
		{ header: '菜单备注', dataIndex: 'remark'}
	],
	selType:'checkboxmodel',//设定选择模式  
    multiSelect:true,//运行多选 
	tbar:[{
		xtype:'panel',
		margin: 2,
		width:'100%',
		frame:true,
		defaults: {
			anchor: '100%',
		},
		fieldDefaults: {
			labelWidth: 20,
			labelAlign: "right",
			flex: 5,
			margin: 5
		},
		items: [{
			xtype: "container",
			layout: "hbox",
			items: [
				{ xtype: "textfield", name: "name", fieldLabel: "姓名", allowBlank: false },
				{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" },
				{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
			]
		}]
	}], 
	dockedItems: [{
		xtype: 'pagingtoolbar',
		store: 'MenuStore',   // same store GridPanel is using
		dock: 'bottom',
		displayInfo: true,
		beforePageText: "当前页",
		afterPageText: "共{0}页",
		displayMsg:'显示{0}条到{1}条记录,共{2}条记录',
		emptyMsg:'没有记录'
    }],
});