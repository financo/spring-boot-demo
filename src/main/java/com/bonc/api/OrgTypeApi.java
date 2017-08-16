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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController  
@RequestMapping("security/api/orgType")  
public class OrgTypeApi {  
  
        @Resource(name="orgTypeService") 
        private IOrgTypeService orgTypeService;  
         
        /*
         * 查询所有组织机构类型类型对象接口
         */
        @ApiOperation(value="获取全部组织机构类型信息", notes="")
        @RequestMapping(value="",method = RequestMethod.GET)  
        public List<OrgType>getAllOrgTypes(){  
            return orgTypeService.findAll();  
        }  
        
        /*
         * 分页查询组织机构类型对象接口
         */
        @ApiOperation(value="分页获取组织机构类型信息", notes="根据传过来的参数来分页获取组织机构类型信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "Integer"),
                @ApiImplicitParam(name = "size", value = "每页条数", required = false, dataType = "Integer"),
        })
        @RequestMapping(value="/page",method = RequestMethod.GET)  
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
        @ApiOperation(value="获取单个组织机构类型信息", notes="根据url的id来获取组织机构类型详细信息")
        @ApiImplicitParam(name = "id", value = "组织机构类型ID", required = true, dataType = "Long")
        @RequestMapping(value="{id}", method = RequestMethod.GET)  
        public ResponseEntity<OrgType> findOne(@PathVariable("id") Long id){  
            OrgType orgType = orgTypeService.findOne(id);  
            if(orgType == null){  
                return new ResponseEntity<OrgType>(HttpStatus.NOT_FOUND);  
            }  
            return new ResponseEntity<OrgType>(orgType,HttpStatus.OK);  
        }  
         
        /*
         * 更新组织机构类型对象接口
         */
        @ApiOperation(value="更新组织机构类型信息", notes="根据url的id来指定更新对象，并根据传过来的orgType信息来更新组织机构类型详细信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "id", value = "组织机构类型ID", required = true, dataType = "Long"),
                @ApiImplicitParam(name = "orgType", value = "组织机构类型详细实体orgType", required = true, dataType = "OrgType")
        })
        @RequestMapping(value="{id}", method = RequestMethod.PUT)  
        public ResponseEntity<OrgType> updateOrgType(@Valid @RequestBody OrgType orgType, @PathVariable("id") Long id){  
            OrgType orgTypeDb = orgTypeService.findOne(id);  
            if(orgTypeDb == null){  
                return new ResponseEntity<OrgType>(HttpStatus.NOT_FOUND);  
            }  
            else{  
                orgTypeDb = orgTypeService.save(orgType);  
                return new ResponseEntity<OrgType>(orgType,HttpStatus.OK);  
            }  
        } 
        
        /*
         * 新增组织机构类型对象接口
         */
        @ApiOperation(value="新增组织机构类型信息", notes="根据传过来的orgInfo信息添加组织机构类型")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "orgType", value = "组织机构类型详细实体orgType", required = true, dataType = "OrgType")
        })
        @RequestMapping(method = RequestMethod.POST)  
        public OrgType createOrgType(@Valid @RequestBody OrgType orgType){ 
        	orgType.setId(Long.MAX_VALUE);
            return orgTypeService.save(orgType);  
        }
         
        /*
         * 删除组织机构类型对象接口
         */
        @ApiOperation(value="删除组织机构类型", notes="根据url的id来指定删除对象")
        @ApiImplicitParam(name = "id", value = "组织机构类型ID", required = true, dataType = "Long")
        @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
        public void deleteOrgType(@PathVariable("id") Long id) {  
        	orgTypeService.delete(id);  
        }  
} 