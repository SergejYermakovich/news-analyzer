package tech.ermakovich.service.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.ermakovich.model.dto.ai.ChatRequest;
import tech.ermakovich.model.dto.ai.ChatResponse;
import tech.ermakovich.model.exception.DeepSeekIntegrationException;
import tech.ermakovich.service.ChatRequestGenerator;
import tech.ermakovich.utils.ValidationUtils;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeepSeekWebClient {
    private final WebClient webClient;
    private final ChatRequestGenerator chatRequestGenerator;

    @Value("${deepseek.api.url}")
    private String apiUrl;

    @Value("${deepseek.api.key}")
    private String apiKey;

    public Mono<String> send(Long chatId,
                             String userMessage) {
        LocalTime startTime = LocalTime.now();
        ChatRequest chatRequest = chatRequestGenerator.generateChatRequest(userMessage);
        return webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(chatRequest)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), response -> response.bodyToMono(String.class)
                        .defaultIfEmpty("No error body provided")
                        .map(errorBody -> new DeepSeekIntegrationException("DeepSeek request error: " + response.statusCode() + ", body: " + errorBody
                        ))
                        .flatMap(Mono::error))
                .bodyToMono(ChatResponse.class)
                .doOnSubscribe(sub -> log.info("Request for {} is sent at {}", chatId, LocalTime.now()))
                .map(chatResponse -> {
                    Duration duration = Duration.between(startTime, LocalTime.now());
                    log.info("Request processing time to DeepSeek: {},{} for user with chat id: {}. Number of choices: {}.",
                            duration.toSeconds(),
                            duration.toMillisPart(),
                            chatId,
                            chatResponse.getChoices().size()
                    );

                    ValidationUtils.validateDeepSeekChatResponse(chatResponse);

                    var firstChoice = chatResponse.getChoices().get(0);
                    return firstChoice.getMessage().getContent();
                })
                .onErrorMap(Exception.class, e -> {
                    if (e instanceof DeepSeekIntegrationException) {
                        return e;
                    }
                    return new DeepSeekIntegrationException("Unexpected error during DeepSeek API call", e);
                });
    }
}
