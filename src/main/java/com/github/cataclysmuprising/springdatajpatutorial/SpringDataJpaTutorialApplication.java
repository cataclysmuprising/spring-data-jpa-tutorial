package com.github.cataclysmuprising.springdatajpatutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringDataJpaTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaTutorialApplication.class, args);
    }
}
