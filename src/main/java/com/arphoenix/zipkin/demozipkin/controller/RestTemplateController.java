package com.arphoenix.zipkin.demozipkin.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arphoenix.zipkin.demozipkin.config.RateLimited;

import io.micrometer.observation.annotation.Observed;

@RestController
@RequestMapping("/v1/rest")
@Observed
public class RestTemplateController {

	private final Logger log = Logger.getLogger(RestTemplateController.class.getName());

	@GetMapping
	public String principalTeste() {
		log.info("principal...");
		return "principal...";
	}
	
	@RateLimited
	@GetMapping(path = "/hello")
	public String helloTeste() {
		log.info("RestTemplateController...");
		return "RestTemplateController";
	}
}