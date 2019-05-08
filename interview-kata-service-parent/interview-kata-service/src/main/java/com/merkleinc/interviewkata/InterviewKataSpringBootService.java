package com.merkleinc.interviewkata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.merkleinc.interviewkata"})
public class InterviewKataSpringBootService {

    public static void main(String[] args) {

        ApplicationContext applicationContext =
                SpringApplication.run(InterviewKataSpringBootService.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}

