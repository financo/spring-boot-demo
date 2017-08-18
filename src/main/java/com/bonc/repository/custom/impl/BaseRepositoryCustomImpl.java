package com.bonc.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bonc.repository.custom.BaseRepositoryCustom;

public class BaseRepositoryCustomImpl<T, I> implements BaseRepositoryCustom<T, I> {
	
	@PersistenceContext
	protected EntityManager em;
}
