package com.chatbot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(columnDefinition = "TEXT")
    private String userMessage;

	@Column(columnDefinition = "TEXT")
    private String botResponse;
    
    

	public ChatMessage() {
	}

	public ChatMessage(Long id, String userMessage, String botResponse) {
		super();
		this.id = id;
		this.userMessage = userMessage;
		this.botResponse = botResponse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getBotResponse() {
		return botResponse;
	}

	public void setBotResponse(String botResponse) {
		this.botResponse = botResponse;
	}

    
}