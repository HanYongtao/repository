package com.udcf.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统管理员
 * 
 * @author HanYongtao
 * @date 2018-03-11
 */
@Entity
@Table(name = "T_ROLE")
public class RoleModel {
	private String				pkid;
	private String				name;
	private String				desc;
	private Set<ResourceModel>	resources	= new HashSet<ResourceModel>();

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "PKID", length = 36)
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

	@Column(name = "DESCRIPTION", length = 256)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="T_ROLE_RESOURCE", 
	joinColumns= {@JoinColumn(name="ROLE_PKID", referencedColumnName="PKID")},
	inverseJoinColumns = {@JoinColumn(name="RESOURCE_PKID", referencedColumnName="PKID")})
	public Set<ResourceModel> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceModel> resources) {
		this.resources = resources;
	}
}
