package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.NewsService;

import java.util.List;

import static tech.ermakovich.utils.BotCommands.ANALYZE;
import static tech.ermakovich.utils.BotCommands.START;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommandHandler {
    private final NewsService newsService;
    private final MessageSender messageSender;

    public void handleCommand(String command, Long chatId) {
        switch (command) {
            case START:
                handleStart(chatId);
            case ANALYZE:
                handleAnalyze(chatId);
            default:
                handleDefault(chatId, command);
        }
    }

    private void handleAnalyze(Long chatId) {
        Mono<List<String>> latestNews = newsService.getLatestNews("it", 5);
        List<String> news = latestNews.block();

        if (news == null) {
            messageSender.sendMessage(chatId, "новостей нет...");
            return;
        }
        String message = generateFromNews(news);
        messageSender.sendMessage(chatId, message);
    }

    private String generateFromNews(List<String> newsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String news : newsList) {
            stringBuilder.append(news);
        }
        return stringBuilder.toString();
    }

    private void handleStart(Long chatId) {

    }

    private void handleDefault(Long chatId, String command) {
        log.info("CHAT_ID: {} | No supported command: {}", chatId, command);
    }
}
