package com.zjw.seatmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan("com.zjw.seatmanager.mapper")
@ComponentScan("com.zjw.seatmanager")
public class SeatmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatmanagerApplication.class, args);
    }

}
