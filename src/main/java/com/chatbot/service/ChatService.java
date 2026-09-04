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
    private final PdfService pdfService;

    public ChatService(ChatMessageRepository repository, AIService aiService, PdfService pdfService) {
        this.repository = repository;
        this.aiService = aiService;
        this.pdfService = pdfService;
    }
    
    ChatMessage chat = new ChatMessage();

    public String processMessage(String message) {

//        String reply = "You said: " + message;
        String pdfText = pdfService.getPdfText();
        String prompt;

        if (pdfText == null || pdfText.isBlank()) {
            prompt = """
                Answer the following question clearly and concisely using your general knowledge.

                Question:
                %s
                """.formatted(message);
        } else {
            prompt = """
                Answer the question clearly and concisely.
                Use the document context when it contains relevant information.
                If the document does not contain the answer, answer using your general knowledge.

                Document context:
                %s

                Question:
                %s
                """.formatted(pdfText, message);
        }

        String reply = aiService.getAIResponse(prompt);

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