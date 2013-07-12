<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>安全组</title>
<script type="text/javascript" src="controller/woyun_safetyGroup_controller.js"></script>
<script type="text/javascript" src="view/woyun_safetyGroup_view.js"></script>
</head>
<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	
	<div id="themains" data-options="region:'center'" title="安全组管理"  style="overflow:hidden;padding:10px;margin-top: 40px;">
	<div id="addVmDiv" style="background:#fafafa;padding:20px;width:460px;height:220px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addVmform" method="post" novalidate style="width: 450px;height:220px;">
	    	<div id="thevmleft" style="width: 210px;height:200px;float: left;">
		        <div>
		            <label for="vmNames">Volume Name:</label><br>
		            <input id="todovmNames" class="easyui-validatebox" type="text" name="model.name" style="width: 200px;" data-options="required:true"></input>
		        </div><br>
		         <div>
		            <label for="userInfo">叙 述:</label><br>
		            <textarea name="model.description" style="height:60px;width: 200px;"></textarea>
		        </div>
	        </div>
	        <div id="thevmright" style="width: 210px;height:200px;float: right;">
	        	<h4>叙 述</h4>
				<h5>您可以在这里建立一个新的安全性群组</h5>
	        </div>
		    <div align="right" style="width: 450px;">
		    	 <a href="javascript:void(0)" onclick="createSecurityGroup()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	 <a href="#" onclick="javascript:$('#addVmDiv').css('display','none');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		    </div>
	    </form>
	</div>
	<div id="addpoliceWin" style="background:#fafafa;padding:20px;width:570px;height:310px;display: none;z-index: 9999;border: solid gray 1px;">
	    <form id="addpoliceWinform" method="post" novalidate style="width: 560px;height:310px;">
	   		 <div id="addpoliceleft" style="width: 510px;height:100px;">
		        <div>
					<table id="policeTable"></table>					
		    	</div><br>
		    	<div id="addpolicetop" style="width: 560px;height:280px;float: left;">
		        <div style="width: 50px;float: left;margin-right: 5px;margin-bottom: 20px;">
		            <label for="ippolice">IP协定:</label><br>
		            <select id="protocol" name="securityGroupRuleForCreate.ipProtocol" style="width:50px;margin-top:5px;" data-options="required:true">
				     	<option value="TCP" selected="selected">TCP</option>
				     	<option value="UDP">UDP</option>
				     	<option value="ICMP">ICMP</option>
				    </select>
			    </div>
			    <div style="width: 150px;float: left;margin-right: 5px;margin-bottom: 20px;">
		            <label for="congdk">从端口:</label><br>
		            <input  id="from_port" class="easyui-numberspinner" name="securityGroupRuleForCreate.fromPort" type="text" style="width: 150px;margin-top:5px;" data-options="onChange: function(value){  
                    $('#from_port').text(value);  
                } "></input>
                </div>
                <div style="width: 150px;float: left;margin-right: 5px;margin-bottom: 20px;">
		            <label for="daodk">到端口:</label><br>
		            <input  id="to_port" class="easyui-numberspinner" name="securityGroupRuleForCreate.toPort" type="text" style="width: 150px;margin-top:5px;" data-options="onChange: function(value){  
                    $('#to_port').text(value);  
                } "></input>
                </div>
                <div style="width: 70px;float: left;margin-right: 5px;margin-bottom: 20px;">
		            <label for="ippolice">安全性群组:</label><br>
			        <select id="securipty_group" name="param" onchange="isHiddenCidr(this)" style="width:70px;margin-top:5px;" data-options="required:true">
					</select>
				</div>
				<div id="cidr" style="width: 60px;float: left;margin-right: 5px;margin-bottom: 20px;">
					<label for="cidrs">CIDR:</label><br>
		            <input id="isCidrs" class="easyui-validatebox" type="text" name="securityGroupRuleForCreate.cidr" style="width: 60px;margin-top:5px;" data-options="required:true"></input>
		        </div><br>
		    	<div align="right" style="width: 450px;">
		    	 <a href="javascript:void(0)" onclick="createRuleSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提 交</a>
		    	 <a href="#" onclick="javascript:$('#addpoliceWin').css('display','none');" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
		   		</div>
		    </div>
		    </div><br>
	    </form>
	</div>
	<table id="safetyGroupInfo"></table><br>
	</div>
</body>
</body>
</html>