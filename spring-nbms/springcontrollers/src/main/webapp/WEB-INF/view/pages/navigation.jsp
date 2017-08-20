<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CSS3 Dropdown Menu</title>
</head>
<body>
	<div id="menu" style="z-index:20;position: absolute;"> 
		<ul id="nav">
			<li class="current"><a href="http://www.webdesignerwall.com/">Home</a></li>
			<li><a href="http://www.ndesign-studio.com/">My Projects</a>
				<ul>
					<li><a href="http://www.ndesign-studio.com/">菜单删除</a>
						<ul>
							<li><a href="javascript:void(0)" onclick="">Portfolio</a></li>
							<li><a href="http://www.ndesign-studio.com/wp-themes">WordPress Themes</a></li>
							<li><a href="http://www.ndesign-studio.com/wallpapers">Wallpapers</a></li>
							<li><a href="http://www.ndesign-studio.com/tutorials">Illustrator Tutorials</a></li>
						</ul>
					</li>
					<li><a href="http://www.webdesignerwall.com/">Web Designer Wall</a>
						<ul>
							<li><a href="http://jobs.webdesignerwall.com/">Design Job Wall</a></li>
						</ul>
					</li>
					<li><a href="http://icondock.com/">IconDock</a></li>
					<li><a href="http://bestwebgallery.com/">Best Web Gallery</a></li>
				</ul>
			</li>
			<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Multi-Levels</a>
				<ul>
					<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Team</a>
						<ul>
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a>
								<ul>
									<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
									<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
									<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
								</ul>
							</li>
							
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
						</ul>
					</li>
					<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sales</a></li>
					<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Another Link</a></li>
					<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Department</a>
						<ul>
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
							<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Sub-Level Item</a></li>
						</ul>
					</li>
				</ul>
			</li>	
			<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">About</a></li>
			<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Contact Us</a></li>
		    <li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">About</a></li>
			<li><a href="http://www.webdesignerwall.com/demo/css3-dropdown-menu/#">Contact Us</a></li>
		</ul>
	</div>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		$("#nav a").click(function(){
			var tabs = $('#tabs');
			if(!tabs.tabs('exists','aaa')) {
				tabs.tabs("add",addTab('111','aaa'));
			}
			return ;
		});
		$("#layout-center").panel({
			
			onResize:function(width, height) {
				var newWidth = width + "px";
				var newHeight = height + "px";
				var tabs = $('#tabs').tabs('tabs');
				for(var i = 0; i < tabs.length; i++) {
					/* tabs[i].panel('resize',{
						width : newWidth,
						height : newHeight
					}); */
					//alert("newHeight"+newHeight);
				}
			}
		});
	});
	
	function addTab(id,title) {
		var centerPanel = $("#layout-center");
		var height= centerPanel.panel('options').height - 29 + "px";
		var content = '<iframe scrolling="auto" frameborder="0" style="width:100%;'+'height:'+height+';"></iframe>';
		//content = "aaa";
		var tab = {
			"id" : id,
			"title" : title,	
			"content" : content,
			closable : true,
			fit : true
		};
		return tab;
	}
	
	</script>
	
</body>
</html>