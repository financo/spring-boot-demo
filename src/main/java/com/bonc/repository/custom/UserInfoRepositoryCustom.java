package com.bonc.repository.custom;

import com.bonc.domain.UserInfo;

public interface UserInfoRepositoryCustom extends BaseRepositoryCustom<UserInfo, java.lang.Long>{
	
	public UserInfo test(Long id);
}
