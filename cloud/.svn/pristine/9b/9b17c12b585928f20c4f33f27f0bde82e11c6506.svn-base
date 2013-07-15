<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String realPath = session.getAttribute("realPath").toString();
%>
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script>
		$(function(){
			$('#tt').datagrid({
				url: 'datagrid_data2.json',
				title: '所有配额',
				width: 'auto',
				height: 'auto',
				fitColumns: true,
				columns:[[
					{field:'quotaName',title:'配额名称',width:'500',align:'center'},
					{field:'quotas',title:'限制',width:'50',align:'center'}
				]],
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
		
		function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
			var fields = $('#tt').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						$('#tt').datagrid('hideColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						$('#tt').datagrid('showColumn', item.text);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}
	</script>

<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	<div data-options="region:'center'" title="配额" style="overflow:hidden;padding:10px;">
	
	<table id="tt"></table>
	
	</div>
</body>
</html>