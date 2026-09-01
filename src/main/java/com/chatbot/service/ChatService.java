package com.chatbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chatbot.entity.ChatMessage;
import com.chatbot.repository.ChatMessageRepository;

@Service
public class ChatService {

    private final ChatMessageRepository repository;
    
    private final AIService aiService;

    public ChatService(ChatMessageRepository repository, AIService aiService) {
        this.repository = repository;
        this.aiService = aiService;
    }
    
    ChatMessage chat = new ChatMessage();

    public String processMessage(String message) {

//        String reply = "You said: " + message;
        
        String reply = aiService.getAIResponse(message);

        chat.setUserMessage(message);
        chat.setBotResponse(reply);

        repository.save(chat);

        return reply;
    }
    
    public List<ChatMessage> getHistory(){
    	return repository.findAll();
    }
    
    public Optional<ChatMessage> getById(long Id) {
    	if(repository.findById(Id).isPresent()) {
    		return repository.findById(Id);
    	}
    	return Optional.empty();
    }
    
    
    
    
    
    
}