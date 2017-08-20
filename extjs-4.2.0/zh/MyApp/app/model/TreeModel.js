// JavaScript Document
	Ext.define('Demo.model.TreeModel',{
		extend:'Ext.data.Model',
		fields:[
			{name: 'id', type: 'string'},
			{name: 'text', type: 'string'},
			{name: 'leaf', type: 'boolean'}
		]
	});