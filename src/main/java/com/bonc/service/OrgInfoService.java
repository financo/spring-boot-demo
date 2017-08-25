package com.bonc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgInfo;
import com.bonc.domain.OrgType;
import com.bonc.domain.RoleInfo;
import com.bonc.domain.Sex;
import com.bonc.domain.UserInfo;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.OrgInfoRepository;
import com.bonc.service.interfac.IOrgInfoService;

@Service(value="orgInfoService")
public class OrgInfoService extends BaseService<OrgInfo, java.lang.Long> implements IOrgInfoService{
	@Autowired
	OrgInfoRepository orgInfoRepository;
	
	@Override
	protected BaseRepository getCurrentRepository() {
		return this.orgInfoRepository;
	}
	
	/*
	 * example匹配查询
	 */
	public Page<OrgInfo> findByExample(OrgInfo orgInfo, Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("orgName", GenericPropertyMatchers.contains())
				.withMatcher("orgType.orgtypeName", GenericPropertyMatchers.contains())
				.withMatcher("orgCode", GenericPropertyMatchers.contains());
		Example<OrgInfo> example = Example.of(orgInfo, matcher);
		return orgInfoRepository.findAll(example,pageable);
	}

}
