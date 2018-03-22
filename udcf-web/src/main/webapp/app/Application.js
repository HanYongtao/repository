Ext.define('UDCF.Application', {
	extend : 'Ext.app.Application',

	name : 'UDCF',

	quickTips : false,
	views : ['UDCF.view.login.Login', 'UDCF.view.main.Main'],

	stores : [

	],

	launch : function() {
		Ext.Ajax.request({
			url : 'loadResource.action',
			async : false,
			method : 'POST',
			scope : this,
			success : function(resp, opts) {
				var json = Ext.JSON.decode(resp.responseText, true);
				console.log(json)
				if (json != null && json.username != null) {
					Ext.create({
						xtype : 'app-main'
					});
				} else {
					Ext.create({
						xtype : 'login'
					});
				}
			},
			failure : function(resp, opts) {
				Ext.MessageBox.show({
					titile : '常规功能定义系统',
					icon : Ext.MessageBox.ERROR,
					buttons : Ext.MessageBox.OK,
					msg : '内部错误，请联系技术支持人员'
				})
			}
		})

	},

	onAppUpdate : function() {
		Ext.Msg.confirm('Application Update',
				'This application has an update, reload?', function(choice) {
					if (choice === 'yes') {
						window.location.reload();
					}
				});
	}
});
