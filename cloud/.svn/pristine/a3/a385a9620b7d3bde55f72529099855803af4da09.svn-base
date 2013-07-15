<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
request.setAttribute("ctx",path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../view/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	</script>
<title>Insert title here</title>
</head>
<body >
	<div id="daiban" class="easyui-accordion" style="width:auto;height:200px;">
		<div title="待办任务" data-options="iconCls:'icon-tip',selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#tt').datagrid('reload');
					}
				}]">
			<table id="tt" class="easyui-datagrid"  data-options="url:'Json/mywork.json',border:false,fit:true,fitColumns:true,singleSelect:true,pagination:true,pageList:[5,10,15]">
				<thead>
					<tr >
					<th data-options="field:'id',width:10,align:'center'">序号</th>
						<th data-options="field:'description',width:80,align:'center'">任务</th>
						<th data-options="field:'createTime',width:80,align:'center'">时间</th>
						<th data-options="field:'follow',width:80,align:'center'">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="myhistory" class="easyui-accordion" style="width:auto;height:200px;margin-top: 5px;">
		<div title="我的历史任务" data-options="iconCls:'icon-tip',selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#tt').datagrid('reload');
					}
				}]">
			<table id="tt" class="easyui-datagrid"  data-options="url:'Json/mywork.json',border:false,fit:true,fitColumns:true,singleSelect:true,pagination:true,pageList:[5,10,15]">
				<thead>
					<tr >
					<th data-options="field:'id',width:10,align:'center'">序号</th>
						<th data-options="field:'description',width:80,align:'center'">任务</th>
						<th data-options="field:'createTime',width:80,align:'center'">时间</th>
						<th data-options="field:'follow',width:80,align:'center'">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="qita" class="easyui-accordion" style="width:auto;height:200px;margin-top: 5px;">
		<div title="其他人的任务" data-options="iconCls:'icon-tip',selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#tt').datagrid('reload');
					}
				}]">
			<table id="tt" class="easyui-datagrid"  data-options="url:'Json/other.json',border:false,fit:true,fitColumns:true,singleSelect:true,pagination:true,pageList:[5,10,15]">
				<thead>
					<tr >
					<th data-options="field:'id',width:10,align:'center'">序号</th>
						<th data-options="field:'role',width:20,align:'center'">角色</th>
						<th data-options="field:'name',width:20,align:'center'">处理人</th>
						<th data-options="field:'description',width:80,align:'center'">任务描述</th>
						<th data-options="field:'createTime',width:80,align:'center'">时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>