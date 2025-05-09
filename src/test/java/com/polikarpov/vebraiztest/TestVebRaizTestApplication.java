package com.polikarpov.vebraiztest;

import org.springframework.boot.SpringApplication;

public class TestVebRaizTestApplication {

    public static void main(String[] args) {
        SpringApplication.from(VebRaizTestApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
