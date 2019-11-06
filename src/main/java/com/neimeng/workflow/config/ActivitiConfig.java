
package com.neimeng.workflow.config;

import com.neimeng.workflow.diagram.ICustomProcessDiagramGenerator;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Activiti配置
 */
@Configuration
// @AutoConfigureAfter({DataSourceConfiguration.class })
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    private DataSource dataSource;

    @Resource
    private PlatformTransactionManager transactionManager;

    // 生成流程图接口
    @Autowired
    private ICustomProcessDiagramGenerator customProcessDiagramGenerator;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            SpringAsyncExecutor springAsyncExecutor) throws IOException {

        //注入数据源和事务管理器
        SpringProcessEngineConfiguration springProcessEngineConfiguration = this
                .baseSpringProcessEngineConfiguration(dataSource, transactionManager,
                        springAsyncExecutor);

        //close job executor
        springProcessEngineConfiguration.setAsyncExecutorActivate(false);

        //自定义流程图样式
        springProcessEngineConfiguration.setProcessDiagramGenerator(customProcessDiagramGenerator);

        return springProcessEngineConfiguration;
    }

}