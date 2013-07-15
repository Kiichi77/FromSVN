<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>查看公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="unicomNoticeController.do?save" callback="submitproject" >
			<input id="id" name="id" type="hidden" value="${unicomNoticePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
					<td align="right" style="width: 60px;">
						<label class="Validform_label">
							公告标题:
						</label>
					</td>
					<td class="value">
					<p>${unicomNoticePage.title}</p>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 60px;">
						<label class="Validform_label">
							公告内容:
						</label>
					</td>
					<td class="value">
					<p >${unicomNoticePage.content}</p>
					
					<!-- 
						<input class="inputxt" id="content" name="content" ignore="ignore"
							   value="${unicomNoticePage.content}"  > -->
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				 
				<tr>
					<td align="right">
						<label class="Validform_label">
							附件地址:
						</label>
					</td>
					<td class="value">
					${unicomNoticePage.download} <a href="${basePath}/upload/${unicomNoticePage.download}"  target="_blank">(点击下载)</a>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 60px;">
						<label class="Validform_label">
							发布时间:
						</label>
					</td>
					<td class="value">
					<p>${unicomNoticePage.createTime}</p>
					</td>
				</tr>
				 
			</table>
		</t:formvalid>
		
 </body>