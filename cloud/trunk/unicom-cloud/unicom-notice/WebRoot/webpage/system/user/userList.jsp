<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:dategrid name="userList" title="用户管理" actionUrl="userController.do?datagrid" fit="true" fitColumns="true" idField="id">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" sortable="false" field="userName" query="true" width="20"></t:dgCol>
	<t:dgCol title="部门" field="TSDepart_departname"></t:dgCol>
	<t:dgCol title="真实姓名" field="realName" query="true"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgDelOpt title="删除" url="userController.do?del&id={id}&userName={userName}" />
</t:dategrid>
<div id="userListtb" style="padding: 3px; height: 25px">
 <div style="float:left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('用户录入','userController.do?addorupdate','userList')">用户录入</a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('用户编辑','userController.do?addorupdate','id')">用户编辑</a>
 </div>
 <div align="right">
    用户名:<input type="text" name="userName" id="userName" style="width: 80px"/>
    真实姓名:<input type="text" name="realName" id="realName" style="width: 80px"/>
   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="userListsearch()">查询</a>
 </div>
</div>