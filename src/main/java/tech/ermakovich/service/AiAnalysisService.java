package tech.ermakovich.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import static tech.ermakovich.utils.Prompts.BUSINESS_IDEA_PROMPT;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiAnalysisService {

    private final ChatClient chatClient;

    public String generateBusinessIdeas(String newsText) {
        String fullPrompt = BUSINESS_IDEA_PROMPT + "\n\n" + newsText;

        return chatClient.prompt()
                .user(fullPrompt)
                .advisors(advisor -> advisor.param("temperature", 0.7))
                .call()
                .content();
    }
}