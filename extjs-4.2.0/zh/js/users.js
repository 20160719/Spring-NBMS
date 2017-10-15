	
	
	Ext.onReady(function(){
		Ext.define('User', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id',    type: 'int'},
				{name: 'name',  type: 'string'},
				{name: 'phone', type: 'string', mapping: 'phoneNumber'}
			]
		});
		
		//数据字段不是按照model中定义排列的 - 字段'phone'在此称为'phoneNumber'
		var data = {
			users: [{
					id: 1,
					name: 'Ed Spencer',
					phoneNumber: '555 1234'
				},{
					id: 2,
					name: 'Abe Elias',
					phoneNumber: '666 1234'
				},{
					id: 3,
					name: 'BBbe Elias',
					phoneNumber: '777 1234'
				},{
					id: 4,
					name: 'CCbe Elias',
					phoneNumber: '887 1234'
				}]
		};
		
		//请注意我们是如何通过设置reader的'root'来满足上面的数据结构的.
		/**/
		var store = Ext.create('Ext.data.Store', {
			autoLoad: true,
			model: 'User',
			proxy: {
				type: 'ajax',
				url: 'js/users.json',
				reader: {
					type: 'json',
					root: 'users'
			    }
			}
		});
		
		
		
		/*
		var store = Ext.create('Ext.data.Store', {
			autoLoad: true,
			model: 'User',
			data : data,
			proxy: {
				type: 'memory',
				reader: {
					type: 'json',
					root: 'users'
				}
			}
		});
		*/
		Ext.define('UserStore', {
			extend:'Ext.data.Store',
			requires:'User',
			model:'User',
			xtype:'userStore',
			data:data
		});
		
		Ext.create('Ext.grid.Panel', {
			title: 'Simpsons',
			height: 200,
			width: 800,
			store: store,
			columns: [
				{ header: 'id',  dataIndex: 'id' },
				{ header: 'name', dataIndex: 'name', flex: 1 },
				{ header: 'phone', dataIndex: 'phone' }
			],
			renderTo: 'user',
			bbar:[{
				xtype:'pagingtoolbar',
				width: 800,
				pageSize:20,
				store:store,
				displayInfo:true,
				beforePageText: "当前页",
                afterPageText: "共{0}页",
				displayMsg:'显示{0}条到{1}条记录,共{2}条记录',
				emptyMsg:'没有记录'
			}],
			tbar: [{
				//title:'操作',
				xtype:'form',
				layout:'column',
				width:700,
				frame:true,  
				items:[  
				  {xtype :'button',text:'保存',width:100,handler:showSelectedOptions},
				  {xtype :'button',text:'更新',width:100,handler:showSelectedOptions }, 
				  {xtype :'button',text:'删除',width:100,handler:showSelectedOptions }
			   ]
			}
			],
			//tbar: [
			//  {xtype :'button',text:'保存',handler:showSelectedOptions},
			//  {xtype :'button',text:'删除', handler:showSelectedOptions }, 
			//  {xtype :'button',text:'上传主图', handler:showSelectedOptions },
			//  {xtype :'button',text:'刷新', handler:showSelectedOptions }, 
			//  {xtype :'button',text:'添加提报商品', handler:showSelectedOptions}
			//]
		}).show();
		
		
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields:[
				{name: 'treeId', type: 'string'},
				{name: 'text',  type: 'string'},
				{name: 'leaf',       type: 'boolean'},	
			]
		});
		
		var treedata = {
			trees: [{
					treeId: '1',
					text: 'AA Spencer',
					leaf: true
				},{
					treeId: '2',
					text: 'BB Spencer',
					leaf: true
				},{
					treeId: '3',
					text: 'CC Spencer',
					leaf: true
				},{
					treeId: '4',
					text: 'EE Spencer',
					leaf: true
				}]
		};
		
		var treestore = Ext.create('Ext.data.TreeStore', {
			autoLoad:true,
			model:'TreeModel',
			proxy: {
				type: 'memory',
				data:treedata,
				reader: {
					type: 'json',
					root: 'trees'
				}
			}
		});
		
		Ext.create('Ext.tree.Panel', {
			title: 'Simple Tree',
			width: 200,
			height: 150,
			store: treestore,
			rootVisible: false,
			renderTo: 'tree'
		}).show();

		function showSelectedOptions() {
			var rows = catalogEditProduct.getView().getSelectionModel().getSelection();
			var msg = "";
			for (var i = 0; i < rows.length; i++) {
				msg = msg + rows[i].get('prcaId') + ',';
			}
			Ext.MessageBox.alert("标题", msg);
		}
			
		Ext.create('Ext.form.Panel', {
			title: 'Hello',
			width: 600,
			height:300,
			renderTo: 'panel',
			layout:'column',
			items:[{
				columnWidth:0.5,
				layout:'form',
				style: "margin-left: 2px;padding-left:5px",  
				fieldDefaults:{  
                    labelWidth:70,  
                    labelAlign:"left"  
                },  
				items:[{ fieldLabel: "姓  名", id:"userName", allowBlank: false,xtype:"textfield" },  
                    { fieldLabel: "通信地址", id: "userAddress", allowBlank: false,xtype:"textfield" },  
                    { fieldLabel: "Email", id: "userEmail", vtype: "email", allowBlank: false,xtype:"textfield" },  
                    { fieldLabel: "年  龄", id: "userAge", xtype: "numberfield", maxValue: 100, minValue: 0, allowBlank: false }  
				] //sex 的组件
			},]
		});

		 var win = new Ext.Window({   
             title: "form Layout",   
             height: 150,   
             width: 330,   
             plain: true,    
             bodyStyle: 'padding:15px',    
             items:    
              {    
				 columnWidth:0.5,
                 xtype: "form",   
                 labelWidth: 20,   
                 defaultType: "textfield",    
                 frame:true,   
                 items:[{    
                         fieldLabel:"姓名",   
                         name:"username",   
                         allowBlank:false    
                     },{    
                         fieldLabel:"呢称",   
                         name:"nickname"    
                     },{    
                         fieldLabel: "出生年月",   
                         xtype:'datefield',   
                         name: "birthday",      
                     }   
                 ]   
             }   
         });   
         win.show();    
		
		var form = Ext.create("Ext.form.Panel", {
			width: 500,
			height: 300,
			margin: 20,
			title: "Form",
			renderTo: 'panel1',
			collapsible: true,  //可折叠
			autoScroll: true,   //自动创建滚动条
			defaultType: 'textfield',
			defaults: {
				anchor: '100%',
			},
			fieldDefaults: {
				labelWidth: 40,
				labelAlign: "left",
				flex: 1,
				margin: 5
			},
			items: [
				{
					xtype: "container",
					layout: "hbox",
					items: [
						{ xtype: "textfield", name: "name", fieldLabel: "姓名", allowBlank: false },
						{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" },
						{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
					]
				},
				{
					xtype: "container",
					layout: "hbox",
					items: [
						{ xtype: "textfield", name: "phone", fieldLabel: "电话", allowBlank: false, emptyText: "电话或手机号码" },
						{ xtype: "textfield", name: "phone", fieldLabel: "邮箱", allowBlank: false, emptyText: "Email地址", vtype: "email" },
						{ xtype: "textfield", name: "phone", fieldLabel: "邮箱", allowBlank: false, emptyText: "Email地址", vtype: "email" }
					]
				},
				{
					xtype: "textareafield",
					name: "remark",
					fieldLabel: "备注",
					height: 50
				}
			],
			buttons: [
				{ xtype: "button", text: "保存" }
			]
		});

	});
