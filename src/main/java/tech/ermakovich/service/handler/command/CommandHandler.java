package tech.ermakovich.service.handler.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    void handle(long chatId, Update update);

    String getCommandMessage();
}
