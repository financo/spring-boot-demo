package com.bonc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "test_orgtype") 
public class OrgType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private java.lang.Long id;
	
	@Column(name="ORGTYPE_NAME", length=255,nullable=false, unique=true)
	private java.lang.String orgtypeName;
	
	public OrgType() {
		super();
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getOrgtypeName() {
		return orgtypeName;
	}
	public void setOrgtypeName(java.lang.String orgtypeName) {
		this.orgtypeName = orgtypeName;
	}
}
