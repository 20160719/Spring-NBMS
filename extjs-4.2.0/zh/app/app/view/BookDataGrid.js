// JavaScript Document
Ext.define('Demo.view.BookDataGrid', {
	extend: 'Ext.grid.Panel',
	title: '图书列表',
	alias:'widget.BookDataGrid',
	id:'bookManage',
	closable: true,
	store:'BookStore',
	forceFit:true,
	columns: [
	    { header: '编号', xtype: 'rownumberer', width:30, align:"center"},
		{ header: '图书ID', dataIndex: 'bookId' },
		{ header: '图书名称', dataIndex: 'bookName'},
		{ header: '图书作者', dataIndex: 'bookAuthor'},
		{ header: '图书入库时间', dataIndex: 'bookCreateTime'},
		{ header: '图书修改时间', dataIndex: 'bookModifyTime'},
		{ header: '图书备注', dataIndex: 'bookRemark'}
	],
	selType:'checkboxmodel',//设定选择模式  
    multiSelect:true,//运行多选  
	tbar:[{
		xtype:'panel',
		layout:'form',
		width:'100%',
		frame:true,
		items:[{
			xtype:'fieldset',
			layout:'column',
			title:'更新',
			frame:true,
			height:60,
			defaultType:'button',  
			items:[  
			  {text:'新增',iconCls:'icon_add',width:100},
			  {text:'更新',iconCls:'icon_edit',width:100}, 
			  {text:'删除',iconCls:'icon_delete',width:100}
		   ]
		},{
			xtype:'fieldset',
			layout:'column',
			title:'查询',
			frame:true,
			height:60,
			columnWidth:0.5,
			defaultType:'textfield',  
			items:[
				{fieldLabel:'图书名称',name:'t2',emptyText:'输入图书名称',labelWidth:50,width:200},
				{fieldLabel:'图书名称',name:'t3',labelWidth:50,width:200},
				{fieldLabel:'图书名称',name:'t4',labelWidth:50,width:200}
		   ]
		}]
	}],
	dockedItems: [{
		xtype: 'pagingtoolbar',
		store: 'BookStore',   // same store GridPanel is using
		dock: 'bottom',
		displayInfo: true,
		beforePageText: "当前页",
		afterPageText: "共{0}页",
		displayMsg:'显示{0}条到{1}条记录,共{2}条记录',
		emptyMsg:'没有记录'
    }],
});