// JavaScript Document	
	Ext.define('Demo.controller.AppController',{
		extend:'Ext.app.Controller',
		views:['Viewport'],
		models:['TreeModel'],
		stores:['BookStore'],
		init:function(){
			//Ext.Msg.alert('提示信息','DemoController');
		}
	});