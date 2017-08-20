// JavaScript Document
	EXt.define('Demo.store.MenuStore',{
		extend:'Ext.data.TreeStore',
		requires:'Demo.model.MenuModel',
		model:'Demo.model.MenuModel',
		proxy:{
			type:'ajax',
			url:'./server/MenuDataStore.json',
			reader:'json',
			autoLoad:true	
		}	
	});