package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.service.AnswerGenerator;
import tech.ermakovich.service.KeyboardService;
import tech.ermakovich.service.MessageSender;

import static tech.ermakovich.utils.BotCommands.START;
import static tech.ermakovich.utils.ResponseConsts.START_RESPONSE;

@RequiredArgsConstructor
@Service
public class StartHandler implements CommandHandler {
    private final KeyboardService keyboardService;
    private final MessageSender messageSender;

    @Override
    public void handle(long chatId, Update update) {
        messageSender.sendMessage(chatId, START_RESPONSE, keyboardService.createMainKeyboard());
    }

    @Override
    public String getCommandMessage() {
        return START;
    }
}
