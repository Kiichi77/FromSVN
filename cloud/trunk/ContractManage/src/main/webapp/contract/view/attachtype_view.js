	   
			$(function(){
				$('#tt').datagrid({
					url:  ctx+'/contractTemplates/contractTemplates/contractDocType!listContractDocType.action',
					width: '95%',
					height: 'auto',
//					pagination:true,
					rownumbers:true,
					remoteSort: false,
					nowrap: false,
					autoRowHeight: false,
					striped: true,
					pagination:true,
					collapsible:true,
					fitColumns: true,
					showGroup:true,
					columns:[[  		   
								{field:'id',title:'ID',width:60,align:'center',hidden:true},
								{field:'name',title:'分类名称',width:100,align:'center'},
								{field:'operate',title:'操作',width:80,align:'center',
									formatter:function(value,rec,index){
										var id=rec.id;
										var edit;
										edit='<a href="#" onclick="deleteContractDocType(\''+id+'\')">删除</a>';
										return edit;
									}
								}
								]],
								toolbar:[{
									id:'btnadd',
									text:'添加',
									iconCls:'icon-add',
									handler:function(){
										$('#btnsave').linkbutton('enable');
										showWindow();
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

			});	
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
			
			//添加分类***************************************

			//添加存储操信息操作
			function showWindow() {
				var div_obj = $("#addVmDiv");  
			    var windowWidth = document.body.clientWidth;       
			    var windowHeight = document.body.clientHeight;  
			    var popupHeight = div_obj.height();       
			    var popupWidth = div_obj.width(); 
			    div_obj.css({"position": "absolute"})
			    .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   

			}

			//隐藏存储操信息操作
			function hideDiv() {   
			    $("#mask").remove();   
			    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
			}  

