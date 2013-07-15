//价格总表数据列模型
var threeCluomn = [[  		
	{field:'id',title:'ID',width:60,align:'center',hidden:true},
	{field:'configModelId',title:'configModelId',width:60,align:'center',hidden:true},
	{field:'projectName',title:'项目名称',width:100,align:'center',editor:'text'},
	{field:'quotationName',title:'型号',width:100,align:'center',editor:'text'},
	{field:'unit',title:'单位',width:100,align:'center',
		formatter:function(value,rec,index){
		return	"台";
	}},
	{field:'quantity',title:'数量',width:100,align:'center',editor:'text'},
	{field:'totalPrice',title:'总价',width:100,align:'center',
		formatter:function(value,rec,index){
			var num = new Number(value);
			return num.toFixed(0);
		}
	}
]];

//机型配置总览
$(function(){
	$('#tt').datagrid({
		url: ctx+'/configModel/configModel/configModel!myConfigModels.action',
		width: '95%',
		height: 'auto',
		pagination:true,
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
					{field:'dataSourceId',title:'所属数据源id',width:100,align:'center',hidden:true},
					{field:'dataSourceName',title:'所属数据源',width:100,align:'center',hidden:true},
					{field:'name',title:'配置单名称',width:80,align:'center',formatter:function(value,rec,index){
						var id=rec.id;
						var edit;
						var sourceType = rec.dataSourceType;
						edit='<a href="#" onclick="updateModelsInfos(\''+id+'\',\''+rec.dataSourceId+'\',\''+rec.dataSourceName+'\')">'+rec.name+'</a>';
						return edit;
					}},
					{field:'dataSourceType',title:'分类',width:80,align:'center'},
					{field:'catalogTotalprice',title:'目录合价(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'afterDiscountPrice',title:'折扣后价格(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'afterDiscountNowPrice',title:'折扣后现场价(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'totalPrice',title:'合计(元)',width:80,align:'center',
						formatter:function(value,rec,index){
							var num = new Number(value);
							return num.toFixed(0);
						}
					},
					{field:'createtime',title:'创建时间',width:80,align:'center',hidden:true},	
					{field:'creatorName',title:'创建者',width:80,align:'center',hidden:true},	
					{field:'operate',title:'操作',width:80,align:'center',
							formatter:function(value,rec,index){
								var id=rec.id;
								var edit;
								var sourceType = rec.dataSourceType;
								edit='<a href="#" onclick="updateModels(\''+id+'\',\''+sourceType+'\')">修改</a>';
								return edit;
							}
						}
					]],
					//<a href="#" onclick="deleteConfigModels(\''+id+'\')">删除</a>&nbsp;|&nbsp;
					toolbar:[ {
						id:'btnadd',
						text:'新增配置',
						iconCls:'icon-add',
						handler:function(){
							$('#sourceType').attr("disabled",false);
							$('#dateNames').attr("disabled",false);
							showWindow();
						}
					}/*,{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							deleteSomeConfig();
						}
					}*/,{
						id:'btnsave',
						text:'制作报价表',					
						iconCls:'icon-save',
						handler:function(){
							$('#t11').datagrid('loadData', { total: 0, rows: []});
							// 获取表格选择行
							var rows = $('#tt').datagrid('getSelections');
							// 判断是否选择行
							if (!rows || rows.length == 0) {
								$.messager.alert('提示', '请选择一个或多个机型配置制作报价表。', 'info');
								return;
							}
							//var pronam = $('#projectName option:selected').text();
							$.each(rows, function(i, n) {
								$('#t11').datagrid('appendRow',{ 
									configModelId:""+n.id,
									projectName:"",
									quotationName:""+n.name, 
									unit:"台", 
									quantity:"1",
									totalPrice:""+n.totalPrice
								});
							});
							showWindow1();
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
		}
	});
	//分页工具条
	 $('#tt').datagrid('getPager').pagination({
		    displayMsg:'当前显示从【{from}】到【{to}】共【{total}】记录',
		    onBeforeRefresh:function(pageNumber, pageSize){
		     $(this).pagination('loading');
		     	alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		     $(this).pagination('loaded');
		    }
	 });
	 
	 var seMoney = 0;	//暂时记录选中行数量的变量
	//报价表中的价格总表
		$('#t11').datagrid({
			width: '95%',
			height: 'auto',
			rownumbers:true,
			remoteSort: false,
			nowrap: false,
			pagination:false,
			autoRowHeight: false,
			singleSelect:true,
			striped: true,
			fitColumns: true,
			showGroup:true,
			columns:threeCluomn,
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onDblClickRow: function(rowIndex, rowData) {
				seMoney = rowData.quantity;
				$('#t11').datagrid('endEdit',lastIndex1);
				$('#t11').datagrid('beginEdit', rowIndex);
				lastIndex1 = rowIndex;
			},
			onAfterEdit: function(rowIndex, rowData, changes) {
				//总价 c=a*b
				var totalprices = (rowData.quantity*1.0)*((rowData.totalPrice*1.0)/(seMoney*1.0));
				$('#t11').datagrid('updateRow',{index:rowIndex,row:{totalPrice:totalprices}});
			},
			onClickRow:function(rowIndex, rowData){
				$('#t11').datagrid('endEdit', lastIndex1);
				lastIndex1 = rowIndex;
			}
		});	
	 
	//下拉选择框数据加载   数据源分类
	var sel1 = $("#sourceType");  
	sel1.empty();//清除select中的 option
	$.post(ctx+'/dataSource/dataSource/dataSourceType!queryAllDataSourceType.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].typeName).val(datas.rows[i].id);  
				sel1.append(opt);  
			}
		}
	});	
	
	//下拉选择框数据加载  项目
	var sel2 = $("#projectName");  
	sel2.empty();//清除select中的 option
	$.post(ctx+'/projectManage1/projectManage1/project1!queryAllProject.action',
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].projectName).val(datas.rows[i].id);  
				sel2.append(opt);  
			}
			 var projectName=$("#projectName option:selected").html();
			 $("#projectNames").val(projectName);
		}
	});
	
});	

function changeProject()
{
	 var projectName=$("#projectName option:selected").html();
	 $("#projectNames").val(projectName);
}
function changeSource(){
    var sourceType=$("#sourceType").val();
	var sel = $("#dateNames");  
	sel.empty();//清除select中的 option
	$.post(ctx+'/dataSource/dataSource/dataSource!queryAllDataSourceByType.action?typeId='+sourceType,
	function(data){
		var datas = eval("("+data+")");
		if(null!=datas && ""!=datas){
			for(var i = 0;i < datas.rows.length; i++) {
				var opt = $("<option>").text(datas.rows[i].name).val(datas.rows[i].id);  
				sel.append(opt);  
			}
		}
	});	
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