package com.springsecuritymysql.springsecuritymysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.springsecuritymysql.springsecuritymysql.model") // Adjust the package name
@EnableJpaRepositories(basePackages = "com.springsecuritymysql.springsecuritymysql.repository")
public class SpringsecuritymysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritymysqlApplication.class, args);
    }
}
