package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgType;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.OrgTypeRepository;
import com.bonc.service.interfac.IOrgTypeService;

@Service(value="orgTypeService")
public class OrgTypeService extends BaseService<OrgType, java.lang.Long> implements IOrgTypeService{
	@Autowired
	OrgTypeRepository orgTypeRepository;

	@Override
	protected BaseRepository getCurrentRepository() {
		return this.orgTypeRepository;
	}
	
	/*
	 * example匹配查询
	 */
	public Page<OrgType> findByExample(OrgType orgType, Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("orgtypeName", GenericPropertyMatchers.contains());
		Example<OrgType> example = Example.of(orgType, matcher);
		return orgTypeRepository.findAll(example,pageable);
	}
}
