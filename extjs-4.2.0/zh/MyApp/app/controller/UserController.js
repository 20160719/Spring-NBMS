// JavaScript Document
	Ext.define('Demo.controller.UserController',{
		extend:'Ext.app.Controller',
		//requires: ['app.model.UserModel'],
		models:['UserModel'],
		stores:['UserStore'],
		init:function(){
			
			//this.getUserInfo();
			//var store=this.getStore();
			//Ext.Msg.alert('提示信息',this);
		}
	});