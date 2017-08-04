package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgInfo;
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
}
