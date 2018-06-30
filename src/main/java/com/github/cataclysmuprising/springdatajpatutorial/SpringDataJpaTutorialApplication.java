package com.github.cataclysmuprising.springdatajpatutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaTutorialApplication {

	public static void main(String[] args) {
		System.setProperty("log4j.shutdownHookEnabled", Boolean.toString(false));
		SpringApplication.run(SpringDataJpaTutorialApplication.class, args);
	}
}
