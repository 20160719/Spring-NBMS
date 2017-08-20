// JavaScript Document
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
		enabled:true
	});	
	Ext.application({
		name:'Demo',
		appFolder:'app',
		controllers:['SystemController', 'AppController'],
		autoCreateViewport:true	
	});
});