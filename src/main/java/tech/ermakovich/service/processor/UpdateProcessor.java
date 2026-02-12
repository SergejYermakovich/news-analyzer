package tech.ermakovich.service.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.service.handler.CommandHandler;
import tech.ermakovich.service.handler.MessageHandler;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateProcessor {
    private final CommandHandler commandHandler;
    private final MessageHandler messageHandler;

    public void process(Update update){
        log.info("Update processed: {}", update.getUpdateId());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();

            log.debug("Получено сообщение от @{}: {}", username, messageText);

            try {
                if (isCommand(messageText)) {
                    commandHandler.handleCommand(chatId, messageText);
                } else {
                    messageHandler.handleMessage(chatId, messageText, username);
                }
            } catch (Exception e) {
                log.error("Ошибка обработки сообщения", e);
            }
        }
    }

    private static boolean isCommand(String messageText) {
        return messageText.startsWith("/");
    }
}
