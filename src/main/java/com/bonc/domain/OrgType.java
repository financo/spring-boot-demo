package com.bonc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity 
@Table(name = "test_orgtype") 
@JsonIgnoreProperties(value={"orgInfos"})
public class OrgType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private java.lang.String id;
	@Column(name="ORGTYPE_NAME", length=255,nullable=false, unique=true)
	private java.lang.String orgtypeName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "orgType")  
    private List<OrgInfo> orgInfos = new ArrayList<OrgInfo>();
	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getOrgtypeName() {
		return orgtypeName;
	}
	public void setOrgtypeName(java.lang.String orgtypeName) {
		this.orgtypeName = orgtypeName;
	}
	
	public List<OrgInfo> getOrgInfos() {
		return orgInfos;
	}
	public void setOrgInfos(List<OrgInfo> orgInfos) {
		this.orgInfos = orgInfos;
	}
	public OrgType() {
		super();
	}
	
	
}
