package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessIdeaService {

    private final AiAnalysisService aiService;
    private final NewsService newsService;

    public String generateIdeasFromNews(String newsText) {
        return aiService.generateBusinessIdeas(newsText);
    }
}