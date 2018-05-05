package com.udcf.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资源数据
 * @author HanYongtao
 * @date   2018-03-11
 */
@Entity
@Table( name = "T_RESOURCE" )
public class ResourceModel {
	private String pkid;
	private String path;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "PKID", length=36)
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	@Column(name = "PATH", length=256)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
