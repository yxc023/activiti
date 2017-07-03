package com.yangxiaochen.activiti;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yangxiaochen
 * @date 2017/7/3 16:38
 */
@Configuration
@Import(DBConfiguration.class)
public class ActivitiConfiguration {


    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSourceTransactionManager transactionManager, DataSource dataSource) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("");
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return processEngineConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngine = new ProcessEngineFactoryBean();
        processEngine.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngine;
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return repositoryService;
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        return runtimeService;
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        TaskService taskService = processEngine.getTaskService();
        return taskService;
    }

    @Bean(name = "listenerService")
    public ListenerService listenerService() {
        return new ListenerService();
    }
}
