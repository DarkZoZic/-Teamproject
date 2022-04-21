package com.example.project_20220420;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = { 
	"com.example.config", 
	"com.example.service", 
})

// 엔티티
@EntityScan(basePackages = {"com.example.entity.entity1", "com.example.entity.entity2"})

// 저장소
@EnableJpaRepositories(basePackages = {"com.example.repository.repository_1"})

public class Project20220420Application {
	public static void main(String[] args) {
		SpringApplication.run(Project20220420Application.class, args);
		System.out.println("====success===");
	}

}
