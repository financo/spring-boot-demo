package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
