package com.arphoenix.zipkin.demozipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.arphoenix.zipkin.demozipkin")
public class DemoZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoZipkinApplication.class, args);
	}

}
