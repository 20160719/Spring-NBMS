// JavaScript Document	
	Ext.define('Demo.controller.AppController',{
		extend:'Ext.app.Controller',
		views:['Viewport', 'Navigation', 'SystemMenuTree', 'BookMenuTree', 'MessageMenuTree'],
		stores:['SystemMenuStore', 'BookMenuStore','MessageMenuStore'],
		model:['MenuModel'],
		init:function(){
			this.control({
				'BookMenuTree':{
					itemclick:this.changePage,
				}
			});
		},
		changePage:function(view, rec, item, index, e){
			Ext.Msg.alert('Status', 'Changes saved successfully.');
			var text=rec.data.text;
				var main=Ext.getCmp('mainpanel');
				var tabPanel=main.getComponent('tabPanel');
				var tab;
				if(index==0){
					Ext.Msg.alert('图书查询',view.getId());
					//Ext.Msg.alert('msg',view+" "+text+"　"+el+" "+index+" "+e);
					tab=tabPanel.getComponent('bkQy');
					if(tab==null){
						tab=new Object({
							title: text,
							id:'bkQy',
							html: text,
							closable: true
						});
						tabPanel.add(tab);
					}
				}else if(index==1){
					Ext.Msg.alert('图书查询',view.getId());
					tab=tabPanel.getComponent('bkCe');
					if(tab==null){
						tab=new Object({
							title: text,
							id:'bkCe',
							html: text,
							closable: true
						});
						tabPanel.add(tab);
					}
				}else if(index==2){
					tab=tabPanel.getComponent('bkUe');
					if(tab==null){
						tab=new Object({
							title: text,
							id:'bkUe',
							html: text,
							closable: true
						});
						tabPanel.add(tab);
					}
				}else if(index==3){
					tab=tabPanel.getComponent('bkDe');
					if(tab==null){
						tab=new Object({
							title: text,
							id:'bkDe',
							html: text,
							closable: true
						});
						tabPanel.add(tab);
					}
				}
		}
	});