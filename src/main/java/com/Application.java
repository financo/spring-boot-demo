package com;

import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bonc.repository.BaseRepositoryFactoryBean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableTransactionManagement
@SpringBootApplication
//@EnableSwagger2
public class Application {  
    public static void main(String[] args){  
        SpringApplication.run(Application.class, args);  
    }  
}  