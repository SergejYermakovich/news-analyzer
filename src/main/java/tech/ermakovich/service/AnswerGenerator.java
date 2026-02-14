package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerGenerator {
    private final NewsService newsService;
    private final AiAnalysisService analysisService;

    public String generate(Long chatId, String topic, int newsQuantity) {
        Mono<List<String>> latestNews = newsService.getLatestNews(topic, newsQuantity);
        List<String> newsList = latestNews.block();

        if (newsList == null) {
            return "lalalala";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String news : newsList) {
            stringBuilder.append(news);
        }
        String allNews = stringBuilder.toString();

        log.info("|CHAT_ID: {} | All news are set.", chatId);

        return analysisService.generateBusinessIdeas(chatId, allNews).block();
    }
}
