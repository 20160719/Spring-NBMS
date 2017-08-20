

Ext.onReady(function(){
	
	//North Pannel
	var north=new Object({
		title:'图书信息管理系统',
		region:'north',
		height:50,
		collapsible:true,
	});
	
	/*
		West content
	*/
	//West bookmanage store
	var bookstore=Ext.create('Ext.data.TreeStore',{
		root:{
			expanded:true,
			children:[
				{text:'图书查询',leaf:true},
				{text:'图书添加',leaf:true},
				{text:'图书修改',leaf:true},
				{text:'图书删除',leaf:true},
			]	
		}
	});
	//West bookmanage
	var bookmanage=Ext.create('Ext.tree.Panel',{
		title:'图书管理',
		width:'200px',
		height:'150px',
		rootVisible:false,
		store:bookstore,
		listeners:{
			itemclick:function(view,rec,el,index,e){
				//Ext.Msg.alert('msg',rec.text+" "+index+" "+e.text);
				if(index==0){
					//Ext.Msg.alert('图书查询','你确定要查询图书吗？');
					Ext.Msg.alert('msg',view+" "+rec+"　"+el+" "+index+" "+e);
					
				}else if(index==1){
					Ext.Msg.alert('图书添加','你确定要添加图书吗？');
				}else if(index==2){
					Ext.Msg.alert('图书修改','你确定要修改图书吗？');
				}else if(index==3){
					Ext.Msg.alert('图书删除','你确定要删除图书吗？');
				}
			}
		}
	});
	
	//West jieyuemanage store
	var jieyuestore=Ext.create('Ext.data.TreeStore',{
		root:{
			expanded:true,
			children:[
				{text:'借阅查询',leaf:true},
				{text:'借阅添加',leaf:true},
				{text:'借阅修改',leaf:true},
				{text:'借阅删除',leaf:true},
			]	
		}
	});
	
	//West jieyuemanage
	var jieyuemanage=Ext.create('Ext.tree.Panel',{
		title:'借阅管理',
		width:'200px',
		height:'150px',
		rootVisible:false,
		store:jieyuestore,
		listeners:{
			itemclick:function(view,rec,el,index,e){
				//Ext.Msg.alert('msg',rec.text+" "+index+" "+e.text);
				if(index==0){
					//Ext.Msg.alert('图书查询','你确定要查询图书吗？');
					Ext.Msg.alert('msg',view+" "+rec+"　"+el+" "+index+" "+e);
					
				}else if(index==1){
					//Ext.Msg.alert('图书添加','你确定要添加图书吗？');
				}else if(index==2){
					//Ext.Msg.alert('图书修改','你确定要修改图书吗？');
				}else if(index==3){
					//Ext.Msg.alert('图书删除','你确定要删除图书吗？');
				}
			}
		}
	});
	
	//West messagemanage store
	var messagestore=Ext.create('Ext.data.TreeStore',{
		root:{
			expanded:true,
			children:[
				{text:'消息查询',leaf:true},
				{text:'消息发布',leaf:true},
				{text:'消息修改',leaf:true},
				{text:'消息删除',leaf:true},
			]	
		}
	});
	
	//West messagemanage
	var messagemanage=Ext.create('Ext.tree.Panel',{
		title:'消息管理',
		width:'200px',
		height:'150px',
		rootVisible:false,
		store:messagestore,
		listeners:{
			itemclick:function(view,rec,el,index,e){
				//Ext.Msg.alert('msg',rec.text+" "+index+" "+e.text);
				if(index==0){
					//Ext.Msg.alert('图书查询','你确定要查询图书吗？');
					Ext.Msg.alert('msg',view+" "+rec+"　"+el+" "+index+" "+e);
					
				}else if(index==1){
					//Ext.Msg.alert('图书添加','你确定要添加图书吗？');
				}else if(index==2){
					//Ext.Msg.alert('图书修改','你确定要修改图书吗？');
				}else if(index==3){
					//Ext.Msg.alert('图书删除','你确定要删除图书吗？');
				}
			}
		}
	});
	
	//West Pannel
	var west=new Object({
		title:'导航栏',
		region:'west',
		collapsible:true,
		width:200,
		layout:'accordion',
		items:[bookmanage,jieyuemanage,messagemanage]
	});
	
	//Center Pannel
	var center=new Object({
		title:'主窗体',
		region:'center',
		width:200,
	});

	//East Pannel
	var east=new Object({
		title:'',
		region:'east',
		collapsible:true
		
	});
	
	//Main Pannel
	var container=Ext.create('Ext.container.Viewport',{
		layout:{type:'border',padding:'5 10'},
		width:1000,
		height:document.documentElement.clientHeight,
		items:[north,west,center]
	});
	
});