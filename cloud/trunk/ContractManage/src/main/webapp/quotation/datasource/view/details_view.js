//详情数据总览列布局
var infoDataLayOut = [[  		   
	{field:'productOrderNo',title:'产品订货号',width:60,align:'center'},
	{field:'name',title:'产品名称',width:100,align:'center'},
	{field:'productDesc',title:'产品描述',width:200,align:'center'},
	{field:'quantity',title:'数量',width:60,align:'center'},
	{field:'unitPrice',title:'目录单价',width:60,align:'center'},
	{field:'totalprice',title:'目录合价',width:60,align:'center'},
	{field:'discountRate',title:'折扣率',width:50,align:'center',editor:'text'},
	{field:'rebateprice',title:'折扣后价格（￥）',width:60,align:'center'},
	{field:'otherRates',title:'运保及其他费率（%）',width:50,align:'center'},
	{field:'rebateafter',title:'折扣后现场价（￥）',width:60,align:'center'},
	{field:'installServiceCharge',title:'安装服务费（￥）',width:60,align:'center'},
	{field:'firstYear',title:'第一年保修期费用',width:60,align:'center'},
	{field:'secondYear',title:'第二年保修期费用',width:60,align:'center'},
	{field:'thirdYear',title:'第三年保修期费用',width:60,align:'center'},
	{field:'total',title:'合计',width:50,align:'center'}	       	                  
]];

$(function(){
	$.post(ctx+'dataSource/dataSource/dataRecord!listDetailDataRecord.action',{dataSourceId:tocheckIds},
	function(data){
		if(null!=data && ""!=data){
			for(var i = 0;i < data.rows.length; i++) {
				wantDivFun(data.rows[i].name,""+data.rows[i].param,data.rows[i].url);
			}
		} else {
			alert("系统异常，请稍后操作");
		}
	});
});
	

