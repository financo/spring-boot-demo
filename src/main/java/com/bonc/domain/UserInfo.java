package com.bonc.domain;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.bonc.domain.RoleInfo;

@Entity 
@Table(name = "test_user") 
@JsonIgnoreProperties()
public class UserInfo implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
	private java.lang.Long id;
	
	@Column(name="LOGIN_ID", length=255,nullable=false, unique=true)
	private java.lang.String loginId;
	
	@Column(name="PASSWORD", length=255, nullable=false, unique=false) 
	private java.lang.String passWord;
	
	@Column(name="USER_NAME", length=255, nullable=false, unique=false) 
	private java.lang.String userName;
	
	@Column(name="EMAIL", length=100, nullable=true)
	private java.lang.String email;
	
	@Column(name="MOBILE", length=20, nullable=true)
	private java.lang.String mobile;
	
	@Column(name="MEMO", length=500, nullable=true)
	private java.lang.String memo;
	
	@Column(name="IS_ADMIN", length=20, nullable=true)
	private java.lang.Boolean isAdmin;
	
	@Column(name="IS_NORAML", length=20, nullable=true)
	private java.lang.Boolean isNormal;
	
	@Column(name="UPDATE_DATE", length=20, nullable=true)
	private java.util.Date updateDate;
	
	@Column(name="CREATE_DATE", length=20, nullable=true)
	private java.util.Date createDate;
	
	@Column(name="CREATE_USER", length=30, nullable=true)
	private java.lang.String creatUser;
	
	@Column(name="SEX_ID")
	private java.lang.Long sexId;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)  
    @JoinColumn(name = "SEX_ID", insertable=false, updatable=false)  
    private Sex sex;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "test_user_role_rel", 
            joinColumns = { @JoinColumn(name = "USERINFO_ID", referencedColumnName = "ID") }, 
            inverseJoinColumns = { @JoinColumn(name = "ROLEINFO_ID", referencedColumnName = "ID") })
	private Set<RoleInfo> roles = new HashSet<RoleInfo>();
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "test_user_org_rel", 
            joinColumns = { @JoinColumn(name = "USERINFO_ID", referencedColumnName = "ID") }, 
            inverseJoinColumns = { @JoinColumn(name = "ORGINFO_ID", referencedColumnName = "ID") })
	private Set<OrgInfo> orgs = new HashSet<OrgInfo>();
	
	
	
	public Set<OrgInfo> getOrgs() {
		return orgs;
	}
	public void setOrgs(Set<OrgInfo> orgs) {
		this.orgs = orgs;
	}
	public Set<RoleInfo> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleInfo> roles) {
		this.roles = roles;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getUserName() {
		return userName;
	}
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	public java.lang.Long getSexId() {
		return sexId;
	}
	public void setSexId(java.lang.Long sexId) {
		this.sexId = sexId;
	}
	public java.lang.String getLoginId() {
		return loginId;
	}
	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
	
	public java.lang.String getPassWord() {
		return passWord;
	}
	public void setPassWord(java.lang.String passWord) {
		this.passWord = passWord;
	}
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.lang.String getMobile() {
		return mobile;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	public java.lang.String getMemo() {
		return memo;
	}
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}
	public java.lang.Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(java.lang.Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getCreatUser() {
		return creatUser;
	}
	public void setCreatUser(java.lang.String creatUser) {
		this.creatUser = creatUser;
	}
	public java.lang.Boolean getIsNormal() {
		return isNormal;
	}
	public void setIsNormal(java.lang.Boolean isNormal) {
		this.isNormal = isNormal;
	}
	public UserInfo() {
		super();
	}
	
}