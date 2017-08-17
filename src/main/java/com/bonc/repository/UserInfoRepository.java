package com.bonc.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bonc.domain.UserInfo;
import com.bonc.repository.custom.UserInfoRepositoryCustom;  
  
@Repository  
public interface UserInfoRepository extends BaseRepository<UserInfo, java.lang.Long>, 
		JpaSpecificationExecutor<UserInfo>, UserInfoRepositoryCustom{ 

}  