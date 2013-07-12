
var products = [
		    {productid:'FI-SW-01',name:'DELL'},
		    {productid:'K9-DL-01',name:'HP'},
		    {productid:'RP-SN-01',name:'华为'},
		    {productid:'RP-LI-02',name:'东芝'},
		    {productid:'FL-DSH-01',name:'IBM'}
		];

function productFormatter(value){
	for(var i=0; i<products.length; i++){
		if (products[i].productid == value) return products[i].name;
	}
	return value;
}
		
//单元数据总览列布局
var dyDataLayOut = [[  		   
	{field:'ck',checkbox:true,align:'center'},
	{field:'id',title:'机型配置id',width:60,align:'center',hidden:true},
	{field:'configName',title:'机型配置名称',width:80,align:'center'},
	{field:'buyPrice',title:'买价',width:80,align:'center',editor:'text'},
	{field:'Supplier',title:'供货商',width:100,align:'center',
		formatter:function(value,rec,index){
			return productFormatter(value);
		},editor:{
	        type: 'combobox',
	        options: {
                valueField: 'productid',
                textField: 'name',
                data: products,
                required:true
            }
	}},
	{field:'number',title:'数量',width:100,align:'center'},
	{field:'totalPrice',title:'总价',width:100,align:'center'}
]];
var lastIndex = 0;
$(function(){
	$('#tt').datagrid({
		url:  ctx+'/InquiryOrder/view/addInquiryOrderlist.json',
		title:'请设置配置信息',
		width: '95%',
		height: 'auto',
		pagination:true,
		rownumbers:true,
		remoteSort: false,
		nowrap: false,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
	    pagination:true,
		fitColumns: true,
		showGroup:true,
		columns:dyDataLayOut,
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!$('#tmenu').length){
				createColumnMenu();
			}
			$('#tmenu').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},onClickRow:function(rowIndex,rowData){
			$('#tt').datagrid('selectRow', rowIndex);
			$('#tt').datagrid('endEdit',lastIndex);
			$('#tt').datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
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
