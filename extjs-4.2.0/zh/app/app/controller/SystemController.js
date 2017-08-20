// JavaScript Document	
Ext.define('Demo.controller.SystemController',{
	extend:'Ext.app.Controller',
	views:['Viewport', 'Navigation', 'SystemMenuTree', 'MenuDataGrid'],
	stores:['SystemMenuStore', 'MenuStore'],
	model:['MenuModel', 'TreeModel'],
	init:function(){
		this.control({
			'SystemMenuTree':{
				itemclick:this.changePage,
			}
		});
	},
	changePage:function(view, rec, item, index, e){
		var text=rec.data.text;
		var main=Ext.getCmp('mainpanel');
		var tabPanel=main.getComponent('tabPanel');
		var tab;
		if(index==0){
			tab=tabPanel.getComponent('menuManage');
			if(tab==null){
				tab=Ext.create('Demo.view.MenuDataGrid');
				tabPanel.add(tab);
			}
			tabPanel.setActiveTab(tab);
		}else if(index==1){
			
		}else if(index==2){
			
		}else if(index==3){
			
		}
	}
});