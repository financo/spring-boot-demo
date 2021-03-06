package com.bonc.api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.bonc.domain.OrgInfo;
import com.bonc.service.interfac.IOrgInfoService;
import com.bonc.util.PageInfoUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping("/api")  
public class OrgInfoApi {  
	
	  private static final Log logger = LogFactory.getLog(OrgInfoApi.class);

      @Resource(name="orgInfoService")  
      private IOrgInfoService orgInfoService;  
        
      /*
       * 查询所有组织机构对象接口
       */
      @ApiOperation(value="获取全部组织机构信息", notes="")
      @RequestMapping(value="/orgInfos",method = RequestMethod.GET)  
      public List<OrgInfo> getAllOrgInfos(){  
          return orgInfoService.findAll();  
      }  
      
      /*
       * 分页查询角色对象接口
       */
      @ApiOperation(value="分页获取组织机构信息", notes="根据传过来的参数来分页获取组织机构信息")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "orgInfo", value = "组织机构信息", required = false, dataType = "OrgInfo"),
              @ApiImplicitParam(name = "pageInfo", value = "页面信息", required = false, dataType = "PageInfo"),
      })
      @RequestMapping(value="/orgInfo",method = RequestMethod.GET)  
      public Page<OrgInfo> getAllOrgInfo(OrgInfo orgInfo, PageInfo pageInfo){ 
          Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
          return orgInfoService.findByAuto(orgInfo, pageable);
      } 
      
      /*
       * 根据id查找组织机构对象接口
       */  
      @ApiOperation(value="获取单个组织机构信息", notes="根据url的id来获取组织机构详细信息")
      @ApiImplicitParam(name = "id", value = "组织机构ID", required = true, dataType = "Long")
      @RequestMapping(value="/orgInfo/{id}", method = RequestMethod.GET)  
      public ResponseEntity<OrgInfo> getOrgInfo(@PathVariable("id") Long id){  
          OrgInfo orgInfo = orgInfoService.findOne(id);  
          if(orgInfo == null){  
              return new ResponseEntity<OrgInfo>(HttpStatus.NOT_FOUND);  
          }  
          return new ResponseEntity<OrgInfo>(orgInfo,HttpStatus.OK);  
      }  
       
      /*
       * 更新组织机构对象接口
       */
      @ApiOperation(value="更新组织机构信息", notes="根据url的id来指定更新对象，并根据传过来的orgInfo信息来更新组织机构详细信息")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "id", value = "组织机构ID", required = true, dataType = "Long"),
              @ApiImplicitParam(name = "orgInfo", value = "组织机构详细实体orgInfo", required = true, dataType = "OrgInfo")
      })
      @RequestMapping(value="/orgInfo/{id}", method = RequestMethod.PUT)  
      public ResponseEntity<OrgInfo> updateOrgInfo(@Valid @RequestBody OrgInfo orgInfo, @PathVariable("id") Long id){  
          OrgInfo orgInfoDb = orgInfoService.findOne(id);  
          if(orgInfoDb == null){  
              return new ResponseEntity<OrgInfo>(HttpStatus.NOT_FOUND);  
          }  
          else{  
              orgInfoDb = orgInfoService.save(orgInfo);  
              return new ResponseEntity<OrgInfo>(orgInfoDb,HttpStatus.OK);  
          }  
      } 
      
      /*
       * 新增组织机构对象接口
       */
      @ApiOperation(value="新增组织机构信息", notes="根据传过来的orgInfo信息添加组织机构")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "orgInfo", value = "组织机构详细实体orgInfo", required = true, dataType = "OrgInfo")
      })
      @RequestMapping(value="/orgInfo", method = RequestMethod.POST)  
      public ResponseEntity<OrgInfo> createOrgInfo(@Valid @RequestBody OrgInfo orgInfo){  
    	  orgInfo.setId(Long.MAX_VALUE);
    	  OrgInfo orgInfoDb = orgInfoService.save(orgInfo); 
          return new ResponseEntity<OrgInfo>(orgInfoDb,HttpStatus.OK);
      } 
       
      /*
       * 删除组织机构对象接口
       */
      @ApiOperation(value="删除组织结构", notes="根据url的id来指定删除对象")
      @ApiImplicitParam(name = "id", value = "组织机构ID", required = true, dataType = "Long")
      @RequestMapping(value="/orgInfo/{id}", method = RequestMethod.DELETE)  
      public void deleteOrgInfo(@PathVariable("id") Long id) {  
    	  orgInfoService.delete(id);  
      }  
      
} 