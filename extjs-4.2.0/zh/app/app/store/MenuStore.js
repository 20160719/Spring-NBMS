// JavaScript Document
	var menudata = {
		menus: [{
			id: '11',
			name: 'Java编程',
			parentId: '李佳',
			value: '2017-05-05',
			level: '2017-05-05',
			seq: '1',
			createTime: '2017-05-05',
			modifyTime: '2017-05-05',
			remark: '2017-05-05'
		},{
			id: '11',
			name: 'Java编程',
			parentId: '李佳',
			value: '2017-05-05',
			level: '2017-05-05',
			seq: '2',
			createTime: '2017-05-05',
			modifyTime: '2017-05-05',
			remark: '2017-05-05'
		}]
	};
	
	Ext.define('Demo.store.MenuStore',{
		extend:'Ext.data.Store',
		requires:'Demo.model.TreeModel',
		model:'Demo.model.TreeModel',
		proxy: {
			type: 'memory',
			data : menudata,
			reader: {
				type: 'json',
				root: 'menus'
			}
		},
		autoLoad: true
	});