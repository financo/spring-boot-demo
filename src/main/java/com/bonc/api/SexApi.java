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
import com.bonc.domain.Sex;
import com.bonc.service.interfac.ISexService;
import com.bonc.util.PageInfoUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping("security/api")  
public class SexApi {  

      @Resource(name="sexService")
      private ISexService sexService;  
        
      /*
       * 查询所有性别对象接口
       */
      @ApiOperation(value="获取全部性别信息", notes="")
      @RequestMapping(value="/sexs",method = RequestMethod.GET)  
      public List<Sex>getAllSexs(){  
          return sexService.findAll();  
      } 
      
      /*
       * 分页查询性别对象接口
       */
      @ApiOperation(value="分页获取性别信息", notes="根据传过来的参数来分页获取性别信息")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "sex", value = "性别信息", required = false, dataType = "Sex"),
              @ApiImplicitParam(name = "pageInfo", value = "页面信息", required = false, dataType = "PageInfo"),
      })
      @RequestMapping(value="/sex",method = RequestMethod.GET)  
      public Page<Sex>getAllSex(Sex sex, PageInfo pageInfo){ 
          Pageable pageable = PageInfoUtil.retirevePageInfo(pageInfo);
          return sexService.findByAuto(sex, pageable);
      } 
        
        
      /*
       * 根据id查询性别对象接口
       */ 
      @ApiOperation(value="获取单个性别信息", notes="根据url的id来获取性别详细信息")
      @ApiImplicitParam(name = "id", value = "性别ID", required = true, dataType = "Long")
      @RequestMapping(value="/sex/{id}", method = RequestMethod.GET)  
      public ResponseEntity<Sex> getSex(@PathVariable("id") Long id){  
          Sex sex = sexService.findOne(id);  
          if(sex == null){  
              return new ResponseEntity<Sex>(HttpStatus.NOT_FOUND);  
          }  
          return new ResponseEntity<Sex>(sex,HttpStatus.OK);  
      }  
       
      /*
       * 根据id更新性别对象接口
       */
      @ApiOperation(value="更新性别信息", notes="根据url的id来指定更新对象，并根据传过来的sex信息来更新性别详细信息")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "id", value = "性别ID", required = true, dataType = "Long"),
              @ApiImplicitParam(name = "sex", value = "角色详细实体sex", required = true, dataType = "Sex")
      })
      @RequestMapping(value="/sex/{id}", method = RequestMethod.PUT)  
      public ResponseEntity<Sex> updateSex(@Valid @RequestBody Sex sex, @PathVariable("id") Long id){  
          Sex sexDb = sexService.findOne(id);  
          if(sexDb == null){  
              return new ResponseEntity<Sex>(HttpStatus.NOT_FOUND);  
          }  
          else{  
              sexDb = sexService.save(sex);  
              return new ResponseEntity<Sex>(sex,HttpStatus.OK);  
          }  
      }  
      
      /*
       * 增加性别对象接口
       */
      @ApiOperation(value="新增性别信息", notes="根据传过来的sex信息添加性别")
      @ApiImplicitParams({
              @ApiImplicitParam(name = "sex", value = "性别详细实体sex", required = true, dataType = "Sex")
      })
      @RequestMapping(value="/sex",method = RequestMethod.POST)  
      public ResponseEntity<Sex> createSex(@Valid @RequestBody Sex sex){ 
    	  sex.setId(Long.MAX_VALUE);
    	  Sex sexDb = sexService.save(sex);  
    	  return new ResponseEntity<Sex>(sex,HttpStatus.OK); 
      }
      
      /*
       * 根据id删除性别对象接口
       */
      @ApiOperation(value="删除性别", notes="根据url的id来指定删除对象")
      @ApiImplicitParam(name = "id", value = "性别ID", required = true, dataType = "Long")
      @RequestMapping(value="/sex/{id}", method = RequestMethod.DELETE)  
      public void deleteSex(@PathVariable("id") Long id) {  
    	  sexService.delete(id);  
      }  
} 