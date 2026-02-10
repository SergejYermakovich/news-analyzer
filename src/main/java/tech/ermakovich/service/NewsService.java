package tech.ermakovich.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.ermakovich.model.dto.Article;
import tech.ermakovich.model.dto.NewsApiResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    @Value("${news.api.key}")
    private String newsApiKey;

    @Value("${news.api.url:https://newsapi.org/v2/everything}")
    private String newsApiUrl;

    private final WebClient webClient;

    public Mono<List<String>> getLatestNews(String topic, int count) {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            return Mono.just(List.of());
        }

        String fromDate = LocalDate.now().minusDays(7)
                .format(DateTimeFormatter.ISO_DATE);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/everything")
                        .queryParam("q", topic)
                        .queryParam("from", fromDate)
                        .queryParam("sortBy", "publishedAt")
                        .queryParam("pageSize", count)
                        .queryParam("apiKey", newsApiKey)
                        .queryParam("language", "ru")
                        .build())
                .retrieve()
                .bodyToMono(NewsApiResponse.class)
                .map(response -> response.getArticles().stream()
                        .map(this::mapArticle)
                        .limit(count)
                        .toList())
                .onErrorResume(e -> {
                    log.warn("Ошибка получения новостей, возвращаем пустой список", e);
                    return Mono.just(List.of());
                });
    }

    private String mapArticle(Article article) {
        return "Title: " + article.getTitle() + "\n" +
                "Description: " + (article.getDescription() != null ? article.getDescription() : "") + "\n";
    }
}
