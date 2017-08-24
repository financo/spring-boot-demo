package com.bonc.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class MySpecs {
	public static <T> Specification<T> byAuto(final EntityManager em, final T example){
		
		final Class<T> type = (Class<T>) example.getClass();
		
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				EntityType<T> entity = em.getMetamodel().entity(type);
				for(javax.persistence.metamodel.Attribute<T, ?> attr: entity.getDeclaredAttributes()){
					Object attrValue = getValue(example, attr);
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) {
							if (!StringUtils.isEmpty(attrValue)) {
								predicates.add(cb.like(root.get(Attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue)));
							}
						}else {
							predicates.add(cb.equal(root.get(Attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue));
						}
					}
				}
				Predicate[] p = new Predicate[predicates.size()];  
				return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(p));
			}
			
			private <T> Object getValue(T example, javax.persistence.metamodel.Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			private <E, T> SingularAttribute<T, E> Attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) {
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}
		};
	}
	

	
	static private String pattern(String str){
		return "%"+str+"%";
	}
}
