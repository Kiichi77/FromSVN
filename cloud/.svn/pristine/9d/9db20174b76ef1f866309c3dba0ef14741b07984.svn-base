<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="jeecgDemoList" title="开发DEMO列表" actionUrl="jeecgDemoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用户名" field="userName" query="true"></t:dgCol>
   <t:dgCol title="电话号码" sortable="false" field="mobilePhone" width="20" query="true"></t:dgCol>
   <t:dgCol title="办公电话" field="officePhone"></t:dgCol>
   <t:dgCol title="邮箱" field="email"></t:dgCol>
   <t:dgCol title="年龄" sortable="true" field="age"></t:dgCol>
   <t:dgCol title="工资"  field="salary"></t:dgCol>
   <t:dgCol title="性别" sortable="true" field="sex" replace="女_1,男_0"></t:dgCol>
   <t:dgCol title="生日" field="birthday" formatter="yyyy/MM/dd"></t:dgCol>
   <t:dgCol title="创建日期" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="szqm(id)" title="审核" />
   <t:dgDelOpt title="删除" url="jeecgDemoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="jeecgDemoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="jeecgDemoController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:dategrid>
  </div>
  </div>
 <script type="text/javascript">
	function szqm(id) {
		createwindow('审核', 'jeecgDemoController.do?doCheck&id=' + id);
	}
</script>