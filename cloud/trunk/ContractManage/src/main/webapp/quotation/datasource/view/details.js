//服务器
$(function(){
	var lastIndex;
	$('#t11').datagrid({
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#t11').datagrid('endEdit', lastIndex);
				$('#t11').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
//硬盘框	
	var lastIndex1;
	$('#t12').datagrid({
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex1 != rowIndex){
				$('#t12').datagrid('endEdit', lastIndex1);
				$('#t12').datagrid('beginEdit', rowIndex1);
			}
			lastIndex1 = rowIndex;
		}
	});
	
	//CPU
	$(function(){
		var lastIndex;
		$('#t21').datagrid({
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickRow:function(rowIndex){
				if (lastIndex != rowIndex){
					$('#t21').datagrid('endEdit', lastIndex);
					$('#t21').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
		});
		//内存
		var lastIndex2;
		$('#t22').datagrid({
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickRow:function(rowIndex){
				if (lastIndex2 != rowIndex){
					$('#t22').datagrid('endEdit', lastIndex2);
					$('#t22').datagrid('beginEdit', rowIndex2);
				}
				lastIndex2 = rowIndex;
			}
		});
		//硬盘
		var lastIndex3;
		$('#t23').datagrid({
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickRow:function(rowIndex){
				if (lastIndex3 != rowIndex){
					$('#t23').datagrid('endEdit', lastIndex3);
					$('#t23').datagrid('beginEdit', rowIndex3);
				}
				lastIndex3 = rowIndex;
			}
		});
});
	//其他
	var lastIndex;
	$('#t31').datagrid({
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#t31').datagrid('endEdit', lastIndex);
				$('#t31').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
});
	


