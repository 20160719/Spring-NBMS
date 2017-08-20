// JavaScript Document
	var bookdata = {
		trees: [{
			id: '11',
			text: '菜单管理',
			leaf: true
		},{
			id: '12',
			text: '角色管理',
			leaf: true
		},{
			id: '13',
			text: '组织管理',
			leaf: true
		},{
			id: '14',
			text: '人员管理',
			leaf: true
		}]
	};
	
	Ext.define('Demo.store.SystemMenuStore',{
		extend:'Ext.data.TreeStore',
		requires:'Demo.model.MenuModel',
		model:'Demo.model.MenuModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		},
		autoLoad: true
	});