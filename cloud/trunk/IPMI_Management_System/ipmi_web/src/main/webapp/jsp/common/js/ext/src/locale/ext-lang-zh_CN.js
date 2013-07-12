/*!
 * Ext JS Library 3.1.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
/*
 * Simplified Chinese translation
 * By DavidHu
 * 09 April 2007
 * 
 * update by andy_ghg
 * 2009-10-22 15:00:57
 */

Ext.UpdateManager.defaults.indicatorText = '<div class="loading-indicator">加载中...</div>';

if(Ext.DataView){
   Ext.DataView.prototype.emptyText = "";
}

if(Ext.grid.GridPanel){
   Ext.grid.GridPanel.prototype.ddText = "选择了 {0} 行";
}

if(Ext.TabPanelItem){
   Ext.TabPanelItem.prototype.closeText = "关闭此标签";
}

if(Ext.form.Field){
   Ext.form.Field.prototype.invalidText = "输入值非法";
}

if (Ext.LoadMask) {
    Ext.LoadMask.prototype.msg = "读取中...";
}

Date.monthNames = [
   "一月",
   "二月",
   "三月",
   "四月",
   "五月",
   "六月",
   "七月",
   "八月",
   "九月",
   "十月",
   "十一月",
   "十二月"
];

Date.dayNames = [
   "日",
   "一",
   "二",
   "三",
   "四",
   "五",
   "六"
];

Date.formatCodes.a = "(this.getHours() < 12 ? '上午' : '下午')";
Date.formatCodes.A = "(this.getHours() < 12 ? '上午' : '下午')";

if(Ext.MessageBox){
   Ext.MessageBox.buttonText = {
      ok     : "确定",
      cancel : "取消",
      yes    : "是",
      no     : "否"
   };
}

if(Ext.util.Format){
   Ext.util.Format.date = function(v, format){
      if(!v) return "";
      if(!(v instanceof Date)) v = new Date(Date.parse(v));
      return v.dateFormat(format || "y年m月d日");
   };
}

if(Ext.DatePicker){
   Ext.apply(Ext.DatePicker.prototype, {
      todayText         : "今天",
      minText           : "日期必须大于最小允许日期",//update
      maxText           : "日期必须小于最大允许日期",//update
      disabledDaysText  : "",
      disabledDatesText : "",
      monthNames        : Date.monthNames,
      dayNames          : Date.dayNames,
      nextText          : '下个月 (Ctrl+Right)',
      prevText          : '上个月 (Ctrl+Left)',
      monthYearText     : '选择一个月 (Control+Up/Down 来改变年份)',//update
      todayTip          : "{0} (空格键选择)",
      format            : "y年m月d日",
      okText            : "确定",
      cancelText        : "取消"
   });
}

if(Ext.PagingToolbar){
   Ext.apply(Ext.PagingToolbar.prototype, {
      beforePageText : "第",//update
      afterPageText  : "页,共 {0} 页",//update
      firstText      : "第一页",
      prevText       : "上一页",//update
      nextText       : "下一页",
      lastText       : "最后页",
      refreshText    : "刷新",
      displayMsg     : "显示 {0} - {1}条，共 {2} 条",//update
      emptyMsg       : '没有数据'
   });
}

if(Ext.form.TextField){
   Ext.apply(Ext.form.TextField.prototype, {
      minLengthText : "该输入项的最小长度是 {0} 个字符",
      maxLengthText : "该输入项的最大长度是 {0} 个字符",
      blankText     : "该输入项为必输项",
      regexText     : "",
      emptyText     : null
   });
}

if(Ext.form.NumberField){
   Ext.apply(Ext.form.NumberField.prototype, {
      minText : "该输入项的最小值是 {0}",
      maxText : "该输入项的最大值是 {0}",
      nanText : "{0} 不是有效数值"
   });
}

