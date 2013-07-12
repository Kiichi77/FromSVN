<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="unicomgdCloudResourcesList" title="云平台资源管理" actionUrl="unicomgdCloudResourcesController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="映射私网IP地址" field="privateIpAddress" ></t:dgCol>
   <t:dgCol title="新公网IP" field="publicIpAddress" ></t:dgCol>
   <t:dgCol title="映射私网端口号" field="privatePort" ></t:dgCol>
   <t:dgCol title="映射公网IP地址" field="publicAddress" ></t:dgCol>
   <t:dgCol title="映射公网端口号" field="publicPort" ></t:dgCol>
   <t:dgCol title="是否能上网" field="internet" ></t:dgCol>
   <t:dgCol title="所属网元" field="networkElements" ></t:dgCol>
   <t:dgCol title="使用情况" field="content" ></t:dgCol>
   <t:dgCol title="使用时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="unicomgdCloudResourcesController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="unicomgdCloudResourcesController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="unicomgdCloudResourcesController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:dategrid>
  </div>
 </div>