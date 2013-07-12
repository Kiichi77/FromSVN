<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cn.com.woyun.keystone.model.Tenant" %>
<%
	String realPath = session.getAttribute("realPath").toString();
%>
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/users_controller.js"></script>
<script type="text/javascript" src="view/users_view.js"></script>
<script>
//用户信息列表
$(function(){
	$('#tt').datagrid({
		url:ctx+'/user/user/user!queryAllUser.action',
		idField:'id',
		width: 'auto',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[
		    {field:'ck',checkbox:true,align:'center'},
			{field:'id',title:'UserID',width:60,align:'center'},
			{field:'username',title:'用户名称',width:60,align:'center'},
			{field:'email',title:'邮箱地址',width:30,align:'center'},
			{field:'enabled',title:'已启用',width:30,align:'center'},
			{field:'todos',title:'操作',width:50,align:'center',
				//添加超级链 
                formatter:function(value,rowData,rowIndex){ 
                	var userids = ""+rowData.id;
                    return '<a href="#" onclick="deleteUser(\''+userids+'\')">删除</a>';  
               }  
			}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				clearUserinfoForm();
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeUser();
			}
		}],
		onDblClickRow: function(rowIndex,rowData) {
		    	editUserInfo(rowData.id)
		},       
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}
	});
	var s = $("#thezuhu");  
	s.empty();//清除select中的 option
	<% 
		List<Tenant> tens = (List<Tenant>) session.getAttribute("userTenants");
		for(int i = 0;i < tens.size(); i++) {
	%>
		var opt = $("<option>").text('<%=tens.get(i).getName()%>').val('<%=tens.get(i).getId()%>');  
		s.append(opt);  
	<%}%>
});
</script>

<body class="easyui-layout">
	<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
	<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	<div data-options="region:'center'" title="用户管理" style="overflow: hidden; padding: 10px;">
		<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:310px;display: none;z-index: 9999;border: solid gray 1px;">
		    <form id="addVmform" method="post" novalidate style="width: 450px;height:310px;">
		    	<div id="thevmleft" style="width: 210px;height:300px;float: left;">
		    		<div style="display: none;">			            
			            <input id="userIds" class="easyui-validatebox" type="text" name="model.id" style="width: 200px;" data-options="required:false"></input>
			        </div><br>
			        <div id="thecon">
			            <label for="usercon">账号:</label><br>
			            <input id="usercon" class="easyui-validatebox" type="text" name="model.name" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div id="thepwd">
			            <label for="userpwd">密码:</label><br>
			            <input id="userpwd" class="easyui-validatebox" type="password" name="model.password" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div id="therepwd">
			            <label for="repwd">重复密码:</label><br>
			            <input id="repwd" class="easyui-validatebox" type="password" name="repwd" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="userNames">用户名称:</label><br>
			            <input id="userNames" class="easyui-validatebox" type="text" name="model.username" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="userEmail">电子邮箱:</label><br>
			            <input id="userEmail" class="easyui-validatebox" type="text" name="model.email" style="width: 200px;" data-options="required:true"></input>
<!-- 			        </div><br> -->
<!-- 			        <div id="therole"> -->
<!-- 			            <label for="userRole">角色:</label><br> -->
<!-- 			            <select id="userRole" name="userRole" style="width:200px;" data-options="required:true"></select> -->
			        </div><br>
			        <div id="thezuhus">
			            <label for="thezuhus">所属项目:</label><br>
			            <select id="thezuhu" name="model.tenantId" style="width:200px;" data-options="required:true"></select>
			        </div>
		        </div>
		        <div id="thevmright" style="width: 210px;height:280px;float: right;">
		        	<h4>叙 述</h4>
					<h5>您可以在这里建立一个新的用户及将其分配到项目</h5>
		        </div>
			    <div align="right" style="width: 450px;">
			    	 <a href="#" onclick="submitUserInfo();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			    </div>
		    </form>
		</div>
		<table id="tt"></table>
	</div>
</body>
</html>