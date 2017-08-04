package com.bonc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity 
@Table(name = "test_role") 
@JsonIgnoreProperties(value={"users"})
public class RoleInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private java.lang.Long id;
	@Column(name="ROLE_CODE", length=255,nullable=true)
	private java.lang.String roleCode;
	@Column(name="ROLE_NAME", length=255,nullable=true)
	private java.lang.String roleName;
	@Column(name="CREATE_TIME", length=255,nullable=true)
	private java.util.Date createTime ;
	@Column(name="CREATE_USER", length=255,nullable=true)
	private java.lang.String creatUser;
	@Column(name="ROLEINFO_ID", length=255,nullable=true)
	private java.lang.String roleId;
	@ManyToMany(mappedBy = "roles")
	private Set<UserInfo> users = new HashSet<UserInfo>();
	
	
	public Set<UserInfo> getUsers() {
		return users;
	}
	public void setUsers(Set<UserInfo> users) {
		this.users = users;
	}
	public RoleInfo() {
		super();
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(java.lang.String roleCode) {
		this.roleCode = roleCode;
	}
	public java.lang.String getRoleName() {
		return roleName;
	}
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getCreatUser() {
		return creatUser;
	}
	public void setCreatUser(java.lang.String creatUser) {
		this.creatUser = creatUser;
	}
	public java.lang.String getRoleId() {
		return roleId;
	}
	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}
	
}
