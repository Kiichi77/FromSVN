<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jqueryui"></t:base>
<SCRIPT type="text/javascript">
  function aa(obj) {
		if (obj.nodate == 'yes') {
			$('#msg').show();
		}
		$('#real').html(obj.realName);
	}
	function close(tag) {
		if (tag == false) {
			$('#msg').hide();
			$('#real').html('');
		}

	}
  </SCRIPT>
<t:formvalid formid="formobj" dialog="false" layout="div" action="demoController.do?saveDemo">
 <fieldset class="step">
  <div class="form">
   <label class="form">
    自动补全：
   </label>
   <t:autocomplete selectfun="aa" closefun="close" valueField="id" searchField="userName" labelField="userName,realName" name="user" entityName="TSUser" datatype="*" nullmsg="请输入关键字" errormsg="数据不存在,请重新输入"></t:autocomplete>
   <span id="msg" style="display: none">真实姓名:<span id="real"></span>
   </span>
   <span class="Validform_checktip">自动提示数据说明:用户名,真实姓名</span>
  </div>
 </fieldset>
</t:formvalid>
