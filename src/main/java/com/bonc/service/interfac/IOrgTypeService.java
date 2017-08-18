package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.OrgType;

public interface IOrgTypeService extends IBaseService<OrgType, java.lang.Long>{

	public Page<OrgType> findByExample(OrgType orgType, Pageable pageable);
}
