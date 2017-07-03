package com.yangxiaochen.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yangxiaochen
 * @date 2017/7/3 16:35
 */
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
