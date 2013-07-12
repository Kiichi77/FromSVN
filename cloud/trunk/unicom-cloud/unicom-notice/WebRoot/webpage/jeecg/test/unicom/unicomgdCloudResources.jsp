<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>云平台资源管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="unicomgdCloudResourcesController.do?save">
			<input id="id" name="id" type="hidden" value="${unicomgdCloudResourcesPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							映射私网IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="privateIpAddress" name="privateIpAddress" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.privateIpAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							新公网IP:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="publicIpAddress" name="publicIpAddress" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.publicIpAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							映射私网端口号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="privatePort" name="privatePort" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.privatePort}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							映射公网IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="publicAddress" name="publicAddress" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.publicAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							映射公网端口号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="publicPort" name="publicPort" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.publicPort}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否能上网:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="internet" name="internet" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.internet}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属网元:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="networkElements" name="networkElements" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.networkElements}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							使用情况:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="content" name="content" ignore="ignore"
							   value="${unicomgdCloudResourcesPage.content}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							使用时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" ignore="ignore"
							     value="<fmt:formatDate value='${unicomgdCloudResourcesPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>