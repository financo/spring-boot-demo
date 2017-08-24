package com.bonc.api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.bean.PageInfo;
import com.bonc.domain.RoleInfo;
import com.bonc.service.interfac.IRoleInfoService;
import com.bonc.util.PageInfoUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping("security/api")  
public class RoleInfoApi {  
  
        @Resource(name="roleInfoService")  
        private IRoleInfoService roleInfoService;  
          
        /*
         * 查询所有角色对象接口
         */
        @ApiOperation(value="获取全部角色信息", notes="")
        @RequestMapping(value="/roleInfos",method = RequestMethod.GET)  
        public List<RoleInfo>getAllRoleInfos(){  
            return roleInfoService.findAll();  
        }  
        
        /*
         * 分页查询角色对象接口
         */
        @ApiOperation(value="分页获取角色信息", notes="根据传过来的参数来分页获取角色信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "roleInfo", value = "角色信息", required = false, dataType = "RoleInfo"),
                @ApiImplicitParam(name = "pageInfo", value = "页面信息", required = false, dataType = "PageInfo"),
        })
        @RequestMapping(value="/roleInfo",method = RequestMethod.GET)  
        public Page<RoleInfo> getAllRoleInfo(RoleInfo roleInfo, PageInfo pageInfo){ 
            Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
            return roleInfoService.findByExample(roleInfo, pageable);
        } 
          
        /*
         * 根据id查询角色对象接口
         */ 
        @ApiOperation(value="获取单个角色信息", notes="根据url的id来获取角色详细信息")
        @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
        @RequestMapping(value="/roleInfo/{id}", method = RequestMethod.GET)  
        public ResponseEntity<RoleInfo> getRoleInfo(@PathVariable("id") Long id){  
            RoleInfo roleInfo = roleInfoService.findOne(id);  
            if(roleInfo == null){  
                return new ResponseEntity<RoleInfo>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<RoleInfo>(roleInfo,HttpStatus.OK);  
        }  
         
        /*
         * 更新角色对象接口
         */
        @ApiOperation(value="更新角色信息", notes="根据url的id来指定更新对象，并根据传过来的roleInfo信息来更新角色详细信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long"),
                @ApiImplicitParam(name = "roleInfo", value = "角色详细实体userInfo", required = true, dataType = "RoleInfo")
        })
        @RequestMapping(value="/roleInfo/{id}", method = RequestMethod.PUT)  
        public ResponseEntity<RoleInfo> updateRoleInfo(@Valid @RequestBody RoleInfo roleInfo, @PathVariable("id") Long id){  
            RoleInfo roleInfoDb = roleInfoService.findOne(id);  
            if(roleInfoDb == null){  
                return new ResponseEntity<RoleInfo>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                roleInfoDb = roleInfoService.save(roleInfo);  
                return new ResponseEntity<RoleInfo>(roleInfoDb,HttpStatus.OK);  
            }  
        }
        
        /*
         * 新增角色对象接口
         */
        @ApiOperation(value="新增角色信息", notes="根据传过来的roleInfo信息添加角色")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "roleInfo", value = "用户详细实体roleInfo", required = true, dataType = "RoleInfo")
        })
        @RequestMapping(value="/roleInfo", method = RequestMethod.POST)  
        public ResponseEntity<RoleInfo> createRoleInfo(@Valid @RequestBody RoleInfo roleInfo){
        	roleInfo.setId(Long.MAX_VALUE);
        	RoleInfo roleInfoDb = roleInfoService.save(roleInfo);
        	return new ResponseEntity<RoleInfo>(roleInfoDb,HttpStatus.OK);
        } 
         
        /*
         * 删除角色对象接口
         */
        @ApiOperation(value="删除角色", notes="根据url的id来指定删除对象")
        @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
        @RequestMapping(value="/roleInfo/{id}", method = RequestMethod.DELETE)  
        public void deleteRoleInfo(@PathVariable("id") Long id) {  
        	roleInfoService.delete(id);  
        }  
        
        /*
         * 测试
         */
        @RequestMapping(value="/testRole", method = RequestMethod.GET)  
        public Page<RoleInfo> deleteRoleInfo(RoleInfo roleInfo, PageInfo pageInfo) { 
        	Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
        	return roleInfoService.findByAuto(roleInfo, pageable);
        } 
} 