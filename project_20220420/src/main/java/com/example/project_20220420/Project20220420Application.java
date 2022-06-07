package com.example.project_20220420;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication

@ComponentScan(basePackages = { 
	"com.example.config", 
	"com.example.controller",
	"com.example.controller.controller_1",
	"com.example.controller.controller_2",
	"com.example.controller.controller_3",
	"com.example.controller.controller_4",
	"com.example.service", 
	"com.example.service.service_1",
	"com.example.service.service_2",
	"com.example.service.service_3",
	"com.example.service.service_4", 
	"com.example.jwt", 

})

// 엔티티
@EntityScan(basePackages = {"com.example.entity.entity1", "com.example.entity.entity2"})

// 저장소
@EnableJpaRepositories(basePackages = {
	"com.example.repository",
	"com.example.repository.repository_1",
	"com.example.repository.repository_gibum",
	"com.example.repository.repository_3",
	"com.example.repository.repository_4",
})

// 정의 변수 설정
@PropertySource("classpath:global.properties")

public class Project20220420Application extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(Project20220420Application.class, args);
		System.out.println("====success===");
	}
	@Override
	protected SpringApplicationBuilder 
		configure(SpringApplicationBuilder builder) {
		return builder.sources(Project20220420Application.class);
	}

}

