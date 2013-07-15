<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Locale,cn.com.kxcomm.common.util.LocalUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("path",path);
	request.setAttribute("ctx",path);
	request.setAttribute("basePath",basePath);
	
	String countryCode = LocalUtil.getCountryCode();
	request.setAttribute("countryCode",countryCode);
	
	String fileName = LocalUtil.getResourceFileName(request.getRequestURL().toString());
	request.setAttribute("fileName",fileName);
%>
<%@taglib prefix ="s" uri ="/struts-tags" %>
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
        <title>IPMI系统</title>  
        <script type="text/javascript">
        </script>
<link rel="stylesheet" type="text/css" href="${ctx}/jsp/common/js/ext/resources/css/ext-all.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/jsp/common/js/ext/resources/css/ext-patch.css" />
<script type="text/javascript" src="${ctx}/jsp/common/js/ext/adapter/ext/ext-base.js"> </script>  
<script type="text/javascript" src="${ctx}/jsp/common/js/ext/ext-all.js"> </script>
<script type="text/javascript" src="${ctx}/jsp/common/js/ext/src/locale/ext-lang-zh_CN.js"> </script>
<script type="text/javascript" src="js/update_pwd.js"> </script>
<style type="text/css">
.x-border-layout-ct{ background:#D1DDDD;}
.changPage a:hover{
    font-size: 10pt; 
	color: #C07AB8;
}
.x-panel-header{ font-size:12px;}
.changPage a{
	color:white;
} 
.changPage a:link { text-decoration: none}
.changPage a:active { text-decoration: none }
.changPage a:visited { text-decoration: none}
#header {
	width: 100%;
	height: 104px;
	float:right;
	background:url(../common/images/back.jpg) repeat-x;
	padding: 0 0 2px;
}

#greeting {
	height: 80px;
	float: right;
}

#header p {
	position: relative;;
	font-size: 12px;
	display: block;
	height:28px;
	/*background: url(common/images/people.gif) bottom left no-repeat;*/
	padding-left:27px;
	left: 5px;
	top: 46px;
	color:#FFF;
}

#header a {
	text-decoration:underline;
	margin-left: 8px;
	color:#FFF;
}

.welcome {
	padding: 0;
	width: 100%;
	height: 100%;
	background: #ecf4ff;
}

