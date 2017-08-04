package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
