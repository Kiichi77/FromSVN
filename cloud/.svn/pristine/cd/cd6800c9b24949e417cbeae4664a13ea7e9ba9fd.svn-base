<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
 </script>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jeecgOrderMainController.do?save">
			<input id="id" name="id" type="hidden" value="${jeecgOrderMainPage.id }">
			<table  cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							订单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goOrderCode" name="goOrderCode" ignore="ignore"
							   value="${jeecgOrderMainPage.goOrderCode}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							订单类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goderType" name="goderType" 
							   value="${jeecgOrderMainPage.goderType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							顾客类型 
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="usertype" name="usertype" 
							   value="${jeecgOrderMainPage.usertype}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goContactName" name="goContactName" 
							   value="${jeecgOrderMainPage.goContactName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goTelphone" name="goTelphone" 
							   value="${jeecgOrderMainPage.goTelphone}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							订单人数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goOrderCount" name="goOrderCount" 
							   value="${jeecgOrderMainPage.goOrderCount}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总价(不含返款):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goAllPrice" name="goAllPrice" 
							   value="${jeecgOrderMainPage.goAllPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							返款:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="goReturnPrice" name="goReturnPrice" 
							   value="${jeecgOrderMainPage.goReturnPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" colspan="3">
						<input class="inputxt" id="goContent" name="goContent" 
							   value="${jeecgOrderMainPage.goContent}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="jeecgOrderMainController.do?jeecgOrderProductList&goOrderCode=${jeecgOrderMainPage.goOrderCode}" icon="icon-search" title="产品明细" id="Product"></t:tab>
				 <t:tab href="jeecgOrderMainController.do?jeecgOrderCustomList&goOrderCode=${jeecgOrderMainPage.goOrderCode}" icon="icon-search" title="客户明细" id="Custom"></t:tab>
				</t:tabs>
			</div>
		</t:formvalid>
		<!-- 添加 产品明细 模版 -->
		<table style="display:none">
		<tbody id="add_jeecgOrderProduct_table_template">
			<tr>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
			 <td align="left"><input nullmsg="请输入订单产品明细的产品名称！" datatype="s6-10"
							errormsg="订单产品明细的产品名称应该为6到10位" name="jeecgOrderProductList[#index#].gopProductName" maxlength="100" type="text" style="width:220px;"><span class="Validform_checktip"></span></td>
			 <td align="left"><input name="jeecgOrderProductList[#index#].gopCount" maxlength="" type="text" style="width:120px;"><span class="Validform_checktip"></span></td>
			 <td align="left"><input name="jeecgOrderProductList[#index#].gopProductType" maxlength="3" type="text" style="width:120px;"><span class="Validform_checktip"></span></td>
			 <td align="left"><input name="jeecgOrderProductList[#index#].gopOnePrice" maxlength="" type="text" style="width:120px;"><span class="Validform_checktip"></span></td>
			 <td align="left"><input name="jeecgOrderProductList[#index#].gopSumPrice" maxlength="" type="text" style="width:120px;"><span class="Validform_checktip"></span></td>
			 <td align="left"><input name="jeecgOrderProductList[#index#].gopContent" maxlength="200" type="text" style="width:120px;"><span class="Validform_checktip"></span></td>
			</tr>
		 </tbody>
		 <tbody id="add_jeecgOrderCustom_table_template">
			<tr>
				<td align="center"><input  style="width:20px;" type="checkbox" name="ck"/></td>
				<td align="left"><input name="jeecgOrderCustomList[#index#].gocCusName" maxlength="50" type="text"  style="width:220px;" ><span class="Validform_checktip"></span></td>
				<td align="left"><input name="jeecgOrderCustomList[#index#].gocSex" maxlength="2" type="text"  style="width:120px;"/><span class="Validform_checktip"></span></td>
	            <td align="left"><input name="jeecgOrderCustomList[#index#].gocIdcard" maxlength="32" type="text"  style="width:120px;" ><span class="Validform_checktip"></span></td>
				<td align="left"><input name="jeecgOrderCustomList[#index#].gocPassportCode" maxlength="32" type="text"  style="width:120px;" ><span class="Validform_checktip"></span></td>
				<td align="left"><input name="jeecgOrderCustomList[#index#].gocBussContent" maxlength="100" type="text"  style="width:120px;" ><span class="Validform_checktip"></span></td>
				<td align="left"><input name="jeecgOrderCustomList[#index#].gocContent" maxlength="200" type="text"  style="width:120px;" ><span class="Validform_checktip"></span></td>
   			</tr>
		 </tbody>
		</table>
		
 </body>