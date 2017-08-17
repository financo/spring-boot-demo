package com.bonc.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> 
		implements BaseRepository<T, ID>{
	
	protected final Class<T> domainClass;
	protected EntityManager em;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
		this.em = em;
	}

	public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

	@Override
	public T testCommon(ID i) {
		return (T) em.find(domainClass, i);
	}
}
