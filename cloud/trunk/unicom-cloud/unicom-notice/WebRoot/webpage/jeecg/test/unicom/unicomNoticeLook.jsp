<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:dategrid name="unicomNoticeList" title="发布公告" actionUrl="unicomNoticeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="公告标题" field="title"  width="10"></t:dgCol>
   <t:dgCol title="公告内容" field="content"  ></t:dgCol>
   <t:dgCol title="下载地址" field="download"  width="30"  hidden="false"></t:dgCol> 
   <t:dgCol title="发布时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss" width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="10"></t:dgCol>
   <t:dgFunOpt funname="aa(download)" title="下载附件"></t:dgFunOpt>
   <t:dgToolBar title="查看详情" icon="icon-tip" url="unicomNoticeController.do?lookById" funname="update"></t:dgToolBar>
  </t:dategrid>
  </div>
 </div>
 
 <script type="text/javascript">
function aa(download){
	var url = '${basePath}'+'upload/'+download;
	window.open(url, "_blank");
}
</script>