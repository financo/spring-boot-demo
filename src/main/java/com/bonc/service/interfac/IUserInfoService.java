package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.UserInfo;

public interface IUserInfoService extends IBaseService<UserInfo, java.lang.Long>{
	
	public UserInfo test();
	public UserInfo testCommon();
	public Page<UserInfo> findByCondition(UserInfo userInfo, Pageable pageable);
}
