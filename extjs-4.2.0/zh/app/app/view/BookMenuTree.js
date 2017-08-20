// JavaScript Document
	Ext.define('Demo.view.BookMenuTree',{
		extend:'Ext.tree.Panel',
		title:'图书管理',
		alias:'widget.BookMenuTree',
		rootVisible:false,
		store:'BookMenuStore'	
	});