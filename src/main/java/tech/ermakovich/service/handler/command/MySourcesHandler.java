package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static tech.ermakovich.utils.BotCommands.MY_SOURCES_BTN;

@RequiredArgsConstructor
@Service
public class MySourcesHandler implements CommandHandler {

    @Override
    public void handle(long chatId, Update update) {

    }

    @Override
    public String getCommandMessage() {
        return MY_SOURCES_BTN;
    }
}
