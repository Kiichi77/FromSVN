<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../view/head.jsp"%>
<%
String srealPath = session.getAttribute("realPath").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>top</title>


</head>

<body >

  <!-- Wrapper for the radial gradient background -->
  
  <div id="sidebar">
       <ul id="main-nav">
       
      </ul> 
    </div>
    
  
<script type="text/javascript">
$(function(){
	var isOpen=0;
	$.post(ctx+ 'customer/customer/customer!queryMenuById.action',function(data) {
		if (null != data.menulist && 0 < data.menulist.length) {
			var xiaodiv = "";
			
		for(var i = 0; i < data.menulist.length; i++) {
				xiaodiv +="<li> <a href='#' class='nav-top-item'>"+data.menulist[i].menuName+" </a> <ul>";
				if(null != data.menulist[i].list && 0 < data.menulist[i].list.length) {
					for(var j = 0; j < data.menulist[i].list.length; j++) {
					xiaodiv +="<li><a href='"+realCtx+data.menulist[i].list[j].url+"' target='main'>"+data.menulist[i].list[j].rightName+"</a></li>";
					}
				}
				xiaodiv += " </ul></li>";
				$('#main-nav').append(xiaodiv);
				
//************特效
$("#main-nav li ul").hide(); // Hide all sub menus
$("#main-nav li a.current").parent().find("ul").slideToggle("slow"); // 滑下当前菜单项的子菜单

$("#main-nav li .nav-top-item").hover(
		function () {
			$(this).stop().animate({ paddingRight: "25px" }, 200);
		}, 
		function () {
			$(this).stop().animate({ paddingRight: "15px" });
		}
	);
			xiaodiv = "";
			}
		$("#main-nav li .nav-top-item").click( // 当一个顶级菜单项点击...
				function () {		
					//alert("isOpen"+isOpen);
					//isOpen++;
					$(this).parent().siblings().find("ul").slideUp("normal"); // 上滑音所有子菜单除了一个点击 
					$(this).parent().find("ul").slideToggle("slow");
					//$(this).next().slideToggle("normal"); // 滑下点击子菜单
		});
		}
	});
});
</script>

<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery Configuration -->
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
</body>
</html>