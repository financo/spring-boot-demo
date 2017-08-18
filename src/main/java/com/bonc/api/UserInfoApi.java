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

import com.bonc.bean.PageInfo;
import com.bonc.domain.UserInfo;
import com.bonc.service.interfac.IUserInfoService;
import com.bonc.util.PageInfoUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;  
  
  
@RestController  
@RequestMapping("security/api/userInfo")  
public class UserInfoApi {  
  
        @Resource(name="userInfoService")  
        private IUserInfoService userInfoService;
        
        /*
         * 查询所有用户信息
         */  
        @ApiOperation(value="获取全部用户信息", notes="")
        @RequestMapping(value="",method = RequestMethod.GET)  
        public List<UserInfo>getAllUserInfos(){  
            return userInfoService.findAll();  
        }  
        
        /*
         * 获取pathVariable参数id
         * 查询id的信息
         */
        @ApiOperation(value="获取单个用户信息", notes="根据url的id来获取用户详细信息")
        @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
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
        @ApiOperation(value="分页获取用户信息", notes="根据传过来的参数来分页获取用户信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String"),
                @ApiImplicitParam(name = "loginId", value = "用户登陆名", required = false, dataType = "String"),
                @ApiImplicitParam(name = "org", value = "组织机构", required = false, dataType = "String"),
                @ApiImplicitParam(name = "role", value = "角色", required = false, dataType = "String"),
                @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "Integer"),
                @ApiImplicitParam(name = "size", value = "每页条数", required = false, dataType = "Integer"),
        })
        @RequestMapping(value="/page",method = RequestMethod.GET)  
        public Page<UserInfo> findUserInfosfindByCondition(UserInfo userInfo, PageInfo pageInfo,
        		@RequestParam(value="orgName", defaultValue="") String orgName,
        		@RequestParam(value="roleName", defaultValue="") String roleName ){
            Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
            return userInfoService.findByCondition(userInfo, pageable, orgName, roleName);
//            return userInfoService.findByCondition("", "", orgName, roleName, pageable);
        } 
        
        /*
         * 通过 requestBody获取用户信息
         * 在数据库中添加一条用户信息
         */  
        @ApiOperation(value="新增用户信息", notes="根据传过来的userInfo信息添加用户")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "userInfo", value = "用户详细实体userInfo", required = true, dataType = "UserInfo")
        })
        @RequestMapping(method = RequestMethod.POST)  
        public UserInfo createUserInfo(@Valid @RequestBody UserInfo userInfo){ 
        	userInfo.setId(Long.MAX_VALUE);
            return userInfoService.save(userInfo);  
        } 
        
        /*
         * 通过  pathVariable获取用户id
         * 通过 requestBody获取用户信息
         * 更新id的用户信息
         */
        @ApiOperation(value="更新用户信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
                @ApiImplicitParam(name = "userInfo", value = "用户详细实体userInfo", required = true, dataType = "UserInfo")
        })
        @RequestMapping(value="{id}", method = RequestMethod.PUT)  
        public ResponseEntity<UserInfo> updateUserInfo(@Valid @RequestBody UserInfo userInfo, @PathVariable("id") Long id){  
            UserInfo userInfoDb = userInfoService.findOne(id);  
            if(userInfoDb == null){  
                return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                userInfoDb = userInfoService.save(userInfo);  
                return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);  
            }  
        }  
        
        /*
         * 通过  pathVariable获取用户id
         * 删除id的用户
         */ 
        @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
        @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
        @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
        public void deleteUserInfo(@PathVariable("id") Long id) {  
            userInfoService.delete(id);  
        }  
        
        /*
         * test1
         */ 
        @RequestMapping(value="/test", method = RequestMethod.GET)  
        public void test() { 
        	int page=1,size=10;
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            userInfoService.findByExample("wang", "", "", "", pageable);  
        } 
        /*
         * test2
         */ 
        @RequestMapping(value="/test2", method = RequestMethod.GET)  
        public UserInfo test2() { 
            return userInfoService.test();
        }
        
        /*
         * test3
         */ 
        @RequestMapping(value="/test3", method = RequestMethod.GET)  
        public UserInfo test3() { 
            UserInfo uInfo = userInfoService.testCommon();
            return uInfo;
        }
        
        
} 