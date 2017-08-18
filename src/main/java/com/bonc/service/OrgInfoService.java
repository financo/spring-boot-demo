package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgInfo;
import com.bonc.domain.RoleInfo;
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
