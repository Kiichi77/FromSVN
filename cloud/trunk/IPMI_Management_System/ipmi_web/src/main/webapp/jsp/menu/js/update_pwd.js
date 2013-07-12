Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = ctx+'/jsp/common/js/ext/resources/images/default/s.gif';
	Ext.QuickTips.init(); // 初始化控件
	Ext.form.Field.prototype.msgTarget = 'under';//验证信息显示的位置 side表示在右边
	Ext.apply(Ext.form.VTypes, {
    	password : function(val, field) {
	     	if (field.initialPassField) {
	      		var pwd = Ext.getCmp(field.initialPassField);
	      		return (val == pwd.getValue());
	     	}
	     	return true;
    	},
    	passwordText : '两次输入的密码不一致'
   	});
   	var updatePwdForm;
   	var updatePwdWindow;
   	this.updatePwd = function updatePwd(){
			   	    var isOk=true;
				    function checkPasswd(){
				        Ext.Ajax.request({
				            url:ctx+'/customer/customer/customer!checkPasswd.action',
				            params:{
				            	'model.password':Ext.getCmp("oldPasswd").getValue()
				            },
				            success: function(response, options) {  
				               var obj = Ext.util.JSON.decode(response.responseText);                                         
				               if(obj.msg == 'no'){ //旧密码输入不一致（与当前密码对比）                       
				                   isOk = false;
				               }else{// 旧密码验证通过                       
				                   isOk = true;
				               }  
				            }  
				        });
				        return isOk;
				    }
    			if(updatePwdForm==null&&updatePwdWindow==null){
	    			
	    			updatePwdForm = new Ext.FormPanel({
                        layout :'form',
						width:280,
						baseCls : "x-plain",
						//monitorValid :true,
						items:[{
							xtype :'textfield',
							fieldLabel :"旧密码",
							id:'oldPasswd',
							validator:checkPasswd,//指定验证器
         				    invalidText:'原密码不正确!',
							name:'oldPasswd',
							inputType :'password',
							allowBlank :false	
						},{
							xtype:'textfield',
				    		fieldLabel:'新密码',
				    		inputType:'password',
				    		allowBlank:false,
				    		id:'newpwd',
				    		name:'newPasswd'
				    	},{
				    		xtype:'textfield',
				    		fieldLabel:'新密码确认',
				    		vtype : 'password',
				    		initialPassField : 'newpwd',
				    		id:'newpwd1',
				    		inputType:'password'
				    	}]
	    			});
	    			updatePwdWindow  = new Ext.Window({
						autoScroll : false,
						width:280,
						title:'修改密码',
						bodyStyle:"padding:5px",
						//plain : true,
						closeAction:"hide",//关闭时隐藏窗口,而不销毁,
						frame:true,   
						items:[updatePwdForm],
						modal:true,
						buttons:[{
							id:'enter',
							text:'确定',
							handler:function(btn){
								if(updatePwdForm.form.isValid()){
									btn.disable();
				    				updatePwdForm.form.doAction('submit',{
						            	url :ctx+'/customer/customer/customer!updatePasswd.action',
						                method:'post',
						                params:{
							            	'model.password':Ext.getCmp("newpwd").getValue()
							            },
						                success:function(form,action){						                	
//            								if(action.result.error!=null){
//            									Ext.Msg.alert("Error",action.result.error);
//            								}else{
												if (action.result.msg=='ok') {
													Ext.MessageBox.alert('提示','修改成功！');
													updatePwdWindow.hide();
													updatePwdForm.form.reset();
													btn.enable();
												}else{
													Ext.MessageBox.alert('信息','修改失败!');
													btn.enable();
												}
//            								}
						                }	
				    				});
								}
							}
						},{
							text:'取消',
							handler:function(){
								updatePwdWindow.hide();
								Ext.getCmp('enter').enable();
								updatePwdForm.form.reset();
							}
						}]
	    			});
	    			updatePwdWindow.show();
    			}else{
    				updatePwdWindow.show();
    			}
   	}
});