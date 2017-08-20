// JavaScript Document
	Ext.define('Demo.view.MessageMenuTree',{
		extend:'Ext.tree.Panel',
		title:'消息管理',
		alias:'widget.MessageMenuTree',
		rootVisible:false,
		store:'MessageMenuStore'	
	});