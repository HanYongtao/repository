package com.udcf.login.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.udcf.util.ValidateCodeUtil;
import com.udcf.util.ValidateCodeUtil.ValidateCode;

/**
 * 生成图片验证码
 * 
 * @author HanYongtao
 * @date 2018-03-17
 */
public class ValidateCodeAction extends ActionSupport implements SessionAware {
	private static final long	serialVersionUID	= -7816280330854156887L;

	private String				fileName			= "vcode_" + System.currentTimeMillis() + ".png";
	private String				contentType			= "image/png";
	private InputStream         inputStream         = null;
	private Map<String, Object> session             = null;
	
	@Override
	public String execute() throws Exception {
		ValidateCodeUtil vCodeUtil = new ValidateCodeUtil();
		ValidateCode     vCode     = vCodeUtil.createCode();
		session.put(ValidateCodeUtil.SESSION_VCODE_NAME, vCode.getCode());
		inputStream = new ByteArrayInputStream(vCode.getImage());
		
		return SUCCESS;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
