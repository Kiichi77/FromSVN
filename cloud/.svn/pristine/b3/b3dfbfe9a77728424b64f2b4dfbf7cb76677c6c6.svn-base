<%@page import="cn.com.kxcomm.contractmanage.web.util.SessionUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript">
		function select(){
			$('#aa').accordion('select','Title1');
		}
		var idx = 1;
		function add(){
			$('#aa').accordion('add',{
				title:'New Title'+idx,
				content:'New Content'+idx
			});
			idx++;
		}
		function remove(){
			var pp = $('#aa').accordion('getSelected');
			if (pp){
				var index = $('#aa').accordion('getPanelIndex',pp);
				$('#aa').accordion('remove',index);
			}
		}
		
		//分页工具条
		 $('#tt').datagrid('getPager').pagination({
			    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
			    onBeforeRefresh:function(pageNumber, pageSize){
			     $(this).pagination('loading');
			     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
			     $(this).pagination('loaded');
			    }
		 });
		
		//弹出层***************************************
			//添加存储操信息操作
			function showWindow(operType) {
			  onselectUser();
			  $('#addDataInfoDiv').window('open');
			  $('#addDataInfoform').show();
		      $('#addDataInfoform').form('clear');
		       $('#addDataInfoform').appendTo('#aa');
		       $("#operType").val(operType); //设置操作类型 1、发布模板审核流程,0、发布项目流程
			}

			function startTemplateCheckPM(){
				$('#addDataInfoform').form('submit', {
					url:ctx+'activiti/activiti/activiti!startAprocessByUser.action',
					onSubmit: function(){
						// 做某些检查
						// 返回 false 来阻止提交
						return $(this).form('validate'); 
					},
					success:function(data){
						var data = eval('(' + data + ')');
						if(null!=data && ""!=data){
							if(data.msg=="ok"){
								$.messager.alert("提示","流程创建成功!");
								reloadDataGrid();
								hideDataInfoDiv();
							}else {
								$.messager.alert("提示","流程创建失败!");
							}
						}
					}
				}); 
			}
			
			//重新加载列表
			function reloadDataGrid() {
				$('#tt1').datagrid('reload');
				$('#tt2').datagrid('reload');
				$('#tt3').datagrid('reload');
			}
			
			//隐藏数据详情窗口操作
			function hideDataInfoDiv() {
				/*  $("#addDataInfoDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");    */
				 $('#addDataInfoDiv').window('close');
			}
				
			//角色下拉选择框数据加载
			function onselectUser(){
				var sel = $("#tbUser");  
				sel.empty();//清除select中的 option
				$.post(ctx+'user/user/user!queryAllsales.action',function(data){
					var datas = eval("("+data+")");
					if(null!=datas && ""!=datas){
						for(var i = 0;i < datas.rows.length; i++) {
							var opt = $("<option>").text(datas.rows[i].userName).val(datas.rows[i].id);  
							sel.append(opt);  
						}
					}
				});	
			};
			
	</script>
<!-- <body>
		
	     <iframe name="main" src="mywork.jsp" width="98%" height="100%"  frameborder="no"  scrolling="auto"></iframe>
	    
</body> -->


<body  class="easyui-layout" >
	<div id="content"  data-options="region:'center'" title="欢迎使用" style="overflow:hidden;">
	
		<div style="width:auto;margin: auto;margin-top: 10px;margin-left: 20px;">
		 	<font style="width:auto;margin: auto;margin-top: 5px;">发布流程</font>
		 </br>
		<%
			Long roleid=(Long)session.getAttribute(SessionUtils.USER_ROLE_ID);
		    if(roleid==1l){ //只有管理人员和销售人员才能够发布流程
		%>
                <a href="javascript:void(0)" onclick="showWindow(0);"><font style="width:auto;margin: auto;margin-top: 5px;">发布模板审核流程</font></a>
                <a href="javascript:void(0)" onclick="showWindow(1);"><font style="width:auto;margin: auto;margin-top: 5px;">发布报价审核流程</font></a>
                <a href="javascript:void(0)" onclick="showWindow(2);"><font style="width:auto;margin: auto;margin-top: 5px;">发布销售采购流程</font></a>
		<%}else if(roleid==4){ %>
				<a href="javascript:void(0)" onclick="showWindow(1);"><font style="width:auto;margin: auto;margin-top: 5px;">发布报价审核流程</font></a>
                <a href="javascript:void(0)" onclick="showWindow(2);"><font style="width:auto;margin: auto;margin-top: 5px;">发布销售采购流程</font></a>
		<%} %>
		</div>

		<table style="width: auto; margin: auto; margin-top: 18px;"
			cellpadding="5">
			<tr>
				<td>
					<div id="daiban" class="easyui-accordion"
						style="width: 450px; height: 550px;">
						<div title="待办任务"
							data-options="iconCls:'icon-tip',selected:true,
								tools:[{
									iconCls:'icon-reload',
									handler:function(){
										$('#tt').datagrid('reload');
									}
								}]">
							<table id="tt1">
							</table>
						</div>
					</div>
				</td>
				<td>
					<div id="myhistory" class="easyui-accordion"
						style="width: 450px; height: 550px;">
						<div title="我发起的流程"
							data-options="iconCls:'icon-tip',selected:true,
								tools:[{
									iconCls:'icon-reload',
									handler:function(){
										$('#tt').datagrid('reload');
									}
								}]">
							<table id="tt2">
							</table>
						</div>
					</div>
				</td>
				<td>
					<div id="qita" class="easyui-accordion"
						style="width: 450px; height: 550px;">
						<div title="我的历史任务"
							data-options="iconCls:'icon-tip',selected:true,
								tools:[{
									iconCls:'icon-reload',
									handler:function(){
										$('#tt').datagrid('reload');
									}
								}]">
							<table id="tt3">
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>

		<form id="addDataInfoform" method="post" enctype="multipart/form-data"
			novalidate style="width: 300px; height: 100px;">
			<div id="addthevmleft"
				style="width: 300px; height: 100px; float: center;">
				<input id="operType" name="typeId" type="hidden">
				<div style="margin-left: 20px">
					<font style="font-size: 13px;">流程名称：</font>&nbsp;&nbsp; <input
						id="processName" name="process_name" class="easyui-validatebox"
						type="text" style="width: 160px;" data-options="required:true" />
				</div>
				<br />
				<div style="margin-left: 20px">
					<font style="font-size: 13px;">处理人：</font>&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="tbUser" name="user_id" style="width: 160px;"
						data-options="required:true"></select>
				</div>
			</div>
			<div align="center" style="width: 300px;">
				<a href="javascript:void(0);" onclick="startTemplateCheckPM();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">提
					交</a> <a href="#" onclick="hideDataInfoDiv();"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取
					消</a>
			</div>
		</form>
		<!--  style="background:#fafafa;padding:20px;width:350px;height:150px;display: none;border: solid gray 1px;" -->
		<div id="addDataInfoDiv" class="easyui-window" closed="true"
			maximizable="false" minimizable="false" collapsible="false"
			title="发布流程" style="padding: 10px; width: 350px; height: 200px;"
			iconCls="icon-add">
			<div id="aa"></div>
		</div>

	</div>
</body>
</html>