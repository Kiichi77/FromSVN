$(function(){
	$('#tt').datagrid({
		url: ctx+'contractTemplates/contractTemplates/contractTemplatesDoc!queryAll.action',
		width: '95%',
		height: 'auto',
		rownumbers:true,
		pagination:true,
		remoteSort: false,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		fitColumns: true,
		columns:[[  		   
                    {field:'ck',checkbox:true,align:'center'},
					{field:'id',title:'ID',width:60,align:'center',hidden:true},
					{field:'typeName',title:'分类',width:60,align:'center'},
					{field:'docname',title:'名称',width:60,align:'center'},
					{field:'createtime',title:'创建时间',width:60,align:'center'},
					{field:'path',title:'文件',width:60,align:'center'},
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
					},
					{field:'opt',title:'操作',width:80,align:'center',
						formatter:function(value,rec,index){
							var fileUrl = rec.path;
							var id=rec.id;
							var bVariable=rec.bVariable;
							var edit;
							if(1==bVariable){
								edit='<a href="#" onclick="updateFindbyId(\''+id+'\')">修改</a>&nbsp;|&nbsp;<a href="#" onclick="open1('+id+')">设置变量</a>';
								return edit;
							}else{
								edit='<a href="#" onclick="updateFindbyId(\''+id+'\')">修改</a>';
								return edit;
							}
						}
					}]],
					toolbar:[{
						id:'btnadd',
						text:'上传文件',
						iconCls:'icon-add',
						handler:function(){
							optType=1;
							$('#btnsave').linkbutton('enable');
							$("#filediv").css("display","");
							tempDocsId = "";
							openWindow();
						}
					},{
						id:'btndelete',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							$('#btnsave').linkbutton('enable');
							deleteSomeContractTemplatesDoc();
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
	//隐藏、显示
	$("#dd").toggle();
//	$("#add").toggle();
	$(".datagrid-toolbar").append($("#datagridsearch"));
	getDocType();
});