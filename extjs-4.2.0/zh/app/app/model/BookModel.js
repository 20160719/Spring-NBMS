// JavaScript Document
Ext.define('Demo.model.BookModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'bookId',type:'string'},
		{name:'bookName',type:'string'},
		{name:'bookAuthor',type:'string'},
		{name:'bookCreateTime',type:'string'},
		{name:'bookModifyTime',type:'string'},
		{name:'bookRemark',type:'string'}
	]	
});