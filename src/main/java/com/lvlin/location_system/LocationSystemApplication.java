package com.lvlin.location_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lvlin.location_system.dao")
public class LocationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationSystemApplication.class, args);
    }

}
