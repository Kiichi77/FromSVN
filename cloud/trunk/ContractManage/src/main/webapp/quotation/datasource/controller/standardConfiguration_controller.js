//动态添加层，装在table的div层
function wantDivFun(paramName,which,tdata) {
	var paramsIds = "";
	var cid = "";
	if("1" == which) {//基本配置单元divID序列号
		paramsIds = "jdiv"+accordionDivId1;
		$('#menu1').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "jcol"+columnId1;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId1 = accordionDivId1+1;
		columnId1 = columnId1+1;
	} else if("2" == which) {//同系列通用单元divID序列号
		paramsIds = "tdiv"+accordionDivId2;
		$('#menu2').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "tcol"+columnId2;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId2 = accordionDivId2+1;
		columnId2 = columnId2+1;
	} else if("3" == which) {//全通用单元divID序列号
		paramsIds = "qdiv"+accordionDivId3;
		$('#menu3').accordion('add',{
			title:paramName,
			content:"<table id='"+paramsIds+"'></table>"
		});
		cid = "qcol"+columnId3;
		wantDataGrid(paramsIds,cid,tdata);
		accordionDivId3 = accordionDivId3+1;
		columnId3 = columnId3+1;
	}
}

function wantDataGrid(paramsId,cid,tdata) {
	$('#'+paramsId+'').datagrid({
		url:ctx+tdata,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		pagination:true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		showGroup:true,
		columns:optiona,
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#'+cid+'').length){
				createColumnMenu(paramsId,cid);
			}
			$('#'+cid+'').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},
		onClickRow:function(rowIndex,rowData){
//			$('#ttt').datagrid('endEdit',lastIndex);
			$('#'+paramsId+'').datagrid('unselectAll');
			$('#'+paramsId+'').datagrid('selectRow',rowIndex);
			checkRowToUpdateRow(rowData);
		}
	});
	//分页工具条
	 $('#'+paramsId+'').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     $(this).pagination('loaded');
		 }
	 });
}

//创建列菜单
function createColumnMenu(paramsId,cid){
	var tmenu = $('<div id="'+cid+'" style="width:100px;"></div>').appendTo('body');
	var fields = $('#'+paramsId+'').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#'+paramsId+'').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#'+paramsId+'').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}

//选择配置信息修改标配信息
function checkRowToUpdateRow(paramData) {
	var end = true;
	var numb = 0;
	var rows = $('#ttt').datagrid('getRows');
	$.each(rows, function(i, n) {
		if(paramData.id == n.dataRecordId) {	//该数据已经存在
			numb = i;
			end = false;
		}
	});
	if(end) {
		$('#ttt').datagrid('appendRow',{ 
			id:"",
			dataRecordId:""+paramData.id,
			typeId:""+paramData.typeId,
			productOrderNo:""+paramData.productOrderNo, 
			name:""+paramData.name, 
			productDesc:""+paramData.productDesc, 
			quantity:""+paramData.quantity, 
			unitPrice:""+paramData.unitPrice,
			totalprice:""+paramData.totalprice,
			yincangzk:""+paramData.discountRate*100,
			discountRate:""+paramData.discountRate*100,
			rebateprice:""+paramData.rebateprice,
			otherRates:""+paramData.otherRates*100,
			rebateafter:""+paramData.rebateafter,
			installServiceCharge:""+paramData.installServiceCharge,
			firstYear:""+paramData.firstYear,
			secondYear:""+paramData.secondYear,
			thirdYear:""+paramData.thirdYear,
			total:""+paramData.total
		});
		var lastIndex1 = $('#ttt').datagrid('getRows').length-1;
		$('#ttt').datagrid('selectRow', lastIndex1);
		$('#ttt').datagrid('endEdit',lastIndex);
		$('#ttt').datagrid('beginEdit', lastIndex1);
		lastIndex = lastIndex1;
	}
}

//保存模板配置信息,修改标配信息
function saveAndUpdateQuotation() {
	var iscanadd = true;
	//获取表格选择行
	var rows = $('#ttt').datagrid('getRows');
	var json = "[";
	//循环给提交删除参数赋值(音乐风格编码)
	$.each(rows, function (i, n) {
		var endcha = n.discountRate;
		var fristnum = n.yincangzk;
		if(endcha<fristnum) {
			$.messager.alert('提示', "折扣率不可超过标准值!", 'info');
			$('#ttt').datagrid('beginEdit', lastIndex);
			iscanadd = false;
		}
		var discountRate = n.discountRate*0.01;
		json += "{'id':'"+n.id+"','sourceDataId':'"+n.dataRecordId+"','number':'"+n.quantity+"','discount':'"+discountRate+"','firstYear':'"+n.firstYear+"','secondYear':'"+n.secondYear+"','thirdYear':'"+n.thirdYear+"','catalogPrice':'"+n.totalprice+"','discountedPrice':'"+n.rebateprice+"','discountAfterPrice':'"+n.rebateafter+"','totalPrice':'"+n.total+"'}";
		if(i!=rows.length-1){
			json += ',';
		}
	});
	if(!iscanadd) {
		return;
	}
	json += "]";
	$.post(ctx+ 'quotations/quotations/quotations!saveStandard.action', {
		id : json,
		sourceId:configsIds
	}, function(data) {
		if (null != data && "" != data && "ok" != data) {
			$.messager.alert('提示', "操作成功", 'info');
			$("#ttt").datagrid("reload");
		} else {
			$.messager.alert('提示', "系统异常，请稍后操作", 'info');
		}
	});
}

