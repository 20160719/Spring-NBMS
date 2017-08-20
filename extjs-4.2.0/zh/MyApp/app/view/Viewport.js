// JavaScript Document

	Ext.require('Demo.store.BookStore');
	
	var north=new Object({
		height:30,
		region:'north',
		html:"<p style='{color:red;font-soze:30}'>学校图书管理系统</p>",
		frame:true
	});

	var bookdata = {
		trees: [{
			id: '11',
			text: '图书查询',
			leaf: true
		},{
			id: '12',
			text: '图书添加',
			leaf: true
		},{
			id: '13',
			text: '图书修改',
			leaf: true
		},{
			id: '14',
			text: '图书删除',
			leaf: true
		}]
	};
	
	var borrowdata = {
		trees: [{
			id: '21',
			text: '借阅查询',
			leaf: true
		},{
			id: '22',
			text: '借阅添加',
			leaf: true
		},{
			id: '23',
			text: '借阅修改',
			leaf: true
		},{
			id: '24',
			text: '借阅删除',
			leaf: true
		}]
	};
	
	var messagedata = {
		trees: [{
			id: '31',
			text: '消息查询',
			leaf: true
		},{
			id: '32',
			text: '消息添加',
			leaf: true
		},{
			id: '33',
			text: '消息修改',
			leaf: true
		},{
			id: '34',
			text: '消息删除',
			leaf: true
		}]
	};
	/* */
	 bookstore=Ext.create('Ext.data.TreeStore',{
		autoLoad: true,
		model:'Demo.model.TreeModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		}
	});
	
	
	//var bookstore=Ext.create('Demo.store.BookStore',{});
	
	
	var borrowstore=Ext.create('Ext.data.TreeStore',{
		autoLoad: true,
		model:'Demo.model.TreeModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		}
	});
	
	var messagestore=Ext.create('Ext.data.TreeStore',{
		autoLoad: true,
		model:'Demo.model.TreeModel',
		proxy: {
			type: 'memory',
			data : bookdata,
			reader: {
				type: 'json',
				root: 'trees'
			}
		}
	});
	
	var bookmanage=Ext.create('Ext.tree.Panel',{
		title:'图书管理',
		id:'bookmanage',
		width:'200px',
		height:'150px',
		xtype:'treepanel',
		store: bookstore,
		rootVisible:false,
		listeners:{
			itemclick:function(view,rec,el,index,e){
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
		}
	});
	
	
	//借阅管理
	var borrowmanage=Ext.create('Ext.tree.Panel',{
		title:'借阅管理',
		id:'borrowmanage',
		width:'200px',
		height:'150px',
		xtype:'treepanel',
		store: borrowstore,
		rootVisible:false			  
	});
	
	//消息管理
	var messagemanage=Ext.create('Ext.tree.Panel',{
		title:'消息管理',
		id:'messagemanage',
		width:'200px',
		height:'150px',
		xtype:'treepanel',
		store: messagestore,
		rootVisible:false	  
	});
	
	
	var westtree=Ext.create('Ext.panel.Panel',{
		title:'功能导航栏',
		region:'west',
		collapsible:true,
		width:200,
		layout:'accordion',
		split:true,	
		items:[bookmanage,borrowmanage,messagemanage],
		listeners: {

		}
	});
	
	var centertree=Ext.create('Ext.panel.Panel',{
		title:'主窗体',
		id:'mainpanel',
		region:'center',
		layout: 'fit',
		constrain: true,
		items: {
			xtype: 'tabpanel',
			activeTab: 0,
			itemId: 'tabPanel',
			items: [{
				title: 'Content tab',
				id:'content',
				html: 'Creating more tabs...',
				closable: true
			}]
		}				
	});
	
	var easttree=Ext.create('Ext.panel.Panel',{
		title:'East Panel',
		region:'east',	
		width:200,
		split:true,
		collapsible:true		
	});
	
	var southtree=Ext.create('Ext.panel.Panel',{
		title:'South Panel',
		region:'south',	
		collapsible:true,
		height:50,
		split:true
	});
	
	Ext.define('Demo.view.Viewport',{
		extend:'Ext.container.Viewport',
		id:'mainview',
		layout:{type:'border',padding:'5,10'},
		items:[north,westtree,centertree,easttree,southtree]
	});