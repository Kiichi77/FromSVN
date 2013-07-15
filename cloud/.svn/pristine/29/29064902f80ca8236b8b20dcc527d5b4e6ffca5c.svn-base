 $(function(){
			$('#tt').datagrid({
				url: ctx+'/quotations/quotations/quotations!listQuotations.action',
				width: '95%',
				height: 'auto',
//				pagination:true,
				rownumbers:true,
				remoteSort: false,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				fitColumns: true,
				pagination:true,
				columns:[[ 
							{field:'id',title:'ID',width:60,align:'center',hidden:true},
							{field:'dataSourceName',title:'所属数据源',width:300,align:'center'},
							{field:'title',title:'项目名称',width:300,align:'center'},
							{field:'createtime',title:'创建时间',width:200,align:'center'},
							{field:'createname',title:'创建者',width:100,align:'center',
								formatter:function(value,rec,index){
									return 'admin';
							}
							},
							{field:'status',title:'状态',width:100,height: 30,align:'center',
								 formatter:function(value,rec,index)
								 {
									 var status=rec.status;
									 var edit;
									  if(0==status)
										  {
										   edit='已送审';
										  }else if(1==status)
											  {
											   edit='已审核';
											  }
									  return edit;
								 }
							},
							{field:'opt',title:'操作',width:200,align:'center',
								formatter:function(value,rec,index){
									var edit;
									edit='<a href="checkDetails.jsp">审核</a>';
									return edit;
								}
								
							}
							]],
							toolbar:[],
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
		});	
	 //隐藏存储操信息操作
	   function hideDiv() {
	   	$("#mask").remove();
	   	$("#addVmDiv").animate({
	   		left : 0,
	   		top : 0,
	   		opacity : "hide"
	   	}, "slow");
	   }
		//弹出层***************************************
		function showWindow() {
		  var div_obj = $("#addVmDiv");  
		  var windowWidth = document.body.clientWidth;       
		  var windowHeight = document.body.clientHeight;  
		  var popupHeight = div_obj.height();       
		  var popupWidth = div_obj.width(); 
		  div_obj.css({"position": "absolute"})
		  .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
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
