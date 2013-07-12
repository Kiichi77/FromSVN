<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$('#tt').datagrid({
		url: 'vm_data.json',
//		title: '所有虚拟机',
		width: 'auto',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		      
					{field:'vmName',title:'虚拟机名称',width:140},
					{field:'IPAddress',title:'浮动IP地址',width:80},
					{field:'publicIPAddress',title:'公网IP地址',width:80},
					{field:'name',title:'所属项目名称',width:80},
					{field:'disk',title:'硬盘',width:80},
					{field:'RAM',title:'内存',width:80},
					{field:'status',title:'状态',width:80},
					{field:'task',title:'工作',width:80},
					{field:'powerState',title:'电源状态',width:80},
					{field:'uptime',title:'运行时间',width:80},
					{field:'details',title:'查看详情',width:80,align:'center'}
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
	var p = $('#tt').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});	
//创建列菜单
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
	<div data-options="region:'center'" title="虚拟机总览" style="overflow:hidden;">
	<div style="margin-left: 30px;width: 90%">
	  <div><p>请选择一个月份以查询使用量:</p></div>
	  	<div >
		   <select id="date" class="easyui-combobox" name="state" style="margin-left: 30px;width:200px;" data-options="required:true">
		        <option value="1">一月</option>
		        <option value="2">二月</option>
		        <option value="3">三月</option>
		        <option value="4">四月</option>
		        <option value="5">五月</option>
		        <option value="6">六月</option>
		        <option value="7">七月</option>
		        <option value="8">八月</option>
		        <option value="9">九月</option>
		        <option value="10">十月</option>
		        <option value="11">十一月</option>
		        <option value="12">十二月</option>
		   </select>
		   <select id="date" class="easyui-combobox" name="state" style="margin-left: 30px;width:200px;" data-options="required:true">
		        <option value="2010">2010</option>
		        <option value="2011">2011</option>
		        <option value="2012">2012</option>
		        <option value="2013">2013</option>
		        <option value="2014">2014</option>
		        <option value="2015">2015</option>
		        <option value="2016">2016</option>
		   </select>
		  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>	   
		</div>
		<div><p>活动实例: 3 运作中内存: 6GB 本月的虚拟处理器-时数: 423.97 本月的GB-时数: 25437.90</p></div>
		<div><font style="font-size: 16px;font-weight: bold;">使用量摘要</font></div>
	    <br><br>
	    <div>
	      <table id="tt"></table>
		</div>	   
	 </div>
	 </div>
</body>
</html>