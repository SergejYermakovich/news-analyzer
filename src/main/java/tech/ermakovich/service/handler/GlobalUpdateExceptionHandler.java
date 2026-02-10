package tech.ermakovich.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import tech.ermakovich.service.MessageSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalUpdateExceptionHandler {

    private final MessageSender messageSender;

    public void handleException(Update update, Throwable exception) {
        log.error("Exception occurred while processing update. UpdateId: {}",
                update != null ? update.getUpdateId() : "null",
                exception);
    }
}

