// JavaScript Document

	Ext.define('Demo.view.Viewport',{
		extend:'Ext.container.Viewport',
		layout:{type:'border',padding:'5,10'},
		items:[{
				title:'North Panel',
				region:'north',
				height:80,
				//frame:true
			},{
				xtype:'Navigation'	
			},{
				title:'主窗体',
				region:'center',
				id:'mainpanel',
				layout: 'fit',
				constrain: true,
				items: {
					xtype: 'tabpanel',
					activeTab: 0,
					itemId: 'tabPanel',
					items: [{
						title: '首页',
						id:'content',
						html: 'Creating more tabs...',
						//closable: true
					}]
				}			
			},{
				title:'East Panel',
				region:'east',	
				width:200,
				split:true,
				collapsible:true
			},{
				title:'South Panel',
				region:'south',	
				collapsible:true,
				height:80,
				split:true
			}
		]
	});