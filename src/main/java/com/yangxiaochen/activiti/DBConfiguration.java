package com.yangxiaochen.activiti;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yangxiaochen
 * @date 2017/7/3 20:03
 */
@Configuration
public class DBConfiguration {
    @Bean
    public DataSource dataSource() {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/activiti2");
        dataSource.setUser("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;

    }

}
