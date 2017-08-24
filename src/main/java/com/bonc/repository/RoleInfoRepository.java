package com.bonc.repository;

import org.springframework.stereotype.Repository;

import com.bonc.domain.RoleInfo;
import com.bonc.repository.custom.RoleInfoRepositoryCustom;

@Repository
public interface RoleInfoRepository extends BaseRepository<RoleInfo, java.lang.Long>, RoleInfoRepositoryCustom{

}
