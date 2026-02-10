package tech.ermakovich.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiAnalysisService {

    private final DeepSeekWebClient deepSeekWebClient;

    public Mono<String> generateBusinessIdeas(Long chatId, String newsText) {
        return  deepSeekWebClient.send(chatId, newsText);
    }
}