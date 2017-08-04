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

import com.bonc.domain.Sex;
import com.bonc.service.interfac.ISexService;

@RestController  
@RequestMapping("security/api/sex")  
public class SexApi {  

      @Resource(name="sexService")
      private ISexService sexService;  
        
      /*
       * 查询所有性别对象接口
       */
      @RequestMapping(value="",method = RequestMethod.GET)  
      public List<Sex>getAllSex(){  
          return sexService.findAll();  
      } 
      
      /*
       * 分页查询性别对象接口
       */
      @RequestMapping(value="/page",method = RequestMethod.GET)  
      public Page<Sex>getAllSexPageable(
      		@RequestParam(value = "page", defaultValue = "0") Integer page,
      		@RequestParam(value = "size", defaultValue = "10") Integer size
      		){ 
          Sort sort = new Sort(Direction.DESC, "id");
          Pageable pageable = new PageRequest(page, size, sort);
          return sexService.findAll(pageable);
      } 
        
        
      /*
       * 根据id查询性别对象接口
       */ 
      @RequestMapping(value="{id}", method = RequestMethod.GET)  
      public ResponseEntity<Sex> findOne(@PathVariable("id") Long id){  
          Sex sex = sexService.findOne(id);  
          if(sex == null){  
              return new ResponseEntity<Sex>(HttpStatus.NOT_FOUND);  
          }  
          return new ResponseEntity<Sex>(sex,HttpStatus.OK);  
      }  
       
      /*
       * 根据id更新性别对象接口
       */
      @RequestMapping(value="{id}", method = RequestMethod.PUT)  
      public ResponseEntity<Sex> updateSex(@Valid @RequestBody Sex sex, @PathVariable("id") Long id){  
          Sex sexDb = sexService.findOne(id);  
          if(sexDb == null){  
              return new ResponseEntity<Sex>(HttpStatus.NOT_FOUND);  
          }  
          else{  
              sexDb.setSexCode(sex.getSexCode());  
              sexDb.setSexName(sex.getSexName());  
              sexDb = sexService.save(sexDb);  
              return new ResponseEntity<Sex>(sexDb,HttpStatus.OK);  
          }  
      }  
      
      /*
       * 增加性别对象接口
       */
      @RequestMapping(method = RequestMethod.POST)  
      public Sex createSex(@Valid @RequestBody Sex sex){  
          return sexService.save(sex);  
      }
      
      /*
       * 根据id删除性别对象接口
       */
      @RequestMapping(value="{id}", method = RequestMethod.DELETE)  
      public void deleteSex(@PathVariable("id") Long id) {  
    	  sexService.delete(id);  
      }  
} 