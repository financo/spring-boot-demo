package com.bonc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bonc.domain.OrgInfo;
import com.bonc.domain.RoleInfo;
import com.bonc.domain.UserInfo;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.UserInfoRepository;
import com.bonc.service.interfac.IUserInfoService;

@Service(value="userInfoService")
public class UserInfoService extends BaseService<UserInfo, java.lang.Long> implements IUserInfoService{
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	protected BaseRepository getCurrentRepository() {
		return this.userInfoRepository;
	}
	
	//******************************条件查询**********************************//
	
	public Page<UserInfo> findByCondition(final String username, final String loginId, final String org, final String role ,Pageable pageable){
		return userInfoRepository.findAll(new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				if (!"".equals(username)) {
					list.add(cb.like((Path)root.get("userName"),"%"+username+"%"));
				}
				if (!"".equals(loginId)) {
					list.add(cb.like((Path)root.get("loginId"),"%"+loginId+"%"));
				}
				if (!"".equals(org)) {
					SetJoin<UserInfo, OrgInfo> join= root.join(root.getModel().getSet("orgs",OrgInfo.class),JoinType.LEFT);
					list.add(cb.like((Path)join.get("orgName"),"%"+org+"%"));
				}
				if (!"".equals(role)) {
					SetJoin<UserInfo, RoleInfo> join= root.join(root.getModel().getSet("roles",RoleInfo.class),JoinType.LEFT);
					list.add(cb.like((Path)join.get("roleName"),"%"+role+"%"));
				}
				Predicate[] p = new Predicate[list.size()];  
                return cb.and(list.toArray(p)); 
			}
		}, pageable);
	}
	
	/*
	 * example匹配查询
	 */
	public Page<UserInfo> findByExample(String username, String loginId, String org, String role ,Pageable pageable){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(username);
		userInfo.setLoginId(loginId);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("userName", GenericPropertyMatchers.contains())
				.withMatcher("loginId", GenericPropertyMatchers.contains());
		Example<UserInfo> example = Example.of(userInfo, matcher);
		return userInfoRepository.findAll(example,pageable);
	}
	
}
