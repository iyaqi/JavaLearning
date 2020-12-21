package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 * @author Evan
 */
@Configuration
@ComponentScan("com.example")
public class Bootstrap {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Bootstrap.class);
    }
}
