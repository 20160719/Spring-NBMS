// JavaScript Document
Ext.define('Demo.model.TreeModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'id',type:'string'},
		{name:'name',type:'string'},
		{name:'parentId',type:'string'},
		{name:'value',type:'string'},
		{name:'level',type:'int'},
		{name:'seq',type:'string'},
		{name:'createTime',type:'string'},
		{name:'modifyTime',type:'string'},
		{name:'remark',type:'string'},
	]	
});