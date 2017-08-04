package com.bonc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
