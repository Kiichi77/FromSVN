<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("ctx", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../view/head.jsp"></jsp:include>
<script type="text/javascript"
	src="controller/checkDetails_controller.js"></script>
<script type="text/javascript" src="view/checkDetails_view.js"></script>
<script type="text/javascript">
<%-- var processId = '<%=request.getParameter("processId")%>' --%>
var qoutaionId = '<%=request.getParameter("qoutaionId")%>';
var projectId = '<%=request.getParameter("projectId")%>';
var status = '<%=request.getParameter("status")%>';
	/***************DIV*****************/
	//基本配置单元divID序列号
	var addtabIds = 0;
	$(function() {
		$('#tt').datagrid({
			url: ctx+'quotations/quotations/quotations!queryByQuotationId.action?quotationsId='+qoutaionId,//根据报价表ID查询该报价表下的所有机型配置
			width: '95%',
			height: 'auto',
			pagination:false,
			rownumbers:true,
			remoteSort: false,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			fitColumns: true,
			columns:[[  		   
// 	                    {field:'ck',checkbox:true,align:'center'},
						{field:'id',title:'ID',width:60,align:'center',hidden:true},
						{field:'dataSourceId',title:'所属数据源id',width:100,align:'center',hidden:true},
						{field:'dataSourceName',title:'所属数据源',width:100,align:'center'},
						{field:'title',title:'配置单名称',width:80,align:'center'},
						{field:'dataSourceType',title:'分类',width:80,align:'center'},
						{field:'quantity',title:'数量',width:80,align:'center'},
						{field:'catalogTotalPrice',title:'目录合价(元)',width:80,align:'center',
							formatter:function(value,rec,index){
								value = value.replace(/,/g,'');
								var num = new Number(value);
								return num.toFixed(0);
							}			
						},
						{field:'afterDiscountPrice',title:'折扣后价格(元)',width:80,align:'center',
							formatter:function(value,rec,index){
								value = value.replace(/,/g,'');
								var num = new Number(value);
								return num.toFixed(0);
							}			
						},
						{field:'afterDiscountNowPrice',title:'折扣后现场价(元)',width:80,align:'center',
							formatter:function(value,rec,index){
								value = value.replace(/,/g,"");
								var num = new Number(value);
								return num.toFixed(0);
							}			
						},
						{field:'totalPrice',title:'合计(元)',width:80,align:'center',
							formatter:function(value,rec,index){
								value = value.replace(/,/g,"");
								var num = new Number(value);
								return num.toFixed(0);
							}			
						},
						{field:'createtime',title:'创建时间',width:80,align:'center'},	
						{field:'creatorName',title:'创建者',width:80,align:'center'},
						{field:'opt',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								edit='<a href="configDetails.jsp?configId='+id+'&qoutaionId='+qoutaionId+'&status='+status+'">查看详情</a>';
								return edit;
							}
						}
						]],
						toolbar:[ {
							id:'btnback',
							text:'返回',
							iconCls:'icon-back',
							handler:function(){
								window.location = "checkRapaport_manage.jsp";
							}
						},{
							id:'btnadd',
							text:'审核',					
							iconCls:'icon-save',
							handler:function(){
								showWindow();
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
		//审核按钮控制
		checkButton();
	});

</script>
<body class="easyui-layout">
	<!-- 报价表明细tab -->
	<div data-options="region:'center'" title="报价表审核" style="overflow:hidden;">
			<table id="tt"></table>
	</div>

	<!-- 弹出层 -->
	<div id="addVmDiv" class="easyui-window" closed="true" maximizable="false" minimizable="false" collapsible="false" title="报价表审核" style="padding: 10px;width: 450px;height:300px;" iconCls="icon-add">
		<form id="addVmform" method="post" novalidate>
			<%-- <input type="hidden" id="processId" name="checkedVo.process_id" value="<%=request.getParameter("processId")%>"> --%>
			<input type="hidden" id="projectId" name="checkedVo.projectId">
			<div id="thevmleft" style="float: left;">
				<div>
					<font style="font-size: 13px;">审核结果：</font>&nbsp;&nbsp;
					 <input type="radio" id="pass" checked="checked" name="checkedVo.result" value="0" />同意&nbsp;&nbsp;<!-- onclick="changeSelect();" -->
					<input type="radio" id="unpass" name="checkedVo.result" value="1" />不同意 <!-- onclick="changeSelect();" -->
				</div>
				<br />
				<div>
					<font style="font-size: 13px;">批示：</font>&nbsp;&nbsp;<br/>
					<textarea id="describe" rows="4" cols="40" name="checkedVo.describe"></textarea>
				</div>
				<br />
				<br />
				<div align="right" style="width: 400px;">
					<a href="#" onclick="chekcForm();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确 定</a> 
					<a href="#" onclick="hideDiv();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>