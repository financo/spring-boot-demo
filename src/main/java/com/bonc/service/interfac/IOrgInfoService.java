package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.OrgInfo;

public interface IOrgInfoService extends IBaseService<OrgInfo, java.lang.Long>{

	public Page<OrgInfo> findByExample(OrgInfo orgInfo, Pageable pageable);
}
