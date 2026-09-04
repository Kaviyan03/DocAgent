package com.chatbot.service;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class AIService {
//
//    private final RestTemplate restTemplate;
//
//    @Value("${gemini.api.key}")
//    private String apiKey;
//
//    public AIService(RestTemplate restTemplate) {
//    	this.restTemplate = restTemplate;
//    }
//    
//    public String getAIResponse(String message) {
//    	
//    	System.out.println(apiKey);
//
//        return "Gemini Integration Pending: " + message;
//    }
//}

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

    private final RestTemplate restTemplate;

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String ollamaModel;

    public AIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAIResponse(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "model", ollamaModel,
                "prompt", message,
            "stream", false,
            "think", false,
            "options", Map.of("num_predict", 256));

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        Map<?, ?> response =
                restTemplate.postForObject(
                        ollamaUrl,
                        request,
                        Map.class);

        return response == null ? "Ollama returned no response." : String.valueOf(response.get("response"));
    }
}