	
	Ext.onReady(function(){
		var win=new Ext.window.Window({
			title:"添加人员",
			width:500,
			height:360,
			plains:true,
			layout:'form',
			defaultType:'textfield',
			items:[{
				xtype:'panel',
				baseCls:'x-plains',
				style:'padding:5px',
				layout:'column',
				items:[{
					baseCls:'x-plains',
					columnWidth:0.5,
					layout:'form',
					labelWidth:20,
					defaultType:'textfield',
					defaults:{labelWidth:80},
					items:[{
						fieldLabel:'姓名'
					},{
						fieldLabel:'年龄'
					},{
						fieldLabel:'出生日期'
					},{
						fieldLabel:'联系电话'
					},{
						fieldLabel:'手机号码'
					},{
						fieldLabel:'电子邮件'
					},{
						fieldLabel:'性别'
					}]
				},{
					baseCls:'x-plains',
					columnWidth:0.5,
					layout:'form',
					items:[{
						xtype:'textfield',
						fieldLabel:'个人照片',
						width:100,
						height:185,
						inputType:'image'
					}]
				}]
			},{
				fieldLabel:'身份证号',
				labelStyle : "padding:5px",
				labelWidth:85
			},{
				fieldLabel:'具体地址',
				labelStyle : "padding:5px",
				labelWidth:85
			},{
				fieldLabel:'职称',
				labelStyle : "padding:5px",
				labelWidth:85,
				
			}],
			buttons:[{
				text:'确定'
			},{
				text:'取消'
			}]
		});

		win.show();

	});
