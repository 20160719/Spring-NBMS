// JavaScript Document
	
	Ext.require('Demo.model.TreeModel');
	
	var bookdata = {
		trees: [{
			id: '11',
			text: '图书查询',
			leaf: true
		},{
			id: '12',
			text: '图书添加',
			leaf: true
		},{
			id: '13',
			text: '图书修改',
			leaf: true
		},{
			id: '14',
			text: '图书删除',
			leaf: true
		}]
	};

	Ext.define('Demo.store.BookStore',{
		extend:'Ext.data.TreeStore',
		model:'Demo.model.TreeModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		}
	});