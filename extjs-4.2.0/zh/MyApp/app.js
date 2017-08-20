// JavaScript Document
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
		enabled:true
	});	
	Ext.application({
		name:'Demo',
		appFolder:'app',
		controllers:['DemoController','UserController'],
		autoCreateViewport:true,
		enableQuickTips:true,
		launch:function(){
			//Ext.Msg.alert('提示信息','app');
		}
	});
});