if(Ext.form.DateField){
   Ext.apply(Ext.form.DateField.prototype, {
      disabledDaysText  : "禁用",
      disabledDatesText : "禁用",
      minText           : "该输入项的日期必须在 {0} 之后",
      maxText           : "该输入项的日期必须在 {0} 之前",
      invalidText       : "{0} 是无效的日期 - 必须符合格式： {1}",
      format            : "y年m月d日"
   });
}

if(Ext.form.ComboBox){
   Ext.apply(Ext.form.ComboBox.prototype, {
      loadingText       : "加载中...",
      valueNotFoundText : undefined
   });
}

if(Ext.form.VTypes){
   Ext.apply(Ext.form.VTypes, {
      emailText    : '该输入项必须是电子邮件地址，格式如： "user@example.com"',
      urlText      : '该输入项必须是URL地址，格式如： "http:/'+'/www.example.com"',
      alphaText    : '该输入项只能包含半角字母和_',//update
      alphanumText : '该输入项只能包含半角字母,数字和_'//update
   });
}
//add HTMLEditor's tips by andy_ghg
if(Ext.form.HtmlEditor){
  Ext.apply(Ext.form.HtmlEditor.prototype, {
    createLinkText : '添加超级链接:',
    buttonTips : {
      bold : {
        title: '粗体 (Ctrl+B)',
        text: '将选中的文字设置为粗体',
        cls: 'x-html-editor-tip'
      },
      italic : {
        title: '斜体 (Ctrl+I)',
        text: '将选中的文字设置为斜体',
        cls: 'x-html-editor-tip'
      },
      underline : {
        title: '下划线 (Ctrl+U)',
        text: '给所选文字加下划线',
        cls: 'x-html-editor-tip'
      },
      increasefontsize : {
        title: '增大字体',
        text: '增大字号',
        cls: 'x-html-editor-tip'
      },
      decreasefontsize : {
        title: '缩小字体',
        text: '减小字号',
        cls: 'x-html-editor-tip'
      },
      backcolor : {
        title: '以不同颜色突出显示文本',
        text: '使文字看上去像是用荧光笔做了标记一样',
        cls: 'x-html-editor-tip'
      },
      forecolor : {
        title: '字体颜色',
        text: '更改字体颜色',
        cls: 'x-html-editor-tip'
      },
      justifyleft : {
        title: '左对齐',
        text: '将文字左对齐',
        cls: 'x-html-editor-tip'
      },
      justifycenter : {
        title: '居中',
        text: '将文字居中对齐',
        cls: 'x-html-editor-tip'
      },
      justifyright : {
        title: '右对齐',
        text: '将文字右对齐',
        cls: 'x-html-editor-tip'
      },
      insertunorderedlist : {
        title: '项目符号',
        text: '开始创建项目符号列表',
        cls: 'x-html-editor-tip'
      },
      insertorderedlist : {
        title: '编号',
        text: '开始创建编号列表',
        cls: 'x-html-editor-tip'
      },
      createlink : {
        title: '转成超级链接',
        text: '将所选文本转换成超级链接',
        cls: 'x-html-editor-tip'
      },
      sourceedit : {
        title: '代码视图',
        text: '以代码的形式展现文本',
        cls: 'x-html-editor-tip'
      }
    }
  });
}


if(Ext.grid.GridView){
   Ext.apply(Ext.grid.GridView.prototype, {
      sortAscText  : "正序",//update
      sortDescText : "倒序",//update
      lockText     : "锁定列",//update
      unlockText   : "解除锁定",//update
      columnsText  : "列"
   });
}

if(Ext.grid.PropertyColumnModel){
   Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
      nameText   : "名称",
      valueText  : "值",
      dateFormat : "y年m月d日"
   });
}

if(Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion){
   Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
      splitTip            : "拖动来改变尺寸.",
      collapsibleSplitTip : "拖动来改变尺寸. 双击隐藏."
   });
}


