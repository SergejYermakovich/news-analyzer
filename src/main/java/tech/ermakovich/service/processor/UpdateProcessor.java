package tech.ermakovich.service.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.model.entity.User;
import tech.ermakovich.service.UserService;
import tech.ermakovich.service.handler.MainMenuCommandHandler;
import tech.ermakovich.service.handler.MessageHandler;

import static tech.ermakovich.utils.BotCommands.MAIN_MENU_BUTTONS;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateProcessor {
    private final MainMenuCommandHandler mainMenuCommandHandler;
    private final MessageHandler messageHandler;
    private final UserService userService;

    public void process(Update update) {
        log.info("Update processed: {}", update.getUpdateId());

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();

            User currentUser = userService.getOrCreate(chatId);

            log.debug("Получено сообщение от @{}: {}", username, messageText);

            try {
                if (isCommand(messageText)) {
                    mainMenuCommandHandler.handleCommand(chatId, messageText, update);
                } else {
                    messageHandler.handleMessage(chatId, messageText, username);
                }
            } catch (Exception e) {
                log.error("Ошибка обработки сообщения", e);
            }
        }
    }

    private static boolean isCommand(String messageText) {
        return messageText.startsWith("/") || MAIN_MENU_BUTTONS.contains(messageText);
    }
}
