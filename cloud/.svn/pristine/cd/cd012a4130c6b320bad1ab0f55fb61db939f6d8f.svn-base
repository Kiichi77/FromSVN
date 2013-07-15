<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	/*
	 *	excel导出
	 */
	function exportXls() {
		 window.location.href="jpPersonController.do?exportXls";
	}
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="jpPersonList" title="Excel导出" actionUrl="jpPersonController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="年龄" field="age" ></t:dgCol>
   <t:dgCol title="生日" field="birthday" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="出生日期" field="createdt" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="用户名" field="name" ></t:dgCol>
   <t:dgCol title="工资" field="salary" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="jpPersonController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="jpPersonController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="jpPersonController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="导出excel"  onclick="exportXls();" ></t:dgToolBar>
   <%--
   <t:dgOpenOpt url="jpPersonController.do?goImplXls" title="导入excel" height="100%" width="100%"></t:dgOpenOpt>
    <t:dgFunOpt funname="goImplXls()" title="导入excel" />
  --%>
  </t:dategrid>
  </div>
 </div>
 