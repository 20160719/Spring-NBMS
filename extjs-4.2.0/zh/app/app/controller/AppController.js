// JavaScript Document	
	Ext.define('Demo.controller.AppController',{
		extend:'Ext.app.Controller',
		views:['Viewport', 'Navigation', 'BookMenuTree', 'MessageMenuTree', 'BookDataGrid'],
		stores:['BookMenuStore','MessageMenuStore', 'BookStore'],
		model:['MenuModel', 'BookModel'],
		init:function(){
			this.control({
				'BookMenuTree':{
					itemclick:this.changePage,
				}
			});
		},
		changePage:function(view, rec, item, index, e){
			//Ext.Msg.alert('Status', 'Changes saved successfully.');
			var text=rec.data.text;
			var main=Ext.getCmp('mainpanel');
			var tabPanel=main.getComponent('tabPanel');
			var tab;
			if(index==0){
				tab=tabPanel.getComponent('bookManage');
				if(tab==null){
					tab=Ext.create('Demo.view.BookDataGrid');
					tabPanel.add(tab);
				}
				tabPanel.setActiveTab(tab);
			}
		}
	});