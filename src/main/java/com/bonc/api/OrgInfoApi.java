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

import com.bonc.domain.OrgInfo;
import com.bonc.service.interfac.IOrgInfoService;

@RestController  
@RequestMapping("security/api/orgInfo")  
public class OrgInfoApi {  

      @Resource(name="orgInfoService")  
      private IOrgInfoService orgInfoService;  
        
      /*
       * 查询所有组织机构对象接口
       */
      @RequestMapping(value="",method = RequestMethod.GET)  
      public List<OrgInfo>getAllOrgInfos(){  
          return orgInfoService.findAll();  
      }  
      
      /*
       * 分页查询角色对象接口
       */
      @RequestMapping(value="/pageable",method = RequestMethod.GET)  
      public Page<OrgInfo>getAllOrgInfosPageable(
      		@RequestParam(value = "page", defaultValue = "0") Integer page,
      		@RequestParam(value = "size", defaultValue = "10") Integer size
      		){ 
          Sort sort = new Sort(Direction.DESC, "id");
          Pageable pageable = new PageRequest(page, size, sort);
          return orgInfoService.findAll(pageable);
      } 
      
      /*
       * 根据id查找组织机构对象接口
       */  
      @RequestMapping(value="{id}", method = RequestMethod.GET)  
      public ResponseEntity<OrgInfo> findOne(@PathVariable("id") Long id){  
          OrgInfo orgInfo = orgInfoService.findOne(id);  
          if(orgInfo == null){  
              return new ResponseEntity<OrgInfo>(HttpStatus.NOT_FOUND);  
          }  
          return new ResponseEntity<OrgInfo>(orgInfo,HttpStatus.OK);  
      }  
       
      /*
       * 更新组织机构对象接口
       */
      @RequestMapping(value="{id}", method = RequestMethod.PUT)  
      public ResponseEntity<OrgInfo> updateOrgInfo(@Valid @RequestBody OrgInfo orgInfo, @PathVariable("id") Long id){  
          OrgInfo orgInfoDb = orgInfoService.findOne(id);  
          if(orgInfoDb == null){  
              return new ResponseEntity<OrgInfo>(HttpStatus.NOT_FOUND);  
          }  
          else{  
              orgInfoDb.setOrgInfoId(orgInfo.getOrgInfoId());  
              orgInfoDb.setOrgName(orgInfo.getOrgName());  
              orgInfoDb = orgInfoService.save(orgInfoDb);  
              return new ResponseEntity<OrgInfo>(orgInfoDb,HttpStatus.OK);  
          }  
      } 
      
      /*
       * 新增组织机构对象接口
       */
      @RequestMapping(method = RequestMethod.POST)  
      public OrgInfo createOrgInfo(@Valid @RequestBody OrgInfo orgInfo){  
          return orgInfoService.save(orgInfo);  
      } 
       
      /*
       * 删除组织机构对象接口
       */
      @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
      public void deleteOrgInfo(@PathVariable("id") Long id) {  
    	  orgInfoService.delete(id);  
      }  
} 