package com.udcf.auth.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 系统管理员
 * 
 * @author HanYongtao
 * @date 2018-03-11
 */
@Entity
@Table(name = "T_ADMIN")
public class AdminModel {
	private String		pkid;
	private String		name;
	private String		pass;
	private String		desc;
	private RoleModel   role;

	@Id
	@Column(name = "PKID", length = 32)
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	@Column(name = "NAME", length = 24, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASS", length = 64)
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "DESCRIPTION", length = 256)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToOne(cascade= {CascadeType.DETACH}, fetch= FetchType.EAGER)
	@JoinColumn(name="R_PKID")
	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

}
