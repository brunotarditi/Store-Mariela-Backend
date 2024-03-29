package com.library.stockcontrolservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockControlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockControlServiceApplication.class, args);
    }

}
