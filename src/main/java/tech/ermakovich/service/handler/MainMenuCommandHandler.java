package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.UserService;
import tech.ermakovich.service.handler.command.CommandHandler;

import java.util.Map;

import static tech.ermakovich.utils.ResponseConsts.USER_PROFILE;

@RequiredArgsConstructor
@Slf4j
@Service
public class MainMenuCommandHandler {
    private final Map<String, CommandHandler> commandHandlerMap;
    private final UserService userService;
    private final AnswerGenerator answerGenerator;
    private final MessageSender messageSender;

    public void handleCommand(Long chatId, String command, Update update) {
        CommandHandler commandHandler = commandHandlerMap.get(command);
        commandHandler.handle(chatId, update);
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

    private void handleDefault(Long chatId, String command) {
        log.info("CHAT_ID: {} | No supported command: {}", chatId, command);
    }
}
