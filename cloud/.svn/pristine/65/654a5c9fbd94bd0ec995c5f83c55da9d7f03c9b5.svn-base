<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script>
     var typelist = [
 		   		    {type:"CPU",name:"CPU"},
 		   		    {type:"磁盘",name:"磁盘"},
 		   		    {type:"内存",name:"内存"}
 		   		 
 		];
		var thresholdlist = [
		   		    {threshold:50,name:"50%"},
		   		    {threshold:60,name:"60%"},
		   		    {threshold:70,name:"70%"},
		   		    {threshold:80,name:"80%"},
		   		    {threshold:90,name:"90%"},
		   		    {threshold:100,name:"100%"}
		];
		var namelist = [
		 		   		    {name:"admin",name:"admin"},
		 		   		    {name:"kanxun",name:"kanxun"}
		 		];
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('appendRow',{
							alarmID:'',
							type:'',
							threshold:'',
							name:'',
							email:'',
							enabled:''
						});
						lastIndex = $('#tt').datagrid('getRows').length-1;
						$('#tt').datagrid('selectRow', lastIndex);
						$('#tt').datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt').datagrid('getSelected');
						if (row){
							var index = $('#tt').datagrid('getRowIndex', row);
							$('#tt').datagrid('deleteRow', index);
						}
					}
				},'-',{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('acceptChanges');
					}
				},'-',{
					text:'取消',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				}],
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');

				},
				onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
		});
	</script>


<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	<div data-options="region:'center'" title="报警" style="overflow:hidden;padding:10px;">
	<div style="margin-left: 20px">
	<table id="tt" style="width:auto;height:auto"
			data-options="iconCls:'icon-edit',idField:'itemid',url:'alarm_data.json'">
		<thead>
			<tr>	
			<th data-options="field:'cd',checkbox:true"></th>			
			<th data-options="field:'alarmID',width:120,align:'center'">报警ID</th>		
				<th data-options="field:'type',width:120,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'type',
								textField:'name',
								data:typelist,
								required:true
							}
						}">类型</th>
				<th data-options="field:'threshold',width:120,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'threshold',
								textField:'name',
								data:thresholdlist,
								required:true
							}
						}">触发告警阀值</th>				
				<th data-options="field:'name',width:120,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'name',
								textField:'name',
								data:namelist,
								required:true
							}
						}">所属项目名称</th>					
				 <th data-options="field:'email',width:120,align:'center',editor:'text'">邮箱</th>
				<th data-options="field:'enabled',width:120,align:'center',editor:{type:'checkbox',options:{on:'True',off:'False'}}">已启用</th>
			</tr>
		</thead>
	</table>

	</div>
	</div>
</body>
</html>