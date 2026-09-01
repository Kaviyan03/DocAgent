package com.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatbot.entity.ChatMessage;

@Repository
public interface ChatMessageRepository
        extends JpaRepository<ChatMessage, Long> {
}
