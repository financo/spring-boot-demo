package com.bonc.repository;

import org.springframework.stereotype.Repository;

import com.bonc.domain.OrgInfo;
import com.bonc.repository.custom.OrgInfoRepositoryCustom;

@Repository
public interface OrgInfoRepository extends BaseRepository<OrgInfo, java.lang.Long>, OrgInfoRepositoryCustom{
       
}
