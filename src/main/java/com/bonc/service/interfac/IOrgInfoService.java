package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.OrgInfo;
import com.bonc.domain.RoleInfo;

public interface IOrgInfoService extends IBaseService<OrgInfo, java.lang.Long>{

	public Page<OrgInfo> findByExample(OrgInfo orgInfo, Pageable pageable);
	public Page<OrgInfo> findByAuto(OrgInfo orgInfo, Pageable pageable);
	public Page<OrgInfo> findSpecification(OrgInfo orgInfo, Pageable pageable);
}
