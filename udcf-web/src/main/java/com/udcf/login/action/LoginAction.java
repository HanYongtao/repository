package com.udcf.login.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.udcf.login.model.Administrator;
import com.udcf.login.service.AdministratorService;
import com.udcf.util.AppContext;

/**
 * 管理员登录验证
 * 
 * @author HanYongtao
 * @date 2018-03-12
 */
public class LoginAction extends ActionSupport {
	private static final long		serialVersionUID		= 1100860108906880608L;

	private boolean					success = false;

	private String					message;
	
	private String                  username;
	
	private String                  password;

	private AdministratorService	administratorService	= null;

	@Override
	public void validate() {
		
		if(this.username == null || "".equals(this.username.trim()))
		{
			this.addFieldError("username", this.getText("login.form.field.username.required"));
		}
	}

	@Override
	public String execute() throws Exception {

		Administrator admin = administratorService.getAdministrator(username);
		
		if((admin != null && admin.getPassword().equals(this.password))) {
			success = true;
			// 设置登录会话
			ActionContext.getContext().getSession().put(AppContext.APP_SESSION_ADMIN, admin.getName().trim());
		} else {
			success = false;
		}
		
		return SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
}