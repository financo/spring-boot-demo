package com.bonc.service.interfac;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBaseService<T,I> {
	
	public T findOne(I id);
	public List<T> findAll();
	public List<T> findAll(List<I> ids);
	public Page<T> findAll(Pageable pageable);
	public List<T> findAll(Sort sort);
	
	public T save(T entity);
	public List<T> save(List<T> entities);
	public T saveAndFlush(T entity);
	
	public void delete(I id);
	public void deleteAll();
	public void delete(List<T> entities);
	
	public long count();
	public boolean exists(I id);
	
}
