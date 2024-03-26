package com.arphoenix.zipkin.demozipkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arphoenix.zipkin.demozipkin.service.JiraService;

@RestController
@RequestMapping("/api/projects")
public class JiraController {

	private final JiraService jiraService;

	@Autowired
	public JiraController(JiraService jiraService) {
		this.jiraService = jiraService;
	}

	@GetMapping
	public String listProjects() {
		return jiraService.listProjects();
	}
}