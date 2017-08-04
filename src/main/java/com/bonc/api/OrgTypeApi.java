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

import com.bonc.domain.OrgType;
import com.bonc.service.interfac.IOrgTypeService;


@RestController  
@RequestMapping("security/api/orgType")  
public class OrgTypeApi {  
  
        @Resource(name="orgTypeService") 
        private IOrgTypeService orgTypeService;  
         
        /*
         * 查询所有角色类型对象接口
         */
        @RequestMapping(value="",method = RequestMethod.GET)  
        public List<OrgType>getAllOrgTypes(){  
            return orgTypeService.findAll();  
        }  
        
        /*
         * 分页查询角色对象接口
         */
        @RequestMapping(value="/pageable",method = RequestMethod.GET)  
        public Page<OrgType>getAllOrgTypesPageable(
        		@RequestParam(value = "page", defaultValue = "0") Integer page,
        		@RequestParam(value = "size", defaultValue = "10") Integer size
        		){ 
            Sort sort = new Sort(Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            return orgTypeService.findAll(pageable);
        } 
          
        /*
         * 根据id查询角色类型对象接口
         */  
        @RequestMapping(value="{id}", method = RequestMethod.GET)  
        public ResponseEntity<OrgType> findOne(@PathVariable("id") Long id){  
            OrgType orgType = orgTypeService.findOne(id);  
            if(orgType == null){  
                return new ResponseEntity<OrgType>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<OrgType>(orgType,HttpStatus.OK);  
        }  
         
        /*
         * 更新角色类型对象接口
         */
        @RequestMapping(value="{id}", method = RequestMethod.PUT)  
        public ResponseEntity<OrgType> updateOrgType(@Valid @RequestBody OrgType orgType, @PathVariable("id") Long id){  
            OrgType orgTypeDb = orgTypeService.findOne(id);  
            if(orgTypeDb == null){  
                return new ResponseEntity<OrgType>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                orgTypeDb.setId(orgType.getId());  
                orgTypeDb.setOrgtypeName(orgType.getOrgtypeName());  
                orgTypeDb = orgTypeService.save(orgTypeDb);  
                return new ResponseEntity<OrgType>(orgTypeDb,HttpStatus.OK);  
            }  
        } 
        
        /*
         * 新增角色类型对象接口
         */
        @RequestMapping(method = RequestMethod.POST)  
        public OrgType createOrgType(@Valid @RequestBody OrgType orgType){  
            return orgTypeService.save(orgType);  
        }
         
        /*
         * 删除角色类型对象接口
         */
        @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
        public void deleteOrgType(@PathVariable("id") Long id) {  
        	orgTypeService.delete(id);  
        }  
} 