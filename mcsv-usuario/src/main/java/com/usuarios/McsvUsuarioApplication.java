package com.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.Instant;
import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class McsvUsuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsvUsuarioApplication.class, args);
    }

}
