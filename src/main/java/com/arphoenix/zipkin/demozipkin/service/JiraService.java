package com.arphoenix.zipkin.demozipkin.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JiraService {

    @Value("${jira.baseUrl}")
    private String jiraBaseUrl;

    @Value("${jira.username}")
    private String jiraUsername;

    @Value("${jira.apiToken}")
    private String jiraApiToken;

    public String listProjects() {
        String url = jiraBaseUrl + "/rest/api/latest/project";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(jiraUsername, jiraApiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, 
                new HttpEntity<>(headers), String.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Erro ao listar projetos do Jira: " + response.getStatusCode());
        }
    }
}
