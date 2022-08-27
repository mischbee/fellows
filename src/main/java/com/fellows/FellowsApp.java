package com.fellows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FellowsApp {
    public static void main(String[] args) {
        try {
            SpringApplication.run(FellowsApp.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
