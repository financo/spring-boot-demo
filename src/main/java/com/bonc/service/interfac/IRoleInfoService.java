package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.RoleInfo;

public interface IRoleInfoService extends IBaseService<RoleInfo, java.lang.Long>{

	public Page<RoleInfo> findByExample(RoleInfo roleInfo, Pageable pageable);
	public Page<RoleInfo> findByAuto(RoleInfo roleInfo, Pageable pageable);
}
