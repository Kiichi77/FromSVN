 $(function(){
			$('#tt').datagrid({
				url: ctx+'quotations/quotations/quotations!listQuotations.action',
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
					//		{field:'projectId',title:'项目id',width:100,align:'center',hidden:true},
					//		{field:'projectName',title:'所属项目',width:100,align:'center'},
							{field:'title',title:'报价表名称',width:100,align:'center'},
							{field:'catalogTotalPrice',title:'目录合价(元)',width:80,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'afterDiscountPrice',title:'折扣后价格(元)',width:80,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'afterDiscountNowPrice',title:'折扣后现场价(元)',width:80,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'totalPrice',title:'合计(元)',width:80,align:'center',
								formatter:function(value,rec,index){
									value = value.replace(/,/g,"");
									var num = new Number(value);
									return num.toFixed(0);
								}			
							},
							{field:'createtime',title:'创建时间',width:100,align:'center'},
							{field:'creatorName',title:'创建者',width:60,align:'center'},
							{field:'status',title:'状态',width:60,height: 30,align:'center',
								 formatter:function(value,rec,index){
									 var status=rec.status;
									 var edit;
									  if(0==status){
										   edit='无效';
									  }else if(1==status){
										   edit='未审核';
									  }else if(2==status){
										  edit='待审核';
									  }else if(3==status){
										  edit='审核通过';
									  }else if(4==status){
										  edit='审核不通过';
									  }
									  return edit;
								 }
							},
							{field:'opt',title:'操作',width:80,align:'center',
								formatter:function(value,rec,index){
									var edit;
									edit='<a href="checkDetails.jsp?qoutaionId='+rec.id+'&projectId='+rec.projectId+'&status='+rec.status+'">查看详情</a>';
									return edit;
								}
								
							}
							]],
						//	toolbar:[],
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
