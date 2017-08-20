// JavaScript Document
	Ext.define('Demo.model.UserModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'userId',type:'string'},
		{name:'name',type:'string'},
		{name:'sex',type:'string'},
		{name:'age',type:'int'},
		{name:'height',type:'int'},
	],
	idProperty: 'userId',
	getUserInfo:function(){
		var userinfo="UserModel:[name:\""+this.get('name')+"\",age:"
				+this.get('age')+",sex:\""+this.get('sex')+"\"]";
		Ext.Msg.alert('用户信息',userinfo);
	}
});