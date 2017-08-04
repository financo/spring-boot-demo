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

import com.bonc.domain.RoleInfo;
import com.bonc.service.interfac.IRoleInfoService;

@RestController  
@RequestMapping("security/api/roleInfo")  
public class RoleInfoApi {  
  
        @Resource(name="roleInfoService")  
        private IRoleInfoService roleInfoService;  
          
        /*
         * 查询所有角色对象接口
         */
        @RequestMapping(value="",method = RequestMethod.GET)  
        public List<RoleInfo>getAllRoleInfos(){  
            return roleInfoService.findAll();  
        }  
        
        /*
         * 分页查询角色对象接口
         */
        @RequestMapping(value="/pageable",method = RequestMethod.GET)  
        public Page<RoleInfo>getAllUserInfosPageable(
        		@RequestParam(value = "page", defaultValue = "0") Integer page,
        		@RequestParam(value = "size", defaultValue = "10") Integer size
        		){ 
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            return roleInfoService.findAll(pageable);
        } 
          
        /*
         * 根据id查询角色对象接口
         */ 
        @RequestMapping(value="{id}", method = RequestMethod.GET)  
        public ResponseEntity<RoleInfo> findOne(@PathVariable("id") Long id){  
            RoleInfo roleInfo = roleInfoService.findOne(id);  
            if(roleInfo == null){  
                return new ResponseEntity<RoleInfo>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<RoleInfo>(roleInfo,HttpStatus.OK);  
        }  
         
        /*
         * 更新角色对象接口
         */
        @RequestMapping(value="{id}", method = RequestMethod.PUT)  
        public ResponseEntity<RoleInfo> updateRoleInfo(@Valid @RequestBody RoleInfo roleInfo, @PathVariable("id") Long id){  
            RoleInfo roleInfoDb = roleInfoService.findOne(id);  
            if(roleInfoDb == null){  
                return new ResponseEntity<RoleInfo>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                roleInfoDb.setRoleId(roleInfo.getRoleId());  
                roleInfoDb.setRoleName(roleInfo.getRoleName());  
                roleInfoDb = roleInfoService.save(roleInfoDb);  
                return new ResponseEntity<RoleInfo>(roleInfoDb,HttpStatus.OK);  
            }  
        }
        
        /*
         * 新增角色对象接口
         */
        @RequestMapping(method = RequestMethod.POST)  
        public RoleInfo createRoleInfo(@Valid @RequestBody RoleInfo roleInfo){  
            return roleInfoService.save(roleInfo);  
        } 
         
        /*
         * 删除角色对象接口
         */
        @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
        public void deleteRoleInfo(@PathVariable("id") Long id) {  
        	roleInfoService.delete(id);  
        }  
} 