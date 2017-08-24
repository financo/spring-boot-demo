package com.bonc.repository;

import org.springframework.stereotype.Repository;

import com.bonc.domain.OrgType;
import com.bonc.repository.custom.OrgTypeRepositoryCustom;

@Repository
public interface OrgTypeRepository extends BaseRepository<OrgType, java.lang.Long>, OrgTypeRepositoryCustom{

}
