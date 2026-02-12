package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.KeyboardService;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.UserService;

import static tech.ermakovich.utils.BotCommands.*;
import static tech.ermakovich.utils.ResponseConsts.START_RESPONSE;
import static tech.ermakovich.utils.ResponseConsts.USER_PROFILE;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommandHandler {
    private final KeyboardService keyboardService;
    private final UserService userService;
    private final AnswerGenerator answerGenerator;
    private final MessageSender messageSender;

    public void handleCommand(Long chatId, String command) {
        switch (command) {
            case START -> handleStart(chatId);
            case ANALYZE -> handleAnalyze(chatId);
            case MY_PROFILE_BTN -> handleGetProfile(chatId);
            default -> handleDefault(chatId, command);
        }
    }

    private void handleGetProfile(Long chatId) {
        User user = userService.getById(chatId);
        messageSender.sendMessage(chatId,
                String.format(
                        USER_PROFILE,
                        user.getSubscriptions().getSources(),
                        user.getBudget().getMax() + " " + user.getBudget().getCurrency(),
                        "RB",
                        "test",
                        "test"
                )
        );
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
