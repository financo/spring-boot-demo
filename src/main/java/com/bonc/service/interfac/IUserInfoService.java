package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.RoleInfo;
import com.bonc.domain.UserInfo;

public interface IUserInfoService extends IBaseService<UserInfo, java.lang.Long>{
	
	public Page<UserInfo> findByCondition(final String username, final String loginId, final String org, final String role ,Pageable pageable);
	public Page<UserInfo> findByExample(String username, String loginId, String org, String role ,Pageable pageable);
	public UserInfo test();
	public UserInfo testCommon();
	public Page<UserInfo> findByCondition(UserInfo userInfo, Pageable pageable);
	public Page<UserInfo> findByAuto(UserInfo userInfo, Pageable pageable);
}
