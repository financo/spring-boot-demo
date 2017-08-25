package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.bonc.domain.RoleInfo;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.RoleInfoRepository;
import com.bonc.service.interfac.IRoleInfoService;

@Service(value="roleInfoService")
public class RoleInfoService extends BaseService<RoleInfo, java.lang.Long> implements IRoleInfoService{
	@Autowired
	RoleInfoRepository roleInfoRepository;

	@Override
	protected BaseRepository getCurrentRepository() {
		return this.roleInfoRepository;
	}
	
	/*
	 * example匹配查询
	 */
	public Page<RoleInfo> findByExample(RoleInfo roleInfo, Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("roleName", GenericPropertyMatchers.contains())
				.withMatcher("roleCode", GenericPropertyMatchers.contains());
		Example<RoleInfo> example = Example.of(roleInfo, matcher);
		return roleInfoRepository.findAll(example,pageable);
	}
	
}
