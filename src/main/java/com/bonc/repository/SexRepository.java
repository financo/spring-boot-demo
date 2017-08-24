package com.bonc.repository;

import org.springframework.stereotype.Repository;

import com.bonc.domain.Sex;
import com.bonc.repository.custom.SexRepositoryCustom;

@Repository
public interface SexRepository extends BaseRepository<Sex, java.lang.Long>, SexRepositoryCustom{
	
}
