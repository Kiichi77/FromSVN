//添加虚拟机操信息操作
function showWindow() {
	var div_obj = $("#addVmDiv");  
    var windowWidth = document.body.clientWidth;       
    var windowHeight = document.body.clientHeight;  
    var popupHeight = div_obj.height();       
    var popupWidth = div_obj.width(); 
    div_obj.css({"position": "absolute"})
    .animate({left: windowWidth/2-popupWidth/2,top: windowHeight/2-popupHeight/2, opacity: "show" }, "slow");   
}
//隐藏虚拟机操信息操作
function hideDiv() {   
    $("#mask").remove();   
    $("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 