package com.bonc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
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

import com.bonc.bean.PageInfo;
import com.bonc.domain.OrgInfo;
import com.bonc.domain.RoleInfo;
import com.bonc.domain.UserInfo;
import com.bonc.repository.BaseRepository;
import com.bonc.repository.UserInfoRepository;
import com.bonc.service.interfac.IUserInfoService;
import com.bonc.util.PageInfoUtil;

@Service(value="userInfoService")
public class UserInfoService extends BaseService<UserInfo, java.lang.Long> implements IUserInfoService{
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	protected BaseRepository getCurrentRepository() {
		return this.userInfoRepository;
	}
	
	public UserInfo test(){
		return userInfoRepository.test(1L);
	}

	@Override
	public Page<UserInfo> findByCondition(final UserInfo userInfo, Pageable pageable) {
		return userInfoRepository.findAll(new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				if (null != userInfo.getUserName()) {
					list.add(cb.like((Path)root.get("userName"),"%"+userInfo.getUserName()+"%"));
				}
				if (null != userInfo.getLoginId()) {
					list.add(cb.like((Path)root.get("loginId"),"%"+userInfo.getLoginId()+"%"));
				}
				if (userInfo.getRoles().size()>0 && !"".equals(userInfo.getRoles().get(0).getRoleName())) {
					ListJoin<UserInfo, RoleInfo> join= root.join(root.getModel().getList("roles",RoleInfo.class),JoinType.LEFT);
					list.add(cb.like((Path)join.get("roleName"),"%"+userInfo.getRoles().get(0).getRoleName()+"%"));
				}
				if (userInfo.getOrgs().size()>0 && !"".equals(userInfo.getOrgs().get(0).getOrgName())) {
					ListJoin<UserInfo, OrgInfo> join= root.join(root.getModel().getList("orgs",OrgInfo.class),JoinType.LEFT);
					list.add(cb.like((Path)join.get("orgName"),"%"+userInfo.getOrgs().get(0).getOrgName()+"%"));
				}
				Predicate[] p = new Predicate[list.size()];  
                return cb.and(list.toArray(p)); 
			}
		}, pageable);
	}

}
