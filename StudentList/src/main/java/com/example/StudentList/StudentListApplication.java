package com.example.StudentList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
public class StudentListApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentListApplication.class, args);
    }
}