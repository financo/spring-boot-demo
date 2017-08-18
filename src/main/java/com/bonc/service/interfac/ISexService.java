package com.bonc.service.interfac;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bonc.domain.Sex;

public interface ISexService extends IBaseService<Sex, java.lang.Long>{

	public Page<Sex> findByExample(Sex sex, Pageable pageable);
}
