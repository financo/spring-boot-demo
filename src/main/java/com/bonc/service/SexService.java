package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgType;
import com.bonc.domain.Sex;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.SexRepository;
import com.bonc.service.interfac.ISexService;

@Service(value="sexService")
public class SexService extends BaseService<Sex, java.lang.Long> implements ISexService{
	@Autowired
	SexRepository sexRepository;
	
	@Override
	protected BaseRepository getCurrentRepository() {
		return this.sexRepository;
	}
	
	/*
	 * example匹配查询
	 */
	public Page<Sex> findByExample(Sex sex, Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("sexCode", GenericPropertyMatchers.contains())
				.withMatcher("sexName", GenericPropertyMatchers.contains());
		Example<Sex> example = Example.of(sex, matcher);
		return sexRepository.findAll(example,pageable);
	}

}
