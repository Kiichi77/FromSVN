/**
 *部分常用组件的获得方法
 *创建人：lizl
 *创建时间：2012-08-06
 *修改人：lizl 
 */
//gridepanel的获得方法
function wantGridePanel(id,render,cm,store,title) {
	return Ext.create('Ext.grid.Panel',{
		id:id,
		title: title,
    	autoHeight : true, 
    	renderTo:render,
		loadMask:{msg:"数据正在加载中,请耐心等待......"},
		viewConfig: {forceFit:true}, 		//列自适应宽
		columns :cm,
		stripeRows: true,
		trackMouseOver : true ,		
		frame:true,
		store:store,  //将数据源添加到grid中
		boder:true
	});
}  

//返回小时和分钟的方法
function backHourAndMinnsFun(params) {
	var data = [];
	if(1 == params) {		//返回小时
		for(var i = 0;i < 24;i++) {
			data.push({
	            ids: i,
	            names: i
	        });
		}
	}else {		//返回分钟
		for(var i = 0;i < 60;i++) {
			data.push({
	            ids: i,
	            names: i
	        });
		}
	}
	return data;
}

/**
 * 获得起始时间
 */
function getStartTime(){
	var a = new Date();
	a = a.valueOf();
	a = new Date(a);
	return a;
	
}

function getEndTime(){
	var a = new Date();
	a = a.valueOf();
	a = a + (-1 * 24 * 60 * 60 * 1000);
	a = new Date(a);
	return a;
}

//loading等待方法
//参数：params：需要渲染在那个容器的ID 
function doloadingFunc(params) {
	var loadMarsk = new Ext.LoadMask(params,{//元素、DOM节点或id,
		msg : 'Loading...',
		removeMask : true// 完成后移除
	});
	return loadMarsk;
}

/***********************************pagingChangeStart**************************************/
var pageingStore = Ext.create('Ext.data.Store', {
    storeId:'pageingStore',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
               	{'ids':'10','names':'10'},
            	{'ids':'20','names':'20'},
               	{'ids':'30','names':'30'},
               	{'ids':'40','names':'40'},
               	{'ids':'50','names':'50'},
               	{'ids':'60','names':'60'},
               	{'ids':'70','names':'70'},
               	{'ids':'80','names':'80'},
               	{'ids':'90','names':'90'},
               	{'ids':'100','names':'100'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});
var thePublicPageSize = 10;
Ext.override(Ext.toolbar.Paging,{getPagingItems : function(){
	var me = this;
	return [{
	    itemId: 'first',
	    tooltip: me.firstText,
	    overflowText: me.firstText,
	    iconCls: Ext.baseCSSPrefix + 'tbar-page-first',
	    disabled: true,
	    handler: me.moveFirst,
	    scope: me
	},{
	    itemId: 'prev',
	    tooltip: me.prevText,
	    overflowText: me.prevText,
	    iconCls: Ext.baseCSSPrefix + 'tbar-page-prev',
	    disabled: true,
	    handler: me.movePrevious,
	    scope: me
	},
	'-',
	me.beforePageText,
	{
	    xtype: 'numberfield',
	    itemId: 'inputItem',
	    name: 'inputItem',
	    cls: Ext.baseCSSPrefix + 'tbar-page-number',
	    allowDecimals: false,
	    minValue: 1,
	    hideTrigger: true,
	    enableKeyEvents: true,
	    keyNavEnabled: false,
	    selectOnFocus: true,
	    submitValue: false,
	    // mark it as not a field so the form will not catch it when getting fields
	    isFormField: false,
	    width: me.inputItemWidth,
	    margins: '-1 2 3 2',
	    listeners: {
	        scope: me,
	        keydown: me.onPagingKeyDown,
	        blur: me.onPagingBlur
	    }
	},{
	    xtype: 'tbtext',
	    itemId: 'afterTextItem',
	    text: Ext.String.format(me.afterPageText, 1)
	},
	'-',
	{
	    itemId: 'next',
	    tooltip: me.nextText,
	    overflowText: me.nextText,
	    iconCls: Ext.baseCSSPrefix + 'tbar-page-next',
	    disabled: true,
	    handler: me.moveNext,
	    scope: me
	},{
	    itemId: 'last',
	    tooltip: me.lastText,
	    overflowText: me.lastText,
	    iconCls: Ext.baseCSSPrefix + 'tbar-page-last',
	    disabled: true,
	    handler: me.moveLast,
	    scope: me
	},
	'-',
	{
	    itemId: 'refresh',
	    tooltip: me.refreshText,
	    overflowText: me.refreshText,
	    iconCls: Ext.baseCSSPrefix + 'tbar-loading',
	    handler: me.doRefresh,
	    scope: me
	},
	"-",
	Ext.create('Ext.form.ComboBox',{
		xtype: 'combobox',
//	    fieldLabel: '页容量',
//	    lableWidth:30,
		width:50,
	    store:pageingStore,
	    queryMode: 'local',
	    displayField: 'ids',
	    editable:false,
	    valueField: 'names',
	    allowBlank: false,
	    name:'pageinsg_type',
	    id:'pageinsg_type',
	    listeners:{
	    	'select':function(params){
				thePublicPageSize = params.getValue();
			}
		}
	})];       
}});
/**********************************pagingChangeEnd***************************************/