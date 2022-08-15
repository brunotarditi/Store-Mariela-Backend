package com.library.mariela.orderdetailservice.orderdetailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderDetailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDetailServiceApplication.class, args);
    }

}
