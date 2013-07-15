<%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript">
	var historyPagesize = 15;//历史数据页大小
	var currentPagesize = 15;//我的待办任务数据页大小
	//历史任务读取下一页
	function nextPageHistory() {
		historyPagesize += 15;
		//历史任务
				$.post(ctx + 'task/task/task!finishTask.action',
						{
							'page' : 1,
							'rows' : historyPagesize
						},
						function(data) {
							var data = eval('(' + data + ')');
							if (0 < data.rows.length) {
								$('tbody[name="tt3"]').empty();
								for ( var i = 0; i < data.rows.length; i++) {
									var taskData = data.rows[i];
									var state;
									var href;
									if (taskData.state == 0) {
										state = '未处理';
									} else {
										state = '已处理';
									}
									;
									var text = '<tr><td>' + taskData.taskID+ '</td>' 
											+ '<td><a href="'+ctx+taskData.url+'">'+ taskData.taskName + '</a></td>'
											+ '<td>' + taskData.sponsorName+ '</td>' 
											+ '<td>'+ taskData.projectName + '</td>'
											+ '<td>'+ taskData.personInChargeName+ '</td>' 
											+ '<td>' + state+ '</td>' 
											+ '<td>'+ taskData.allocationTime + '</td>'
											+ '<td>' + taskData.finishTime+ '</td>' 
											+ '<td>'+ taskData.remark + '</td>'
											+ '</tr>';
									$('tbody[name="tt3"]').append(text);
								}
								$('button[name="butt3"]').css('display','block');
							} else {
								$('tbody[name="tt3"]').append("<tr><td>无</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
							}
						});
	}

	//代办任务读取下一页
	function nextPagecurn() {
		currentPagesize += 15;
		//代办任务
		$.post(ctx + 'task/task/task!myCurrentTask.action',
						{
							'page' : 1,
							'rows' : currentPagesize
						},
						function(data) {
							var data = eval('(' + data + ')');
							if (0 < data.rows.length) {
								$('tbody[name="tt1"]').empty();
								for ( var i = 0; i < data.rows.length; i++) {
									var taskData = data.rows[i];
									var state;
									var href;
									if (taskData.state == 0) {
										state = '未处理';
										href = '<a href="javascript:showWindow(\''+taskData.roleId+'\',\''+taskData.taskID+'\',\''+taskData.taskName+'\');">转交</a>';
										//&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:submitTask(\''+taskData.taskID+'\',\''+taskData.taskName+'\');">提交任务</a>
									} else {
										state = '已处理';
										href = '转交'; //&nbsp;&nbsp;|&nbsp;&nbsp;提交任务
									}
									;
									var text = '<tr><td>' + taskData.taskID+ '</td>' 
											+ '<td><a href="'+ctx+taskData.url+'">'+ taskData.taskName + '</a></td>'
											+ '<td>' + taskData.sponsorName+ '</td>' 
											+ '<td>'+ taskData.projectName + '</td>'
											+ '<td>'+ taskData.personInChargeName+ '</td>' 
											+ '<td>' + state+ '</td>' 
											+ '<td>'+ taskData.allocationTime + '</td>'
											+ '<td>' + taskData.remark+ '</td>' 
											+ '<td>' + href + '</td>'
											+ '</tr>';
									$('tbody[name="tt1"]').append(text);
								}
								$('button[name="butt1"]').css('display','block');
							} else {
								$('tbody[name="tt1"]').append("<tr><td>无</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
							}
						});
	}
	
	//弹出层***************************************
	//添加存储操信息操作
	function showWindow(roleId,taskId,taskName) {
	  onselectUser(roleId);
	  $('#addDataInfoDiv').window('open');
	  $('#addDataInfoform').show();
      $('#addDataInfoform').form('clear');
      $('#addDataInfoform').appendTo('#aa');
      $('#taskID').val(taskId);
      $('#taskName').val(taskName);
	}
	
	//重新加载列表
	function reloadDataGrid() {
		window.location.href = ctx+'/view/main.jsp';
	}
	
	//隐藏数据详情窗口操作
	function hideDataInfoDiv() {
		 $('#addDataInfoDiv').window('close');
	}
		
	//角色下拉选择框数据加载
	function onselectUser(roleId){
		var sel = $("#tbUser");  
		sel.empty();//清除select中的 option
		$.post(ctx+'user/user/user!forwordTaskFindUserByRole.action',
				{
					roleid:roleId
				},function(data){
			var datas = eval("("+data+")");
			if(null!=datas && ""!=datas){
				for(var i = 0;i < datas.rows.length; i++) {
					var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
					sel.append(opt);  
				}
			}
		});	
	};
	
	//任务转交
	function startTemplateCheckPM(){
		$('#addDataInfoform').form('submit', {
			url:ctx+'task/task/task!forwardedTask.action',
			onSubmit: function(){
				// 做某些检查
				// 返回 false 来阻止提交
				return $(this).form('validate'); 
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(null!=data && ""!=data){
					if(data.msg=="ok"){
						$.messager.alert("提示","任务转交成功!");
					}else {
						$.messager.alert("提示","任务转交失败，请联系管理员!");
					}
					reloadDataGrid();
					hideDataInfoDiv();
				}
			}
		}); 
	}
	
	//提交任务
	function submitTask(pkid,taskName){
		$.messager.confirm('提示','确定要提交任务？',function(r){
			if(r){
				$.post(ctx+'task/task/task!submitTask.action',
						{
						'model.id':pkid,
						'model.taskName':taskName,
						},function(data){
					var datas = eval("("+data+")");
					if(null!=datas && ""!=datas){
						if(datas.msg=='ok'){
							$.messager.alert("提示","任务已经完成!");
						}else{
							$.messager.alert("提示","任务提交失败，请联系管理员!");
						}
						reloadDataGrid();
					}
				});	
			}
		});
	}

	$(function() {
		//影藏流程任务分配弹出框
		$('#addDataInfoform').hide();
		//代办任务
			$.post(ctx + 'task/task/task!myCurrentTask.action',
						{
							'page' : 1,
							'rows' : currentPagesize
						},
						function(data) {
							var data = eval('(' + data + ')');
							if (0 < data.rows.length) {
								for ( var i = 0; i < data.rows.length; i++) {
									var taskData = data.rows[i];
									var state;
									var href;
									if (taskData.state == 0) {
										state = '未处理';
										href = '<a href="javascript:showWindow(\''+taskData.roleId+'\',\''+taskData.taskID+'\',\''+taskData.taskName+'\');">转交</a>';
										//&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:submitTask(\''+taskData.taskID+'\',\''+taskData.taskName+'\');">提交任务</a>
									} else {
										state = '已处理';
										href = '转交'; //&nbsp;&nbsp;|&nbsp;&nbsp;提交任务
									};
									var text = '<tr><td>' + taskData.taskID+ '</td>' 
									+ '<td><a href="'+ctx+taskData.url+'">'+ taskData.taskName + '</a></td>'
									+ '<td>' + taskData.sponsorName+ '</td>' 
									+ '<td>'+ taskData.projectName + '</td>'
									+ '<td>'+ taskData.personInChargeName+ '</td>' 
									+ '<td>' + state+ '</td>' 
									+ '<td>'+ taskData.allocationTime + '</td>'
									+ '<td>' + taskData.remark+ '</td>' 
									+ '<td>' + href + '</td>'
									+ '</tr>';
									$('tbody[name="tt1"]').append(text);
								}
								$('button[name="butt1"]').css('display','block');
							} else {
								$('tbody[name="tt1"]').append("<tr><td>无</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
							}
						});

		//已完成的任务
			$.post(ctx + 'task/task/task!finishTask.action',
						{
							'page' : 1,
							'rows' : historyPagesize
						},
						function(data) {
							var data = eval('(' + data + ')');
							if (0 < data.rows.length) {
								for ( var i = 0; i < data.rows.length; i++) {
									var taskData = data.rows[i];
									var state;
									var href;
									if (taskData.state == 0) {
										state = '未处理';
									} else {
										state = '已处理';
									}
									;
									var text = '<tr><td>' + taskData.taskID+ '</td>' 
														+ '<td><a href="'+ctx+taskData.url+'">'+ taskData.taskName + '</a></td>'
														+ '<td>' + taskData.sponsorName+ '</td>' 
														+ '<td>'+ taskData.projectName + '</td>'
														+ '<td>'+ taskData.personInChargeName+ '</td>' 
														+ '<td>' + state+ '</td>' 
														+ '<td>'+ taskData.allocationTime + '</td>'
														+ '<td>' + taskData.finishTime+ '</td>' 
														+ '<td>'+ taskData.remark + '</td>'
														+ '</tr>';
									$('tbody[name="tt3"]').append(text);
								}
								$('button[name="butt3"]').css('display','block');
							} else {
								$('tbody[name="tt3"]').append("<tr><td>无</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
							}
						});
	});
</script>
<!-- <body>
		
	     <iframe name="main" src="mywork.jsp" width="98%" height="100%"  frameborder="no"  scrolling="auto"></iframe>
	    
</body> -->


<body>


	<div class="tabbable" style="margin-bottom: 18px; margin: 2px 2px;">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">待办任务</a></li>
			<li><a href="#tab3" data-toggle="tab">已处理的任务</a></li>
		</ul>
		<div class="tab-content"
			style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
			<div class="tab-pane active" id="tab1">
				<table class="table">
					<thead>
						<tr>
							<th>任务编号</th>
							<th>任务名称</th>
							<th>发起人</th>
							<th>所属项目</th>
							<th>负责人</th>
							<th>任务状态</th>
							<th>分配时间</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody name="tt1">

					</tbody>
				</table>
				<button class="btn btn-large btn-block btn-primary" type="button"
					name="butt1" style="display: none" onclick="nextPagecurn();">更多</button>

			</div>
			<div class="tab-pane" id="tab3">
				<table class="table">
					<thead>
						<tr>
							<th>任务编号</th>
							<th>任务名称</th>
							<th>发起人</th>
							<th>所属项目</th>
							<th>负责人</th>
							<th>任务状态</th>
							<th>分配时间</th>
							<th>完成时间</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody name="tt3">

					</tbody>
				</table>
				<button class="btn btn-large btn-block btn-primary" type="button"
					name="butt3" style="display: none" onclick="nextPageHistory();">更多</button>
			</div>
		</div>
	</div>
	
	
	<form id="addDataInfoform" method="post" enctype="multipart/form-data"
			novalidate style="width: 300px; height: 100px;">
			<div id="addthevmleft" style="width: 300px; height: 100px; float: center;">
				<input id="taskID" name="taskVo.taskID" type="hidden">
				<div style="margin-left: 20px">
					<font style="font-size: 13px;">任务名称：</font>
					<input id="taskName" name="taskVo.taskName" class="easyui-validatebox" type="text" readonly="readonly" style="width: 160px;" data-options="required:true" />
				</div>
				<div style="margin-left: 20px">
					<font style="font-size: 13px;">处理人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="tbUser" name="taskVo.personInChargeId" style="width: 160px;"
						data-options="required:true"></select>
				</div>
			</div>
			<div align="center" style="width: 300px;">
				<a href="javascript:void(0);" onclick="startTemplateCheckPM();"class="easyui-linkbutton" >提交</a> 
				<a href="#" onclick="hideDataInfoDiv();"class="easyui-linkbutton">取消</a>
			</div>
		</form>
		<div id="addDataInfoDiv" class="easyui-window" closed="true"
			maximizable="false" minimizable="false" collapsible="false"
			title="任务转交" style="padding: 10px; width: 350px; height: 200px;"
			iconCls="icon-add">
			<div id="aa"></div>
		</div>

</body>
</html>