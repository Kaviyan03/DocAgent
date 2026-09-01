package com.chatbot.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.entity.ChatMessage;
import com.chatbot.service.ChatService;

@RestController
public class ChatBotController {

    private final ChatService chatService;

    public ChatBotController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public Map<String, String> getChat(
            @RequestParam String message) {

        String reply = chatService.processMessage(message);

        return Map.of("reply", reply);
    }
    
    @GetMapping("/history")
    public List<ChatMessage> getHistory(){
    	return chatService.getHistory();
    }
    
    @GetMapping("/chat/{Id}")
    public Optional<ChatMessage> getById(@PathVariable long Id){
    	return chatService.getById(Id);
    }
    
    
    
    
    
    
    
}