Ext.apply(Ext.form.VTypes, {
    num:  function(v) {
        return  /^\d+(\.\d+)?$/.test(v);
    },
    numText: '必须为数字'
});

Ext.apply(Ext.form.VTypes, {
    numAndy:  function(v) {
	 	if(32768 <= v || 0 > v || !/^\d+(\.\d+)?$/.test(v)) {
	    	
	    	return false;
	    }
        return true;
    },
   
    numAndyText: '必须为数字,且不得大于32767'
});

Ext.apply(Ext.form.VTypes, {
    mobilephonenum:  function(v) {
        return  /^(13[0-9]|15[0|3|6|7|8|9]|18[6|8|9])\d{8}$/.test(v);
    },
    mobilephonenumText: '请输入正确的手机号码'
});
if(Ext.form.CheckboxGroup){
  Ext.apply(Ext.form.CheckboxGroup.prototype, {
    blankText : "请选择至少一个网络类型!"
  });
}
Ext.apply(Ext.form.VTypes, {
    numAge:  function(v) {
        return  /^[0-9]*[1-9][0-9]*$/.test(v);
    },
    numAgeText: '必须是大于0的整数!'
});

Ext.apply(Ext.form.VTypes, {
    numw:  function(v) {
    	var bl = false;
    	if(/^\d+(\.\d+)?$/.test(v)&&v<=999999.99){	
    		bl=true;
    	}
    	if(v.split(".")[1]!=null){
	    	if(v.split(".")[1].length>2){
	    		bl=false;	
	    	}
    	}    	
        return  bl;
    },
    numwText: '必须为数字并且小于999999.99，保留2位小数'
});
Ext.apply(Ext.form.VTypes, {
    numDayMax:  function(v) {
	 	if(v > 1000000  || 0 > v || !/^\d+(\.\d+)?$/.test(v)) {
	    	return false;
	    }
        return true;
    },
   
    numDayMaxText: '必须为数字,且不得大于100W'
});
//判断开始时间是否大于结束时间
Ext.apply(Ext.form.VTypes,{
	checkStartAndEndDate:function(value,field){			
		if(field.compareTo){ //需要在enddate里面价格compareTo的字段。字段的值是startdate的ID。
			var startformat=Ext.getCmp(field.compareTo).format;
			var start=Ext.getCmp(field.compareTo).getValue();
			start=Ext.util.Format.date(start,startformat);			
			if(start>value){
				return false;
			}			
		}
		return true;
	},
	checkStartAndEndDateText:'开始时间不能大于结束时间'
});
//验证短信长度
Ext.apply(Ext.form.VTypes,{
	messageLengthCheck:function(value){
		if(value!=null){			
			if(value.replace(/[^\x00-\xFF]/g,'**').length>140){
				return false;
			}
		}
		return true;
	},
	messageLengthCheckText:'短信内容长度过长'
});
//验证歌曲代码长度
Ext.apply(Ext.form.VTypes,{
	ringCodeLengthCheck:function(value) {
		if(value!=null){			
			if(value.replace(/[^\x00-\xFF]/g,'**').length > 20){
				return false;
			}
		}
		return true;
	},
	ringCodeLengthCheckText:'歌曲代码长度过长'
});
//验证wappush地址长度
Ext.apply(Ext.form.VTypes,{
	wappushAdrLengthCheck:function(value) {
		if(value!=null){			
			if(value.replace(/[^\x00-\xFF]/g,'**').length > 70){
				return false;
			}
		}
		return true;
	},
	wappushAdrLengthCheckText:'wappush地址长度过长'
});
//验证任务主题长度
Ext.apply(Ext.form.VTypes,{
	jobTitleLengthCheck:function(value) {
		if(value!=null){			
			if(value.replace(/[^\x00-\xFF]/g,'**').length > 50){
				return false;
			}
		}
		return true;
	},
	jobTitleLengthCheckText:'任务主题长度过长'
});