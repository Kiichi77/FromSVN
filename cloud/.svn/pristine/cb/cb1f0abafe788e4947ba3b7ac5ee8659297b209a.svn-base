var mStore = Ext.create('Ext.data.Store', {
    storeId:'simpsonsStore',
//    autoLoad: true,
    fields:['serverIpIn', '最大值', '最小值', '平均值','isHaveData'],
    proxy: {
	    type: 'ajax',
	    url : ctx+'/historyServer/historyServer/historyServer!getGraphicsData.action',
	    reader: {
	        type: 'json',
	        root: 'historyData'
	    }
    }
});
var Store = Ext.create('Ext.data.Store', {
    storeId:'simpsonsStores',
    fields:['id', 'text','parName','level'],
    pageSize : thePublicPageSize,   //页面容量
    proxy: {
	    type: 'ajax',
	    url : ctx+'/datacenter/datacenter/datacenter!findListByLevel.action',
	    reader: {
	        type: 'json',
	        totalProperty:'pageBelongList.totalCount',
	        root: 'pageBelongList.result'
	    }
    }
});

var hours = Ext.create('Ext.data.JsonStore', {
    fields: ['ids', 'names'],
    data: backHourAndMinnsFun(1)
});
var minins = Ext.create('Ext.data.JsonStore', {
    fields: ['ids', 'names'],
    data: backHourAndMinnsFun(2)
});

//位置类型
var placeType = Ext.create('Ext.data.Store', {
    storeId:'placeType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
               	{'ids':'数据中心','names':'1'},
            	{'ids':'机房','names':'2'},
            	{'ids':'机架系','names':'3'},
            	{'ids':'机架','names':'4'},
            	{'ids':'机箱','names':'5'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});


//位置类型
var dataType = Ext.create('Ext.data.Store', {
    storeId:'dataType',
    autoLoad: true,
    fields:['ids','names'],
    data:{'items':[
               	{'ids':'CPU功耗','names':'1'},
            	{'ids':'内存功耗','names':'2'},
            	{'ids':'电源功耗','names':'3'},
            	{'ids':'进风口温度','names':'4'}
            	]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});


//报表类型
 var tableType=Ext.create('Ext.data.Store',{
	 storeId:'tableType',
 autoLoad: true,
 fields:['ids','names'],
 data:{'items':[
            {'ids':'日报','names':'1'},
         	{'ids':'周报','names':'2'},         	
         	{'ids':'月报','names':'3'},
         	{'ids':'年报','names':'4'}
         	]},
 proxy: {
     type: 'memory',
     reader: {
         type: 'json',
         root: 'items'
     }
 }
 });
 
 //时间
 
//周
 function getArray(l){
 	var array=[];
 	for(var i=1;i<=l;i++)
 		{
 		array.push({
 			ids:'第'+i+'周',
 			names:i
 		});
 		}
 	return array;
 }
 
 var week=Ext.create('Ext.data.Store',{
	 storeId:'week',
	 fields:['ids','names'],
	 data:getArray(53)
    });
 //月

 var month=Ext.create('Ext.data.Store',{
	 storeId:'month',
 autoLoad: true,
 fields:['ids','names'],
 data:{'items':[
            {'ids':'一月','names':'1'},
         	{'ids':'二月','names':'2'},         	
         	{'ids':'三月','names':'3'},
         	{'ids':'四月','names':'4'},
         	{'ids':'五月','names':'5'},
         	{'ids':'六月','names':'6'},
         	{'ids':'七月','names':'7'},
         	{'ids':'八月','names':'8'},
         	{'ids':'九月','names':'9'},
         	{'ids':'十月','names':'10'},
         	{'ids':'十一月','names':'11'},
         	{'ids':'十二月','names':'12'}
         	]},
 proxy: {
     type: 'memory',
     reader: {
         type: 'json',
         root: 'items'
     }
 }
 });
 
 //年
 var year=Ext.create('Ext.data.Store',{
	 storeId:'year',
 autoLoad: true,
 fields:['ids','names'],
 data:{'items':[
            {'ids':'2010年','names':'2010'},
            {'ids':'2011年','names':'2011'},
         	{'ids':'2012年','names':'2012'},         	
         	{'ids':'2013年','names':'2013'},
         	{'ids':'2014年','names':'2014'},
         	{'ids':'2015年','names':'2015'},
         	{'ids':'2016年','names':'2016'},
         	{'ids':'2017年','names':'2017'},
         	{'ids':'2018年','names':'2018'},
         	{'ids':'2019年','names':'2019'}
         	]},
 proxy: {
     type: 'memory',
     reader: {
         type: 'json',
         root: 'items'
     }
 }
 });
