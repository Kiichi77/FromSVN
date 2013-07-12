<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../../view/head.jsp"></jsp:include>
<script type="text/javascript">
var chart;
$(function(){
chart = new Highcharts.Chart({
    chart: {
        renderTo: 'chart_spline', //图表放置的容器，DIV
        defaultSeriesType: 'line', //图表类型line(折线图),
		zoomType: 'x'   //x轴方向可以缩放
    },
	credits: {
		enabled: false   //右下角不显示LOGO
	},
    title: {
        text: 'CPU' //图表标题
    },
    subtitle: {
        text: '2010年一月'  //副标题
    },
    xAxis: {  //x轴
        categories: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29', '30', '31'], //x轴标签名称
		gridLineWidth: 1, //设置网格宽度为1
		lineWidth: 1,  //基线宽度
		labels:{y:26}  //x轴标签位置：距X轴下方26像素
    },
    yAxis: {  //y轴
        title: {text: 'CPU利用率'}, //标题
		lineWidth: 2 //基线宽度
    },
	plotOptions:{ //设置数据点
		line:{
			dataLabels:{
				enabled:true  //在数据点上显示对应的数据值
			},
			enableMouseTracking: false //取消鼠标滑向触发提示框
		}
	},
	legend: {  //图例
        layout: 'horizontal',  //图例显示的样式：水平（horizontal）/垂直（vertical）
        backgroundColor: '#ffc', //图例背景色
        align: 'left',  //图例水平对齐方式
        verticalAlign: 'top',  //图例垂直对齐方式
        x: 100,  //相对X位移
        y: 70,   //相对Y位移
        floating: true, //设置可浮动
        shadow: true  //设置阴影
    },
	exporting: {
		enabled: false  //设置导出按钮不可用
	},
    series: [{  //数据列
        name: 'win2008',
        data: [ 10, 13,26, 16, 19, 26, 40, 87, 15, 67,60, 32,10, 13,26, 16, 19, 26, 40, 87, 15, 67,60, 32,48,38,76,92,43,23,87]
    },
 ]
});
	  
});

</script>
<style type="text/css">
.demo{width:auto; height:320px; margin:10px auto}
</style>
</head>
<script type="text/javascript" src="../../jquery-easyui-1.3.1/exporting.js"></script>
<script type="text/javascript" src="../../jquery-easyui-1.3.1/highcharts.js"></script>
<body class="easyui-layout">
<%-- 	<jsp:include page="../../view/top.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="../../view/left.jsp"></jsp:include> --%>
	<div data-options="region:'center'" title="虚拟机详情：win2008" style="overflow:hidden;">
	    <div style="margin-left: 30px;width: 90%">
	        <div><font style="font-size: 16px;font-weight: bold;">详情</font>
	             <hr>
	        </div>
	        <div><font style="font-weight: bold;">名称</font> <br>
	             win2008	                      
	             <br> 
	             <font style="font-weight: bold;">IP地址</font><br>
	             10.11.11.6         
	        </div>	     
	        <hr>   
	        <div>
	                      月份：
	          <select id="date" class="easyui-combobox" name="state" style="margin-left: 30px;width:150px;" data-options="required:true">
		        <option value="1">一月</option>
		        <option value="2">二月</option>
		        <option value="3">三月</option>
		        <option value="4">四月</option>
		        <option value="5">五月</option>
		        <option value="6">六月</option>
		        <option value="7">七月</option>
		        <option value="8">八月</option>
		        <option value="9">九月</option>
		        <option value="10">十月</option>
		        <option value="11">十一月</option>
		        <option value="12">十二月</option>
		   </select>
		   <select id="date" class="easyui-combobox" name="state" style="margin-left: 30px;width:150px;" data-options="required:true">
		        <option value="2010">2010</option>
		        <option value="2011">2011</option>
		        <option value="2012">2012</option>
		        <option value="2013">2013</option>
		        <option value="2014">2014</option>
		        <option value="2015">2015</option>
		        <option value="2016">2016</option>
		   </select>
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		       统计指标：
		     <select id="date" class="easyui-combobox" name="state" style="margin-left: 30px;width:150px;" data-options="required:true">
		        <option value="cpu">CPU</option>
		        <option value="RAM">内存</option>
		        <option value="disk">硬盘</option>
		   </select>
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	   
		    <a href="#" class="easyui-linkbutton" >提交</a>       
	                 <div id="chart_spline" class="demo"></div>	            
	        </div>
	    </div>
</div>
</body>
</html>