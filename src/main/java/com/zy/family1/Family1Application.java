package com.zy.family1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.zy.family1.mapper")
public class Family1Application {

    public static void main(String[] args) {
        SpringApplication.run(Family1Application.class, args);
    }

}
