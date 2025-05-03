package com.example.chat_app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class SpringSwingApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringSwingApplication.class).headless(false).
                web(WebApplicationType.NONE).run();
    }
}
