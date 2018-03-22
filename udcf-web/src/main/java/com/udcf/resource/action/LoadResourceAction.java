package com.udcf.resource.action;

import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.udcf.util.AppContext;

/**
 * 加载基础资源信息
 * 
 * @author HanYongtao
 * @date 2018-03-12
 */
public class LoadResourceAction extends ActionSupport {
	private static final long				serialVersionUID	= 1100860108906880608L;

	private boolean							success				= true;

	private String							message;

	private LinkedHashMap<String, String>	resources			= new LinkedHashMap<String, String>();

	private String							username			= null;

	@Override
	public String execute() throws Exception {

		ResourceBundle bundle = ResourceBundle.getBundle("resources", this.getLocale());

		for (String key : bundle.keySet()) {
			resources.put(key, bundle.getString(key));
		}

		username = (String) ActionContext.getContext().getSession().get(AppContext.APP_SESSION_ADMIN);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
