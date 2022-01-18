package com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for running a spring boot application
 */
@ComponentScan(basePackages = "com.project")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.project.repositories")
@EntityScan(basePackages = "com.project.models")
public class Application {
    /**
     * The main method serving as the starting point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * Method for getting the password encoder based on the bcrypt algorithm
     * @return bcrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
