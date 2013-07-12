<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发布公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="unicomNoticeController.do?save" callback="submitproject" >
			<input id="id" name="id" type="hidden" value="${unicomNoticePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							公告标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"
							   value="${unicomNoticePage.title}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公告内容:
						</label>
					</td>
					<td class="value">
					
					<textarea rows="5"  class="inputxt" id="content" name="content" ignore="ignore"
							  style="width: 400px" >${unicomNoticePage.content}</textarea>
					
					<!-- 
						<input class="inputxt" id="content" name="content" ignore="ignore"
							   value="${unicomNoticePage.content}"  > -->
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<!-- 
				<tr>
					<td align="right">
						<label class="Validform_label">
							下载地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="download" name="download" ignore="ignore"
							   value="${unicomNoticePage.download}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				-->
				    <tr>
     <td align="right">
       <label class="Validform_label">上传文件:</label>
     </td>
     <td class="value">
      <t:upload  name="download" dialog="false" queueID="instructionfile" view="true" auto="true" uploader="unicomNoticeController.do?saveFiles" extend="*" id="download" formData="documentTitle" ></t:upload>
     </td>
    </tr><!-- 
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" ignore="ignore"
							     value="<fmt:formatDate value='${unicomNoticePage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				 -->
			</table>
		</t:formvalid>
 </body>