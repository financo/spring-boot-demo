package com.bonc.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="test_org")
public class OrgInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private java.lang.Long id;
	
	@Column(name="PATH", length=255, nullable=false)
	private java.lang.String path;
	
	@Column(name="ORG_NAME", length=255, nullable=false)
	private java.lang.String orgName;
	
	@ManyToOne(cascade={CascadeType.PERSIST},optional=true)  
    @JoinColumn(name = "ORGTYPE_ID") 
	private OrgType orgType;
	
	@ManyToOne(cascade={CascadeType.PERSIST},optional=true)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	@Column(name="MEMO", length=255, nullable=false)
	private java.lang.String memo;
	
	@Column(name="CREATE_TIME", length=255, nullable=false)
	private java.util.Date createTime ;
	
	@Column(name="CREATE_USER", length=255, nullable=false)
	private java.lang.String creatUser;
	
	@Column(name="ORD", length=255, nullable=false)
	private java.lang.Long ord;
	
	@Column(name="STATUS", length=255, nullable=false)
	private java.lang.Long status;
	
	public OrgInfo() {
		super();
	}
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}
	public OrgType getOrgType() {
		return orgType;
	}
	public void setOrgType(OrgType orgType) {
		this.orgType = orgType;
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getPath() {
		return path;
	}
	public void setPath(java.lang.String path) {
		this.path = path;
	}
	public java.lang.String getOrgName() {
		return orgName;
	}
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}
	public java.lang.String getMemo() {
		return memo;
	}
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
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
	public java.lang.Long getOrd() {
		return ord;
	}
	public void setOrd(java.lang.Long ord) {
		this.ord = ord;
	}
	public java.lang.Long getStatus() {
		return status;
	}
	public void setStatus(java.lang.Long status) {
		this.status = status;
	}
}
