package com.bonc.api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.domain.UserInfo;
import com.bonc.service.UserInfoService;  
  
  
@RestController  
@RequestMapping("security/api/userInfo")  
public class UserInfoApi {  
  
        @Resource(name="userInfoService")  
        private UserInfoService userInfoService;
        
        /*
         * 查询所有用户信息
         */  
        @RequestMapping(value="",method = RequestMethod.GET)  
        public List<UserInfo>getAllUserInfos(){  
            return userInfoService.findAll();  
        }  
        
        /*
         * 获取pathVariable参数id
         * 查询id的信息
         */
        @RequestMapping(value="{id}", method = RequestMethod.GET)  
        public ResponseEntity<UserInfo> findOne(@PathVariable("id") Long id){  
            UserInfo userInfo = userInfoService.findOne(id);  
            if(userInfo == null){  
                return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);  
        }
        
        /*
         *  根据username loginId org role模糊查询并分页
         */
        @RequestMapping(value="/page",method = RequestMethod.GET)  
        public Page<UserInfo> findUserInfosfindByCondition(
        		@RequestParam(value = "userName", defaultValue = "") String username,
        		@RequestParam(value = "loginId", defaultValue = "") String loginId,
        		@RequestParam(value = "org", defaultValue = "") String org,
        		@RequestParam(value = "role", defaultValue = "") String role,
        		@RequestParam(value = "page", defaultValue = "0") Integer page,
        		@RequestParam(value = "size", defaultValue = "10") Integer size
        		){ 
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            return userInfoService.findByCondition(username, loginId, org, role, pageable);
        } 
        
        /*
         * 简单分页查询
         */
        @RequestMapping(value="/pageable",method = RequestMethod.GET)  
        public Page<UserInfo>getUserInfosByUsernamePageable(
        		@RequestParam(value = "page", defaultValue = "0") Integer page,
        		@RequestParam(value = "size", defaultValue = "10") Integer size
        		){ 
        	//int page=1,size=10;
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            return userInfoService.findAll(pageable);
        }
        
        /*
         * 通过 requestBody获取用户信息
         * 在数据库中添加一条用户信息
         */  
        @RequestMapping(method = RequestMethod.POST)  
        public UserInfo createUserInfo(@Valid @RequestBody UserInfo userInfo){  
            return userInfoService.save(userInfo);  
        } 
        
        /*
         * 通过  pathVariable获取用户id
         * 通过 requestBody获取用户信息
         * 更新id的用户信息
         */
        @RequestMapping(value="{id}", method = RequestMethod.PUT)  
        public ResponseEntity<UserInfo> updateUserInfo(@Valid @RequestBody UserInfo userInfo, @PathVariable("id") Long id){  
            UserInfo userInfoDb = userInfoService.findOne(id);  
            if(userInfoDb == null){  
                return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                userInfoDb.setLoginId(userInfo.getLoginId());  
                userInfoDb.setUserName(userInfo.getUserName());  
                userInfoDb = userInfoService.save(userInfoDb);  
                return new ResponseEntity<UserInfo>(userInfoDb,HttpStatus.OK);  
            }  
        }  
        
        /*
         * 通过  pathVariable获取用户id
         * 删除id的用户
         */ 
        @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
        public void deleteUserInfo(@PathVariable("id") Long id) {  
            userInfoService.delete(id);  
        }  
        
        /*
         * 通过  pathVariable获取用户id
         * 删除id的用户
         */ 
        @RequestMapping(value="/test", method = RequestMethod.GET)  
        public void test() { 
        	int page=1,size=10;
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            userInfoService.findByExample("wang", "", "", "", pageable);  
        } 
} 