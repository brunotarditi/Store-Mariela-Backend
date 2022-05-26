package com.library.mariela.historicalpurchasesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HistoricalPurchasesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HistoricalPurchasesServiceApplication.class, args);
    }

}
