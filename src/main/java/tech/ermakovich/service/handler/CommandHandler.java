package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.KeyboardService;
import tech.ermakovich.service.MessageSender;

import static tech.ermakovich.utils.BotCommands.ANALYZE;
import static tech.ermakovich.utils.BotCommands.START;
import static tech.ermakovich.utils.ResponseConsts.START_RESPONSE;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommandHandler {
    private final KeyboardService keyboardService;
    private final AnswerGenerator answerGenerator;
    private final MessageSender messageSender;

    public void handleCommand(Long chatId, String command) {
        switch (command) {
            case START:
                handleStart(chatId);
                break;
            case ANALYZE:
                handleAnalyze(chatId);
                break;
            default:
                handleDefault(chatId, command);
                break;
        }
    }

    private void handleAnalyze(Long chatId) {
        String message = answerGenerator.generate(chatId);
        messageSender.sendMessage(chatId, message);
    }

    private void handleStart(Long chatId) {
        messageSender.sendMessage(chatId, START_RESPONSE, keyboardService.createMainKeyboard());
    }

    private void handleDefault(Long chatId, String command) {
        log.info("CHAT_ID: {} | No supported command: {}", chatId, command);
    }
}
