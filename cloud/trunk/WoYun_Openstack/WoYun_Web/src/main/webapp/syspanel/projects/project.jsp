<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/project_controller.js"></script>
<script type="text/javascript" src="view/project_view.js"></script>
<script>
//用户信息列表
$(function(){
	$('#tt').datagrid({
		url:ctx+'/user/user/project!listProjects.action',
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
			{field:'id',title:'项目ID',width:60,align:'center'},
			{field:'name',title:'名称',width:30,align:'center'},
			{field:'description',title:'叙述',width:70,align:'center'},
			{field:'enabled',title:'已启用',width:30,align:'center'},
			{field:'todos',title:'操作',width:40,align:'center',
				//添加超级链 
                formatter:function(value,rowData,rowIndex){ 
                	var projectids = ""+rowData.id;
                	var edit = '<select id="edit" class="easyui-combobox" name="state" style="width:150px;font-size: 12;"'+
					   'data-options="required:true" onchange="changeForm(this,\''+projectids+'\')" >'+
					   '<option value="0" selected="selected">请选择操作</option>'+
					   '<option value="1">编辑项目</option>'+
			     	   '<option value="2">修改用户</option>'+
			     	   '<option value="3">修改配额</option>'+
			     	   '<option value="4">删除  项目</option>'+
			     	   '</select>';
                    return edit;  
               }  
			}
		]],
		toolbar:[{
			id:'btnadd',
			text:'建立项目',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				clearProjectInfoForm("toproject")
				showWindow("toproject");
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeProject();
			}
		}],
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
});
</script>

<body class="easyui-layout">
	<div data-options="region:'center'" title="项目管理" style="overflow: hidden; padding: 10px;">
		<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:250px;display: none;z-index: 9999;border: solid gray 1px;">
		    <form id="addVmform" method="post" novalidate style="width: 450px;height:250px;">
		    	<div id="thevmleft" style="width: 210px;height:240px;float: left;">
		    		<div style="display: none;">			            
			            <input id="projectIds" class="easyui-validatebox" type="text" name="model.id" style="width: 200px;" data-options="required:false"></input>
			        </div><br>
			        <div>
			            <label for="projectNames">名称:</label><br>
			            <input id="projectNames" class="easyui-validatebox" type="text" name="model.name" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="projectDescription">叙述:</label><br>
			            <textarea id="projectDescription" name="model.description" style="height:80px;width: 200px;"></textarea>
			        </div><br>
			        <div>
			            <label for="theAbeld">已启用:</label><br>
			            <input id="theAbeld" type="checkbox" name="model.enabled"></input>
			        </div>
		        </div>
		        <div id="thevmright" style="width: 210px;height:220px;float: right;">
		        	<h4>叙 述</h4>
					<h5>您可以在这里建立新项目以管理用户</h5>
		        </div>
			    <div align="right" style="width: 450px;">
			    	 <a href="#" onclick="submitProjectInfo('toproject');" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	 <a href="#" onclick="hideDiv('toproject');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			    </div>
		    </form>
		</div>
		<div id="updateQuota" style="background:#fafafa;padding:20px;width:460px;height:250px;display: none;z-index: 9999;border: solid gray 1px;">
		    <form id="updateQuotaform" method="post" novalidate style="width: 450px;height:250px;">
		    	<div id="updateQuotaleft" style="width: 210px;height:240px;float: left;">
		    		<div>
		    			<label for="idnames">ID(名称):</label><br>			            
			            <input id="idnames" class="easyui-numberspinner" type="text" name="model.id" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="0">中介资料对象数:</label><br>
			            <input id="0" class="easyui-numberspinner" type="text" name="0" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="01">注入档案数:</label><br>
			            <input id="01" class="easyui-numberspinner" type="text" name="01" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			         <div>
			            <label for="1">注入档案內容位元数:</label><br>
			            <input id="1" class="easyui-validatebox" type="text" name="1" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			         <div>
			            <label for="2">虚拟处理器:</label><br>
			            <input id="2" class="easyui-validatebox" type="text" name="2" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			         <div>
			            <label for="3">运行实例:</label><br>
			            <input id="3" class="easyui-validatebox" type="text" name="3" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="4">存储:</label><br>
			            <input id="4" class="easyui-validatebox" type="text" name="4" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="5">儲存空间 （Gigabytes）:</label><br>
			            <input id="5" class="easyui-validatebox" type="text" name="5" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="6">内存空间 （MB）:</label><br>
			            <input id="6" class="easyui-validatebox" type="text" name="6" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="7">浮动IP:</label><br>
			            <input id="7" class="easyui-validatebox" type="text" name="7" style="width: 200px;" data-options="required:true"></input>
			        </div>
		        </div>
		        <div style="width: 210px;height:220px;float: right;">
		        	<h4>叙 述</h4>
					<h5>您可以在这里编辑项目的资源配额（最大用额）。</h5>
		        </div>
			    <div align="right" style="width: 450px;">
			    	 <a href="#" onclick="submitUserInfo();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	 <a href="#" onclick="hideDiv('toproject');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			    </div>
		    </form>
		</div>
		<table id="tt"></table>
	</div>
</body>
</html>