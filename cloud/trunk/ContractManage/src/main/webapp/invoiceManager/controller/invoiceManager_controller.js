//弹出层***************************************
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
//	clearVMFormFun(1);
	$("#addVmDiv").animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 

function upload() {
	alert("操作成功!");
	hideDiv();
}