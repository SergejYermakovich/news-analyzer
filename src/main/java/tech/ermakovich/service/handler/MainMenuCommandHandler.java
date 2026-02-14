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
import tech.ermakovich.service.handler.command.DefaultCommandHandler;

import java.util.Map;
import java.util.Optional;

import static tech.ermakovich.utils.ResponseConsts.USER_PROFILE;

@RequiredArgsConstructor
@Slf4j
@Service
public class MainMenuCommandHandler {
    private final Map<String, CommandHandler> commandHandlerMap;
    private final DefaultCommandHandler defaultCommandHandler;

    public void handleCommand(Long chatId, String command, Update update) {
        CommandHandler commandHandler = commandHandlerMap.get(command);
        if (commandHandler == null) {
            commandHandler = defaultCommandHandler;
        }
        commandHandler.handle(chatId, update);
    }




}
