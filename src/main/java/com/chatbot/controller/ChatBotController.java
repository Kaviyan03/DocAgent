package com.chatbot.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatbot.entity.ChatMessage;
import com.chatbot.service.ChatService;
import com.chatbot.service.PdfService;

@RestController
public class ChatBotController {

    private final ChatService chatService;
    private final PdfService pdfService;

    public ChatBotController(ChatService chatService, PdfService pdfService) {
        this.chatService = chatService;
        this.pdfService = pdfService;
    }

    @PostMapping("/upload")
    public Map<String, String> uploadPdf(@RequestParam("file") MultipartFile file) throws Exception {
        pdfService.upload(file);
        return Map.of("message", "PDF uploaded successfully.");
    }

    @DeleteMapping("/upload")
    public Map<String, String> removePdf() {
        pdfService.clear();
        return Map.of("message", "PDF removed successfully.");
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