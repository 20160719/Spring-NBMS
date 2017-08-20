	
	
	Ext.onReady(function(){
		
		var win=new Ext.window.Window({
			title:'window窗体',
			width:300,
			height:200
		});
		win.show();
		

		var win=Ext.create('Ext.window.Window',{
			title:'window窗体',
			width:300,
			height:200,
			
		});
	
		win.show();

		Ext.define("say",{
			say:function(){
				alert("hello");
			}
		});

		Ext.define("user",{
			mixins:{
				say:'say'
			}
		});

		var user=Ext.create("user",{});
		user.say();
	});
