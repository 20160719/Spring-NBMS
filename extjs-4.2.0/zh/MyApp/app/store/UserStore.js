// JavaScript Document
	/*
	Ext.require([
		'Demo.model.UserModel',
	]);
	*/
	Ext.define('Demo.store.UserStore',{
		extend:'Ext.data.Store',
		model:'Demo.model.UserModel',
		data:[
			//['111','aaa','男',20,175],
			//['222','bbb','男',21,170],
			['333','ccc','男',22,165]
		]
		/*proxy:{
			type:'ajax',
			url:'app/server/user.json',
			reader:{
				type:'json',
				root:'users'
			}
		},
		autoLoad:true*/
	});