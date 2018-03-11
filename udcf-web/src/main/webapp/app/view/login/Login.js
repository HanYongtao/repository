Ext.define('UDCF.view.login.Login', {
	extend : 'Ext.window.Window',
	xtype : 'login',

	requires : ['UDCF.view.login.LoginController', 'Ext.form.Panel'],

	controller : 'login',
	title : '常规功能定义系统',
	width : 324,
	height : 180,
	closable : false,
	autoShow : true,
	layout : 'fit',
	items : {
		xtype : 'form',
		reference : 'form',
		bodyPadding : '25',
		url : 'login.action',
		items : [{
			xtype : 'textfield',
			name : 'username',
			fieldLabel : '登录账号',
			allowBlank : false
		}, {
			xtype : 'textfield',
			name : 'password',
			inputType : 'password',
			fieldLabel : '登录密码',
			allowBlank : false
		}],
		buttons : [{
			text : '登录',
			formBind : true,
			listeners : {
				click : 'onLoginClick'
			}
		}]
	}
});