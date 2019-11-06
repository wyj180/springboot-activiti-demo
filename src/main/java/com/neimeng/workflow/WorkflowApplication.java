package com.neimeng.workflow;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// activiti6.0和spring boot 2.* 整合说明
// exclude = SecurityAutoConfiguration.class 配置是为了activiti6.0和spring boot 2.*整合，否则会报错
// Caused by: java.lang.ArrayStoreException: sun.reflect.annotation.TypeNotPresentExceptionProxy

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.neimeng.workflow.dao")
public class WorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }
}
