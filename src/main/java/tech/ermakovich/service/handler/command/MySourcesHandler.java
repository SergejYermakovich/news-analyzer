package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.service.MessageSender;
import tech.ermakovich.service.factory.SourceKeyboardFactory;

import static tech.ermakovich.utils.BotCommands.MY_SOURCES_BTN;

@RequiredArgsConstructor
@Service
public class MySourcesHandler implements CommandHandler {
    private final MessageSender messageSender;
    private final SourceKeyboardFactory sourceKeyboardFactory;

    @Override
    public void handle(long chatId, Update update) {
        messageSender.sendMessage(chatId, "MY SOURCES:\n", sourceKeyboardFactory.createSourcesKeyboard(
                        chatId,
                        false
                )
        );
    }

    @Override
    public String getCommandMessage() {
        return MY_SOURCES_BTN;
    }
}
