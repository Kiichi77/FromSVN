$(function(){
	$('#tt').datagrid({
		url: ctx+'/contractTemplates/contractTemplates/contractTemplatesDoc!list.action?contractTemplatesDocVo.contractTemplatesId='+contractTemplatesId,
		width: '95%',
		height: 'auto',
		rownumbers:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		   
                    {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'templateDocTypeId',title:'templateDocTypeId',width:60,align:'center',hidden:true},
					{field:'typeName',title:'分类',width:60,align:'center'},
					{field:'docname',title:'名称',width:60,align:'center'},
					{field:'path',title:'文件',width:60,align:'center'},
					{field:'createtime',title:'创建时间',width:60,align:'center'},
					{field:'bVariable',title:'设置变量',width:100,align:'center',
						formatter:function(value,rec,index){
							var bVariable=rec.bVariable;
							var edit;
							if("2"==bVariable){
								return '否';
							}else{
								return '是';
							}
						}
					}
					]],
					toolbar:[{
						id:'btnsave',
						text:'返回',					
						iconCls:'icon-back',
						handler:function(){
							window.location.href = ctx+"/contract/contract.jsp";
						}
					},{
						id:'btnadd',
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
							$('#btnsave').linkbutton('enable');
							//保存合同模板文件和合同模板之间的关系
							saveTempleAndFile();
						}
					}],
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},
		onLoadSuccess: function(data){
			//获取表格选择行
			var rows = $('#tt').datagrid('getRows');
			//获得模板与合同模板文件之间的关系，并勾选相应文件
			// 循环给提交删除参数赋值(合同文件ID)
			$.each(rows, function(i, n) {
				var id=n.templateDocTypeId;
				if(null != id && 0 != id && "0" != id){
					$('#tt').datagrid("selectRow",i);
				}
			});
		}
	});
	//隐藏、显示
	$("#dd").toggle();
	$("#add").toggle();
	$(".datagrid-toolbar").append($("#datagridsearch"));
	if(1 != iscontext && 3 != iscontext && 5 != iscontext && 7 != iscontext) {
		$('#btnadd').linkbutton('disable');
	}
	//加载添加弹出框中的类型
	getDocType();
});	

//获取文档类型
function getDocType(){
	//设置为同步
	$.ajaxSettings.async = false;
	$.post(
		ctx+'/contractTemplates/contractTemplates/contractDocType!findAll.action',
		function(data){
			var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				var add = $("#typeDIV");
				$.each(data.rows,function(i,sg){ //,'name':'templateType'}
					var input = $("<input>"); //onclick='setChecked(this);' type='checkbox' name='typeName' value='"+sg.id+"'
					input.attr('type','checkbox');
					input.attr('name','typeName');
					input.val(sg.id);
					var font = $("<font>");
					font.text(sg.name);
					add.append(input);
					add.append(font);
					if((i+1)%4==0){
						add.append($("<br/>"));
					}
				});
			}
		}
	);
}

//创建列菜单
function createColumnMenu(){
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $('#tt').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				$('#tt').datagrid('hideColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#tt').datagrid('showColumn', item.text);
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}


function handler(){
	$('#tt').datagrid('acceptChanges');
}



////弹出上传log 上传新文件
//function showlog()
//{
//$('#add').dialog({
//	buttons:[{
//		text:'确定',
//		iconCls:'icon-ok',
//		handler:function(){
//			alert('ok');
//		}
//	},{
//		text:'取消',
//		handler:function(){
//			$('#add').dialog('close');
//		}
//	}]
//});
//};

//function open2(){
//	showlog();
//	$('#add').dialog('open');
//}


//弹出log 设置变量
function showlog1()
{
$('#dd').dialog({
});
};

function open1(templatesDocId){
	showlog1();
	$('#dd').dialog('open');
	variable(templatesDocId);
}


//设置变量	
var lastIndex1;
function variable(templatesDocId){
$('#ta').datagrid({
	url: ctx+'/variable/variable/templatesDocVariable!listContractTemplatesDocVariable.action?templatesDocId='+templatesDocId,
	width: '95%',
	height: 'auto',
//	pagination:true,
//	rownumbers:true,
	remoteSort: false,
	nowrap: true,
	autoRowHeight: false,
	striped: true,
	collapsible:true,
	fitColumns: true,
	columns:[[  	
	          	{field:'ck',checkbox:true,width:60,align:'center'},
				{field:'id',title:'ID',width:60,align:'center',hidden:true},
				{field:'variableName',title:'变量名',width:60,align:'center',editor:'text'},
				{field:'description',title:'描述',width:60,align:'center',editor:'text'},
				{field:'templatesDocId',title:'模版文件Id',width:60,align:'center',hidden:true}
				]],
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						$('#ta').datagrid('endEdit', lastIndex1);
						 $('#ta').datagrid('appendRow',{
							id:'',						
							variableName:'',
							description:'',
							templatesDocId:templatesDocId
						 });
						lastIndex1 = $('#ta').datagrid('getRows').length-1;
						$('#ta').datagrid('selectRow', lastIndex1);
						$('#ta').datagrid('beginEdit', lastIndex1);
					}
					},'-',{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
								$('#btnsave').linkbutton('enable');
								deleteSomeVariable();
								var row = $('#ta').datagrid('getSelected');
									if (row){
										var index = $('#ta').datagrid('getRowIndex', row);
										$('#ta').datagrid('deleteRow', index);
									}
						}
				},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$('#ta').datagrid('acceptChanges');
					saveAndUpdateVariable();
					
					
				}
			},'-',{
				text:'取消',
				iconCls:'icon-undo',
				handler:function(){
					$('#ta').datagrid('rejectChanges');
					$('#dd').dialog('close');
				}
			}],
	onHeaderContextMenu: function(e, field){
		e.preventDefault();
		if (!$('#tmenu').length){
			createColumnMenu();
		}
		$('#tmenu').menu('show', {
			left:e.pageX,
			top:e.pageY
		});
	},
	onClickRow:function(rowIndex){
		if (lastIndex1 != rowIndex){
			$('#ta').datagrid('endEdit', lastIndex1);
			$('#ta').datagrid('beginEdit', rowIndex);
		}
		lastIndex1 = rowIndex;
	}

});
}









