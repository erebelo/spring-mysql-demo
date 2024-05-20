package com.erebelo.springmysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringMysqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMysqlDemoApplication.class, args);
    }
}
