package com.bonc.repository.custom.impl;

import com.bonc.domain.UserInfo;
import com.bonc.repository.custom.UserInfoRepositoryCustom;

public class UserInfoRepositoryImpl extends BaseRepositoryCustomImpl<UserInfo, java.lang.Long>
		implements UserInfoRepositoryCustom{

	@Override
	public UserInfo test(Long id) {
		return em.find(UserInfo.class, 1L);
	}

}
