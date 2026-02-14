package tech.ermakovich.service.handler.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultCommandHandler implements CommandHandler {
    @Override
    public void handle(long chatId, Update update) {
        String command = update.getMessage().getText();

        log.info("CHAT_ID: {} | No supported command: {}", chatId, command);
    }

    @Override
    public String getCommandMessage() {
        return null;
    }
}
