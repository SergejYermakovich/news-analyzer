package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.service.MessageSender;

import static tech.ermakovich.utils.BotCommands.MY_SOURCES_BTN;

@RequiredArgsConstructor
@Service
public class MySourcesHandler implements CommandHandler {
    private final MessageSender messageSender;

    @Override
    public void handle(long chatId, Update update) {
        messageSender.sendMessage(chatId, "MySourcesHandler");
    }

    @Override
    public String getCommandMessage() {
        return MY_SOURCES_BTN;
    }
}
