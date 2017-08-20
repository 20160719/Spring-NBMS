	
	
	Ext.onReady(function(){
		
		Ext.apply(Ext.data.validations,{
			age:function(config,value){
				var min=config.min;
				var max=config.max;
				if(value>=min&&value<=max){
					return true;
				}else{
					return false;
				}
			},
			ageMessage:'age数据出现错误'
		});


		Ext.define("Person",{
			extend:"Ext.data.Model",
			fields:[
				{name:'name',type:'string'},
				{name:'age',type:'int'},
				{name:'email',type:'string'}	
			],
			validations:[
				{type:'length',field:'name',min:2,max:6},
				{type:'age',field:'age',min:0,max:100}
			],
			getPersonInfo:function(){
				var info="我的姓名:"+this.get('name')+
						 "我的年龄:"+this.get('age')+
						 "我的邮箱:"+this.get('email');
				Ext.Msg.alert("我的信息",info);
			}
		});

		var per=Ext.create('Person',{
			name:'aaaaaaaa',
			age:23,
			email:'11@qq.com'
		});

		var per1=Ext.ModelManager.create({
			name:'aaaaaaaa',
			age:-23,
			email:'11@qq.com'
		},'Person');
		
		per1.getPersonInfo();

		var errors=per1.validate();
		var errorinfo=[];
		errors.each(function(v){
			errorinfo.push(v.field+"  "+v.message);
		});
		alert(errorinfo.join("\n"));

		Ext.regModel("User",{
			fields:[
				{name:'name',type:'string'},
				{name:'age',type:'int'},
				{name:'email',type:'string'}	
			],
			getUserInfo:function(){
				var info="我的姓名:"+this.get('name')+
						 "我的年龄:"+this.get('age')+
						 "我的邮箱:"+this.get('email');
				Ext.Msg.alert("我的信息",info);
			}
		});

		var user=Ext.ModelManager.create({
			name:'aaa',
			age:20,
			email:'11@qq.com'
		},'User');

		user.getUserInfo();
		
		
	});
