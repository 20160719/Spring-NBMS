// JavaScript Document
Ext.define('Demo.model.MenuModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'id',type:'string'},
		{name:'text',type:'string'},
		{name:'leaf',type:'Boolean'}
	]	
});