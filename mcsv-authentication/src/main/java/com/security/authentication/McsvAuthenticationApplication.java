package com.security.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class McsvAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsvAuthenticationApplication.class, args);
    }

}
