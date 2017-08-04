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
@Table(name = "test_sex")
@JsonIgnoreProperties(value={"userInfos"})
public class Sex implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private java.lang.Long id;
	@Column(name="SEX_CODE", length=20, nullable=true)
	private java.lang.Long sexCode;
	@Column(name="SEX_NAME", length=255, nullable=false)
	private java.lang.String sexName;
	
/*    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "sex")  
    private List<UserInfo> userInfos = new ArrayList<UserInfo>();  
  
	
	public List<UserInfo> getUserInfo() {
		return userInfos;
	}
	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfos = userInfo;
	}*/
	
	public Sex() {
		super();
	}
	public java.lang.Long getSexCode() {
		return sexCode;
	}
	public void setSexCode(java.lang.Long sexCode) {
		this.sexCode = sexCode;
	}
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getSexName() {
		return sexName;
	}
	public void setSexName(java.lang.String sexName) {
		this.sexName = sexName;
	}
}
