Ext.define('UDCF.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',

	onLoginClick : function() {
		var form = this.getView().down('form').getForm();
		
		if(!form.isValid()) {
			return;
		}
		
		form.submit({
			scope : this,
			success : function(form, action) {
				try {
					this.getView().destroy();
					Ext.create({
						xtype : 'app-main'
					});
				} catch(e) {}
			},
			failure : function(form, action) {
				var field = null;
				var index = -1;
				
				if(action.result != null && action.result.errors != null) {
					for(var fieldName in action.result.errors) {
						field = form.findField(fieldName);
						if(field == null) {
							continue;
						}
						
						Ext.MessageBox.show({
							title : '系统提示',
							msg : '【' + field.getFieldLabel() + '】' + action.result.errors[fieldName],
							icon : Ext.MessageBox.INFO,
							buttons : Ext.MessageBox.OK
						});
						
						break;
					}
				}
				
				if(field == null) {
					Ext.MessageBox.show({
						title : '系统提示',
						msg : '内部错误，请联系网站技术支持人员或稍后重试',
						icon : Ext.MessageBox.INFO,
						buttons : Ext.MessageBox.OK
					});
				}
			}
		});
		
		

	}
});