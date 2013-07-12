<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#demotree').tree({
			animate : true,
			url : 'demoController.do?pDemoList',
			onClick : function(node) {
				if ($('#demotree').tree('isLeaf', node.target)) {
					$('#demopanle').panel("refresh", "demoController.do?demoTurn&id=" + node.id + "&page=" + node.attributes.href);
					//$('#demo').attr("src", "demoController.do?demoTurn&id=" + node.id + "&page=" + node.attributes.href);
				} else {
					$('#demotree').tree('expand', node.target);
				}
			}
		});
	});
</script>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding: 3px;" class="easyui-panel" id="demopanle">
  <%--<iframe name="demo" id="demo" scrolling="no" frameborder="0" style="width: 100%; height: 99%;"></iframe>
 --%>
 </div>
 <div region="west" style="width: 150px;" title="DEMO分类" split="true">
  <div class="easyui-panel" style="padding:1px;" fit="true" border="false">
   <ul id="demotree">
   </ul>
  </div>
 </div>
</div>
