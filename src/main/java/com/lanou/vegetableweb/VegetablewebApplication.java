package com.lanou.vegetableweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class VegetablewebApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetablewebApplication.class, args);
    }

}
