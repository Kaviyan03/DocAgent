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

    @Value("${gemini.api.key}")
    private String apiKey;

    public AIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAIResponse(String message) {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="
                        + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "%s"
                }
              ]
            }
          ]
        }
        """.formatted(message);

        HttpEntity<String> request =
                new HttpEntity<>(body, headers);

        String response =
                restTemplate.postForObject(
                        url,
                        request,
                        String.class);

        return response;
    }
}