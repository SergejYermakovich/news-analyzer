package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.MessageSender;

import static tech.ermakovich.utils.BotCommands.ANALYZE;
import static tech.ermakovich.utils.BotCommands.START;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommandHandler {
    private final AnswerGenerator answerGenerator;
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
        String message = answerGenerator.generate(chatId);
        messageSender.sendMessage(chatId, message);
    }

    private void handleStart(Long chatId) {

    }

    private void handleDefault(Long chatId, String command) {
        log.info("CHAT_ID: {} | No supported command: {}", chatId, command);
    }
}
