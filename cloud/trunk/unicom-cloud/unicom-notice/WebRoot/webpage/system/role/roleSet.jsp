<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#functionid').tree({
			checkbox : true,
			url : 'roleController.do?setAuthority&roleId=${roleId}',
			onLoadSuccess : function(node) {
				expandAll();
			}
		});
	});
	function mysubmit() {
		var roleId = $("#rid").val();
		var s = GetNode();
		doSubmit("roleController.do?updateAuthority&rolefunctions=" + s + "&roleId=" + roleId);
	}
	function GetNode() {
		var node = $('#functionid').tree('getChecked');
		var cnodes = '';
		var pnodes = '';
		var prevNode = ''; //保存上一步所选父节点
		for ( var i = 0; i < node.length; i++) {
			if ($('#functionid').tree('isLeaf', node[i].target)) {
				cnodes += node[i].id + ',';
				var pnode = $('#functionid').tree('getParent', node[i].target); //获取当前节点的父节点
				if (prevNode != pnode.id) //保证当前父节点与上一次父节点不同
				{
					pnodes += pnode.id + ',';
					prevNode = pnode.id; //保存当前节点
				}
			}
		}
		cnodes = cnodes.substring(0, cnodes.length - 1);
		pnodes = pnodes.substring(0, pnodes.length - 1);
		return cnodes + "," + pnodes;
	};
	
	function expandAll() {
		var node = $('#functionid').tree('getSelected');
		if (node) {
			$('#functionid').tree('expandAll', node.target);
		} else {
			$('#functionid').tree('expandAll');
		}
	}
	//update-begin--Author:anchao  Date:20130324 for：角色编辑全选、取消菜单
	function selecrAll() {
		var node = $('#functionid').tree('getRoots');
		for ( var i = 0; i < node.length; i++) {
			var childrenNode =  $('#functionid').tree('getChildren',node[i].target);
			for ( var j = 0; j < childrenNode.length; j++) {
				$('#functionid').tree("check",childrenNode[j].target);
			}
	    }
	}
	function reset() {
		$('#functionid').tree('reload');
	}

	$('#selecrAllBtn').linkbutton({   
	}); 
	$('#resetBtn').linkbutton({   
	});   
		
	//update-end--Author:zhangdaihao  Date:20130205 for：角色编辑全选、取消菜单
</script>

 <input type="hidden" name="roleId" value="${roleId}" id="rid">
  <a id = "selecrAllBtn" onclick="selecrAll();">全选</a>
  <a id = "resetBtn"  onclick="reset();">重置</a>
 <ul id="functionid"></ul>


