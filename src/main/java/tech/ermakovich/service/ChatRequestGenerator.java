package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.ermakovich.model.dto.ChatRequest;
import tech.ermakovich.model.dto.Message;
import tech.ermakovich.utils.Prompts;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatRequestGenerator {
    @Value("${deepseek.model}")
    private String model;

    @Value("${deepseek.user.role}")
    private String userRole;

    @Value("${deepseek.system.role}")
    private String systemRole;

    public ChatRequest generateChatRequest(String userMessage) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel(model);

        Message contextMessage = Message.builder()
                .role(systemRole)
                .content(Prompts.BUSINESS_IDEA_PROMPT)
                .build();
        Message questionMessage = Message.builder()
                .role(userRole)
                .content(String.format(Prompts.NEWS_PROMPT, userMessage))
                .build();

        chatRequest.setMessages(List.of(contextMessage, questionMessage));
        return chatRequest;
    }
}

