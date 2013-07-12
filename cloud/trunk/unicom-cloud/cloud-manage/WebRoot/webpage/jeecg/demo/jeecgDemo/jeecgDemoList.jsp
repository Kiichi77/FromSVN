<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <!--begin ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式-->
  <t:datagrid name="jeecgDemoList" title="DEMO示例列表" autoLoadData="true" actionUrl="jeecgDemoController.do?datagrid" sortName="userName"  fitColumns="true" idField="id" fit="true" queryMode="group">
   <!--end ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式-->
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <!--begin-  author:邢双阳 - date:2013-05-18 - for:[106]实现冰冻列  -->
   <t:dgCol title="用户名" field="userName" query="true" frozenColumn="true"  ></t:dgCol>
   <!--end-  author:邢双阳 -- date:2013-05-18 - -for:[106]实现冰冻列 -->
   <t:dgCol title="电话号码" sortable="false" field="mobilePhone" query="true"></t:dgCol>
   <t:dgCol title="办公电话" field="officePhone" query="true"></t:dgCol>
   <t:dgCol title="创建日期" field="createTime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="邮箱" field="email" query="true"></t:dgCol>
   <t:dgCol title="年龄" sortable="true" field="age" query="true"></t:dgCol>
   <t:dgCol title="工资"  field="salary" query="true"></t:dgCol>
   <t:dgCol title="生日" field="birthday" formatter="yyyy/MM/dd" query="true"></t:dgCol>
   <t:dgCol title="性别" sortable="true" field="sex" dictionary="sex" query="true"  replace="男_0,女_1"></t:dgCol>
   <t:dgCol title="状态"  field="status"  query="true"  replace="未处理_0,已处理_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="80"></t:dgCol>
   <%--   update-start--Author:anchao  Date:20130415 for按钮权限--%>
   <t:dgFunOpt exp="status#eq#0" operationCode="szqm" funname="szqm(id)" title="审核" />
   <t:dgDelOpt operationCode="del"   title="删除" url="jeecgDemoController.do?del&id={id}" />
   <t:dgToolBar operationCode="add" title="录入" icon="icon-add" url="jeecgDemoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="编辑" icon="icon-edit" url="jeecgDemoController.do?addorupdate" funname="update"></t:dgToolBar>
   <%--   update-start--Author:sun  Date:20130503 for 增加页面详细查看功能--%>
   <t:dgToolBar operationCode="detail" title="查看" icon="icon-search" url="jeecgDemoController.do?addorupdate" funname="detail"></t:dgToolBar>
   <%--   update-end--Author:sun  Date:20130503 for 增加页面详细查看功能--%>
   <%--   update-end--Author:anchao  Date:20130415 for按钮权限--%>
  </t:datagrid>
  </div>
  </div>
 <script type="text/javascript">
	function szqm(id) {
		createwindow('审核', 'jeecgDemoController.do?doCheck&id=' + id);
	}
	$(document).ready(function(){
		$("input[name='createTime_begin']").attr("class","easyui-datebox");
		$("input[name='createTime_end']").attr("class","easyui-datebox");
	});
</script>