.welcome  img {
	border: 0;
	margin: 0;
}
 		    html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
    p {
        margin:5px;
    }
			.add { 
			    background-image:
			    url(common/js/ext/extjsico/drop-add.gif)
			    !important;
			}
			.edit { 
			    background-image:
			    url(common/js/ext/extjsico/cog_edit.png)
			    !important;
			}
			.search_16x16 { 
			    background-image:
			    url(common/js/ext/extjsico/search_16x16.gif)
			    !important;
			}
			.delete { 
			    background-image:
			    url(common/js/ext/extjsico/delete.gif)
			    !important;
			}
			
			.registration2_16x16 { 
			    background-image:
			    url(common/js/ext/extjsico/registration2_16x16.gif)
			    !important;
			}
			.business_16x16 { 
			    background-image:
			    url(common/js/ext/extjsico/business_16x16.gif)
			    !important;
			}
			.tip-target {
			    width: 100px;
			    text-align:center;
			    padding: 5px 0;
			    border:1px dotted #99bbe8;
			    background:#dfe8f6;
			    color: #15428b;
			    cursor:default;
			    margin:10px;
			    font:bold 11px tahoma,arial,sans-serif;
			    float:left;
			 }		
		</style>
        <script type="text/javascript" >
        var ctx = '${ctx}';
        Ext.onReady(function(){
            Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        	var tabs=new Ext.TabPanel({
        　　　　	resizeTabs:true,
        　　　　	minTabWidth:115,
        　　　　	tabWidth:135,
        　　　　	enableTabScroll:true,
        		activeTab:0,
        		region:"center",
        　　　　	width:600,
        		split:false,
        		animFloat:false, 
        　　　　	height:150,
        		frame:true,
        　　　　	defaults:{autoScroll:true},
        		items : [{
        			id : 'tabId1',
           			title : "IPMI",
           			autoScroll : true,
           			html : ' <div class="welcome"><img src="../common/images/welcome.jpg"/></div>'
          		}]
        　　});    
 
        	var panelTree = new Ext.Panel(  {
                    region: 'west',
                    id: 'west-panel', // see Ext.getCmp() below
                    title: "菜单",
                    split: true,
                    width: 225,
                    minSize: 175,
                   // autoScroll:true,
                    maxSize: 400,
                    collapsible: true,
                    margins: '0 0 0 5',
                    layout: {
                        type: 'accordion',
                        animate: true,
                        fill : false
                    }
             }); 
        	Ext.Ajax.request({   
        		url:ctx+'/customer/customer/customer!queryMenuById.action', //ctx+'/consumer/consumer!queryMenuById.action',//ctx+'/right/right!selectMenu.action',   //    
        		method: 'post',   
        		callback : function(options, success, response) {   
        			var obj = Ext.util.JSON.decode(response.responseText); 
        			for(var i=0;i<obj.listMenu.length;i++){   
        				panelTree.add(obj.listMenu[i]);   
        			} 
        	   		var x=Ext.getCmp('west-panel');
        	   		x.doLayout();
        	       	this.goPage = function abc(id,text,href){
                		tabs.remove('tabId1');
                		tabs.add({
                			 title:text,
                	         id:"tabId1",
                	         html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+href+'"></iframe>'                      
                		});  
                		
                	   tabs.setActiveTab('tabId1');
                	   var x=Ext.getCmp('tabId1');
                	  // x.getUpdater().refresh();
                	}
        	       	var _today = ",今天是";
        	       	var viewport = new Ext.Viewport({
                        layout: 'border',
                        items: [
                        new Ext.BoxComponent({
                            region: 'north',
                            //height: auto, 
                            autoEl: {
                                tag: 'div',
                                html:'<div id="header" align="right"><div id="greeting"></div><p>欢迎<font size="3" color="red">'+obj.adminname+'</font>'+_today+'<%=new java.text.SimpleDateFormat("yyyy-MM-dd")
							.format(new java.util.Date())%></a><a href="javascript:updatePwd()">修改密码</a><a href="'+ctx+'/customer/customer/customer!loginOut.action">注销</a></p></div>'
                            }
                        }),panelTree,tabs]
                    });    
        	       	var gotoUrl = "";//ctx+'=request.getParameter("goPage")%>';
        	       	var flagStr;
        	       	if(gotoUrl.length >= 22)
        	       		flagStr = gotoUrl.substring(gotoUrl.length-21,gotoUrl.length);
        	       	else
        	       		return;
        	       	if(flagStr = 'FeveryDayMsgReport.jsp'){
        	       		goPage(-100,'<b>网关数据统计</b>',ctx+'<%=request.getParameter("goPage")%>');
        			}else{
        				goPage(-100,'<b>客户数据分析</b>',ctx+'<%=request.getParameter("goPage")%>');
        			}
        		},  
        		failure: function(){   
            		Ext.Msg.alert('错误', '发生错误了');   
        		}    
        	});          
 
        }); 
        </script>    

 		<style type="text/css"> 
 		
 		    html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
    p {
        margin:5px;
    }
			.add { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/drop-add.gif)
			    !important;
			}
			.edit { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/cog_edit.png)
			    !important;
			}
			.search_16x16 { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/search_16x16.gif)
			    !important;
			}
			.delete { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/delete.gif)
			    !important;
			}
			
			.registration2_16x16 { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/registration2_16x16.gif)
			    !important;
			}
			.business_16x16 { 
			    background-image:
			    url(${ctx}/jsp/common/js/ext/extjsico/business_16x16.gif)
			    !important;
			}
			.tip-target {
			    width: 100px;
			    text-align:center;
			    padding: 5px 0;
			    border:1px dotted #99bbe8;
			    background:#dfe8f6;
			    color: #15428b;
			    cursor:default;
			    margin:10px;
			    font:bold 11px tahoma,arial,sans-serif;
			    float:left;
			 }
		</style>  
    </head>  
  
  <body>
</body>
</html>