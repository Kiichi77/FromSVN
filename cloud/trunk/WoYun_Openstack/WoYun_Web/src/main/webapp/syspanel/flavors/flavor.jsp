<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript" src="controller/flavors_controller.js"></script>
<script type="text/javascript" src="view/flavors_view.js"></script>
<script>
//用户信息列表
$(function(){
	$('#tt').datagrid({
		url:ctx+'/server/server/flavors!listFlavors.action',
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
			{field:'id',title:'模板ID',width:60,align:'center',hidden:true},
			{field:'name',title:'模板名称',width:60,align:'center'},
			{field:'vcpus',title:'虚拟处理器',width:30,align:'center'},
			{field:'ram',title:'内存(MB)',width:30,align:'center'},
			{field:'disk',title:'主硬盘(GB)',width:30,align:'center'},
			{field:'ephemeral',title:'暫用硬盘(GB)',width:30,align:'center'},
			{field:'todos',title:'操作',width:50,align:'center',
				//添加超级链 
                formatter:function(value,rowData,rowIndex){ 
                	var flavorids = ""+rowData.id;
                    return '<a href="#" onclick="deleteFlavor(\''+flavorids+'\')">删除</a>';  
               }  
			}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				clearFlavorinfoForm();
				showWindow();
			}
		},{
			id:'btndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				deleteSomeFlavor();
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
	<div data-options="region:'center'" title="模板管理" style="overflow: hidden; padding: 10px;">
		<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:310px;display: none;z-index: 9999;border: solid gray 1px;">
		    <form id="addVmform" method="post" novalidate style="width: 450px;height:310px;">
		    	<div id="thevmleft" style="width: 210px;height:300px;float: left;">
		    		<div>
			            <label for="flavorId">模板ID:</label><br>
			            <input id="flavorId" class="easyui-numberspinner" type="text" name="model.id" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="flavorNames">模板名称:</label><br>
			            <input id="flavorNames" class="easyui-validatebox" type="text" name="model.name" style="width: 200px;" data-options="required:true"></input>
			        </div><br>
			        <div>
			            <label for="vmcpus">虚拟处理器:</label><br>
			            <input id="vmcpus" class="easyui-numberspinner" type="text" name="model.vcpus" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="vmRam">内存(MB):</label><br>
			            <input id="vmRam" class="easyui-numberspinner" type="text" name="model.ram" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="vmDisks">主硬盘(GB):</label><br>
			            <input id="vmDisks" class="easyui-numberspinner" type="text" name="model.disk" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div><br>
			        <div>
			            <label for="vmEphemeral">暂用硬盘(GB):</label><br>
			            <input id="vmEphemeral" class="easyui-numberspinner" type="text" name="model.ephemeral" style="width: 200px;" data-options="required:true,onChange: function(value){  
                    	$('#vv').text(value);} "></input>
			        </div>
		        </div>
		        <div id="thevmright" style="width: 210px;height:280px;float: right;">
		        	<h4>叙 述</h4>
					<h5>您可以在这里制定新模板的配置</h5>
		        </div>
			    <div align="right" style="width: 450px;">
			    	 <a href="#" onclick="submitFlavorInfo();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
			    	 <a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
			    </div>
		    </form>
		</div>
		<table id="tt"></table>
	</div>
</body>
</html>