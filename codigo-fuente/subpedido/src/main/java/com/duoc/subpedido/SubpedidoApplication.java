package com.duoc.subpedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubpedidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubpedidoApplication.class, args);
    }
}