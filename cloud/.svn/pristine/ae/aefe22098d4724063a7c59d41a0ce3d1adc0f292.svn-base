<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:dategrid name="departList" title="部门列表" actionUrl="departController.do?departgrid" treegrid="true" idField="departid" pagination="false" >
 <t:dgCol title="编号" field="id" treefield="id" hidden="false"></t:dgCol>
 <t:dgCol title="部门名称" field="departname" treefield="text" query="true"></t:dgCol>
 <t:dgCol title="职能描述" field="description" treefield="src"></t:dgCol>
 <t:dgCol title="操作" field="opt"></t:dgCol>
 <t:dgDelOpt url="departController.do?del&id={id}" title="删除"></t:dgDelOpt>
</t:dategrid>
<div id="departListtb" style="padding: 3px; height: 25px">
 <div style="float:left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('部门录入','departController.do?add','departList')">部门录入</a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('用户编辑','departController.do?update','departid')">用户编辑</a>
 </div>
 <div align="right">
    部门名称:<input type="text" name="departname" id="departname" style="width: 80px"/>
   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="departListsearch()">查询</a>
 </div>
</div>
</div>