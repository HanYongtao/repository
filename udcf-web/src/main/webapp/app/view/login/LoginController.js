Ext.define('UDCF.view.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',

	onLoginClick : function() {
		this.getView().destroy();

		Ext.create({
			xtype : 'app-main'
		});

	}
});