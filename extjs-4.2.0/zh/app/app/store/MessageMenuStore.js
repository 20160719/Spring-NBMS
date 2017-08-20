// JavaScript Document
	
	var massagedata = {
		trees: [{
			id: '11',
			text: '消息查询',
			leaf: true
		},{
			id: '12',
			text: '消息添加',
			leaf: true
		},{
			id: '13',
			text: '消息修改',
			leaf: true
		},{
			id: '14',
			text: '消息删除',
			leaf: true
		}]
	};
	
	Ext.define('Demo.store.MessageMenuStore',{
		extend:'Ext.data.TreeStore',
		requires:'Demo.model.MenuModel',
		model:'Demo.model.MenuModel',
		proxy: {
			type: 'memory',
			data : massagedata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		},
		autoLoad: true
	});
